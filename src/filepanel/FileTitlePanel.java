package filepanel;

import lib.AddComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.io.File;
import java.util.*;

/**
 * Created by andrey on 3/28/16.
 */
public class FileTitlePanel extends JComponent implements FileViewPanel {

    private static final ImageIcon DIRECTORY = new ImageIcon("img/DIRECTORY_64.png");
    private static final ImageIcon FILE = new ImageIcon("img/FILE_64.png");
    private static final int filesInRow = 2;
    private FilePanel filePanel;
    private JScrollPane scrollPane;
    private List<JPanel> titlePanelList;

    public FileTitlePanel(FilePanel filePanel){
        this.filePanel = filePanel;
        setLayout(new BorderLayout());
        createPanel();
    }

    private void updateScrollPane() {
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    @Override
    public void createPanel() {
        titlePanelList = new ArrayList<JPanel>();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel title = new JPanel();
        title.setLayout(new GridBagLayout());
        File folder = new File(filePanel.getPatchText());
        List<File> listOfFiles = filePanel.getFiles(folder);
        Collections.sort(listOfFiles);
        for (int numberFileInArray = 0, numberFile = 0;
             numberFileInArray < listOfFiles.size();
             numberFileInArray++) {
            File file = listOfFiles.get(numberFileInArray);
            int position = (numberFile % filesInRow == 0) ? 0 : 1;
            JPanel fileWithName = new JPanel();
            fileWithName.setLayout(new BoxLayout(fileWithName,BoxLayout.X_AXIS));
            if (file.isDirectory()) {
                fileWithName.add(new JLabel(DIRECTORY));
                numberFile = addFileOnPanel(title, numberFile, file, position, fileWithName);
            } else {
                if (filePanel.compliesExtentions(file)) {
                    fileWithName.add(new JLabel(FILE));
                    numberFile = addFileOnPanel(title, numberFile, file, position, fileWithName);
                }
            }
        }
        mainPanel.add(title, BorderLayout.PAGE_START);
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                updateScrollPane();
            }
        });
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                updateScrollPane();
            }
        });
        add(scrollPane);
    }

    private int addFileOnPanel(JPanel title, int numberFile, File file, int position, JPanel fileWithName) {
        fileWithName.addMouseListener(new FileTitleListener(this, file.getAbsolutePath()));
        fileWithName.add(new JLabel(file.getName()));
        AddComponent.add(title, fileWithName, position, numberFile/filesInRow, 1, 1);
        titlePanelList.add(fileWithName);
        return numberFile + 1;
    }

    public void deleteSelection(){
        for (JPanel panel: titlePanelList){
            panel.setBackground(filePanel.getBackground());
        }
    }

    public FilePanel getFilePanel() {
        return filePanel;
    }

    @Override
    public void updatePanel() {
        removeAll();
        createPanel();
        revalidate();
        repaint();
    }

}
