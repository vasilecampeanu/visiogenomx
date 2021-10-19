package com.visiogenomx.app.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL
{
    /**
     * Class constructor
     * @param DB_PATH DB Path
     * @param DB_USER DB Username
     * @param DB_PASS DB Password
     */
    private MySQL(String DB_PATH, String DB_USER, String DB_PASS)
    {
        this.DB_PATH = DB_PATH;
        this.DB_USER = DB_USER;
        this.DB_PASS = DB_PASS;

        System.out.println("Trying to connect to MySQL database ...");

        try {
            conn = DriverManager.getConnection(this.DB_PATH, this.DB_USER, this.DB_PASS);
            System.out.println("Connection was established with success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create only one connection to the database
     * @param DB_PATH DB Path
     * @param DB_USER DB Username
     * @param DB_PASS DB Password
     * @return Returns a MySQL object
     */
    public static MySQL createConnection(String DB_PATH, String DB_USER, String DB_PASS)
    {
        if (MySQL.connection == null)
        {
            MySQL.connection = new MySQL(DB_PATH, DB_USER, DB_PASS);
        }

        return MySQL.connection;
    }

    /**
     * Get access to the private property conn
     * @return Return an object
     */
    public Connection get_conn()
    {
        return this.conn;
    }

    /**
     * Private properties
     */
    private String DB_PATH;
    private String DB_USER;
    private String DB_PASS;

    private static MySQL connection = null;
    private Connection conn;
}