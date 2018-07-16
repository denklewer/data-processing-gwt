package com.epam.server;

import com.epam.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.DoubleStream;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
        GreetingService {

    public double[] greetServer(String input) throws IllegalArgumentException {

        try {
           String filename = "D:\\php.dat";

            ByteBuffer bb = ByteBuffer.wrap(Files.readAllBytes(Paths.get(filename)))
                                      .order(ByteOrder.LITTLE_ENDIAN);

            double[] result = new double[1000];
            for (int i = 0; i < 1000; i++) {
                result[i] = bb.getFloat();
            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new double[]{0};
    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }
}
