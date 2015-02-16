package com.curiousdev.xtchart.internal.chartpart;

import com.curiousdev.xtchart.Series;

public class FinalResult {
    private final double xdata;
    private final double ydata;
    private final double error;

    private final Series linkedSeries;

    public FinalResult(double xdata, double ydata, double error, Series linkedSeries) {
        this.xdata = xdata;
        this.ydata = ydata;
        this.error = error;
        this.linkedSeries = linkedSeries;
    }

    public double getXdata() {
        return xdata;
    }

    public double getYdata() {
        return ydata;
    }

    public double getError() {
        return error;
    }
}
