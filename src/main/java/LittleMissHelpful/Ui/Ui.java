package LittleMissHelpful.Ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import LittleMissHelpful.Tasks.Task;
import LittleMissHelpful.Tasks.TaskList;

public class Ui {
    private static final String LINE_BREAK = "---------------------------------";
    private BufferedReader br;

    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String showWelcome() {
        /**
         * Returns welcome statement
         */
        return LINE_BREAK + "\nHello! I'm Ah Bang Mang.\nWhat you want sia?\n" + LINE_BREAK;
    }

    public String showExit() {
        /**
         * Returns exit statement
         */
        return LINE_BREAK + "\nOk, I zao first then!\n" + LINE_BREAK;
    }

    public String showLine() {
        /**
         * Returns line break
         */
        return LINE_BREAK;
    }

    public String showNoTasks() {
        /**
         * Returns message when no tasks in tasklist
         */
        return LINE_BREAK + "\nWah shiok! No tasks at the moment!\n" + LINE_BREAK;
    }

    public String showAllTasks(ArrayList<Task> tasks) {
        /**
         * Returns the task list
         */
        StringBuilder sb = new StringBuilder();
        sb.append(LINE_BREAK).append("\nSiao liao! This your current task list leh...\n");
        for (int i = 0; i < tasks.size(); i++) {
            int listNumber = i + 1;
            Task t = tasks.get(i);
            sb.append(listNumber).append(". ").append(t.toString()).append("\n");
        }
        sb.append(LINE_BREAK);
        return sb.toString();
    }

    public String showSearchResults(ArrayList<Task> tasks) {
        /**
         * Returns search results list
         */
        StringBuilder sb = new StringBuilder();
        sb.append(LINE_BREAK).append("\n");
        if (tasks.size() == 0) {
            sb.append("Aiyo, got no matching tasks leh...\n");
        } else {
            sb.append("Only got these few matching tasks ah...\n");
            for (int i = 0; i < tasks.size(); i++) {
                int listNumber = i + 1;
                Task t = tasks.get(i);
                sb.append(listNumber).append(". ").append(t.toString()).append("\n");
            }
        }
        sb.append(LINE_BREAK);
        return sb.toString();
    }

    public String showAddedNewTask(Task t, TaskList tasks) {
        /**
         * Returns message after successfully adding a new task to list
         */
        return LINE_BREAK + "\nAdded to list liao: " + t.toString() +
                "\nSian, now got " + tasks.size() + " tasks in your list.\n" + LINE_BREAK;
    }

    public String showMarkedTask(Task t) {
        /**
         * Returns message after successfully marking a task as done
         */
        return LINE_BREAK + "\nWah upz! You have marked this task as done: " + t.toString() + "\n" + LINE_BREAK;
    }

    public String showUnmarkedTask(Task t) {
        /**
         * Returns message after successfully unmarking a task
         */
        return LINE_BREAK + "\nOk, I see you laze. You have marked this task as not done yet: " + t.toString() + "\n" + LINE_BREAK;
    }

    public String showDeletedTask(Task t, TaskList tasks) {
        /**
         * Returns message after successfully deleting a task
         */
        return LINE_BREAK + "\nWah shiok! This task no more liao: " + t.toString() +
                "\nNow got only " + tasks.size() + " tasks left.\n" + LINE_BREAK;
    }

    public String showError(String message) {
        /**
         * Returns error message
         */
        return LINE_BREAK + "\nAlamak! " + message + "\n" + LINE_BREAK;
    }

    public String readCommand() throws IOException {
        /**
         * Reads a command from the user (for CLI version)
         */
        return br.readLine();
    }
}

//package LittleMissHelpful.Ui;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import LittleMissHelpful.Tasks.Task;
//import LittleMissHelpful.Tasks.TaskList;
//
//public class Ui {
//    private static final String LINE_BREAK = "---------------------------------";
//    private BufferedReader br;
//
//    public Ui() {
//        br = new BufferedReader(new InputStreamReader(System.in));
//    }
//
//    public void showWelcome() {
//        /**
//         * Prints welcome statement
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Hello! I'm Ah Bang Mang.\nWhat you want sia?");
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showExit() {
//        /**
//         * Prints exit statement
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Ok, I zao first then!");
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showLine() {
//        /**
//         * Prints line break
//         */
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showNoTasks() {
//        /**
//         * Prints message given no tasks in tasklist
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Wah shiok! No tasks at the moment!");
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showAllTasks(ArrayList<Task> tasks) {
//        /**
//         * Prints task list
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Siao liao! This your current task list leh...");
//        for (int i = 0; i < tasks.size(); i++) {
//            int listNumber = i + 1;
//            Task t = tasks.get(i);
//            System.out.println(listNumber + ". " + t.toString());
//        }
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showSearchResults(ArrayList<Task> tasks) {
//        /**
//         * Prints search results list
//         */
//        System.out.println(LINE_BREAK);
//        if (tasks.size() == 0) {
//            System.out.println("Aiyo, got no matching tasks leh...");
//        } else {
//            System.out.println("Only got these few matching tasks ah...");
//            for (int i = 0; i < tasks.size(); i++) {
//                int listNumber = i + 1;
//                Task t = tasks.get(i);
//                System.out.println(listNumber + ". " + t.toString());
//            }
//        }
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showAddedNewTask(Task t, TaskList tasks) {
//        /**
//         * Prints message given successfully adding a new task to list
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Added to list liao: " + t.toString());
//        System.out.println("Sian, now got " + tasks.size() + " tasks in your list.");
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showMarkedTask(Task t) {
//        /**
//         * Prints message given successfully marking a task as done
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Wah upz! You have marked this task as done: " + t.toString());
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showUnmarkedTask(Task t) {
//        /**
//         * Prints message given successfully unmarking a task
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Ok, I see you laze. You have marked this task as not done yet: " + t.toString());
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showDeletedTask(Task t, TaskList tasks) {
//        /**
//         * Prints message given successfully deleting a task
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Wah shiok! This task no more liao: " + t.toString());
//        System.out.println("Now got only " + tasks.size() + " tasks left.");
//        System.out.println(LINE_BREAK);
//    }
//
//    public void showError(String message) {
//        /**
//         * Prints error message
//         */
//        System.out.println(LINE_BREAK);
//        System.out.println("Alamak! " + message);
//        System.out.println(LINE_BREAK);
//    }
//
//    public String readCommand() throws IOException {
//        /**
//         * Returns a string of the command to pass to parser
//         */
//        return br.readLine();
//    }
//
//}
