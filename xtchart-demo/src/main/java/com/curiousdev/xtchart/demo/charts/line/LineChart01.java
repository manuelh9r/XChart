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
package com.curiousdev.xtchart.demo.charts.line;

import com.curiousdev.xtchart.Chart;
import com.curiousdev.xtchart.ChartBuilder;
import com.curiousdev.xtchart.StyleManager;
import com.curiousdev.xtchart.SwingWrapper;
import com.curiousdev.xtchart.demo.charts.ExampleChart;

/**
 * Logarithmic Y-Axis
 * <p>
 * Demonstrates the following:
 * <ul>
 * <li>Logarithmic Y-Axis
 * <li>Building a Chart with ChartBuilder
 * <li>Place legend at Inside-NW position
 */
public class LineChart01 implements ExampleChart {

  public static void main(String[] args) throws InterruptedException {
    ExampleChart exampleChart = new LineChart01();
    Chart chart = exampleChart.getChart();
    new SwingWrapper(chart).displayChart();

    Thread.sleep(10000);
  }

  @Override
  public Chart getChart() {

    // generates Log data

    // Create Chart
    Chart chart = new ChartBuilder().width(800).height(600).theme(StyleManager.ChartTheme.Matlab).build();

    // Customize Chart
    chart.getStyleManager().setChartTitleVisible(false);
    chart.getStyleManager().setLegendPosition(StyleManager.LegendPosition.InsideNW);
    chart.getStyleManager().setYAxisLogarithmic(false);
    chart.getStyleManager().setYAxisTicksVisible(false);

    // Series
    chart.addSeries("10^x1", new double[] {30.0}, new double[] {7.0});
    chart.addSeries("10^x2", new double[] {30.0}, new double[] {10.0});
    chart.addSeries("10^x3", new double[] {30.0}, new double[] {20.0});

    return chart;
  }
}
