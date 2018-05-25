import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class F10Writer {
    public void write(String fileName) {
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(inputFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            System.out.println("sheetname:" + datatypeSheet.getSheetName());
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                if (currentRow.getRowNum() < 10 || currentRow.getRowNum() > 10) {
                    continue;
                }

                System.out.println("Row " + currentRow.getRowNum());
                Cell c1 = currentRow.getCell(1);
                c1.setCellValue(new Date());
                Cell c2 = currentRow.getCell(2);
                c2.setCellValue("10:30");
                Cell c3 = currentRow.getCell(3);
                c3.setCellValue(new Date());
                Cell c7 = currentRow.getCell(7);
                c7.setCellValue(672001);
                Cell c9 = currentRow.getCell(9);
                c9.setCellValue("Sprint 12.1, JAMP-160");
            }

            inputFile.close();
            FileOutputStream fos = new FileOutputStream(new File("/home/moo/firma/f10-gen.xlsx"));
            workbook.write(fos);
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
