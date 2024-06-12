package se.johsteran.logic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileReader {
    private ArrayList<RowContent> rows;
    private String directory;
    private ArrayList<String> files;
    private HashMap<Integer, Integer> cellsToRead;

    public FileReader() {
        this.rows = new ArrayList<>();
        this.files = new ArrayList<>();
        this.cellsToRead = new HashMap<>();
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public ArrayList<RowContent> getRows() {
        return rows;
    }

    public void addCellsToRead(String cell) {
        int firstNumber = letterToAlphabeticNumericPlacementOfLetter(String.valueOf(cell.charAt(0)));
        if (firstNumber == 100) {
            return;
        }
        cellsToRead.put(firstNumber, Character.getNumericValue(cell.charAt(1))-1);
    }

    public int letterToAlphabeticNumericPlacementOfLetter(String letter) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabetArray = alphabet.toCharArray();

        for (int i = 0; i < alphabetArray.length; i++) {
            if (letter.equalsIgnoreCase(String.valueOf(alphabetArray[i]))) {
                return i;
            }
        }
        return 100;
    }

    public void findXlsxFilesInDirectory() {
        File dir = new File(directory);
        File[] dirFiles = dir.listFiles();

        if (dirFiles != null) {
            for (int i = 0; i < dirFiles.length; i++) {
                if(dirFiles[i].isFile() && dirFiles[i].getName().endsWith(".xlsx")) {
                    files.add(dirFiles[i].getAbsolutePath());
                }
            }
        }
    }

    public void readAllWorkBooks() {
        for (String file : files) {
            readWorkBook(file);
        }
    }

    public void readWorkBook(String file) {
        RowContent rowContent = new RowContent();
        rowContent.addCell(getFileNameFromAbsolutePath(file));
        cellsToRead.forEach((column, row) -> {
            rowContent.addCell(readCellData(column, row, file));
        });

        rows.add(rowContent);
    }
    public String getFileNameFromAbsolutePath(String absolutePath) {
        String[] fileNames = absolutePath.split("[\\\\/]");
        return fileNames[fileNames.length - 1];
    }

    public String readCellData(int vColumn, int vRow, String file) {
        Workbook wb = null;           //initialize Workbook null

        try {
            //reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream(file);
            //constructs an XSSFWorkbook object, by buffering the whole stream into the memory
            wb = new XSSFWorkbook(fis);
        }
        catch(IOException e1) {
            System.out.println(e1);
        }

        assert wb != null;
        Sheet sheet = wb.getSheetAt(0);   //getting the XSSFSheet object at given index

        Row row = sheet.getRow(vRow); //returns the logical row
        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(vColumn); //getting the cell representing the given column
        if (cell == null) {
            return "";
        }

        String value = cell.getStringCellValue();   //gets the cell value

        return value == null ? "" : value;  //returns the cell value if it´s not null
    }
}