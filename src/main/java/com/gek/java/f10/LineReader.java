package com.gek.java.f10;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LineReader {
    public static List<String> getLines(String inputFileName) throws IOException {
        List<String> inputLines = new ArrayList<String>();

        File inputFile = new File(inputFileName);
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String readLine = null;

        while ((readLine = br.readLine()) != null) {
            inputLines.add(readLine);
        }

        br.close();

        return inputLines;
    }
}
