package se.johsteran;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SwingIO {

    private JPanel mainPanel;
    private JFrame frame;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel centerPanel;
    private ArrayList<CellEntry> cellEntries =  new ArrayList<>();
    private String directory;
    private JTextField selectedDirectory;
    private GridLayout gridLayout;

    public SwingIO() {
        frame = new JFrame();

        mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        northPanel = new JPanel();
        mainPanel.add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new BorderLayout());
        southPanel = new JPanel();
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        centerPanel = new JPanel();
        gridLayout = new GridLayout(12, 1, 15, 1);

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setHorizontalScrollBarPolicy(31);

        centerPanel.setLayout(gridLayout);
        centerPanel.setAutoscrolls(true);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        addCellEntryField();


        selectedDirectoryPreview();

        findFolderTroughBrowse();

        runButton();

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void runButton() {
        JButton runButton = new JButton("Summarize");

        southPanel.add(runButton);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.run();
                System.exit(0);
            }
        });
    }

    public void addCellEntryField() {
        JTextField columnTitle = new JTextField("Enter Column Title");
        addPlaceHolderText("Enter Column Title", columnTitle);

        JTextField cellId = new JTextField("Enter cell ID");
        addPlaceHolderText("Enter cell ID", cellId);

        JButton button = new JButton("Add cell id");

        JPanel cellPanel = new JPanel();

        cellEntries.add(new CellEntry(cellId, columnTitle, button, cellPanel, false));

        cellPanel.add(cellEntries.getLast().getCellName());
        cellPanel.add(cellEntries.getLast().getCellId());
        cellPanel.add(cellEntries.getLast().getButton());

        if (cellEntries.size() > 12) {
            gridLayout.setRows(gridLayout.getRows()+1);
        }


        centerPanel.add(cellPanel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CellEntry cellEntry = new CellEntry();

                for (int i = 0; i < cellEntries.size(); i++) {
                    if (cellEntries.get(i).getButton().equals(e.getSource())) {
                        cellEntry = cellEntries.get(i);
                        break;
                    }
                }

                if (cellEntry.getButton().getText().equals("❌")) {

                    centerPanel.remove(cellEntry.getPanel());
                    cellEntries.remove(cellEntry);
                    gridLayout.setRows(gridLayout.getRows()-1);
                    centerPanel.revalidate();

                } else {
                    cellEntry.setSelected(true);
                    cellEntry.getButton().setText("❌");
                    addCellEntryField();
                }
            }
        });
    }

    public void addPlaceHolderText(String text, JTextField textField) {
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(text)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(text);
                }
            }
        });
    }

    public void selectedDirectoryPreview() {
        selectedDirectory = new JTextField("Select the folder containing the excel files");
        selectedDirectory.setEditable(false);
        selectedDirectory.setFocusable(false);
        selectedDirectory.setForeground(Color.GRAY);
        southPanel.add(selectedDirectory);
    }

    public void findFolderTroughBrowse() {
        JButton browseDirectoriesButton = new JButton("Browse folders");
        southPanel.add(browseDirectoriesButton, BorderLayout.LINE_END);

        browseDirectoriesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser targetDir = new JFileChooser();
                targetDir.setDialogTitle("Choose Target Directory.");
                targetDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if(targetDir.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
                    directory = targetDir.getSelectedFile().getAbsolutePath();
                    selectedDirectory.setText(directory);
                    selectedDirectory.setForeground(Color.BLACK);
                    southPanel.revalidate();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a directory.");
                }
            }
        });
    }

//    public void addToolTipButton() {
//        JButton toolTipButton = new JButton("?");
//        JToolTip jToolTip = new JToolTip();
//        jToolTip.setTipText("The folders appear empty because the browsing tool only looks for folders and not files.");
//        jToolTip.setComponent(toolTipButton);
//        southPanel.add(toolTipButton);
//    }

    public String getDirectory() {
        return directory;
    }

    public ArrayList<CellEntry> getCellEntries() {
        return cellEntries;
    }
}
