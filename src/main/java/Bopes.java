import java.util.ArrayList;
import java.util.Scanner;

public class Bopes {
    public static void main(String[] args) {
        String intro = "Bopes is a personal assistant that helps you manage your tasks.";
        System.out.println(intro);

        Scanner scanner = new Scanner(System.in);
        String input;

        ArrayList<Task> inputs = new ArrayList<Task>();

        while (true) {
            System.out.println("What can I do for you?");
            input = scanner.nextLine();

            // Exit 
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                // Input special commands
                if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println((i + 1) + ". " + inputs.get(i).toString());
                    }
                } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < inputs.size()) {
                        inputs.get(index).markAsDone();
                        System.out.println("Marked task " + (index + 1) + " as done.");
                    } else {
                        throw BopesException.invalidIndex(inputs.size());
                    }
                } else if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < inputs.size()) {
                        inputs.get(index).markAsUndone();
                        System.out.println("Unmarked task " + (index + 1) + " as undone.");
                    } else {
                        throw BopesException.invalidIndex(inputs.size());
                    }
                } else if (input.split(" ")[0].equalsIgnoreCase("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < inputs.size()) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(inputs.get(index).toString());
                        inputs.remove(index);
                        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    } else {
                        throw BopesException.invalidIndex(inputs.size());
                    }
                }else {
                    // Adding of tasks
                    Task newTask = null;
                    if (input.startsWith("todo ")) {
                        newTask = new ToDo(input.substring(5));
                    } else if (input.startsWith("deadline ")) {
                        String[] temp = input.substring(9).split(" /by ");
                        if (temp.length == 2) {
                            newTask = new Deadline(temp[0], temp[1]);
                        } else {
                            throw BopesException.invalidDeadlineFormat();
                        }
                    } else if (input.startsWith("event ")) {
                        String[] temp = input.substring(6).split(" /from | /to ");
                        if (temp.length == 3) {
                            newTask = new Event(temp[0], temp[1], temp[2]);
                        } else {
                            throw BopesException.invalidEventFormat();
                        }
                    } else {
                        throw BopesException.unknownCommand();
                    }
                    inputs.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.toString());
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                }
            } catch (BopesException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: Something went wrong. Please try again.");
            }
        }

        scanner.close();
    }
}
