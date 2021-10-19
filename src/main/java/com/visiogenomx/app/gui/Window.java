package com.visiogenomx.app.gui;

import com.visiogenomx.app.interfaces.WindowUtils;

import javax.swing.*;

public class Window extends JFrame implements WindowUtils
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

    @Override
    public void hello() 
    {
        System.out.println("Hello world!");
    }
}