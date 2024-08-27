import Command.*;
import Data.*;
import Exceptions.*;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
import Parser.Parser;

import java.nio.file.Paths;

public class Nah {
    private Storage storage;
    private UI ui;
    private TaskList tasks;

    private static final String hardDisk = Paths.get("D:","cs2103T_week_2", "Data", "Nah.txt").toString();
    public Nah(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());

        } catch (NahException e) {
            ui.show(e.getMessage());
            tasks = new TaskList();
        }

    }
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NahException e) {
                ui.show(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {


        Nah nah = new Nah(hardDisk);
        nah.run();

    }
}
