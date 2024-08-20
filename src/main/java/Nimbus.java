import java.util.ArrayList;
import java.util.Scanner;

public class Nimbus {
    final private static String name = "Nimbus";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    private static void printDash() {
        System.out.println("____________________________________________________________");
    }

    public static void printWelcomeMessage() {
        printDash();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printDash();
    }

    public static void printGoodbyeMessage() {
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void removeTask(int index) {
        System.out.println("Noted. I've removed this task: " + tasks.remove(index));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void setDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index).setDone();
        System.out.println(tasks.get(index));
    }

    public static void setNotDone(int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        tasks.get(index).setNotDone();
        System.out.println(tasks.get(index));
    }

    public static void printAllTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void invalidCommand() {
        System.out.println("Invalid Command");
    }

    public static void readCommand(String line) throws InvalidCommandException, InvalidArgumentException{
        String text;
        String command = "";
        int index = line.indexOf(" ");
        if (index == -1) {
            switch (line) {
                case "list":
                    printAllTask();
                    break;
                default:
                    throw new InvalidArgumentException(line + ": Is not a valid command");
            }
        } else {
            command = line.substring(0, index);
            text    = line.substring(index + 1);
            switch (command) {
                case "remove":
                    removeTask(Integer.parseInt(text) - 1);
                    break;
                case "list":
                    printAllTask();
                    break;
                case "mark":
                    setDone(Integer.parseInt(text) - 1);
                    break;
                case "unmark":
                    setNotDone(Integer.parseInt(text) - 1);
                    break;
                case "todo":
                    if (text.isEmpty())
                        throw new InvalidArgumentException("Todo's description can't be empty!");
                    addTask(new Todo(text));
                    break;
                case "deadline": {
                    String byArg = "/by";
                    int byIndex = text.indexOf(byArg);
                    String description = text.substring(0, byIndex);
                    String deadline = text.substring(byIndex + byArg.length());
                    addTask(new Deadline(description.trim(), deadline.trim()));
                    break;
                }
                case "event": {
                    String fromArg = "/from";
                    String toArg = "/to";
                    int fromIndex = text.indexOf(fromArg);
                    int toIndex = text.indexOf(toArg);
                    String description = text.substring(0, fromIndex);
                    String from = text.substring(fromIndex + fromArg.length(), toIndex);
                    String to = text.substring(toIndex + toArg.length());
                    addTask(new Event(description.trim(), from.trim(), to.trim()));
                    break;
                }
                default:
                    throw new InvalidCommandException(command + " is not a valid command");
            }
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String line;

        while(!(line = scanner.nextLine()).equals("bye")) {
            try {
                readCommand(line);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        printGoodbyeMessage();
    }
}
