package se.johsteran;

import se.johsteran.io.SwingIO;
import se.johsteran.logic.FileReader;
import se.johsteran.logic.FileWriter;

import java.util.ArrayList;

public class Main {

    public static FileReader fileReader;
    public static FileWriter fileWriter;
    public static ArrayList<String> cellIds;
    public static String directory;
    public static SwingIO swingIO;
    public static ArrayList<String> columnTitles;

    public static void main(String[] args) {

        swingIO = new SwingIO();


    }

    public static void run() {
        ArrayList<CellEntry> cellEntries = swingIO.getCellEntries();
        directory = swingIO.getDirectory();

        addColumnTitles(cellEntries);
        addCellIds(cellEntries);

        readFiles(directory);
        writeFiles(directory);
    }

    public static void addCellIds(ArrayList<CellEntry> cellEntries) {
        cellIds = new ArrayList<>();
        for (int i = 0; i < (cellEntries.size() -1); i++) {
            cellIds.add(cellEntries.get(i).getCellId().getText());
            System.out.println(cellIds.getLast());
        }
    }

    public static void addColumnTitles(ArrayList<CellEntry> cellEntries) {
        columnTitles = new ArrayList<>();
        columnTitles.add("File name");

        for (int i = 0; i < (cellEntries.size() -1); i++) {
            columnTitles.add(cellEntries.get(i).getCellName().getText());
        }
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

        fileWriter.setColumnTitles(columnTitles);

        fileWriter.setRowContents(fileReader.getRows());
        fileWriter.createFile(directory);
    }
}