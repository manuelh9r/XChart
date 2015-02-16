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
package com.curiousdev.xtchart;

import com.curiousdev.xtchart.internal.chartpart.ChartPainter;
import com.curiousdev.xtchart.internal.style.Theme;
import gnu.trove.list.array.TDoubleArrayList;

import java.awt.*;
import java.util.Collection;
import java.util.Map;

/**
 * An XChart Chart
 * 
 * @author timmolter
 */
public class Chart {
  protected final ChartPainter chartPainter;

  /**
   * Constructor
   * 
   * @param width
   * @param height
   */
  public Chart(int width, int height) {

    chartPainter = new ChartPainter(width, height);
  }

  /**
   * Constructor
   * 
   * @param width
   * @param height
   * @param chartTheme
   */
  public Chart(int width, int height, StyleManager.ChartTheme chartTheme) {

    this(width, height, chartTheme.newInstance(chartTheme));
  }

  /**
   * Constructor
   * 
   * @param width
   * @param height
   * @param theme instance of Theme class
   */
  public Chart(int width, int height, Theme theme) {

    chartPainter = new ChartPainter(width, height);
    chartPainter.getStyleManager().setTheme(theme);
  }

  /**
   * Constructor
   * 
   * @param chartBuilder
   */
  public Chart(ChartBuilder chartBuilder) {

    this(chartBuilder.width, chartBuilder.height, chartBuilder.chartTheme);
    setChartTitle(chartBuilder.title);
    setXAxisTitle(chartBuilder.xAxisTitle);
    setYAxisTitle(chartBuilder.yAxisTitle);
    getStyleManager().setChartType(chartBuilder.chartType);
  }

  /**
   * @param g
   * @param width
   * @param height
   */
  public void paint(Graphics2D g, int width, int height) {

    chartPainter.paint(g, width, height);
  }

  /**
   * @param g
   */
  public void paint(Graphics2D g) {

    chartPainter.paint(g);
  }

  /**
   * Add a series to the chart using Collections
   * 
   * @param seriesName
   * @param xData the X-Axis data
   * @param yData the Y-Axis data
   * @return A Series object that you can set properties on
   */
  public Series addSeries(String seriesName, TDoubleArrayList xData, TDoubleArrayList yData) {
    return chartPainter.getAxisPair().addSeries(seriesName, xData, yData, null, false);
  }

  /**
   * Add a Number series to the chart using Collections with error bars
   * 
   * @param seriesName
   * @param xData the X-Axis data
   * @param yData the Y-Axis data
   * @param errorBars the error bar data
   * @return A Series object that you can set properties on
   */
  public Series addSeries(String seriesName, TDoubleArrayList xData, TDoubleArrayList yData, TDoubleArrayList errorBars, boolean onlyPositiveErrors) {
    return chartPainter.getAxisPair().addSeries(seriesName, xData, yData, errorBars, onlyPositiveErrors);
  }

  /**
   * Add a series to the chart using double arrays
   * 
   * @param seriesName
   * @param xData the X-Axis data
   * @param xData the Y-Axis data
   * @return A Series object that you can set properties on
   */
  public Series addSeries(String seriesName, double[] xData, double[] yData) {
    return addSeries(seriesName, xData, yData, null, false);
  }

  /**
   * Add a series to the chart using double arrays with error bars
   * 
   * @param seriesName
   * @param xData the X-Axis data
   * @param xData the Y-Axis data
   * @param errorBars the error bar data
   * @return A Series object that you can set properties on
   */
  public Series addSeries(String seriesName, double[] xData, double[] yData, double[] errorBars, boolean onlyPositiveErrors) {
    TDoubleArrayList xDataNumber = new TDoubleArrayList(xData);
    TDoubleArrayList yDataNumber = new TDoubleArrayList(yData);

    TDoubleArrayList errorBarDataNumber = null;
    if (errorBars != null) {
      errorBarDataNumber = new TDoubleArrayList(errorBars);
    }

    return chartPainter.getAxisPair().addSeries(seriesName, xDataNumber, yDataNumber, errorBarDataNumber, onlyPositiveErrors);
  }

  /**
   * Add a series to the chart using int arrays
   * 
   * @param seriesName
   * @param xData the X-Axis data
   * @param xData the Y-Axis data
   * @return A Series object that you can set properties on
   */
  public Series addSeries(String seriesName, int[] xData, int[] yData) {

    return addSeries(seriesName, xData, yData, null, false);
  }

  /**
   * Add a series to the chart using int arrays with error bars
   * 
   * @param seriesName
   * @param xData the X-Axis data
   * @param xData the Y-Axis data
   * @param errorBars the error bar data
   * @return A Series object that you can set properties on
   */
  public Series addSeries(String seriesName, int[] xData, int[] yData, int[] errorBars, boolean onlyPositiveError) {

    TDoubleArrayList xDataNumber = null;
    if (xData != null) {
      xDataNumber = new TDoubleArrayList();
      for (int d : xData) {
        xDataNumber.add(d);
      }
    }
    TDoubleArrayList yDataNumber = new TDoubleArrayList();
    for (int d : yData) {
      yDataNumber.add(d);
    }
    TDoubleArrayList errorBarDataNumber = null;
    if (errorBars != null) {
      errorBarDataNumber = new TDoubleArrayList();
      for (int d : errorBars) {
        errorBarDataNumber.add(d);
      }
    }

    return chartPainter.getAxisPair().addSeries(seriesName, xDataNumber, yDataNumber, errorBarDataNumber, onlyPositiveError);
  }

  /**
   * Set the chart title
   * 
   * @param title
   */
  public void setChartTitle(String title) {

    chartPainter.getChartTitle().setText(title);
  }

  /**
   * Set the x-axis title
   * 
   * @param title
   */
  public void setXAxisTitle(String title) {

    chartPainter.getAxisPair().getXAxis().getAxisTitle().setText(title);
  }

  /**
   * Set the y-axis title
   * 
   * @param title
   */
  public void setYAxisTitle(String title) {

    chartPainter.getAxisPair().getYAxis().getAxisTitle().setText(title);
  }

  /**
   * Gets the Chart's style manager, which can be used to customize the Chart's appearance
   * 
   * @return the style manager
   */
  public StyleManager getStyleManager() {

    return chartPainter.getStyleManager();
  }

  public int getWidth() {

    return chartPainter.getWidth();
  }

  public int getHeight() {

    return chartPainter.getHeight();
  }

  public Map<String, Series> getSeriesMap() {

    return chartPainter.getAxisPair().getSeriesMap();
  }

  public Series addSeries(String s, Collection<? extends Number> xData, Collection<? extends Number> yData) {
    TDoubleArrayList xDataArrayList = null;
    if (xData != null) {
      xDataArrayList = new TDoubleArrayList(xData.size());
      for (Number number : xData) {
        xDataArrayList.add(number.doubleValue());
      }
    }

    TDoubleArrayList yDataArrayList = new TDoubleArrayList(yData.size());
    for (Number number : yData) {
      yDataArrayList.add(number.doubleValue());
    }

    return this.addSeries(s, xDataArrayList, yDataArrayList);
  }

  public Series addSeries(String seriesName, Collection<? extends Number> xData, Collection<? extends Number> yData, Collection<? extends Number> errorData, boolean onlyPositiveError) {
    TDoubleArrayList xDataArrayList = null;
    if (xData != null) {
      xDataArrayList = new TDoubleArrayList(xData.size());
      for (Number number : xData) {
        xDataArrayList.add(number.doubleValue());
      }
    }

    TDoubleArrayList yDataArrayList = new TDoubleArrayList(yData.size());
    TDoubleArrayList errorDataArrayList = new TDoubleArrayList(errorData.size());

    for (Number number : yData) {
      yDataArrayList.add(number.doubleValue());
    }
    for (Number number : errorData) {
      errorDataArrayList.add(number.doubleValue());
    }

    return this.addSeries(seriesName, xDataArrayList, yDataArrayList, errorDataArrayList, onlyPositiveError);
  }

  public Series addSeries(String seriesName, Collection<? extends Number> xData, Collection<? extends Number> yData, Collection<? extends Number> errorData) {
    return this.addSeries(seriesName,xData,yData,errorData,false);
  }

  public Series addSeries(String seriesName, int[] xData, int[] yData1, int[] errdata) {
    return this.addSeries(seriesName, xData,yData1, errdata, false);
  }
}
