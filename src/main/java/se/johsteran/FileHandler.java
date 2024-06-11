package se.johsteran;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private ArrayList<HSSFRow> rows;
    private ArrayList<RowContent> rowContents;

    public void createFile(String fileName, String destinationPath) {
        try {
            workbook = new HSSFWorkbook();

            createSheet();
            createRows();
            writeToCells();

            String fullPath = createFullPath(destinationPath, fileName);

            saveAndCloseFileOutPutStream(fullPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createFullPath(String destinationPath, String fileName) {
        return destinationPath + "" + fileName;
    }

    public void createSheet() {
        sheet = workbook.createSheet();
    }

    public void createRows() {
        rows = new ArrayList<>();

        int rowAmount = rowContents.size();

        for (int i = 0; i < rowAmount; i++) {
            rows.add(sheet.createRow((short)i));
        }
    }

    public void writeToCells() {
        int rowAmount = rowContents.size();

        for (int i = 0; i < rowAmount; i++) {
            int cellAmount = rowContents.get(i).getCellContents().size();
            RowContent rowContent = rowContents.get(i);

            for (int j = 0; j < cellAmount; j++) {
                rows.get(i).createCell(j).setCellValue(rowContent.getCellContents().get(j));
            }
        }
    }

    public void saveAndCloseFileOutPutStream(String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);

        fileOut.close();
        workbook.close();
    }
}
