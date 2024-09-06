package vuewee;

import vuewee.ui.TaskListCli;
import vuewee.ui.TaskListGui;
import vuewee.ui.TaskListUi;

/**
 * The Vuewee class contains the main method to run the Vuewee application.
 */
public class Vuewee {
    public static void main(String[] args) {
        TaskListUi ui;
        if (args.length >= 1 && args[0].equals("--cli")) {
            ui = TaskListCli.getScannerInstance();
        } else {
            ui = TaskListGui.getInstance();
        }
        ui.run();
        ui.close();
    }
}
