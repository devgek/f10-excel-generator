package com.gek.java.f10;

import java.io.File;
import java.io.FileNotFoundException;

public class F10ExcelGenerator {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: F10ExcelGenerator [fromCSVFile] [toXLSXFile]");
            System.exit(1);
        }

        F10ExcelGenerator generator = new F10ExcelGenerator();
        try {
            generator.generate(args[0], args[1]);
        } catch (Exception e) {
            System.out.println("Exception while generating F10-Excel " + args[1] + "!");
            e.printStackTrace();
        }
    }

    public void generate(String fromFile, String toFile) throws FileNotFoundException {
        File inputFile = new File(fromFile);
        if (!inputFile.exists()) {
            throw new FileNotFoundException("Inputfile " + fromFile + " not exists!");
        }
    }
}
