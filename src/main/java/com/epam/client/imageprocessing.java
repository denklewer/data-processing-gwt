package com.epam.client;

import com.epam.client.gui.ToolPanel;
import com.epam.client.util.Functions;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import org.moxieapps.gwt.highcharts.client.Chart;
import java.util.function.ToDoubleFunction;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class imageprocessing implements EntryPoint {

  double[] yData;
  double[] xData;
  ToDoubleFunction<double[]> drawFunction = x-> 1.0;

  Grapher grapher = new Grapher();
  Chart chart = grapher.createChart();
  ToolPanel toolPanel = new ToolPanel();
  public void onModuleLoad() {

    chart.setSizeToMatchContainer();
    DockPanel dockPanel = new DockPanel();
    dockPanel.setWidth("1.");
    RootPanel.get("toolColumn").add(toolPanel);
    RootPanel.get("chartColumn").add(chart);
    setBehavior();

//    grapher.drawFunction(i-> Math.log10(i),"logFunction",0, 10, 0.1);
//    grapher.drawFunction(i-> Math.sin(i),"sinFunction",0, 10, 0.1);
  }


  public void setBehavior() {
    toolPanel.funListBox.addChangeHandler(changeEvent -> {
      switch (toolPanel.getSelectedFunction()) {
        case "Linear": {
          toolPanel.addFuncParams(new String[] {"k", "b"});
          drawFunction = x -> Functions.LinearFunction(x[0],x[1],x[2]);
          break;
        }
        case "Square": {
          toolPanel.addFuncParams(new String[] {"a", "b", "c"});
          drawFunction = x -> Functions.SquareFunction(x[0],x[1],x[2],x[3]);
          break;
        }
        case "Log": {
          toolPanel.addFuncParams(new String[] {"a"});
          drawFunction = x -> Functions.LogFunction(x[0],x[1]);
          break;
        }
        case "Exp": {
          toolPanel.addFuncParams(new String[] {"b", "a"});
          drawFunction = x -> Functions.ExpFunction(x[0],x[1],x[2]);
          break;
        }
        case "Sin": {
          toolPanel.addFuncParams(new String[] {"a", "f0"});
          drawFunction = x -> Functions.SinFunction(x[0],x[1],x[2]);
          break;
        }
      }
    });

    toolPanel.btDraw.addClickHandler(clickEvent -> {
      double left = toolPanel.getLeft();
      double right = toolPanel.getRight();
      double step = toolPanel.getStep();
      double[] args = toolPanel.getFuncArgs();
      grapher.drawFunction(drawFunction,args,toolPanel.getSelectedFunction(),left,right,step);
    });

  }


}
