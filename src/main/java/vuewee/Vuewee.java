package vuewee;

import java.util.Scanner;

import vuewee.ui.TaskListCli;
import vuewee.ui.TaskListUi;

/**
 * The Vuewee class contains the main method to run the Vuewee application.
 */
public class Vuewee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskListUi ui = new TaskListCli(scanner);
        // TaskListUI ui = new TaskListGUI();
        ui.run();
        ui.close();
    }
}
