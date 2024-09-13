package sinatra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the main Sinatra application.
 */
public class Sinatra {

    private static final String FILE_PATH = "tasks.txt";
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a new Sinatra object, initializes tasks, prints the introduction,
     * loads tasks from storage, and starts the scanner for user input.
     */
    public Sinatra() {
        this.tasks = new ArrayList<Task>();
        this.printIntro();
        this.storage = new Storage(Sinatra.FILE_PATH);
        this.tasks = storage.loadTasksFromFile();

    }

    /**
     * Prints the introduction message.
     */
    private void printIntro() {
        String startingPhrase = "Hello! I'm Sinatra. \nWhat can I do for you? ";
        System.out.println(startingPhrase);
    }
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        List<String> output = handleInputs(input);
        StringBuilder response = new StringBuilder();
        for (String s : output) {
            response.append(s).append("\n");
        }
        return "Sinatra: \n" + response.toString();
    }
    private ArrayList<Task> findTasksWithContent(String contentPart) {

        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getContent().contains(contentPart)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Starts the scanner to handle user inputs.
     */
    private void sinatraScanner() {
        Scanner scanner = new Scanner(System.in);
        List<String> output;
        while (true) {
            String input = scanner.nextLine();
            output = handleInputs(input);
            for (String s : output) {
                System.out.println(s);
            }
        }

    }

    /**
     * Handles the user inputs and performs the corresponding actions.
     *
     * @param message the user input message
     */
    private List<String> handleInputs(String message) {
        List<String> output = new ArrayList<>();

        try {

            if (message.equals("list")) {
                output.add("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    int count = i + 1;
                    output.add(count + "." + tasks.get(i).toString());
                }
            } else if (message.equals("bye")) {
                output.add("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (message.startsWith("mark")) {
                Task currTask = tasks.get(Integer.parseInt(message.substring(5)) - 1);
                currTask.setStatus(true);
                output.add("Nice! I've marked this task as done: ");
                output.add("  " + currTask);

            } else if (message.startsWith("unmark")) {
                Task currTask = tasks.get(Integer.parseInt(message.substring(7)) - 1);
                currTask.setStatus(false);
                output.add("OK, I've marked this task as not done yet: ");
                output.add("  " + currTask);
            } else if (message.startsWith("todo")) {
                if (message.length() <= 4) {
                    throw new SinatraException("OOPS!!! The description of a event cannot be empty.");
                }
                ToDo toDo = new ToDo(message.substring(5, message.length()), false);
                toDo.appendToStorage(Sinatra.FILE_PATH);
                tasks.add(toDo);
                output.add("Got it. I've added this task:");
                output.add("  " + tasks.get(tasks.size() - 1));
                output.add("Now you have " + tasks.size() + " tasks in the list.");
            } else if (message.startsWith("deadline")) {
                if (message.length() <= 8) {
                    throw new SinatraException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] parts = message.substring(9).split(" /by ");
                String content = parts[0];
                String dateTimeString = parts[1];
                Deadline deadline = new Deadline(content, false, dateTimeString);
                deadline.appendToStorage(Sinatra.FILE_PATH);
                tasks.add(deadline);
                output.add("Got it. I've added this task:");
                output.add("  " + tasks.get(tasks.size() - 1));
                output.add("Now you have " + tasks.size() + " tasks in the list.");
            } else if (message.startsWith("event")) {
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
                output.add("Got it. I've added this task:");
                output.add("  " + tasks.get(tasks.size() - 1));
                output.add("Now you have " + tasks.size() + " tasks in the list.");
            } else if (message.startsWith("delete")) {
                Task currTask = tasks.get(Integer.parseInt(message.substring(7)) - 1);
                tasks.remove(currTask);
                output.add("Noted. I've removed this task:");
                output.add("  " + currTask);
                output.add("Now you have " + tasks.size() + " tasks in the list.");
            } else if (message.startsWith("find")) {
                String contentPart = message.substring(5);
                ArrayList<Task> foundTasks = findTasksWithContent(contentPart);
                output.add("Here are the matching tasks in your list:");
                for (int i = 0; i < foundTasks.size(); i++) {
                    output.add(i + 1 + "." + foundTasks.get(i).toString());
                }

            } else {
                output.add("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (SinatraException e) {
            output.add(e.getMessage());
        } catch (Exception e) {
            output.add("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Optional cleanup code
        }
        return output;
    }
}
