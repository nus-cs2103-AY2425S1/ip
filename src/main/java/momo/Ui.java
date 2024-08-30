package momo;

import momo.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    // momo.Ui class should handle greeting, scanning user input, farewell
    public static void showHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private final static Scanner sc = new Scanner(System.in);

    // Greeting
    public void showGreeting() {
        System.out.println("Hello! I'm momo.Momo\nWhat can I do for you?");
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void printList(ArrayList<Task> list) {
        showHorizontalLine();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        showHorizontalLine();

    }


    public void printTaskAdded(ArrayList<Task> TaskList) {
        try {
            showHorizontalLine();
            System.out.println("Noted. I've added this task:\n " + TaskList.get(TaskList.size() - 1));
            System.out.println(String.format("Now you have %d task(s) in the list", TaskList.size()));
            showHorizontalLine();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("This task has not been added: " + e.getMessage());
            showHorizontalLine();
        }
    }

    public void printDeletedTask(String task, int count) {
        System.out.println("Noted. I've removed this task:\n " + task);
        System.out.println(String.format("Now you have %d task(s) in the list", count));
        showHorizontalLine();
    }

    //TO-DO!!!
    public void showLoadingError() {

    }


    // Show Farewell
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











