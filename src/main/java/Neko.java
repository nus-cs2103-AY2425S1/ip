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
            try {
                handleInput(input);
            } catch (NekoException e) {
                System.out.println(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }
        System.out.println(EXIT_MESSAGE);
        scanner.close();
    }

    private static void handleInput (String input) throws NekoException {
        if (input.equals(LIST_COMMAND)) {
            listTask();
        } else if (input.startsWith(MARK_COMMAND)) {
            markTask(input);
        } else if (input.startsWith(UNMARK_COMMAND)) {
            unmarkTask(input);
        } else if (input.startsWith(TODO_COMMAND) || input.startsWith(DEADLINE_COMMAND) || input.startsWith(EVENT_COMMAND)) {
            addTask(input);
        } else {
            throw new NekoException("Gomenasai! Neko doesn't know what that means :(");
        }
    }

    private static void addTask(String input) throws NekoException {
        Task task;
        if (input.startsWith(TODO_COMMAND)) {
            String taskName = input.substring(TODO_COMMAND.length()).trim();
            if (taskName == "") {
                throw new NekoException("The description of a todo cannot be empty!");
            }
            task = new Todo(taskName);
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            String[] parts = input.split("/", 2);

            if (parts.length < 2 || !parts[1].startsWith("by")) {
                throw new NekoException("The deadline must contain a '/by' to separate the task description and the deadline!");
            }

            String taskName = parts[0].substring(DEADLINE_COMMAND.length()).trim();
            String deadline = parts[1].substring(2).trim();

            if (taskName.isEmpty()) {
                throw new NekoException("The description of a deadline cannot be empty!");
            }
            if (deadline.isEmpty()) {
                throw new NekoException("The deadline of a deadline cannot be empty!");
            }
            task = new Deadline(taskName, deadline);
        } else {
            String[] parts = input.split("/", 3);

            if (parts.length < 3 || !parts[1].startsWith("from") || !parts[2].startsWith("to")) {
                throw new NekoException("The event must contain '/from' and '/to' to specify the start and end times!");
            }
            String taskName = parts[0].substring(EVENT_COMMAND.length()).trim();
            String start = parts[1].substring(4).trim();
            String end = parts[2].substring(2).trim();

            if (taskName.isEmpty()) {
                throw new NekoException("The description of an event cannot be empty!");
            }
            if (start.isEmpty()) {
                throw new NekoException("The start time of an event cannot be empty!");
            }
            if (end.isEmpty()) {
                throw new NekoException("The end time of an event cannot be empty!");
            }
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

    private static void markTask(String input) throws NekoException {
        int index = Integer.parseInt(input.substring(5).trim()) - 1;
        if (index >= numOfTask) {
            throw new NekoException("You only have " + numOfTask + " tasks now!");
        }
        if (index < 0 || index >= 100) {
            throw new NekoException("Invalid task number! Please enter a number between 1 and " + numOfTask + ".");
        }
        Task task = taskList[index];
        if (task.markAsDone()) {
            System.out.println("Nice meow! I've marked this task as done:\n " + task);
        } else {
            throw new NekoException("The task is already marked as done!");
        }
    }

    private static void unmarkTask(String input) throws NekoException {
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        if (index >= numOfTask) {
            throw new NekoException("You only have " + numOfTask + " tasks now!");
        }
        if (index < 0 || index >= 100) {
            throw new NekoException("Invalid task number! Please enter a number between 1 and " + numOfTask + ".");
        }
        Task task = taskList[index];
        if (task.markAsNotDone()) {
            System.out.println("Ok meow, I've marked this task as not done yet:\n " + task);
        } else {
            throw new NekoException("The task is not marked as done yet!");
        }
    }


}
