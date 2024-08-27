package util;

//deals with interactions with the user
// everything to do with printing out lines

import java.util.Scanner;

import Tasks.Task;
import Tasks.TaskList;



public class Ui {

    private static final String LINE = "    ___________________________________________";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Prince");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void showTaskList(TaskList tasksArray) {
        System.out.println("    Here are the tasks in your list:");
        int length = tasksArray.size();
        // print the list of inputs
        for (int i = 0; i < length; i++) {
            Task task = tasksArray.get(i);
            // formatting for numbering of list
            int listNum = i + 1;
            String numDot = listNum + ".";
            System.out.println("    " + numDot + task.toString());
        }
        showLine();
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("No tasks were found in storage!");
    }

    public void showBye() {
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

}
