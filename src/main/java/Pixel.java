import java.util.Scanner;
import java.util.ArrayList;

public class Pixel {
    public static String LINE = "\t" + "------------------------------------";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println("\t" + "Hello! I'm Pixel!");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void printExit() {
        System.out.println(LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void printList() {
        System.out.println(LINE);
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int index = 1; index <= tasks.size(); index++) {
            System.out.println("\t" + index + "." + tasks.get(index-1));
        }
        System.out.println(LINE);
    }

    public static void printAddConfirmation(String newTaskDescription) {
        System.out.println(LINE);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + newTaskDescription);
        System.out.println("\t" + "Now you have " + tasks.size() + " task(s) in the list.");
        System.out.println(LINE);
    }

    public static void processResponse() throws
            InvalidCommandException,
            EmptyDescriptionException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("list")) {
                printList();
            } else if (command.startsWith("todo")) {
                String description = command.replace("todo", "").trim();
                if (description.equals("")) {
                    throw new EmptyDescriptionException("Description not found");
                } else {
                    ToDo newToDo = new ToDo(description);
                    tasks.add(newToDo);
                    printAddConfirmation(newToDo.toString());
                }
            } else if (command.startsWith("deadline")) {
                String[] stringArray = command.split("/", 0);
                String description = stringArray[0].replace("deadline", "").trim();
                String by = stringArray[1].replace("by", "").trim();
                Deadline newDeadline = new Deadline(description, by);
                tasks.add(newDeadline);
                printAddConfirmation(newDeadline.toString());
            } else if (command.startsWith("event")) {
                String[] stringArray = command.split("/", 0);
                String description = stringArray[0].replace("event", "").trim();
                String from = stringArray[1].replace("from", "").trim();
                String to = stringArray[2].replace("to", "").trim();
                Event newEvent = new Event(description, from, to);
                tasks.add(newEvent);
                printAddConfirmation(newEvent.toString());
            } else if (command.startsWith("mark")) {
                String[] stringArray = command.split(" ", 0);
                Task currentTask = tasks.get(Integer.parseInt(stringArray[1]) - 1);
                currentTask.markAsDone();
                System.out.println(LINE);
                System.out.println("\t" + "Nice! I've marked this task as done:");
                System.out.println("\t  " + currentTask);
                System.out.println(LINE);
            } else if (command.startsWith("unmark")) {
                String[] stringArray = command.split(" ", 0);
                Task currentTask = tasks.get(Integer.parseInt(stringArray[1]) - 1);
                currentTask.markAsUndone();
                System.out.println(LINE);
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                System.out.println("\t  " + currentTask);
                System.out.println(LINE);
            } else if (command.startsWith("delete")) {
                String[] stringArray = command.split(" ", 0);
                Integer index = Integer.parseInt(stringArray[1]) - 1;
                Task removedTask = tasks.get(index);
                tasks.remove(removedTask);
                System.out.println(LINE);
                System.out.println("\t" + "Noted. I've removed this task:");
                System.out.println("\t  " + removedTask);
                System.out.println("\t" + "Now you have " + tasks.size() + " task(s) in the list.");
                System.out.println(LINE);
            } else if (command.equals("bye")) {
                printExit();
                break;
            } else {
                throw new InvalidCommandException("Command not found");
            }
        }
    }
    public static void main(String[] args) {
        try {
            printGreeting();
            processResponse();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but there is no such index that exists");
            System.out.println("\t" + "Type in a valid index!");
            System.out.println(LINE);
        } catch (InvalidCommandException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but I don't know what that means");
            System.out.println("\t" + "Type in a valid command!");
            System.out.println(LINE);
        } catch (EmptyDescriptionException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but I can't add a task if the description is empty!");
            System.out.println("\t" + "Type in a valid description!");
            System.out.println(LINE);
        }
    }
}
