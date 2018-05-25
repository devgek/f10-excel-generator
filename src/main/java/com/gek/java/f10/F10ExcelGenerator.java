package com.gek.java.f10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class F10ExcelGenerator {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 4) {
            System.out.println("usage: F10ExcelGenerator fromCSVFile toXLSXFile [true/false] [ignorePattern]");
            System.exit(1);
        }

        F10ExcelGenerator generator = new F10ExcelGenerator();
        try {
            String inputFile = args[0];
            String outputFile = args[1];
            boolean hasHeader = true;
            if (args.length > 2) {
                hasHeader = "true".equalsIgnoreCase(args[2]);
            }
            String ignorePattern = "";
            if (args.length > 3) {
                ignorePattern = args[3];
            }

            generator.generate(inputFile, outputFile, hasHeader, ignorePattern);
        } catch (Exception e) {
            System.out.println("Exception while generating F10-Excel " + args[1] + "!");
            e.printStackTrace();
        }
    }

    public void generate(String fromFile, String toFile, boolean hasHeader, String ignorePattern) throws IOException {
        File inputFile = new File(fromFile);
        if (!inputFile.exists()) {
            throw new FileNotFoundException("Inputfile " + fromFile + " not exists!");
        }

        List<String> inputLines = LineReader.getLines(inputFile);
    }
}
