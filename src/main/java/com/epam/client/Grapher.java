package com.epam.client;

import org.moxieapps.gwt.highcharts.client.*;
import org.moxieapps.gwt.highcharts.client.labels.*;
import org.moxieapps.gwt.highcharts.client.plotOptions.LinePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;


import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class Grapher {
    Chart currentChart;
    public Chart createChart() {
        currentChart = new Chart()
                .setType(Series.Type.LINE)
                .setChartTitleText("Test chart tittle")
                .setChartSubtitleText("Test chart subtittle")
                .setZoomType(BaseChart.ZoomType.X)
                .setLegend(
                        new Legend().setAlign(Legend.Align.LEFT)
                                    .setVerticalAlign(Legend.VerticalAlign.TOP)
                                    .setY(20)
                                    .setFloating(true)
                                    .setBorderWidth(1)
                )
                .setToolTip(
                        new ToolTip().setShared(true)
                                     .setCrosshairs(true)
                )
                .setSeriesPlotOptions(
                        new SeriesPlotOptions().setCursor(PlotOptions.Cursor.POINTER)
                                               .setMarker(new Marker().setLineWidth(1))
                );
        currentChart.getYAxis(0)
                    .setAxisTitleText(null)
                    .setLabels(new YAxisLabels()
                            .setAlign(Labels.Align.LEFT)
                            .setX(3)
                            .setY(16)
                    )
                    .setShowFirstLabel(false);

        currentChart.getXAxis(0)
             .setAxisTitleText(null)
             .setLabels(new XAxisLabels()
                     .setAlign(Labels.Align.LEFT)
                     .setX(3)
                     .setY(-3)
             )
             .setShowFirstLabel(false);

        Series series = currentChart.createSeries()
                                        .setName("Function name")
                                        .setPlotOptions(new LinePlotOptions()
                                                .setLineWidth(4)
                                                .setMarker(new Marker()
                                                        .setRadius(4)
                                                )
                                        );
        return currentChart;
    }

    public Series getSeries(  String functionName){
        Series series = currentChart.createSeries()
                                    .setName(functionName)
                                    .setPlotOptions(new LinePlotOptions()
                                            .setLineWidth(4)
                                            .setMarker(new Marker()
                                                    .setRadius(4)
                                            )
                                    );
        return series;
    }
    public void drawFunction(ToDoubleFunction<double[]> function, double[] args, String functionName, double left, double right, double step){
        clear();
        Series series = getSeries(functionName);

        double[] currArgs = new double[args.length +1];
        for (int i = 1; i < currArgs.length; i++) {
            currArgs[i] = args[i-1];
        }
        for (double i = left; i <= right ; i+=step) {
            currArgs[0] = i;
            series.addPoint(i, function.applyAsDouble(currArgs));
        }
        currentChart.addSeries(series);
    }
    public void drawFunction(double[] x , double[] y, String functionName){
        clear();
        Series series = getSeries(functionName);
        for (int i = 0; i <= x.length ; i++) {
            series.addPoint(x[i], y[i]);
        }
        currentChart.addSeries(series);
    }

    public void clear(){
        currentChart.removeAllSeries();
    }
}
