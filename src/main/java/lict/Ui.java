package lict;

import lict.task.Task;
import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void startConversation() {
        showLine();
        System.out.println("Hello! I'm Lict");
        System.out.println("What can I do for you?");
        showLine();
    }

    public String readCommand() throws LictException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            throw new LictException("Please enter a valid command.");
        }
        return input;
    }

    public void showLine() {
        System.out.println("__________________________________");
    }


    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        int size = tasks.size();
        if (size == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void showFilteredTasks(TaskList tasks, String filter) {
        TaskList filteredTasks = new TaskList();
        int listSize = tasks.size();
        if (listSize == 0 || filter.isEmpty()) {
            System.out.println("There are no tasks in your list that matches the keyword '" + filter + "'.");
        }
        for (int j = 0; j < listSize; j++) {
            Task t = tasks.get(j);
            if (t.containsKeyword(filter)) {
                filteredTasks.addTask(t);
            }
        }
        int size = filteredTasks.size();
        if (size == 0) {
            System.out.println("There are no tasks in your list that matches the keyword '" + filter + "'.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + ". " + filteredTasks.get(i));
            }
        }
    }

    public void hasMarkedTask(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + t);
    }

    public void hasUnmarkedTask(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("    " + t);
    }

    public void hasDeletedTask(Task t, int numOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + t);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void endConversation() {
        sc.close();
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void hasAddedTask(Task newTask, int numOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask);
        System.out.println("Now you have " + numOfTasks + " in the list.");
    }
}
