package com.epam.client.gui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.Arrays;

public class ToolPanel extends  VerticalPanel{
    private  DockPanel filePanel = new DockPanel();

    private  DockPanel bordersPanel = new DockPanel();
    private  DockPanel functionPanel = new DockPanel();
    private  DockPanel transformationPannel = new DockPanel();
    private  DockPanel controlPanel = new DockPanel();



    private TextBox tbLeft;
    private TextBox tbRight;
    private TextBox tbStep;

    public  Button btDraw;
    public  Button btTransform;
    public  ListBox funListBox;
    public  ListBox transformListBox;


    VerticalPanel functionParams;
    VerticalPanel transformationParams;

    public String[] functions = {"Linear", "Square", "Log", "Exp", "Sin"};

    public ToolPanel() {
        super();
        this.add(createFilePanel());
        this.add(createBordersPanel());
        this.add(createFunctionPanel());
        this.add(createTransformationPanel());
        this.add(createControlPanel());
        this.setSpacing(4);
    }

    private DockPanel createFilePanel() {
       filePanel.setSpacing(2);
        FileUpload fileUpload = new FileUpload();
        fileUpload.ensureDebugId("fileUpload");
        filePanel.add(fileUpload, DockPanel.NORTH);
        Button uploadButton = new Button("Load file");
        uploadButton.addClickHandler(event -> {
            String filename = fileUpload.getFilename();
            if (filename.length() == 0) {
                Window.alert("error");
            } else {
                Window.alert(filename);
            }
        });
        filePanel.add(uploadButton, DockPanel.NORTH);
        filePanel.setBorderWidth(1);
        return filePanel;
    }

    private DockPanel createBordersPanel() {
        bordersPanel.setSpacing(2);
        bordersPanel.add(new Label("Left: "), DockPanel.NORTH);
        tbLeft = new TextBox();
        bordersPanel.add(tbLeft, DockPanel.NORTH);
        bordersPanel.add(new Label("Right: "),DockPanel.NORTH);
        tbRight = new TextBox();
        bordersPanel.add(tbRight, DockPanel.NORTH);
        bordersPanel.add(new Label("Step: "), DockPanel.NORTH);
        tbStep = new TextBox();
        bordersPanel.add(tbStep, DockPanel.NORTH);
        bordersPanel.setBorderWidth(1);
        return bordersPanel;
    }


    private DockPanel createFunctionPanel() {
        functionPanel.setSpacing(2);
        functionPanel.add(new Label("Выберите функцию"), DockPanel.NORTH);
        funListBox = new ListBox();
        funListBox.setWidth("100%");
        Arrays.stream(functions).forEach(funListBox::addItem);

        functionPanel.add(funListBox, DockPanel.NORTH);
        functionParams = new VerticalPanel();
        functionParams.ensureDebugId("funParamsPanel");
        functionParams.add(new Label("function params"));
        functionPanel.add(functionParams, DockPanel.NORTH);

        functionPanel.setBorderWidth(1);
        return functionPanel;
    }
    private DockPanel createTransformationPanel() {
        transformationPannel.setSpacing(2);
        transformationPannel.add(new Label("Выберите преобразование"), DockPanel.NORTH);
        transformListBox = new ListBox();
        transformListBox.setWidth("100%");
        transformListBox.addItem("1");
        transformListBox.addItem("2");
        transformListBox.addItem("3");
        transformationPannel.add(transformListBox, DockPanel.NORTH);
        transformationParams = new VerticalPanel();
        transformationParams.ensureDebugId("trParamsPanel");
        transformationParams.add(new Label("transformation params"));
        transformationPannel.add(transformationParams, DockPanel.NORTH);
        transformationPannel.setBorderWidth(1);
        return transformationPannel;
    }
    private DockPanel createControlPanel() {
        controlPanel.setSpacing(2);
        btDraw = new Button("Draw");
        btDraw.ensureDebugId("btDraw");
        controlPanel.add(btDraw, DockPanel.WEST);
        btTransform = new Button("Transform");
        btTransform.ensureDebugId("btTransform");
        controlPanel.add(btTransform, DockPanel.EAST);
        return controlPanel;
    }

    private void addParams(VerticalPanel pannel, String[] parameters) {
        pannel.clear();
        for (String parameter : parameters) {
            Label label = new Label(parameter + ":");
            TextBox textBox = new TextBox();
            pannel.add(label);
            pannel.add(textBox);
        }

    }

    public void addFuncParams( String[] parameters) {
        addParams(functionParams, parameters);
    }
    public void addTransParams( String[] parameters) {
        addParams(transformationParams, parameters);
    }


    public double parseValue(String str) {
        double value = Double.parseDouble(str);
        return value;
    }

    public String getSelectedFunction() {
        return funListBox.getSelectedItemText();
    }

    public double getLeft(){
        return parseValue(tbLeft.getText());
    }
    public double getRight() {
        return parseValue(tbRight.getText());
    }
    public double getStep() {
        return parseValue(tbStep.getText());
    }

    public int getFuncArgCount() {
       return functionParams.getWidgetCount()/2;
    }

    public double getFuncArg(int index) {
        String text =((TextBox) functionParams.getWidget(index * 2 + 1)).getText();
        return Double.parseDouble(text);
    }
    public double[] getFuncArgs() {
        double[] result = new double[getFuncArgCount()];

        for (int i = 0; i < getFuncArgCount() ; i++) {
            result[i] = getFuncArg(i);
        }
        return result;
    }


}
