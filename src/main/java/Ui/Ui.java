package Ui;

import TaskList.TaskList;

public class Ui {
    public static void printLine() {
        System.out.println("    ___________________________________________");
    }

    public static void printMessage(String input) {
        printLine();
        System.out.println("    " + input);
        printLine();
    }

    public static void printMessages(String... inputs) {
        printLine();
        for (String s : inputs) {
            System.out.println("    " + s);
        }
        printLine();
    }

    public static void printList(TaskList list) {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("    %s. %s",i + 1, list.get(i).toString()));
        }
        printLine();
    }

    public static void printGreeting() {
        printMessage("Hello! I'm Kotori.\n    What can I do for you?");
    }

    public static void printExit() {
        printMessage("Bye! Hope to see you again soon.");
    }
}
