import java.util.Scanner;
import java.util.ArrayList;
public class Neko {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String GREETING_MESSAGE = "  ∧,,,∧\n( ̳̳• · ̳• )\n づ Meow! I'm Neko\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon meow ฅ ฅ";
    private static int numOfTask = 0;
    private static final Task[] taskList = new Task[100];

    public static void main(String[] args) {

        System.out.println(GREETING_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals(EXIT_COMMAND)) {
            handleInput(input);
            input = scanner.nextLine();
        }

        System.out.println(EXIT_MESSAGE);
        scanner.close();
    }

    private static void handleInput(String input) {
        if (input.equals(LIST_COMMAND)) {
            listTask();
        } else if (input.startsWith(MARK_COMMAND)) {
            markTask(input);
        } else if (input.startsWith(UNMARK_COMMAND)) {
            unmarkTask(input);
        } else if (input.startsWith(TODO_COMMAND) || input.startsWith(DEADLINE_COMMAND) || input.startsWith(EVENT_COMMAND)) {
            addTask(input);
        } else {
            System.out.println("Invalid command meow. Please try again.");
        }
    }

    private static void addTask(String input) {
        Task task;
        if (input.startsWith(TODO_COMMAND)) {
            String taskName = input.substring(4).trim();
            task = new Todo(taskName);
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            String[] parts = input.split("/", 2);
            String taskName = parts[0].substring(8).trim();
            String deadline = parts[1].substring(3).trim();
            task = new Deadline(taskName, deadline);
        } else {
            String[] parts = input.split("/", 3);
            String taskName = parts[0].substring(5).trim();
            String start = parts[1].substring(5).trim();
            String end = parts[2].substring(3).trim();
            task = new Event(taskName, start, end);
        }
        taskList[numOfTask++] = task;
        System.out.println("Got it meow. I've added this task ฅ/ᐠᓀ ﻌ ᓂマ\n "
                + task + "\nNow you have " + numOfTask + " tasks in your list meow");
    }
    private static void listTask() {
        System.out.println("Here are the task in your list meow:");
        for (int i = 0; i < numOfTask; i++) {
            Task task = taskList[i];
            System.out.println(i + 1 + "." + task);
        }
    }

    private static void markTask(String input) {
        int index = Integer.parseInt(input.substring(5).trim()) - 1;
        Task task = taskList[index];
        task.markAsDone();
        System.out.println("Nice meow! I've marked this task as done:\n " + task);
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        Task task = taskList[index];
        task.markAsNotDone();
        System.out.println("Ok meow, I've marked this task as not done yet:\n " + task);
    }


}
