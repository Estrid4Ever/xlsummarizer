package se.johsteran;

import se.johsteran.logic.FileReader;
import se.johsteran.logic.FileWriter;

import java.util.ArrayList;

public class Main {

    public static FileReader fileReader;
    public static FileWriter fileWriter;
    public static ArrayList<String> cells;

    public static void main(String[] args) {

        String directory = "/home/johannes/Documents/GitHub/egenDAO_V47/xlsummarizer";

        readFiles(directory);
        writeFiles(directory);
    }

    public static void addCell(String cell) {
        cells.add(cell);
    }

    public static void readFiles(String directory) {
        fileReader = new FileReader();
        fileReader.setDirectory(directory);

        for (int i = 0; i < cells.size(); i++) {
            fileReader.addCellsToRead(cells.get(i));
        }

        fileReader.findXlsxFilesInDirectory();
        fileReader.readAllWorkBooks();
    }

    public static void writeFiles(String directory) {
        fileWriter = new FileWriter();

        fileWriter.setRowContents(fileReader.getRows());
        fileWriter.createFile(directory);
    }
}