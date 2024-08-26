import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Bob {
    enum Command {
        LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, BYE, INVALID
    }

    public static void main(String[] args) throws IOException {
        String logo = "Bob";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        ArrayList<Task> list = new ArrayList<>();
        // add function to add tasks to list from file
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!getCommand(input).equals(Command.BYE)) {
            try {
                Command command = getCommand(input);
                switch(command) {
                    case LIST:
                        listTasks(list);
                        break;
                    case UNMARK:
                        unmarkTask(list, input);
                        break;
                    case MARK:
                        markTask(list, input);
                        break;
                    case DELETE:
                        deleteTask(list, input);
                        break;
                    case TODO:
                        addToDoTask(list, input);
                        break;
                    case DEADLINE:
                        addDeadlineTask(list,input);
                        break;
                    case EVENT:
                        addEventTask(list, input);
                        break;
                    case INVALID:
                        throw new BobException("Invalid command. Please enter a valid command. Valid commands are: list, unmark, mark, delete, todo, deadline, event, and bye.");
                }
            } catch (BobException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            input = scanner.nextLine();
        }
        FileWriting.writeToFile("data/bob.txt", list);
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static Command getCommand(String input) {
        for (Command command : Command.values()) {
            if (input.startsWith(command.name().toLowerCase())) {
                return command;
            }
        }
        return Command.INVALID;
    }

    private static void listTasks(ArrayList<Task> list) throws FileNotFoundException {
        System.out.println("Here are the tasks in your list:");

        FileReading.printFileContents("data/bob.txt");
    }

    private static void markTask(ArrayList<Task> list, String input) throws BobException {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new BobException("Please enter a valid task number.");
        }
        list.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + list.get(index));
    }

    private static void unmarkTask(ArrayList<Task> list, String input) throws BobException {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new BobException("Please enter a valid task number.");
        }
        list.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + list.get(index));
    }

    private static void deleteTask(ArrayList<Task> list, String input) throws BobException {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new BobException("Please enter a valid task number.");
        }

        System.out.println("Noted. I've removed this task:\n" + list.get(index));
        list.remove(index);
        System.out.println("Now you have " + list.size() + (list.isEmpty() ? " task in the list." : " tasks in the list."));
    }

    private static void addToDoTask(ArrayList<Task> list, String input) throws BobException {
        String description = input.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new BobException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(description);
        list.add(t);
        taskAdded(list, t);
    }

    private static void addDeadlineTask(ArrayList<Task> list, String input) throws BobException {
        String[] parts = input.split("/by");
        if (parts.length < 2) {
            throw new BobException("The date of a deadline cannot be empty. Please enter in the format: description /by <date>");
        }
        String description = parts[0].substring("deadline".length()).trim();
        String date = parts[1].trim();
        if (description.isEmpty()) {
            throw new BobException("The description of a deadline cannot be empty.");
        }
        Task t = new Deadline(description, date);
        list.add(t);
        taskAdded(list, t);
    }

    private static void addEventTask(ArrayList<Task> list, String input) throws BobException {
        String[] parts = input.split("/from|/to");
        if (parts.length < 3) {
            throw new BobException("The start and end date/time of an event cannot be empty. Please enter in the format: description /from <start> /to <end>");
        }
        String description = parts[0].substring("event".length()).trim();
        String from = parts[1];
        String to = parts[2].trim();
        if (description.isEmpty()) {
            throw new BobException("The description of an event cannot be empty.");
        }
        Task t = new Event(description, from, to);
        list.add(t);
        taskAdded(list, t);
    }

    private static void taskAdded(ArrayList<Task> list, Task t) {
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list."));
    }
}
