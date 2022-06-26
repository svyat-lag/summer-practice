package com.company;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Animation {
    protected static void startAnimation(JLabel label){
        for (int i = 0; i < 242; i++){
            label.setIcon(new ImageIcon("C:\\Users\\svyat\\IdeaProjects\\practice\\src\\img\\intro"
                    + i + ".png"));
            try {
                Thread.sleep(3 + i/80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void scrollBarAnimation(JLabel label, JLabel image, JScrollBar jScrollBar){
        label.setText("The current value: " + jScrollBar.getValue());
        image.setIcon(new ImageIcon("C:\\Users\\svyat\\IdeaProjects\\practice\\src\\img\\i" +
                jScrollBar.getValue() + ".png"));
    }

    protected static void endAnimation(JLabel label, JLabel image, JScrollBar jScrollBar){
        int n = jScrollBar.getValue();
        for (int i = n; i > -1; i--){
            jScrollBar.setValue(i);
            scrollBarAnimation(label, image, jScrollBar);
//            ToolTipManager.sharedInstance().setDismissDelay(6);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 116; i++){
            image.setIcon(new ImageIcon("C:\\Users\\svyat\\IdeaProjects\\practice\\src\\img\\outro" +
                    i + ".png"));
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
