package filepanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by andrey on 3/28/16.
 */
public class FileListPanel extends JComponent implements FileViewPanel{

    private static final ImageIcon FILE = new ImageIcon("img/FILE_12.png");
    private static final ImageIcon DIRECTORY = new ImageIcon("img/DIRECTORY_12.png");
    private FilePanel filePanel;
    private JScrollPane scrollPane;

    public FileListPanel(FilePanel filePanel){
        this.filePanel = filePanel;
        setLayout(new BorderLayout());
        createPanel();
    }

    private void updateScrollPane() {
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void clickFile(MouseEvent e) {
        JList list = (JList)e.getSource();
        String fileName = ((FileListModel)list.getModel().getElementAt(list.getSelectedIndex())).getValue();
        File file = new File(filePanel.getPatchText() + File.separator + fileName);
        if (e.getClickCount() == 1) {
            if (file.isFile()) {
                filePanel.setChooseText(file.getAbsolutePath());
            }
        } else if (e.getClickCount() == 2) {
            if (file.isDirectory()) {
                filePanel.setPatchText(file.getAbsolutePath());
                filePanel.updatePanel();
            }
        }
    }

    @Override
    public void createPanel() {
        File folder = new File(filePanel.getPatchText());
        DefaultListModel listModel = new DefaultListModel();
        List<File> listOfFiles = filePanel.getFiles(folder);
        Collections.sort(listOfFiles);
        for (File file: listOfFiles){
            listModel.addElement(new FileListModel(file.getName(), file.isDirectory()? DIRECTORY : FILE));
        }
        JList list = new JList(listModel);
        list.setCellRenderer(new FileListRenderer());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickFile(e);
            }
        });
        scrollPane = new JScrollPane(list);
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

    @Override
    public void updatePanel() {
        removeAll();
        createPanel();
        revalidate();
        repaint();
    }

}
