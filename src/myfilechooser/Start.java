package myfilechooser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 3/29/16.
 */
public class Start {

    private static final String SAVE_MODE = "SAVE_MOD";
    private static final String OPEN_MODE = "OPEN_MOD";

    public static void main(String[] args) {
        List<String> extention = new ArrayList<>();
        extention.add("pdf");
        extention.add("xml");
        extention.add("txt");
        MyFileChooser myFileChooser = new MyFileChooser(SAVE_MODE, extention);
        System.out.println(myFileChooser.getChooserFile());
    }

}
