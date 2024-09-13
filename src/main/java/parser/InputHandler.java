package parser;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import art.Logo;

import storage.Storage;

/**
 * This class is responsible for handling user input and executing the corresponding commands.
 */

public class InputHandler {
    private ArrayList<Task> tasks;

    public InputHandler(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public void handleInput(String input) throws CenaException {
        if (input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println("This was");
            System.out.println(Logo.getLogo());
            System.out.println("signing off!");
            System.out.println("____________________________________________________________");
            System.exit(0);
        } else if (input.equals("list")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        } else if (input.startsWith("mark ")) {
            try {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new CenaInvalidTaskIndexException("The task index is invalid.");
                }
                tasks.get(taskIndex).markAsDone();
                Storage.saveTasks(tasks);
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                throw new CenaInvalidTaskIndexException("The task index must be a number.");
            }
        } else if (input.startsWith("unmark ")) {
            try {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new CenaInvalidTaskIndexException("The task index is invalid.");
                }
                tasks.get(taskIndex).markAsNotDone();
                Storage.saveTasks(tasks);
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                throw new CenaInvalidTaskIndexException("The task index must be a number.");
            }
        } else if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new CenaEmptyDescriptionException("The description of a todo cannot be empty.");
            }
            if (description.contains("/by") || description.contains("/from") || description.contains("/to")) {
                throw new CenaInvalidTodoException("A todo should not contain /by, /from, or /to.");
            }
            Task task = new Todo(description);
            tasks.add(task);
            Storage.saveTasks(tasks);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + task);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new CenaInvalidDeadlineException("Incorrect description for deadline. It should contain only /by.");
            }
            Task task = new Deadline(parts[0], parts[1]);
            tasks.add(task);
            Storage.saveTasks(tasks);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + task);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from | /to ");
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new CenaInvalidEventException("Incorrect description for event. It should contain /from and /to.");
            }
            Task task = new Event(parts[0], parts[1], parts[2]);
            tasks.add(task);
            Storage.saveTasks(tasks);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + task);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else if (input.startsWith("delete ")) {
            try {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new CenaInvalidTaskIndexException("The task index is invalid.");
                }
                Task removedTask = tasks.remove(taskIndex);
                Storage.saveTasks(tasks);
                System.out.println("____________________________________________________________");
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removedTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                throw new CenaInvalidTaskIndexException("The task index must be a number.");
            }
        } else if (input.startsWith("on ")) {
            try {
                String date = input.substring(3).trim();
                LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks on " + targetDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    if (task.occursOn(targetDate)) {
                        System.out.println(" " + (i + 1) + "." + task);
                    }
                }
                System.out.println("____________________________________________________________");

            } catch (DateTimeParseException e) {
                System.out.println("The date and time must be in the format yyyy-MM-dd.");
            }
        } else if (input.equals("hello") || input.equals("hi") || input.equals("hey") || input.equals("yo") || input.equals("sup")) {
            hello();
        } else if (input.equals("help") || input.equals("commands") || input.equals("command") || input.equals("cmds") || input.equals("cmd")) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the commands you can use:");
            System.out.println("  bye - Exits the program");
            System.out.println("  list - Lists all tasks");
            System.out.println("  mark [task number] - Marks a task as done");
            System.out.println("  unmark [task number] - Marks a task as not done");
            System.out.println("  delete [task number] - Deletes a task");
            System.out.println("  todo [description] - Adds a todo task");
            System.out.println("  deadline [description] /by [due date] - Adds a deadline task");
            System.out.println("  event [description] /from [start date] /to [end date] - Adds an event task");
            System.out.println("  on [date] - Lists all tasks on a specific date");
            System.out.println("  hello - Displays the welcome message");
            System.out.println("  help - Displays the list of commands");
            System.out.println("____________________________________________________________");
        } else {
            throw new CenaUnknownCommandException("I'm sorry, but I don't know what that means :-(\n  Please use a valid command (see available commands by typing 'help')");
        }
    }

    public static void hello() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + Logo.getLogo());
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}