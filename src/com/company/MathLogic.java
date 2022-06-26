package com.company;

import java.lang.Math;

import static java.lang.Math.*;

public class MathLogic {
    // Radius (mm)
    final static int R = 300;
    // Thread pitch (mm)
    final static int THREAD_PITCH = 2;

    static int currentTurnAmount = 0;

    protected static int getSteps(int newAngle){
        int newTurnAmount = (int)(2 * R * (sin(toRadians(newAngle) / 2.))) / THREAD_PITCH;
        int steps = (newTurnAmount - currentTurnAmount) * 200;
        currentTurnAmount = newTurnAmount;
        return steps;
    }
}
