package se.johsteran.logic;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FileWriter {
    private Workbook workbook;
    private Sheet sheet;
    private ArrayList<Row> rows;
    private ArrayList<RowContent> rowContents;

    private ArrayList<String> columnTitles;

    public void createFile(String destinationPath) {
        try {
            workbook = new XSSFWorkbook();

            createSheet();
            createRows();
            writeToCells();

            String fullPath = createFullPath(destinationPath);

            saveAndCloseFileOutPutStream(fullPath);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String createFullPath(String destinationPath) {
        String slashType = destinationPath.contains("/") ? "/" : "\\";
        String fileName = createFileName();

        return destinationPath + slashType + fileName;
    }

    public String createFileName() {
        return "xlSummarized_" + LocalDateTime.now().withNano(0) + ".xlsx";
    }

    public void createSheet() {
        sheet = workbook.createSheet();
    }

    public void createRows() {
        rows = new ArrayList<>();

        int rowAmount = rowContents.size() +1;

        for (int i = 0; i < rowAmount; i++) {
            rows.add(sheet.createRow(i));
        }
    }

    public void writeToCells() {
        writeColumnTitles();
        writeReadContent();
    }

    public void writeColumnTitles() {

        for (int i = 0; i < columnTitles.size(); i++) {
            rows.get(0).createCell(i).setCellValue(columnTitles.get(i));
        }
    }

    public void writeReadContent() {
        int rowAmount = rowContents.size();
        System.out.println(rowContents);

        for (int i = 0; i < rowAmount; i++) {
            int cellAmount = rowContents.get(i).getCellContents().size();
            RowContent rowContent = rowContents.get(i);

            for (int j = 0; j < cellAmount; j++) {
                rows.get(i+1).createCell(j).setCellValue(rowContent.getCellContents().get(j));
            }
        }
    }

    public void saveAndCloseFileOutPutStream(String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);

        fileOut.close();
        workbook.close();
    }

    public void setRowContents(ArrayList<RowContent> rowContents) {
        this.rowContents = rowContents;
    }

    public void setColumnTitles(ArrayList<String> columnTitles) {
        this.columnTitles = columnTitles;
    }
}
