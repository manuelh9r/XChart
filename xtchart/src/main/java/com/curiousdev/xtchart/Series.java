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

import com.curiousdev.xtchart.internal.chartpart.Axis;
import com.curiousdev.xtchart.internal.markers.Marker;
import com.curiousdev.xtchart.internal.style.SeriesColorMarkerLineStyle;
import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.list.array.TDoubleArrayList;

import java.awt.*;

/**
 * A Series containing X and Y data to be plotted on a Chart
 * 
 * @author timmolter
 */
public class Series {
  private boolean XDataIsGenerated;
  private boolean onlyPositiveError;

  public void setXDataIsGenerated(boolean XDataIsGenerated) {
    this.XDataIsGenerated = XDataIsGenerated;
  }

  public boolean isXDataIsGenerated() {
    return XDataIsGenerated;
  }

  public void setOnlyPositiveError(boolean onlyPositiveError) {
    this.onlyPositiveError = onlyPositiveError;
  }

  public boolean isOnlyPositiveError() {
    return onlyPositiveError;
  }

  public enum SeriesType {
    Line, Area
  }

  private String name = "";

  private TDoubleArrayList xData;
  private Axis.AxisType xAxisType;

  private TDoubleArrayList yData;
  private Axis.AxisType yAxisType;

  private SeriesType seriesType;
  private TDoubleArrayList errorBars;

  /** the minimum value of axis range */
  private double xMin;

  /** the maximum value of axis range */
  private double xMax;

  /** the minimum value of axis range */
  private double yMin;

  /** the maximum value of axis range */
  private double yMax;

  /** Line Style */
  private BasicStroke stroke;

  /** Line Color */
  private Color strokeColor;

  /** Fill Colour */
  private Color fillColor;

  /** Marker Style */
  private Marker marker;

  /** Marker Color */
  private Color markerColor;

  /**
   * Constructor
   * 
   * @param name
   * @param xData
   * @param xAxisType
   * @param yData
   * @param yAxisType
   * @param errorBars
   * @param seriesColorMarkerLineStyle
   */
  public Series(String name, TDoubleArrayList xData, Axis.AxisType xAxisType, TDoubleArrayList yData, Axis.AxisType yAxisType, TDoubleArrayList errorBars,
      SeriesColorMarkerLineStyle seriesColorMarkerLineStyle, boolean onlyPositiveError) {
    this.onlyPositiveError = onlyPositiveError;

    if (name == null || name.length() < 1) {
      throw new IllegalArgumentException("Series name cannot be null or zero-length!!!");
    }
    this.name = name;
    this.xData = xData;
    this.xAxisType = xAxisType;
    this.yData = yData;
    this.yAxisType = yAxisType;
    this.errorBars = errorBars;

    strokeColor = seriesColorMarkerLineStyle.getColor();
    fillColor = seriesColorMarkerLineStyle.getColor();
    markerColor = seriesColorMarkerLineStyle.getColor();
    marker = seriesColorMarkerLineStyle.getMarker();
    stroke = seriesColorMarkerLineStyle.getStroke();

    calculateMinMax();
  }


  /**
   * Finds the min and max of a dataset
   * 
   * @param data
   * @return
   */
  private double[] findMinMax(TDoubleArrayList data, Axis.AxisType axisType) {

    double min = Double.MAX_VALUE;
    double max = -Double.MAX_VALUE;

    for (double dataPoint : data.toArray()) {
      double value = 0.0;

      value = dataPoint;

      if (value < min) {
        min = value;
      }
      if (value > max) {
        max = value;
      }
    }

    return new double[] { min, max };
  }

  /**
   * Finds the min and max of a dataset accounting for error bars
   * 
   * @param data
   * @return
   */
  private double[] findMinMaxWithErrorBars(TDoubleArrayList data, TDoubleArrayList errorBars) {
    double min = Double.MAX_VALUE;
    double max = -Double.MAX_VALUE;

    TDoubleIterator itr = data.iterator();
    TDoubleIterator ebItr = errorBars.iterator();
    while (itr.hasNext()) {
      double bigDecimal = itr.next();
      double eb = ebItr.next();
      double lowerValue = bigDecimal - eb;

      if (this.onlyPositiveError && lowerValue < 0) {
        lowerValue = 0;
      }

      if (lowerValue < min) {
        min = lowerValue;
      }
      if (bigDecimal + eb > max) {
        max = bigDecimal + eb;
      }
    }
    return new double[] { min, max };
  }

  /**
   * Set the line style of the series
   * 
   * @param seriesLineStyle
   */
  public Series setLineStyle(SeriesLineStyle seriesLineStyle) {

    stroke = SeriesLineStyle.getBasicStroke(seriesLineStyle);
    return this;
  }

  /**
   * Set the line style of the series
   * 
   * @param basicStroke
   */
  public Series setLineStyle(BasicStroke basicStroke) {

    stroke = basicStroke;
    return this;
  }

  /**
   * Set the line color of the series
   * 
   * @param seriesColor
   */
  public Series setLineColor(SeriesColor seriesColor) {

    strokeColor = seriesColor.getColor();
    return this;
  }

  /**
   * Set the line color of the series
   * 
   * @param color
   */
  public Series setLineColor(java.awt.Color color) {

    strokeColor = color;
    return this;
  }

  /**
   * Sets the marker for the series
   * 
   * @param seriesMarker
   */
  public Series setMarker(SeriesMarker seriesMarker) {

    this.marker = seriesMarker.getMarker();
    return this;
  }

  /**
   * Sets the marker color for the series
   * 
   * @param seriesColor
   */
  public Series setMarkerColor(SeriesColor seriesColor) {

    this.markerColor = seriesColor.getColor();
    return this;
  }

  /**
   * Sets the marker color for the series
   * 
   * @param color
   */
  public Series setMarkerColor(java.awt.Color color) {

    this.markerColor = color;
    return this;
  }

  public SeriesType getSeriesType() {

    return seriesType;
  }

  public void setSeriesType(SeriesType seriesType) {

    this.seriesType = seriesType;
  }

  public TDoubleArrayList getXData() {
    return xData;
  }

  public TDoubleArrayList getYData() {
    return yData;
  }

  public TDoubleArrayList getErrorBars() {
    return errorBars;
  }

  public double getXMin() {

    return xMin;
  }

  public double getXMax() {

    return xMax;
  }

  public double getYMin() {

    return yMin;
  }

  public double getYMax() {

    return yMax;
  }

  public BasicStroke getStroke() {

    return stroke;
  }

  public Marker getMarker() {

    return marker;
  }

  public Color getStrokeColor() {

    return strokeColor;
  }

  public Color getMarkerColor() {

    return markerColor;
  }

  public Color getFillColor() {

    return fillColor;
  }

  public void setFillColor(Color fillColor) {

    this.fillColor = fillColor;
  }

  public String getName() {

    return name;
  }

  public void replaceXData(TDoubleArrayList newXData) {
    xData = newXData;
    calculateMinMax();
  }

  public void replaceYData(TDoubleArrayList newYData) {
    yData = newYData;
    calculateMinMax();
  }

  public void replaceErrroBarData(TDoubleArrayList newErrorBars) {
    errorBars = newErrorBars;
    calculateMinMax();
  }


  public void calculateMinMax() {
    // xData
    double[] xMinMax = findMinMax(xData, xAxisType);
    xMin = xMinMax[0];
    xMax = xMinMax[1];
    // System.out.println(xMin);
    // System.out.println(xMax);

    // yData
    double[] yMinMax = null;
    if (errorBars == null) {
      yMinMax = findMinMax(yData, yAxisType);
    }
    else {
      yMinMax = findMinMaxWithErrorBars(yData, errorBars);
    }
    yMin = yMinMax[0];
    yMax = yMinMax[1];
    // System.out.println(yMin);
    // System.out.println(yMax);
  }
}
