package se.johsteran;

import javax.swing.*;

public class CellEntry {
    private JTextField cellId;
    private JTextField cellName;
    private JButton button;
    private JPanel panel;
    private boolean selected;

    public CellEntry(JTextField cellId, JTextField cellName, JButton button, JPanel panel, boolean selected) {
        this.cellId = cellId;
        this.cellName = cellName;
        this.button = button;
        this.panel = panel;
        this.selected = selected;
    }

    public CellEntry() {
    }

    public JTextField getCellId() {
        return cellId;
    }

    public JTextField getCellName() {
        return cellName;
    }

    public JButton getButton() {
        return button;
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean verifyCellIdFormatAndMarkAsSelected() {
        if (cellId.getText().matches("[a-zA-Z]*\\d*")) {
            this.selected = true;
            return true;
        }
        return false;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setCellId(JTextField cellId) {
        this.cellId = cellId;
    }

    public void setCellName(JTextField cellName) {
        this.cellName = cellName;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
