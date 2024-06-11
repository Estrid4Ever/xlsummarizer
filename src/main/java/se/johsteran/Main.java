package se.johsteran;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        FileWriter fileWriter = new FileWriter();


        String directory = "/Users/johannes/Programmering/Github/xlsummarizer";

        fileReader.setDirectory(directory);

        fileReader.readFilesInDirectory();

        //fileWriter.setRowContents(fileReader.getRows());

        //fileWriter.createFile("test1", directory);
    }
}