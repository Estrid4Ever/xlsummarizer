package se.johsteran;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class FileReader {
    private ArrayList<RowContent> rows;
    private String directory;

    private HashMap<String, Integer> cellsToRead;

    public FileReader() {
        this.rows = new ArrayList<>();
        addRow();
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public ArrayList<RowContent> getRows() {
        return rows;
    }

    public void addRow() {
        RowContent rowContent = new RowContent();
        rowContent.addCell("1");
        rowContent.addCell("2");
        rowContent.addCell("3");
        rowContent.addCell("4");
        rows.add(rowContent);
    }

    public int letterToAlphabeticNumericPlacementOfLetter(String letter) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabetArray = alphabet.toCharArray();

        for (int i = 0; i < alphabetArray.length; i++) {
            if (letter.equalsIgnoreCase(String.valueOf(alphabetArray[i]))) {
                return i + 1;
            }
        }
        return 0;
    }

    public void readFilesInDirectory() {
        File dir = new File(directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (int i = 0; i < directoryListing.length; i++) {
                System.out.println(directoryListing[i]);
            }
        }
    }

    public void readWorkBook() {

    }
}
