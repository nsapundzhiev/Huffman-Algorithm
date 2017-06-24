import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 * Author Nikolai.
 */
public class MenuPanel extends JPanel {
    private JLabel fileLabel;
    private JTextArea fileName;
    private JLabel threadsCount;
    private JComboBox threadsCountField;
    private JButton browseButton;
    private JFileChooser fileChooser;
    private JTextArea messages;
    private JButton startProgram;

    /**
     * Constructor.
     */
    public MenuPanel() {
        initComponents();
    }

    /**
     * Init all components and add them in panel.
     */
    private void initComponents() {
        fileLabel = new JLabel("Input File:");
        this.add(fileLabel);

        fileName = new JTextArea(1, 30);
        fileName.setEnabled(false);
        this.add(fileName);

        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select text or binary fileLabel");

        browseButton = new JButton("Browse");
        browseButton.addActionListener(l -> {
            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fileName.setText("");
                fileName.append(selectedFile.getAbsolutePath());
            }
        });

        this.add(browseButton);

        threadsCount = new JLabel("Enter threads number:");
        this.add(threadsCount);

        threadsCountField = new JComboBox<Integer>();

        for (int i = 1; i <= 16; i++) {
            threadsCountField.addItem(i);
        }
        threadsCountField.setSelectedIndex(0);
        this.add(threadsCountField);

        messages = new JTextArea(30, 50);

        messages.setEnabled(false);
        messages.setVisible(true);
        JScrollPane scroll = new JScrollPane(messages,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.add(scroll);

        startProgram = new JButton("Start");
        startProgram.addActionListener(l -> {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            int threadsCount = Integer.parseInt(threadsCountField.getSelectedItem().toString());

            messages.setText("Program is running ...\n");
            Table table = new Table();
            table.run(fileName, threadsCount, messages);
        });

        this.add(startProgram);
    }
}
