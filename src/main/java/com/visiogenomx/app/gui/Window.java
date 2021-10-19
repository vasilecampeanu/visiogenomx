package com.visiogenomx.app.gui;

import javax.swing.*;

public class Window extends JFrame 
{
    public Window()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1080, 760);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Visiogenomx");
    }
}