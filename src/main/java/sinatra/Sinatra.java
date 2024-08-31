package sinatra;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main Sinatra application.
 */
public class Sinatra {

    private ArrayList<Task> tasks;
    private Storage storage;
    private static final String FILE_PATH = "tasks.txt";

    /**
     * Constructs a new Sinatra object, initializes tasks, prints the introduction,
     * loads tasks from storage, and starts the scanner for user input.
     */
    public Sinatra() {
        this.tasks = new ArrayList<Task>();
        this.printIntro();
        this.storage = new Storage(Sinatra.FILE_PATH);
        this.tasks = storage.loadTasksFromFile();
        this.sinatraScanner();
    }

    /**
     * Prints the introduction message.
     */
    private void printIntro() {
        String initial = "Hello! I'm Sinatra. \nWhat can I do for you? ";
        System.out.println(initial);
    }

    /**
     * Starts the scanner to handle user inputs.
     */
    private void sinatraScanner() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            handleInputs(input);
        }
    }

    /**
     * Handles the user inputs and performs the corresponding actions.
     *
     * @param message the user input message
     */
    private void handleInputs(String message) {
        try {
            if (message.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    int count = i + 1;
                    System.out.println(count + "." + tasks.get(i).toString());
                }
            } else if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (message.length() > 4 && message.substring(0, 4).equals("mark")) {
                Task currTask = tasks.get(Integer.parseInt(message.substring(5)) - 1);
                currTask.setStatus(true);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + currTask);
            } else if (message.length() > 6 && message.substring(0, 6).equals("unmark")) {
                Task currTask = tasks.get(Integer.parseInt(message.substring(7)) - 1);
                currTask.setStatus(false);
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("  " + currTask);
            } else if (message.length() >= 4 && message.substring(0, 4).equals("todo")) {
                if (message.length() <= 4) {
                    throw new SinatraException("OOPS!!! The description of a event cannot be empty.");
                }
                ToDo toDo = new ToDo(message.substring(5, message.length()), false);
                toDo.appendToStorage(Sinatra.FILE_PATH);
                tasks.add(toDo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (message.length() >= 8 && message.substring(0, 8).equals("deadline")) {
                if (message.length() <= 8) {
                    throw new SinatraException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] parts = message.substring(9).split(" /by ");
                String content = parts[0];
                String dateTimeString = parts[1];
                Deadline deadline = new Deadline(content, false, dateTimeString);
                deadline.appendToStorage(Sinatra.FILE_PATH);
                tasks.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (message.length() >= 5 && message.substring(0, 5).equals("event")) {
                if (message.length() <= 5) {
                    throw new SinatraException("OOPS!!! The description of a event cannot be empty.");
                }
                String[] parts = message.substring(6).split(" /from ");
                String content = parts[0];
                String[] timeParts = parts[1].split(" /to ");
                String from = timeParts[0];
                String to = timeParts[1];
                Event event = new Event(content, false, from, to);
                event.appendToStorage(Sinatra.FILE_PATH);
                tasks.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (message.length() >= 6 && message.substring(0, 6).equals("delete")) {
                Task currTask = tasks.get(Integer.parseInt(message.substring(7)) - 1);
                tasks.remove(currTask);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + currTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (SinatraException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Optional cleanup code
        }
    }
}