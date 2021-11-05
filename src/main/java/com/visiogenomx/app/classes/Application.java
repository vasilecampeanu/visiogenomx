package com.visiogenomx.app.classes;

import com.visiogenomx.app.gui.*;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static spark.Spark.*;

public class Application 
{
    /**
     * Life cycle
     */
    public Application()
    {
        this.connection = MySQL.createConnection("jdbc:mysql://localhost:3306/visiogenomx", "root", "");

        String os = System.getProperty("os.name").toLowerCase();
        
        if (os.contains("win")) {
            this.window = new WindowWindows();
        } else if (os.contains("osx")) {
            this.window = new WindowMacOS();
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
            this.window = new WindowLinux();
        }
        
        this.window.setVisible(true);
    }

    /**
     * Public methods
     */
    public void table()
    {
        JTable table;

        try {
            String QUERY = "SELECT * FROM proteins";
            Statement statement = this.connection.get_conn().createStatement();
            ResultSet resultset = statement.executeQuery(QUERY);
            
            int index = 0;
            Object[][] data = new Object[30][3];
            
            while (resultset.next())
            {
                data[index][0] = resultset.getInt("id");
                data[index][1] = resultset.getString("mmtf");
                data[index][2] = "View 3D structure";
                index++;
            }

            String[] columnNames = { "Id", "MMTF Id", "Action" };

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            table = new JTable(model);

            Action click = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable)e.getSource();
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    ((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1);

                    try {
                        Process process = Runtime.getRuntime().exec("\"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe\" --app=http://localhost:8080/ngl/" + ((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1) + " --new-window --window-size=760,760");
                        process.waitFor();
                    } catch (Exception er) {
                        er.printStackTrace();
                    }
                }
            };

            ButtonColumn buttonColumn = new ButtonColumn(table, click, 2);
            buttonColumn.setMnemonic(KeyEvent.VK_D);

            JScrollPane scrollpane = new JScrollPane(table);

            this.window.add(scrollpane);
            this.window.setSize(768, 540);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        port(8080);
        staticFileLocation("/static");
        get(Routes.MMTF, new MMTFRoute());
        get(Routes.NGL, new NGLRoute(), new HandlebarsTemplateEngine());
        table();
    }

    /**
     * Private properties
     */
    private MySQL connection;
    private Window window;
}
