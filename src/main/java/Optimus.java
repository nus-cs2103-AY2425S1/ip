import java.util.Objects;
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
public class Optimus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Optimus");
        System.out.println("What can I do for you?");
        Scanner stringScanner = new Scanner(System.in);
        List<Task> record = new ArrayList<>();
        int count = 0;

        while (true) {
            String text = stringScanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < count; j++) {
                    System.out.println((j + 1) + ". " + record.get(j).toString());
                }
            } else if (text.startsWith("mark ") || text.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(text.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= count) {
                        throw new OptimusException("Invalid task number. Please enter a number between 1 and " + count + ".");
                    }
                    if (text.startsWith("mark ")) {
                        record.get(taskNumber).setDone();
                        System.out.println("Nice! I've marked this task as done:");
                    } else if (text.startsWith("unmark ")) {
                        record.get(taskNumber).setNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println("  " + record.get(taskNumber).toString());
                } catch (OptimusException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.startsWith("todo ")) {
                try {
                    String description = text.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new OptimusException("Oops! The description of a todo cannot be empty. Please provide a task description.");
                    }
                    if (count < 100) {
                        record.add(count, new ToDos(description));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + record.get(count).toString());
                        count++;
                        System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
                    }
                } catch (OptimusException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.startsWith("deadline ")) {
                try {
                    String[] parts = text.split(" /by ");
                    String description = parts[0].substring(9).trim();
                    if (description.isEmpty()) {
                        throw new OptimusException("Oops! The description of a deadline cannot be empty. Please provide a task description.");
                    }
                    if (count < 100) {
                        String by = parts.length > 1 ? parts[1] : "";
                        record.add(count, new Deadlines(description, by));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + record.get(count).toString());
                        count++;
                        System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
                    }
                } catch (OptimusException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.startsWith("event ")) {
                try {
                    String[] parts = text.split(" /from | /to ");
                    String description = parts[0].substring(6).trim();
                    if (description.isEmpty()) {
                        throw new OptimusException("Oops! The description of an event cannot be empty. Please provide a task description.");
                    }
                    if (count < 100) {
                        String from = parts.length > 1 ? parts[1] : "";
                        String to = parts.length > 2 ? parts[2] : "";
                        record.add(count, new Events(description, from, to));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + record.get(count).toString());
                        count++;
                        System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
                    }
                } catch (OptimusException e) {
                    System.out.println(e.getMessage());
                }
            } else if (text.startsWith("delete")) {
                try {
                    int taskNumber = Integer.parseInt(text.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= count) {
                        throw new OptimusException("Invalid task number. Please enter a number between 1 and " + count + ".");
                    }
                    String del_get = record.get(taskNumber).toString();
                    record.remove(taskNumber);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + del_get);
                    count--;
                    System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
                } catch (OptimusException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new OptimusException("Sorry, I don't understand that command. " +
                            "Please try again with a valid command (Eg. todo, deadline or event).");
                } catch (OptimusException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

