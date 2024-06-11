package se.johsteran;

import java.util.ArrayList;

public class RowContent {
    private ArrayList<String> cellContents;

    public RowContent(ArrayList<String> cells) {
        this.cellContents = cells;
    }

    public void addCell(String cell) {
        cellContents.add(cell);
    }

    public ArrayList<String> getCellContents() {
        return cellContents;
    }

    public void setCellContents(ArrayList<String> cellContents) {
        this.cellContents = cellContents;
    }
}
