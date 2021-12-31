package com.pl.snake;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {

        JFrame frame =new JFrame("Snake Game");
        frame.setBounds(0,0,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel());

        frame.setVisible(true);
    }
}
