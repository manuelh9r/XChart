/**
 * Copyright 2011 - 2014 Xeiam LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.curiousdev.xtchart.demo.charts.scatter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.curiousdev.xtchart.Series;
import com.curiousdev.xtchart.demo.charts.ExampleChart;
import com.curiousdev.xtchart.Chart;
import com.curiousdev.xtchart.ChartBuilder;
import com.curiousdev.xtchart.SeriesMarker;
import com.curiousdev.xtchart.StyleManager.ChartType;
import com.curiousdev.xtchart.SwingWrapper;

/**
 * Error Bars
 * <p>
 * Demonstrates the following:
 * <ul>
 * <li>Error Bars
 * <li>Using ChartBuilder to Make a Chart
 * <li>List<Number> data sets
 * <li>Setting Series Marker and Marker Color
 */
public class ScatterChart04 implements ExampleChart {

  public static void main(String[] args) {

    ExampleChart exampleChart = new ScatterChart04();
    Chart chart = exampleChart.getChart();
    new SwingWrapper(chart).displayChart();
  }

  @Override
  public Chart getChart() {

    // generates data
    int size = 10;
    List<Double> xData = new ArrayList<Double>();
    List<Double> yData = new ArrayList<Double>();
    List<Double> errorBars = new ArrayList<Double>();
    for (int i = 0; i <= size; i++) {
      xData.add(((double) i) / 100000000);
      yData.add(10 * Math.exp(-i));
      errorBars.add(Math.random() + .3);
    }

    // Create Chart
    Chart chart = new ChartBuilder().width(800).height(600).title("ScatterChart04").xAxisTitle("X").yAxisTitle("Y").chartType(ChartType.Scatter).build();

    // Customize Chart
    chart.getStyleManager().setChartTitleVisible(false);
    chart.getStyleManager().setLegendVisible(false);
    chart.getStyleManager().setAxisTitlesVisible(false);

    // Series
    Series series = chart.addSeries("10^(-x)", xData, yData, errorBars);
    series.setMarkerColor(Color.RED);
    series.setMarker(SeriesMarker.SQUARE);

    return chart;
  }

}
