import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Commands.Commands;
import Commands.deleteTask;
import Commands.handleDeadline;
import Commands.handleEvent;
import Commands.handleTodo;
import Commands.markTask;
import Commands.unmarkTask;

import Task.Task;
import Task.TaskList;
import Task.ToDo;
import Task.Event;
import Task.Deadline;


import exception.CitadelException;
import exception.CitadelInvalidArgException;
import exception.CitadelInvalidCommandException;
import exception.CitadelTaskNoInput;
import ui.TextUI;

public class Citadel {
    public static TaskList items = new TaskList();
    public static Storage db =  new Storage("data/citadel");

    public static TextUI ui = new TextUI();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String name = "Citadel";
        String intro = "Hello! I'm " + name + "\n";
        String question = "What can I do for you?\n";

        String input;

        ui.printStart();

        items = db.getTasks();
        ui.printTasks(items);

        while (true) {
            try {
                input = scanner.nextLine();
                String command = input.split(" ")[0].toUpperCase();
                if (Commands.valueOf(command).equals(Commands.BYE)) {
                    break;
                }
                // handleInput starts here
                if (Commands.valueOf(command).equals(Commands.LIST)) {
                  ui.printTasks(items);
                } else if (Commands.valueOf(command).equals(Commands.MARK)) {
                    new markTask(input, items).run();
                } else if (Commands.valueOf(command).equals(Commands.UNMARK)) {
                    new unmarkTask(input, items).run();
                } else if (Commands.valueOf(command).equals(Commands.DELETE)) {
                    new deleteTask(input, items).run();
                } else {
                    if (Commands.valueOf(command).equals(Commands.DEADLINE)) {
                        new handleDeadline(input, items).run();
                    } else if (Commands.valueOf(command).equals(Commands.EVENT)) {
                        new handleEvent(input, items).run();
                    } else if (Commands.valueOf(command).equals(Commands.TODO)) {
                        new handleTodo(input, items).run();
                    } else {
                        throw new CitadelInvalidCommandException();
                    }
                }
                // handleInput ends here

            } catch (CitadelException e) {
                 ui.printCitadelException(e);
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseException();
            }  catch (Exception e) {
                 ui.printException(e);
            }
            }

        ui.printGoodbye();
        db.saveData(items);
    }



































    private static void mark(String input) throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            items.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(items.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }

    private static void unmark(String input) throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            items.get(index - 1).unMark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(items.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
                throw new CitadelInvalidArgException();
        }
    }

    private static void delete(String input) throws CitadelException {
        try {
        String[] words = input.split(" ");
        int index = Integer.parseInt(words[1]);
        Task t = items.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + items.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
        }

    private static void handleDeadline(String input) throws CitadelException {
        Task t;
        String[] words = input.split(" /by ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }
        String task = words[0].substring(9).trim();
        String deadline = words[1].trim();

        if (task.isEmpty() || deadline.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime deadlineFormatted = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        t = new Deadline(task, deadlineFormatted);
        items.add(t);
        System.out.println("Got it! I have added: " + t);
        System.out.println();
        System.out.println("Now you have " + items.size() + " tasks in the list");
    }

    private static void handleEvent(String input) throws CitadelException {
        Task t;
        String[] words = input.split(" /from ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String task = words[0].substring(5).trim();
        String[] timeline = words[1].split(" /to ");

        if (timeline.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String from = timeline[0].trim();
        String to = timeline[1].trim();

        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime fromFormatted = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        LocalDateTime toFormatted = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        if (fromFormatted.isAfter(toFormatted)) {
            System.out.println("The start time must be before the end time!");
        } else {
            t = new Event(task, fromFormatted, toFormatted);
            items.add(t);

            System.out.println("Got it! I have added: " + t);
            System.out.println();
            System.out.println("Now you have " + items.size() + " tasks in the list");
        }
        }

    private static void handleTodo(String input) throws CitadelException {
            Task t;
            String todo = input.substring(5).trim();
            if (todo.isEmpty()) {
                throw new CitadelTaskNoInput();
            }
            t = new ToDo(todo);
            items.add(t);
            System.out.println("Got it! I have added: " + t);
            System.out.println("Now you have " + items.size() + " tasks in the list");
    }
}
