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
package com.curiousdev.xtchart.demo.charts.realtime;

import com.curiousdev.xtchart.Chart;
import com.curiousdev.xtchart.XChartPanel;
import com.curiousdev.xtchart.demo.charts.ExampleChart;
import gnu.trove.list.array.TDoubleArrayList;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Realtime
 * <p>
 * Demonstrates the following:
 * <ul>
 * <li>real-time chart updates
 * <li>fixed window
 */
public class RealtimeChart00 implements ExampleChart {

  private TDoubleArrayList yData;
  public static final String SERIES_NAME = "series1";

  public static void main(String[] args) throws InterruptedException {

    // Setup the panel
    final RealtimeChart00 realtimeChart01 = new RealtimeChart00();
    final XChartPanel chartPanel = realtimeChart01.buildPanel();

    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {

        // Create and set up the window.
        JFrame frame = new JFrame("XChart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
      }
    });

    // Simulate a data feed
    TimerTask chartUpdaterTask = new TimerTask() {

      @Override
      public void run() {
        realtimeChart01.updateData();
        chartPanel.refreshSeries(SERIES_NAME);
      }
    };

    Timer timer = new Timer();
    timer.scheduleAtFixedRate(chartUpdaterTask, 0, 250);

    Thread.sleep(10000);
  }

  public XChartPanel buildPanel() {

    return new XChartPanel(getChart());
  }

  @Override
  public Chart getChart() {
    yData = getRandomData(5);

    // Create Chart
    Chart chart = new Chart(500, 400);
    chart.setChartTitle("Sample Real-time Chart");
    chart.setXAxisTitle("X");
    chart.setYAxisTitle("Y");
    chart.addSeries(SERIES_NAME, null, yData);

    return chart;
  }

  private TDoubleArrayList getRandomData(int numPoints) {
    TDoubleArrayList data = new TDoubleArrayList(numPoints);
    for (int i = 0; i < numPoints; i++) {
      data.add(Math.random() * 100);
    }
    return data;
  }

  public void updateData() {
    // Get some new data
    TDoubleArrayList newData = getRandomData(1);

    yData.addAll(newData);

    /*// Limit the total number of points
    while (yData.size() > 20) {
      yData.remove(0);
    }*/

  }

  public TDoubleArrayList getyData() {
    return yData;
  }
}
