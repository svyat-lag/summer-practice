package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    private void createAndShowGUI(){
        /** The main window. **/
        JFrame frame = new JFrame("Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 480);

        /** The background image. **/
        JLabel image = new JLabel();
        frame.add(image);

        /** The panel with counter. **/
        JPanel header = new JPanel();
        header.setSize(new Dimension(600, 40));
        header.setBackground(Color.WHITE);
        frame.add(header, BorderLayout.NORTH);

        /** The panel with the scrollbar. **/
        JPanel left = new JPanel();
        left.setBackground(Color.WHITE);
        frame.add(left, BorderLayout.WEST);

        /** The panel with the submit button. **/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        frame.add(buttonPanel, BorderLayout.SOUTH);

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
            Animation.scrollBarAnimation(label, image, jScrollBar);
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
            int angle = jScrollBar.getValue();
            int steps = MathLogic.getSteps(angle);
            System.out.println("?steps=" + steps + "&degrees=" + angle);
            sendDataToDataBase(steps, angle);
//            Animation.endAnimation(label, image, jScrollBar);
        });

        left.add(jScrollBar);
        header.add(label);
        frame.setVisible(true);

        Animation.startAnimation(image);
    }

    private static void sendDataToDataBase(int steps, int degrees){
        String url = "http://svyatptm.beget.tech/put_info.php?steps=" + steps + "&degrees=" + degrees;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main mainWindow = new Main();
        sendDataToDataBase(0, 0);
        mainWindow.createAndShowGUI();
    }
}
