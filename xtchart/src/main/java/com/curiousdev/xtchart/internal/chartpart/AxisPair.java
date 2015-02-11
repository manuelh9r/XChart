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
package com.curiousdev.xtchart.internal.chartpart;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.curiousdev.xtchart.Series;
import com.curiousdev.xtchart.internal.style.SeriesColorMarkerLineStyleCycler;
import com.curiousdev.xtchart.StyleManager.ChartType;
import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.list.array.TDoubleArrayList;

/**
 * @author timmolter
 */
public class AxisPair implements ChartPart {

  /** parent */
  private final ChartPainter chartPainter;

  private Map<String, Series> seriesMap = new LinkedHashMap<String, Series>();

  private Axis xAxis;
  private Axis yAxis;

  private SeriesColorMarkerLineStyleCycler seriesColorMarkerLineStyleCycler = new SeriesColorMarkerLineStyleCycler();

  /**
   * Constructor
   *
   * @param the parent chartPainter
   */
  public AxisPair(ChartPainter chartPainter) {

    this.chartPainter = chartPainter;

    // add axes
    xAxis = new Axis(this, Axis.Direction.X);
    yAxis = new Axis(this, Axis.Direction.Y);
  }

  /**
   * @param seriesName
   * @param xData
   * @param yData
   * @param errorBars
   * @return Series
   */
  public Series addSeries(String seriesName, TDoubleArrayList xData, TDoubleArrayList yData, TDoubleArrayList errorBars, boolean onlyPositiveError) {

    // Sanity checks
    if (seriesName == null) {
      throw new IllegalArgumentException("Series Name cannot be null!!!");
    }
    if (yData == null) {
      throw new IllegalArgumentException("Y-Axis data cannot be null!!!");
    }
    /*if (yData.size() == 0) {
      throw new IllegalArgumentException("Y-Axis data cannot be empty!!!");
    }
    if (xData != null && xData.size() == 0) {
      throw new IllegalArgumentException("X-Axis data cannot be empty!!!");
    }*/

    Series series = null;
    if (xData != null) {
      xAxis.setAxisType(Axis.AxisType.Number);
      yAxis.setAxisType(Axis.AxisType.Number);

      series = new Series(seriesName, xData, xAxis.getAxisType(), yData, yAxis.getAxisType(), errorBars, seriesColorMarkerLineStyleCycler.getNextSeriesColorMarkerLineStyle(), onlyPositiveError);
    }
    else { // generate xData
      TDoubleArrayList generatedXData = new TDoubleArrayList();
      for (int i = 1; i < yData.size() + 1; i++) {
        generatedXData.add((double) i);
      }
      xAxis.setAxisType(Axis.AxisType.Number);
      yAxis.setAxisType(Axis.AxisType.Number);

      series = new Series(seriesName, generatedXData, xAxis.getAxisType(), yData, yAxis.getAxisType(), errorBars, seriesColorMarkerLineStyleCycler.getNextSeriesColorMarkerLineStyle(), onlyPositiveError);

      series.setXDataIsGenerated(true);
    }

    switch (chartPainter.getStyleManager().getChartType()) {
    case Area:
      series.setSeriesType(Series.SeriesType.Area);
      break;
    case Line:
      series.setSeriesType(Series.SeriesType.Line);
    }

    // Sanity check
    if (xData != null && xData.size() != yData.size()) {
      throw new IllegalArgumentException("X and Y-Axis sizes are not the same!!!");
    }
    if (errorBars != null && errorBars.size() != yData.size()) {
      throw new IllegalArgumentException("errorbars and Y-Axis sizes are not the same!!!");
    }

    if (seriesMap.keySet().contains(seriesName)) {
      throw new IllegalArgumentException("Series name >" + seriesName + "< has already been used. Use unique names for each series!!!");
    }

    seriesMap.put(seriesName, series);

    return series;
  }

  @Override
  public void paint(Graphics2D g) {

    yAxis.paint(g);
    xAxis.paint(g);
  }

  @Override
  public Rectangle getBounds() {

    return null; // should never be called
  }

  @Override
  public ChartPainter getChartPainter() {

    return chartPainter;
  }

  // Getters /////////////////////////////////////////////////

  public Map<String, Series> getSeriesMap() {

    return seriesMap;
  }

  public Axis getXAxis() {

    return xAxis;
  }

  public Axis getYAxis() {

    return yAxis;
  }

}
