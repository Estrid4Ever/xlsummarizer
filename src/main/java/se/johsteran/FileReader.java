package se.johsteran;

import java.util.ArrayList;

public class FileReader {
    private ArrayList<RowContent> rows;

    public FileReader() {
        this.rows = new ArrayList<>();
        addRow();
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

    public void readWorkBook() {

    }
}
