package se.johsteran;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        FileWriter fileWriter = new FileWriter();



        fileWriter.setRowContents(fileReader.getRows());

        fileWriter.createFile("test1", "/Users/johannes/Programmering/Github/xlsummarizer");
    }
}