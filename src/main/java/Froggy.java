import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Froggy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<Task> tasks = new ArrayList<>();

        String greeting = "Hello! I'm Froggy!\n"
                + "Enter tasks and I will store it.\n"
                + "Type 'list' to view tasks or 'bye' to exit.\n"
                + "_______________________________";
        String exit = "Bye. Hope to see you again soon!\n"
                + "_______________________________";
        String line = "_______________________________";

        System.out.println(greeting);

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Task List:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i - 1).toString());
                }
                System.out.println("_______________________________");
            } else if (input.toLowerCase().startsWith("mark ") || input.equalsIgnoreCase("mark")) {
                if (input.length() <= 5) {
                    System.out.println("Error: Please enter the index of the task to be marked.");
                    System.out.println(line);
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            tasks.get(index).setStatus(true);
                            System.out.println("Marked the following task as done:");
                            System.out.println(tasks.get(index).toString());
                            System.out.println(line);
                        } else {
                            System.out.println("Error: Invalid index. Please enter an index in range.");
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid index after 'mark'.");
                        System.out.println(line);
                    }
                }
            } else if (input.toLowerCase().startsWith("unmark ") || input.equalsIgnoreCase("unmark")) {
                if (input.length() <= 7) {
                    System.out.println("Error: Please enter the index of the task to be unmarked.");
                    System.out.println(line);
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            tasks.get(index).setStatus(false);
                            System.out.println("Marked the following task as undone:");
                            System.out.println(tasks.get(index).toString());
                            System.out.println(line);
                        } else {
                            System.out.println("Error: Invalid index. Please enter an index in range.");
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid index after 'unmark'.");
                        System.out.println(line);
                    }
                }
            } else if (input.toLowerCase().startsWith("delete ") || input.equalsIgnoreCase("delete")){
                if (input.length() <= 7) {
                    System.out.println("Error: Please enter the index of the task to be deleted.");
                    System.out.println(line);
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            System.out.println("Deleted the following task:");
                            System.out.println(tasks.get(index).toString());
                            System.out.println(line);
                            tasks.remove(index);
                        } else {
                            System.out.println("Error: Invalid index. Please enter an index in range.");
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid index after 'delete'.");
                        System.out.println(line);
                    }
                }
            } else {
                //Handle Task input
                if (input.toLowerCase().startsWith("todo ")) {
                    if (input.length() == 5) {
                        System.out.println("Error: No description for ToDo task.");
                        System.out.println("Please input a description for the task");
                        System.out.println(line);
                    } else {
                        String desc = input.substring(5);
                        Todo current = new Todo(desc);
                        tasks.add(current);
                        System.out.println("Added this task:");
                        System.out.println(current.toString());
                        System.out.println(line);
                    }
                } else if (input.toLowerCase().startsWith("deadline ")) {
                    int index = input.toLowerCase().indexOf("/by ");
                    if (index != -1) {
                        String desc = input.substring(9,index - 1);
                        String by = input.substring(index + 4);
                        Deadline current = new Deadline(desc, by);
                        tasks.add(current);
                        System.out.println("Added this task:");
                        System.out.println(current.toString());
                        System.out.println(line);
                    }
                } else if (input.toLowerCase().startsWith("event ")) {
                    int index = input.toLowerCase().indexOf("/from ");
                    int index2 = input.toLowerCase().indexOf("/to ");
                    if (index != -1) {
                        String desc = input.substring(6, index - 1);
                        String from = input.substring(index + 6, index2 - 1);
                        String to = input.substring(index2 + 4);
                        Event current = new Event(desc, from, to);
                        tasks.add(current);
                        System.out.println("Added this task:");
                        System.out.println(current.toString());
                        System.out.println(line);
                    }
                }
                else {
                    System.out.println("Input not recognised. Please input a valid action:");
                    System.out.println("todo, event, deadline, mark, unmark, list, bye");
                    System.out.println(line);
                }
            }
        }

        scanner.close();
    }
}
