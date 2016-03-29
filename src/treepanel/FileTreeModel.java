package treepanel;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.*;

/**
 * Created by andrey on 3/26/16.
 */
public class FileTreeModel implements TreeModel {

    private Map map;
    private File root;

    public FileTreeModel(File root) {
        this.root = root;
        this.map = new HashMap();
        if (!root.isDirectory()) map.put(root, 0);
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object node, int index) {
        return children(node).get(index);
    }

    @Override
    public int getChildCount(Object node) {
        return children(node) == null ? 0 : children(node).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return map.get(node) == 0;
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return children(parent).indexOf(child);
    }

    private List children(Object node) {
        File file = (File) node;
        Object value = map.get(file);
        if (value == 0) return null;
        List children = (List) value;
        if (children == null) {
            List<File> listFiles = new ArrayList<File>(Arrays.asList(file.listFiles()));
            Collections.sort(listFiles);
            if (listFiles != null) {
                children = new ArrayList(listFiles.size());
                for (int len = listFiles.size(), i = 0; i < len; i++) {
                    if (listFiles.get(i).isDirectory()) children.add(listFiles.get(i));
                    if (!listFiles.get(i).isDirectory()) map.put(listFiles.get(i), 0);
                }
            } else {
                children = new ArrayList(0);
            }
            map.put(file, children);
        }
        return children;
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {
    }

    @Override
    public void addTreeModelListener(TreeModelListener treeModelListener) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener treeModelListener) {
    }

}
