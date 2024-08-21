import java.util.ArrayList;
import java.util.Scanner;

public class Elara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        // Greeting
        System.out.println("Hello! I'm Elara");
        System.out.println("What can I do for you?");

        while (true) {
            String text = scanner.nextLine().trim();
            String[] split = text.split(" ", 2);
            String command = split[0];
            String arg = split.length > 1 ? split[1] : "";

            try {
                switch (command) {
                    case "bye":
                        // exit greeting
                        System.out.println("Bye. Hope to see you again!");
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i) == null) {
                                break;
                            }
                            System.out.println((i + 1) + ". " + list.get(i));
                        }
                        break;
                    case "mark":
                        // mark an item as completed
                        int i = Integer.parseInt(arg) - 1;
                        if (i >= 0 && i < list.size()) {
                            list.get(i).mark();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(list.get(i));
                            break;
                        }
                        throw new InvalidInputException("Index out of range!");
                    case "unmark":
                        // unmark an item; i.e. mark as uncompleted
                        int j = Integer.parseInt(arg) - 1;
                        if (j >= 0 && j < list.size()) {
                            list.get(j).unmark();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(list.get(j));
                            break;
                        }
                        throw new InvalidInputException("Index out of range!");
                    case "todo":
                        // add a new todo task
                        if (arg.isEmpty()) {
                            throw new ToDoException();
                        }
                        ToDoTask toDoTask = new ToDoTask(arg);
                        list.add(toDoTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(toDoTask);
                        System.out.printf("Now you have %d tasks in the list%n", list.size());
                        break;
                    case "deadline":
                        // add a new deadline task
                        String[] deadlineArgs = arg.split("/by ");
                        if (deadlineArgs.length == 2) {
                            DeadlineTask deadlineTask = new DeadlineTask(deadlineArgs[0], deadlineArgs[1]);
                            list.add(deadlineTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(deadlineTask);
                            System.out.printf("Now you have %d tasks in the list%n", list.size());
                            break;
                        }
                        throw new DeadlineException();
                    case "event":
                        // add a new event task
                        String[] eventArgs = arg.split("/from |/to ");
                        if (eventArgs.length == 3) {
                            EventTask eventTask = new EventTask(eventArgs[0], eventArgs[1], eventArgs[2]);
                            list.add(eventTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(eventTask);
                            System.out.printf("Now you have %d tasks in the list%n", list.size());
                            break;
                        }
                        throw new EventException();
                    default:
                        throw new InvalidInputException("Errrrrrrr... I don't know what you are trying to say...\n" +
                                "Try one of our commands: list mark unmark bye deadline todo event");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
