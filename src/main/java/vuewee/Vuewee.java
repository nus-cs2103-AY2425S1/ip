package vuewee;

import java.util.Scanner;

import vuewee.ui.TaskListCli;
import vuewee.ui.TaskListGui;
import vuewee.ui.TaskListUi;

/**
 * The Vuewee class contains the main method to run the Vuewee application.
 */
public class Vuewee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // TaskListUi ui = TaskListCli.getScannerInstance(scanner);
        TaskListUi ui = TaskListGui.getInstance();
        ui.run();
        ui.close();
    }
}
