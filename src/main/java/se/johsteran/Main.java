package se.johsteran;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        FileWriter fileWriter = new FileWriter();


        String directory = "/home/johannes/Documents/GitHub/egenDAO_V47/xlsummarizer";

        fileReader.setDirectory(directory);
        fileReader.addCellsToRead("a1");
        fileReader.addCellsToRead("b1");

        fileReader.findXlsxFilesInDirectory();
        fileReader.readAllWorkBooks();

        fileWriter.setRowContents(fileReader.getRows());

        fileWriter.createFile("test5", directory);
    }
}