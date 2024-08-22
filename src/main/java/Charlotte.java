import java.util.ArrayList;
import java.util.Scanner;

public class Charlotte {
    public static void main(String[] args) {
        String line = "__________________________________________________________________";
        System.out.println(line + "\n Hello! I'm Charlotte!\n What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String input;


        while (true) {
            input = scanner.nextLine();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    break;
                }

                if (input.equalsIgnoreCase("list")) {
                    if (tasks.isEmpty()) {
                        throw new CharlotteException("Your task list is currently empty. Start by adding some tasks!");
                    }

                    System.out.println(line + "\n Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.println(line);
                } else if (input.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new CharlotteException("Task number is invalid. Please try again");
                    }

                    tasks.get(taskNumber).markAsDone();
                    System.out.println(line + "\n Nice! I've marked this task as done:\n  "
                            + tasks.get(taskNumber) + "\n" + line);

                } else if (input.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new CharlotteException("Task number is invalid. Please try again");
                    }
                    tasks.get(taskNumber).unmark();
                    System.out.println(line + "\n OK, I've marked this task as not done yet:\n  "
                            + tasks.get(taskNumber) + "\n" + line);

                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new CharlotteException("The description of a todo cannot be empty. Please try again.");
                    }
                    String description = input.substring(5);
                    Task newTask = new ToDo(description);
                    tasks.add(newTask);
                    System.out.println(line + "\n Got it. I've added this task:\n  " + newTask
                            + "\n Now you have " + tasks.size() + " tasks in the list.\n" + line);
                } else if (input.startsWith("deadline")) {
                    if (input.length() <= 9) {
                        throw new CharlotteException("The description of a deadline cannot be empty. Please try again.");
                    }
                    String[] deadline = input.substring(9).split(" /by ");
                    String description = deadline[0];
                    String by = deadline[1];
                    Task newTask = new Deadline(description, by);
                    tasks.add(newTask);
                    System.out.println(line + "\n Got it. I've added this task:\n  " + newTask
                            + "\n Now you have " + tasks.size() + " tasks in the list.\n" + line);
                } else if (input.startsWith("event")) {
                    if (input.length() <= 6) {
                        throw new CharlotteException("The description of an event cannot be empty. Please try again.");
                    }
                    String[] event = input.substring(6).split(" /from | /to ");
                    String description = event[0];
                    String from = event[1];
                    String to = event[2];
                    Task newTask = new Event(description, from, to);
                    tasks.add(newTask);
                    System.out.println(line + "\n Got it. I've added this task:\n  " + newTask
                            + "\n Now you have " + tasks.size() + " tasks in the list.\n" + line);
                } else {
                    throw new CharlotteException("I'm sorry, I don't know what that means :( Please try again!");
                }
            } catch (CharlotteException error) {
                System.out.println(line + "\n " + error.getMessage() + "\n" + line);
            }

        }

        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);

        scanner.close();
    }
}
