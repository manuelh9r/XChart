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
import java.awt.image.BufferedImage;

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
      g.clearRect(0,0,width,height);

      double multiplyWidth = (double)width / (double)getWidth();
      double multiplyHeight = (double)height / (double)getHeight();

      chart1.chartPainter.getAxisPair().getYAxis().resetForced();
      chart1.chartPainter.getAxisPair().getXAxis().resetForced();

      int previousWidth1 = chart1.getWidth();
      int previousWidth2 = chart2.getWidth();
      int previousHeight1 = chart1.getHeight();
      int previousHeight2 = chart2.getHeight();
      chart1.paint(g, (int)(chart1.getWidth()*multiplyWidth), (int)(chart1.getHeight()*multiplyHeight));
      chart2.paint(g, (int)(chart2.getWidth()*multiplyWidth), (int)(chart2.getHeight()*multiplyHeight));

      alignMinMax();

      chart1.paint(g, (int)(chart1.getWidth()), (int)(chart1.getHeight()));
      g.translate((int)(chart1.getWidth())+1, chart1.chartPainter.getChartTitle().getSizeHint() - 1);
      chart2.paint(g, (int)(chart2.getWidth()), (int)(chart2.getHeight()) - chart1.chartPainter.getChartTitle().getSizeHint() - ((int)chart1.chartPainter.getAxisPair().getXAxis().getAxisTitle().getBounds().getHeight()));

      // Reset size
      BufferedImage image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
      Graphics2D nullG = image.createGraphics();
      chart1.paint(nullG, previousWidth1, previousHeight1);
      chart2.paint(nullG, previousWidth2, previousHeight2);
  }

  /**
   * @param g
   */
  public void paint(Graphics2D g) {
    //g.setBackground(chart1.getStyleManager().getLegendBackgroundColor());
    //g.setColor(chart1.getStyleManager().getLegendBackgroundColor());
   // g.fillRect(0,0,getWidth(), getHeight());
    g.clearRect(0,0,getWidth(),getHeight());

    chart1.chartPainter.getAxisPair().getYAxis().resetForced();
    chart1.chartPainter.getAxisPair().getXAxis().resetForced();

    chart1.paint(g);
    chart2.paint(g);

    alignMinMax();

      chart1.paint(g, (int)(chart1.getWidth()), (int)(chart1.getHeight()));
      g.translate((int)(chart1.getWidth())+1, chart1.chartPainter.getChartTitle().getSizeHint() - 1);

      int previousHeight = chart2.getHeight();
      chart2.paint(g, (int)(chart2.getWidth()), (int)(chart2.getHeight()) - chart1.chartPainter.getChartTitle().getSizeHint() - ((int)chart1.chartPainter.getAxisPair().getXAxis().getAxisTitle().getBounds().getHeight()));

      // Reset height
      BufferedImage image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
      Graphics2D nullG = image.createGraphics();
      chart2.paint(nullG, chart2.getWidth(), previousHeight);
  }
}
