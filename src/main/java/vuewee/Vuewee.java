package vuewee;

import java.util.Scanner;

/**
 * The Vuewee class contains the main method to run the Vuewee application.
 */
public class Vuewee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskListUI ui = new TaskListUI(scanner);
        ui.run();
        scanner.close();
    }
}
