package com.epam.client.util;



import com.epam.client.Grapher;
import com.epam.client.GreetingService;
import com.epam.client.GreetingServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;

import java.util.stream.IntStream;


public class  DataReader {
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    public static double[] fileContent;
    public DataReader() {
    }

    public static native String readFileName(JavaScriptObject files)
/*-{
    return files[0].name;
}-*/;

    public void getFileFromServer(FileUpload fileUpload, Grapher grapher) {
        JavaScriptObject files = fileUpload.getElement().getPropertyJSO("files");
        String filename = readFileName(files);
        greetingService.greetServer(filename, new AsyncCallback<double[]>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("error", throwable);
            }
            @Override
            public void onSuccess(double[] floats) {
                fileContent = floats;
                grapher.drawFunction(  IntStream.range(0,1000).mapToDouble(i->(double)i).toArray(),
                        floats,"funFromFile");
            }
        });
    }
    private double[] getFileContent() {
        return fileContent;
    }
}