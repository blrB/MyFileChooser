package filepanel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by andrey on 3/26/16.
 */
public class FileTablePanel extends JComponent implements FileViewPanel{

    private FilePanel filePanel;
    private JScrollPane scrollPane;

    public FileTablePanel(FilePanel filePanel){
        this.filePanel = filePanel;
        setLayout(new BorderLayout());
        createPanel();
    }

    private void updateScrollPane() {
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void clickFile(MouseEvent e) {
        JTable table = (JTable) e.getComponent();
        int col = table.columnAtPoint(e.getPoint());
        int row = table.rowAtPoint(e.getPoint());
        File file = new File(filePanel.getPatchText() + File.separator + table.getValueAt(row, 1));
        if (e.getClickCount() == 1) {
            if (col >= 0 && row >= 0) {
                if (file.isFile()) {
                    filePanel.setChooseText(file.getAbsolutePath());
                }
            }
        } else if (e.getClickCount() == 2) {
            if (col >= 0 && row >= 0) {
                if (file.isDirectory()) {
                    filePanel.setPatchText(file.getAbsolutePath());
                    filePanel.updatePanel();
                }
            }
        }
    }

    @Override
    public void createPanel() {
        File folder = new File(filePanel.getPatchText());
        List<File> listOfFiles = filePanel.getFiles(folder);
        Collections.sort(listOfFiles);
        TableModel model = new FileTableModel(listOfFiles);
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setMaxWidth(12);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                clickFile(e);
            }
        });
        scrollPane = new JScrollPane(table);
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
