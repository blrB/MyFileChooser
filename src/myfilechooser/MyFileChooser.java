package myfilechooser;

import filepanel.FilePanel;
import lib.AddComponent;
import treepanel.TreePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by andrey on 3/25/16.
 */
public class MyFileChooser {

    private static final String SAVE_MODE = "SAVE_MOD";
    private static final String OPEN_MODE = "OPEN_MOD";
    private TreePanel treePanel;
    private FilePanel filePanel;
    private String chooserFile;
    private List<String> extentions = null;
    private String mod = null;
    private JDialog dialog;

    public MyFileChooser(String mod, List<String> extentions){
        if (mod != null) this.mod = mod;
        else this.mod = OPEN_MODE;
        if (extentions != null) this.extentions = extentions;
        dialog = new JDialog(new JFrame(),"File Chooser",true);
        dialog.setLayout(new GridBagLayout());
        treePanel = new TreePanel(this);
        filePanel = new FilePanel(this);
        AddComponent.add(dialog, treePanel, 0, 0, 1, 1);
        AddComponent.add(dialog, filePanel, 1, 0, 1, 1);
        dialog.setSize(800,350);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    public void closeDialog(){
        dialog.dispose();
        dialog = null;
    }

    public void setChooserFile(String chooserFile) {
        this.chooserFile = chooserFile;
    }

    public String getChooserFile() {
        return chooserFile;
    }

    public FilePanel getFilePanel() {
        return filePanel;
    }

    public TreePanel getTreePanel() {
        return treePanel;
    }

    public String getMod() {
        return mod;
    }

    public List<String> getExtentions() {
        return extentions;
    }

}
