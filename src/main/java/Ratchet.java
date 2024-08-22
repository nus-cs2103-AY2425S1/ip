import exception.InvalidCommandArgumentException;
import exception.InvalidCommandException;
import exception.RatchetException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;

import java.util.Scanner;

public class Ratchet {
    private static final String INDENT = "    ";
    private static final Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        String input;
        String command = "";
        while (!command.equals("bye")) {
            input = scanner.nextLine();
            command = input.split(" ")[0].toLowerCase();
            try {
                if (command.equals("list")) {
                    displayList();
                } else if (command.equals("mark")) {
                    mark(input);
                } else if (command.equals("unmark")) {
                    unmark(input);
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    addTask(input);
                } else if (command.equals("bye")) {
                    exit();
                } else {
                    throw new InvalidCommandException("Ratchet is unable to execute " + command + "!");
                }
            } catch (RatchetException e) {
                lineBreak();
                System.out.println(INDENT + e.getMessage());
                lineBreak();
            }
        }
    }

    private static void lineBreak() {
        System.out.println("   ________________________________________________________");
    }

    private static void greet() {
        lineBreak();
        System.out.println(INDENT + "Hello! I'm Ratchet\n" +
                INDENT + "What can I do for you?");
        lineBreak();
    }

    private static void exit() {
        lineBreak();
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        lineBreak();
    }

    private static void addTask(String text) throws InvalidCommandArgumentException {
        Task task = null;
        if (text.startsWith("todo")) {
            try {
                String description = text.split("todo ")[1];
                task = new TodoTask(description);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandArgumentException("The description of a todo cannot be empty!");
            }
        } else if (text.startsWith("deadline")) {
            String[] split = text.split(" /by ");
            String deadline = split[1];
            String description = split[0].split("deadline ")[1];
            task = new DeadlineTask(description, deadline);
        } else {
            String[] split1 = text.split(" /to ");
            String to = split1[1];
            String[] split2 = split1[0].split(" /from ");
            String from = split2[1];
            String description = split2[0].split("event ")[1];
            task = new EventTask(description, from, to);
        }
        taskList[taskCount++] = task;
        String count = taskCount <= 1 ? taskCount + " task" : taskCount + " tasks";
        lineBreak();
        System.out.println(INDENT + "Got it. I've added this task:\n"
                + INDENT + "  " + task + "\n"
                + INDENT + "Now you have " + count + " in the list.");
        lineBreak();
    }

    private static void displayList() {
        lineBreak();
        if (taskCount == 0) {
            System.out.println(INDENT + "You have no tasks currently!");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(INDENT + (i + 1) + "." + taskList[i]);
            }
        }
        lineBreak();
    }

    private static void mark(String input) {
        int task = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList[task].markAsDone();
        lineBreak();
        System.out.println(INDENT + "Nice! I've marked this task as done:\n"
                + INDENT + "  " + taskList[task]);
        lineBreak();
    }

    private static void unmark(String input) {
        int task = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList[task].markAsNotDone();
        lineBreak();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:\n"
                + INDENT + "  " + taskList[task]);
        lineBreak();
    }
}