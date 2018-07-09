package com.gek.java.f10;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class F10Excel {
    private static final String F10EXCEL = "f10-template.xlsx";
    private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final String[] MONTH_NAMES = new String[]{"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
    private static final int START_ROW = 7;
    private Workbook workbook;

    public static F10Excel newInstance() throws IOException {
//        ClassLoader classLoader = F10Excel.class.getClassLoader();
//        File templateFile = new File(classLoader.getResource(F10EXCEL).getFile());
//        FileInputStream inputFile = new FileInputStream(templateFile);
        InputStream inputFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(F10EXCEL);
        Workbook workbook = new XSSFWorkbook(inputFile);
        inputFile.close();

        F10Excel f10Excel = new F10Excel();
        f10Excel.workbook = workbook;

        return f10Excel;
    }


    private F10Excel() {}

    public boolean populateWith(List<String[]> fieldList) throws ParseException {
        Sheet sheet = workbook.getSheetAt(0);
        populateMonth(fieldList.get(0), sheet);

        int activeRow = START_ROW;

        Iterator<String[]> fieldsIterator = fieldList.iterator();

        while(fieldsIterator.hasNext()) {
            String[] fields = fieldsIterator.next();

            Row row = sheet.getRow(activeRow);

            //day
            Cell c1 = row.getCell(1);
            Date theDate = formatter.parse(fields[2]);
            c1.setCellValue(theDate);

            //from
            Cell c2 = row.getCell(2);
            c2.setCellValue(fields[3]);

            //to
            Cell c3 = row.getCell(3);
            c3.setCellValue(fields[4]);

            //pause
            Cell c4 = row.getCell(4);
            c4.setCellValue(fields[6]);

            //Kst
            Cell c7 = row.getCell(7);
            c7.setCellValue(672001);

            //tasks
            Cell c9 = row.getCell(9);
            c9.setCellValue(fields[8]);

            activeRow++;
        }

//        while (activeRow < 81) {
//            sheet.removeRow(sheet.getRow(activeRow));
//            activeRow++;
//        }

        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
        return true;
    }

    private void populateMonth(String[] fields, Sheet sheet) {
        String[] dateParts = fields[2].split("-");
        if (dateParts.length == 3) {
            int monthIdx = Integer.valueOf(dateParts[1]) - 1;
            if (monthIdx > -1 && monthIdx < 12) {
                Row r0 = sheet.getRow(0);
                Cell r0c4 = r0.getCell(4);
                r0c4.setCellValue(MONTH_NAMES[monthIdx]);
            }
        }
    }

    public void writeTo(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(fileName));
        workbook.write(fos);
        fos.close();
    }

    public void close() throws IOException {
        workbook.close();
    }
}
