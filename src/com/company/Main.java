package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        /** The main window. **/
        JFrame frame = new JFrame("Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        /** The background image. **/
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon("C:\\Users\\svyat\\IdeaProjects\\practice\\src\\img\\i1.png"));
        frame.add(image);

        /** The panel with counter. **/
        JPanel header = new JPanel();
        header.setSize(new Dimension(600, 40));
        frame.add(header, BorderLayout.NORTH);

        /** The panel with the scrollbar. **/
        JPanel left = new JPanel();
        frame.add(left, BorderLayout.WEST);

        /** The panel with the submit button. **/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        frame.add(buttonPanel, BorderLayout.EAST);

        /** The counter. **/
        JLabel label = new JLabel("The current value: " + 0);
        label.setFont(new Font("Verdana", Font.PLAIN, 18));
        label.setPreferredSize(new Dimension(300, 50));

        /**
         * The scrollbar. When user changes it, the value of the
         * counter and the angle of the back of the track are change too.
         **/
        JScrollBar jScrollBar = new JScrollBar(Adjustable.VERTICAL, 0, 1, 0, 31);
        jScrollBar.setPreferredSize(new Dimension(20, 300));
        jScrollBar.addAdjustmentListener(event -> {
            label.setText("The current value: " + jScrollBar.getValue());
            image.setIcon(new ImageIcon("C:\\Users\\svyat\\IdeaProjects\\practice\\src\\img\\i" +
                    jScrollBar.getValue() + ".png"));
        });

        /**
         * The submit-button. When user clicks on it, application should send
         * the GET-request with parameters.
         **/
        JButton submitButton = new JButton("Set the angle");
        submitButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        submitButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(submitButton, BorderLayout.CENTER);
        submitButton.addActionListener(event -> {

            String url = "http://localhost/put_info.php?steps=3&degrees=" + jScrollBar.getValue();

            try {
                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("GET");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        left.add(jScrollBar);
        header.add(label);
        frame.setVisible(true);
    }
}
