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

import java.awt.*;

/**
 * An XChart Chart
 * 
 * @author timmolter
 */
public class CompoundChart extends Chart {
  private final Chart chart1;
  private final Chart chart2;

  /*
     * Constructor
     *
     * @param width
     * @param height
     */
  public CompoundChart(Chart chart1, Chart chart2) {
    super(chart1.getWidth() + chart2.getWidth(), chart1.getHeight());
    this.chart1 = chart1;

    this.chart2 = chart2;
  }

  private void alignMinMax() {
    double ymin;
    double ymax;

    ymin = chart1.chartPainter.getAxisPair().getYAxis().getMin();
    double chart2ymin = chart2.chartPainter.getAxisPair().getYAxis().getMin();
    if (chart2ymin < ymin) {
      ymin = chart2ymin;
    }

    chart1.chartPainter.getAxisPair().getYAxis().setMin(ymin);
    chart2.chartPainter.getAxisPair().getYAxis().setMin(ymin);

    ymax = chart1.chartPainter.getAxisPair().getYAxis().getMax();
    double chart2ymax = chart2.chartPainter.getAxisPair().getYAxis().getMax();
    if (chart2ymax > ymax) {
      ymax = chart2ymax;
    }

    chart1.chartPainter.getAxisPair().getYAxis().setMax(ymax);
    chart2.chartPainter.getAxisPair().getYAxis().setMax(ymax);
  }

  /**
   * @param g
   * @param width
   * @param height
   */
  public void paint(Graphics2D g, int width, int height) {
    // Ignoring external paint

    paint(g);
  }

  /**
   * @param g
   */
  public void paint(Graphics2D g) {
    g.setBackground(chart1.getStyleManager().getLegendBackgroundColor());
    g.fillRect(0,0,getWidth(), getHeight());

    chart1.paint(g);
    chart2.paint(g);

    alignMinMax();

    chart1.paint(g);
    g.translate(chart1.getWidth()+1, chart1.chartPainter.getChartTitle().getSizeHint() - 1);
    chart2.paint(g, chart2.getWidth(), chart2.getHeight() - chart1.chartPainter.getChartTitle().getSizeHint() - (int)chart1.chartPainter.getAxisPair().getXAxis().getAxisTitle().getBounds().getHeight() + 1);
  }
}
