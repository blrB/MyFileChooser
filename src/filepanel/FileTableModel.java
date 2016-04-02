package filepanel;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

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

    private static final int ICON_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int SIZE_COLUMN = 2;
    private static final int DATE_COLUMN = 3;
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
            case ICON_COLUMN:
                return ICON;
            case NAME_COLUMN:
                return NAME;
            case SIZE_COLUMN:
                return SIZE;
            case DATE_COLUMN:
                return DATE;
            default:
                try {
                    throw new WrongNumberArgsException("Colum " + Integer.toString(columnIndex) + " not find");
                } catch (WrongNumberArgsException e) {
                    e.printStackTrace();
                }
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ICON_COLUMN:
                return ImageIcon.class;
            case NAME_COLUMN:
                return String.class;
            case SIZE_COLUMN:
                return long.class;
            case DATE_COLUMN:
                return Date.class;
            default:
                try {
                    throw new WrongNumberArgsException("Colum " + Integer.toString(columnIndex) + " not find");
                } catch (WrongNumberArgsException e) {
                    e.printStackTrace();
                }
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
            case ICON_COLUMN:
                return file.isDirectory() ? DIRECTORY : FILE;
            case NAME_COLUMN:
                return file.getName();
            case SIZE_COLUMN:
                return file.length();
            case DATE_COLUMN:
                return new Date(file.lastModified());
            default:
                try {
                    throw new WrongNumberArgsException("Colum " + Integer.toString(columnIndex) + " not find");
                } catch (WrongNumberArgsException e) {
                    e.printStackTrace();
                }
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
