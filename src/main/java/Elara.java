import jdk.jfr.Event;

import java.util.Scanner;

public class Elara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;

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
                        for (int i = 0; i < count; i++) {
                            if (list[i] == null) {
                                break;
                            }
                            int j = i + 1;
                            System.out.println(j + ". " + list[i]);
                        }
                        break;
                    case "mark":
                        // mark an item as completed
                        int i = Integer.parseInt(arg) - 1;
                        if (i >= 0 && i < count) {
                            list[i].mark();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(list[i]);
                        }
                        break;
                    case "unmark":
                        // unmark an item; i.e. mark as uncompleted
                        int j = Integer.parseInt(arg) - 1;
                        if (j >= 0 && j < count) {
                            list[j].unmark();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(list[j]);
                        }
                        break;
                    case "todo":
                        // add a new todo task
                        if (arg.isEmpty()) {
                            throw new ToDoException();
                        }
                        ToDoTask toDoTask = new ToDoTask(arg);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(toDoTask);
                        System.out.printf("Now you have %d tasks in the list%n", count + 1);
                        list[count] = toDoTask;
                        count++;
                        break;
                    case "deadline":
                        // add a new deadline task
                        String[] deadlineArgs = arg.split("/by ");
                        if (deadlineArgs.length == 2) {
                            DeadlineTask deadlineTask = new DeadlineTask(deadlineArgs[0], deadlineArgs[1]);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(deadlineTask);
                            System.out.printf("Now you have %d tasks in the list%n", count + 1);
                            list[count] = deadlineTask;
                            count++;
                            break;
                        }
                        throw new DeadlineException();
                    case "event":
                        // add a new event task
                        String[] eventArgs = arg.split("/from |/to ");
                        if (eventArgs.length == 3) {
                            EventTask eventTask = new EventTask(eventArgs[0], eventArgs[1], eventArgs[2]);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(eventTask);
                            System.out.printf("Now you have %d tasks in the list%n", count + 1);
                            list[count] = eventTask;
                            count++;
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
