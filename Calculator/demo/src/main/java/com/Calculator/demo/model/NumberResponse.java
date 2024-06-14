package com.Calculator.demo.model;

import java.util.List;

public class NumberResponse {

    private List<Integer> windowPrevState;
    private List<Integer> windowCurrState;
    private double avg;

    // Getters and Setters

    public List<Integer> getWindowPrevState() {
        return windowPrevState;
    }

    public void setWindowPrevState(List<Integer> windowPrevState) {
        this.windowPrevState = windowPrevState;
    }

    public List<Integer> getWindowCurrState() {
        return windowCurrState;
    }

    public void setWindowCurrState(List<Integer> windowCurrState) {
        this.windowCurrState = windowCurrState;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }
}
