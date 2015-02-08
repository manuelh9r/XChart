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
package com.curiousdev.xtchart.standalone;

import com.curiousdev.xtchart.BitmapEncoder;
import com.curiousdev.xtchart.Chart;
import com.curiousdev.xtchart.Series;
import com.curiousdev.xtchart.SeriesMarker;
import com.curiousdev.xtchart.VectorGraphicsEncoder;

/**
 * Creates a simple Chart and saves it as a PNG and JPEG image file.
 */
public class Example1 {

  public static void main(String[] args) throws Exception {

    double[] yData = new double[] { 2.0, 1.0, 0.0 };

    // Create Chart
    Chart chart = new Chart(500, 400);
    chart.setChartTitle("Sample Chart");
    chart.setXAxisTitle("X");
    chart.setYAxisTitle("Y");
    Series series = chart.addSeries("y(x)", null, yData);
    series.setMarker(SeriesMarker.CIRCLE);

    BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
    BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.JPG);
    BitmapEncoder.saveJPGWithQuality(chart, "./Sample_Chart_With_Quality.jpg", 0.95f);
    BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.BMP);
    BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.GIF);

    BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 300);
    BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.JPG, 300);
    BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.GIF, 300);

    VectorGraphicsEncoder.saveVectorGraphic(chart, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.EPS);
    VectorGraphicsEncoder.saveVectorGraphic(chart, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.PDF);
    VectorGraphicsEncoder.saveVectorGraphic(chart, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.SVG);

  }
}
