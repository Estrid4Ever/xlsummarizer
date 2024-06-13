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
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        addCellId();


        selectedDirectoryPreview();

        findFolderTroughBrowse();

        frame.setSize(800, 600);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void addCellId() {
        JTextField textField;

        textField = new JTextField("Enter cell ID");
        addPlaceHolderText("Enter cell ID", textField);

        centerPanel.add(textField, BorderLayout.WEST);
        JButton button = new JButton("Add cell id");
        centerPanel.add(button, BorderLayout.EAST);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.getText().equals("❌")) {
                    centerPanel.remove(textField);
                    centerPanel.remove(button);
                    centerPanel.revalidate();
                    centerPanel.repaint();
                } else {
                    button.setText("❌");
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
        selectedDirectory = new JTextField("Select Folder");
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

    public String getDirectory() {
        return directory;
    }

    public ArrayList<CellEntry> getCellEntries() {
        return cellEntries;
    }
}
