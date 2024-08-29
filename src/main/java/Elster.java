import com.sun.source.tree.CaseTree;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Elster {
    private TaskList taskList;
    private Ui ui;

    public Elster(Path filePath) {
        this.ui = new Ui();

        try {
            taskList = new TaskList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.run(this.taskList);
    }

    public static void main(String[] args) {
        Path dataDir = Paths.get( "data");
        new Elster(dataDir).run();
    }


}
