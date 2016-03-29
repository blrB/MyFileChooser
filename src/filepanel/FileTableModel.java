package filepanel;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by andrey on 3/26/16.
 */
public class FileTableModel implements TableModel {

    private static final String ICON = " ";
    private static final String NAME = "Name";
    private static final String SIZE = "Size";
    private static final String DATE = "Date of last use";
    private static final ImageIcon FILE = new ImageIcon("img/FILE_12.png");
    private static final ImageIcon DIRECTORY = new ImageIcon("img/DIRECTORY_12.png");
    private List<File> files;

    public FileTableModel(List<File> fileInfoList) {
        this.files = fileInfoList;
    }

    @Override
    public int getRowCount() {
        return files.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ICON;
            case 1:
                return NAME;
            case 2:
                return SIZE;
            case 3:
                return DATE;
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ImageIcon.class;
            case 1:
                return String.class;
            case 2:
                return long.class;
            case 3:
                return Date.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        File file = files.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return file.isDirectory() ? DIRECTORY : FILE;
            case 1:
                return file.getName();
            case 2:
                return file.length();
            case 3:
                return new Date(file.lastModified());
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {
    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {
    }
}
