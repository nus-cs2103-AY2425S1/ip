package momo;

import momo.task.Task;
import momo.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles Ui related functionality in the chatbot program, handling some user interactions
 */
public class Ui {
    public static void showHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private final static Scanner sc = new Scanner(System.in);

    // Greeting
    public void showGreeting() {
        System.out.println("Hello! I'm Momo.\nWhat can I do for you?");
    }

    /**
     * Scans and returns user input string
     * @return Returns a String containing user input
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    public void printList(TaskList list) {
        showHorizontalLine();

        for (int i = 0; i < list.getCount(); i++) {
            System.out.println(i + 1 + ". " + list.getTask(i));
        }
        showHorizontalLine();
    }

    public void printTaskListCount(TaskList list) {
        System.out.printf("Now there are %d tasks in your list\n", list.getCount());
        showHorizontalLine();

    }

    /**
     * Shows an ominous farewell message and terminates the program
     */
    public void showFarewell() {
        String logo =
                        "⣿⣿⣿⡉⢀⣾⣿⡟⣩⣭⣭⡈⠙⢿⣿⣿⣿⣿⣿⡿⣻⣿⣿⣿⣿⣿⣿⣿⡇⠄\n" +
                        "⣿⣿⡗⠄⣼⣿⣿⢸⡿⠉⠉⢻⡆⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢠⠄\n" +
                        "⣿⡻⠁⢠⣿⣿⣿⣦⡛⠢⠴⠛⠁⣸⣿⣿⣿⣿⡿⠛⢉⣉⣉⡙⢻⣿⣿⣗⠄⠄\n" +
                        "⠷⠁⠄⢰⣿⣿⣿⣷⣬⣭⣼⣷⣿⣿⣿⣿⣿⡏⢀⣾⠟⠛⢿⣿⣄⣿⣿⡏⠄⠄\n" +
                        "⠄⠄⠄⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠄⠳⢀⣀⡼⢟⣼⣿⡟⠄⠄⠄\n" +
                        "⠄⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣒⣲⣶⣾⣿⣿⠏⠄⠄⠄⢠\n" +
                        "⠄⠄⠄⠸⣿⣽⣿⣿⣿⣿⣉⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠄⠄⠄⢠⣷\n" +
                        "⠄⠄⠄⠄⢻⣷⢻⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⠄⠄⠄⢀⣾⣿\n" +
                        "⠄⠄⠄⠄⠄⢻⣧⡙⢿⣿⣿⣿⣿⣿⡿⣿⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄⢠⣿⣿⣿\n" +
                        "⠄⠄⠄⡀⠄⠈⣿⣿⣶⣭⣭⣭⣿⣾⡿⠟⠋⠁⠄⠄⠄⠄⠄⠄⠄⢠⣿⣿⣿⣿\n" +
                        "⠄⠄⠎⠄⠄⣨⣿⣿⣿⣿⣿⣿⠋⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⣀⡲⣿⣿⣿⣿";

        showHorizontalLine();
        System.out.println("Farewell... for now. I'll be waiting for your return, taking refuge in your shadows. Rest well.... wħɨłɇ ɏøᵾ sŧɨłł ȼȺn\n" + logo);
        showHorizontalLine();
        sc.close();
        System.exit(0);


    }



}











