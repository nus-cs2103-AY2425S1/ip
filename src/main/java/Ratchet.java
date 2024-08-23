import exception.InvalidCommandArgumentException;
import exception.InvalidCommandException;
import exception.RatchetException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;

import java.util.ArrayList;
import java.util.Scanner;

public class Ratchet {
    private static final String INDENT = "    ";
    private static final ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        String input;
        String command = "";
        while (!command.equals("bye")) {
            input = scanner.nextLine();
            command = input.split(" ")[0].toLowerCase();
            try {
                switch (command) {
                    case "list" -> displayList();
                    case "mark" -> mark(input);
                    case "unmark" -> unmark(input);
                    case "todo", "deadline", "event" -> addTask(input);
                    case "delete" -> delete(input);
                    case "bye" -> exit();
                    default -> throw new InvalidCommandException("Ratchet is unable to execute " + command + "!");
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

    private static String taskCount() {
        String count = taskList.size() <= 1 ? taskList.size() + " task" : taskList.size() + " tasks";
        return "Now you have " + count + " in the list.";
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
        taskList.add(task);
        lineBreak();
        System.out.println(INDENT + "Got it. I've added this task:\n"
                + INDENT + "  " + task + "\n"
                + INDENT + taskCount());
        lineBreak();
    }

    private static void displayList() {
        lineBreak();
        if (taskList.isEmpty()) {
            System.out.println(INDENT + "You have no tasks currently!");
        } else {

            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(INDENT + (i + 1) + "." + taskList.get(i));
            }
        }
        lineBreak();
    }

    private static void mark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.get(num).markAsDone();
        lineBreak();
        System.out.println(INDENT + "Nice! I've marked this task as done:\n"
                + INDENT + "  " + taskList.get(num));
        lineBreak();
    }

    private static void unmark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.get(num).markAsNotDone();
        lineBreak();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:\n"
                + INDENT + "  " + taskList.get(num));
        lineBreak();
    }

    private static void delete(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = taskList.remove(num);
        lineBreak();
        System.out.println(INDENT + "Noted. I've removed this task:\n"
                + INDENT + "  " + task + "\n"
                + INDENT + taskCount());
        lineBreak();
    }
}