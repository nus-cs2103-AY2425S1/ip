import java.util.Scanner;

import java.util.NoSuchElementException;

public class YihuiBot {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // The maximum number of tasks that can be stored
    private static final int SIZE = 100;

    private static Task[] tasks;
    private static int numTasks;

    public static void main(String[] args) {
        reset();

        greetings();

        Scanner userInput = new Scanner(System.in);
        try {
            while (true) {
                String input = userInput.nextLine();
                if (input.equals("bye")) {
                    break;
                }
                callSuitableFunction(input);
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("An error has occured.");
        }

        exit();
    }

    private static void reset() {
        tasks = new Task[SIZE];
        numTasks = 0;
    }

    private static void callSuitableFunction(String input) {
        switch (input) {
        case "bye":
            break;
        case "list":
            list();
            break;
        default:
            addTask(input);
            break;
        }
    }

    private static String wrapStringWithHorizontalLines(String s) {
        String horizontalLine = "-----------------------------------------------------";
        return horizontalLine + "\n" + s + "\n" + horizontalLine;
    }

    private static void greetings() {
        String s = String.format(
            "Hello! I am %s!\nWhat can I do for you?",
            NAME
        );
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }

    private static void exit() {
        String s = "Bye. Hope to see you again soon!";
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }

    private static void addTask(String input) {
        if (numTasks >= SIZE) {
            String s = String.format(
                "Maximum number of tasks exceeded.\nCan only store %d tasks.",
                SIZE
            );
            String wrapped = wrapStringWithHorizontalLines(s);
            System.out.println(wrapped);
            return;
        }

        tasks[numTasks++] = new Task(input);
        String s = "added: " + input;
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }

    private static void list() {
        String s = "";
        for (int i = 0; i < numTasks; i++) {
            int idx = i + 1;
            Task task = tasks[i];
            String isComplete = task.isComplete() ? "X" : " ";
            if (i == 0) {
                s = String.format("%d. [%s] %s", idx, isComplete, task.toString());
            } else {
                s += String.format("\n%d. [%s] %s", idx, isComplete, task.toString());
            }
        }
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }
}
