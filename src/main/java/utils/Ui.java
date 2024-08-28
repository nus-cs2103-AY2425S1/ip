package utils;

import java.util.ArrayList;

import tasks.Task;

public class Ui {
    private static final String HORIZONTAL_LINE = "\n" 
        + "-----------------------------------------------------------------------------\n";
    private static final String LOGO = "\n"
            + " ░▒▓██████▓▒░░▒▓█▓▒░       ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░ ░▒▓███████▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒▒▓███▓▒░▒▓█▓▒░      ░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + " ░▒▓██████▓▒░░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░  \n"
            + "\n";

    public static void greet() {
        System.out.println(
            HORIZONTAL_LINE
            + "\nHello, welcome to the Aperture Science computer-aided enrichment center! My name is:\n"
            + LOGO
            + "What task would you like me to perform today?\n"
            + HORIZONTAL_LINE
        );
    }

    public static void echo(String input) {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: " + input.trim() + "\n"
            + HORIZONTAL_LINE
        );
    }

    public static void add(String task, String index) {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I have added the following task to the list...\n"
            + "\n" + task + "\n"
            + "\nNow you have " + index + (Integer.parseInt(index) == 1 ? " task.\n" : " tasks.\n")
            + HORIZONTAL_LINE
        );
    }

    public static void delete(String task, String index) {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I have removed the following task from the list...\n"
            + "\n" + task + "\n"
            + "\nNow you have " + index + (Integer.parseInt(index) == 1 ? " task.\n" : " tasks.\n")
            + HORIZONTAL_LINE
        );
    }
    
    public static void list(ArrayList<Task> taskList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("GLaDOS: Here is the list...\n");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).toString()); 
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void mark(String input) {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I've marked this task as done...\n"
            + "\n" + input + "\n"
            + HORIZONTAL_LINE);
    }

    public static void unmark(String input) {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: Oops, looks like this task is no longer done...\n"
            + "\n" + input + "\n"
            + HORIZONTAL_LINE);
    }

    public static void error(Exception e) {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: " + e.getMessage() + "\n"
            + HORIZONTAL_LINE
        );
    }

    public static void initialise(String str) {
        System.out.println(
            "\n" + str
        );
    }

    public static void exit() {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: Goodbye, user.\n"
            + HORIZONTAL_LINE
        );
    }
}
