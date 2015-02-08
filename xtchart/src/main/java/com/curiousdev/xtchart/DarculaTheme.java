package com.curiousdev.xtchart;

import com.curiousdev.xtchart.internal.style.MatlabTheme;

import java.awt.*;

public class DarculaTheme extends MatlabTheme {

    public static final Color FOREGROUND = new Color(0xbbbbbb);
    public static final Color BACKGROUND = new Color(0x3c3f41);

    @Override
    public Color getChartBackgroundColor() {
        return BACKGROUND;
    }

    @Override
    public Color getChartFontColor() {
        return FOREGROUND;
    }

    @Override
    public Font getChartTitleFont() {
        return new Font(Font.SANS_SERIF, Font.PLAIN, 11);
    }

    @Override
    public Font getAxisTickLabelsFont() {
        return new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    }

    @Override
    public Font getAxisTitleFont() {
        return new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    }

    @Override
    public Font getLegendFont() {
        return new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    }

    @Override
    public Color getLegendBackgroundColor() {
        return BACKGROUND;
    }

    @Override
    public Color getLegendBorderColor() {
        return FOREGROUND.darker();
    }

    @Override
    public Color getErrorBarsColor() {
        return FOREGROUND;
    }

    @Override
    public boolean isErrorBarsColorSeriesColor() {
        return super.isErrorBarsColorSeriesColor();
    }

    @Override
    public Color getPlotBorderColor() {
        return FOREGROUND;
    }

    @Override
    public Color getPlotBackgroundColor() {
        return BACKGROUND.darker();
    }

    @Override
    public Color getPlotGridLinesColor() {
        return FOREGROUND;
    }

    @Override
    public Color getChartTitleBoxBackgroundColor() {
        return super.getChartTitleBoxBackgroundColor();
    }

    @Override
    public Color getChartTitleBoxBorderColor() {
        return super.getChartTitleBoxBorderColor();
    }

    @Override
    public Color getAxisTickMarksColor() {
        return FOREGROUND;
    }

    @Override
    public Color getAxisTickLabelsColor() {
        return FOREGROUND;
    }
}
