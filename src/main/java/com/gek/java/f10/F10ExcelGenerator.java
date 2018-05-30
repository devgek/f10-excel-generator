package com.gek.java.f10;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class F10ExcelGenerator {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 4) {
            System.out.println("usage: F10ExcelGenerator fromCSVFile toXLSXFile [true/false] [ignorePattern]");
            System.exit(1);
        }

        F10ExcelGenerator generator = new F10ExcelGenerator();

        try {
            System.out.println("Parsing parameters ...");
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

            System.out.println("Start generating ...");
            generator.generate(inputFile, outputFile, hasHeader,ignorePattern);
            System.out.println("Generation ready!");

        } catch (Exception e) {
            System.out.println("Exception while generating F10-Excel " + args[1] + "!");
            e.printStackTrace();
        }
    }
        public void generate(String inputFile, String outputFile, boolean hasHeader, String ignorePattern) throws IOException, ParseException {
            List<String> lines = LineReader.getLines(inputFile);

            PTimeCsv ptimeCsv = new PTimeCsv(lines);
            final List<String[]> fieldList = ptimeCsv.getFieldList(hasHeader, ignorePattern);

            F10Excel f10Excel = F10Excel.newInstance();

            f10Excel.populateWith(fieldList);
            f10Excel.writeTo(outputFile);
            f10Excel.close();
        }
}
