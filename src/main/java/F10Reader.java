import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class F10Reader {
    public void read(String fileName) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            System.out.println("sheetname:" + datatypeSheet.getSheetName());
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                if (currentRow.getRowNum() == 0) {
                    Cell c4 = currentRow.getCell(4);
                    printCell(c4);
                }
                if (currentRow.getRowNum() < 7 || currentRow.getRowNum() > 7) {
                    continue;
                }
                System.out.println("Row " + currentRow.getRowNum());
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    System.out.println("");
                    printCell(currentCell);
                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCell(Cell cell) {
        System.out.print("cell " + cell.getAddress().getColumn() + " type:" + cell.getCellTypeEnum().toString() + " ");
        if (cell.getCellTypeEnum() == CellType.STRING) {
            System.out.print(cell.getStringCellValue() + "--");
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            System.out.print(cell.getNumericCellValue() + "--");
        }
    }
}
