package se.johsteran;

import se.johsteran.logic.FileReader;
import se.johsteran.logic.FileWriter;

import java.util.ArrayList;

public class Main {

    public static FileReader fileReader;
    public static FileWriter fileWriter;
    public static ArrayList<String> cellIds;
    public static String directory;

    public static void main(String[] args) {

        SwingIO swingIO = new SwingIO();
        directory = swingIO.getDirectory();

    }

    public static void run() {
        readFiles(directory);
        writeFiles(directory);
    }

    public static void addCell(String cell) {
        cellIds.add(cell);
    }

    public static void readFiles(String directory) {
        fileReader = new FileReader();
        fileReader.setDirectory(directory);

        for (int i = 0; i < cellIds.size(); i++) {
            fileReader.addCellsToRead(cellIds.get(i));
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