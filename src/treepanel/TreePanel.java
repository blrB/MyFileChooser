package treepanel;

import myfilechooser.MyFileChooser;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;

/**
 * Created by andrey on 3/25/16.
 */
public class TreePanel extends JComponent {

    private MyFileChooser myFileChooser;
    private JScrollPane scrollPane;

    public TreePanel(final MyFileChooser myFileChooser) {
        this.myFileChooser = myFileChooser;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 350));
        File root = new File(File.separator);
        TreeModel model = new FileTreeModel(root);
        JTree tree = new JTree();
        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent treeExpansionEvent) throws ExpandVetoException {
                String patch = (String)treeExpansionEvent.getPath().getLastPathComponent().toString();
                myFileChooser.getFilePanel().setPatchText(patch);
                myFileChooser.getFilePanel().updatePanel();
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent treeExpansionEvent) throws ExpandVetoException {
            }
        });
        tree.setModel(model);
        scrollPane = new JScrollPane(tree);
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                updateWindow();
            }
        });
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent evt) {
                updateWindow();
            }
        });
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateWindow() {
        scrollPane.revalidate();
        scrollPane.repaint();
    }

}
