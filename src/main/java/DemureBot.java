import java.util.Scanner;
import java.util.ArrayList;

public class DemureBot {
    public static void check(String command, ArrayList<Task> list) {
        String[] parts = command.split(" ");

        if (parts.length >= 2) {
            if (parts[0].equals("mark") || parts[0].equals("unmark")) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task task = list.get(index);

                    if (parts[0].equals("mark")) {
                        // mark a task as done
                        task.markAsDone();
                        System.out.println("____________________________________________________________\n" +
                                " Nice! I've marked this task as done:\n" +
                                "   [" + task.getStatusIcon() + "] " + task + "\n" +
                                "____________________________________________________________\n" +
                                "\n"
                        );
                    } else {
                        // unmark a task
                        task.unmark();
                        System.out.println("____________________________________________________________\n" +
                                " OK, I've marked this task as not done yet:\n" +
                                "   [" + task.getStatusIcon() + "] " + task + "\n" +
                                "____________________________________________________________\n" +
                                "\n"
                        );
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format: " + parts[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid index: " + (Integer.parseInt(parts[1])));
                }
            } else if (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event")) {
                StringBuilder remainingString = new StringBuilder();
                for (int i = 1; i < parts.length; i++) {
                    if (i > 1) {
                        remainingString.append(" ");
                    }
                    remainingString.append(parts[i]);
                }
                String result = remainingString.toString();
                if (parts[0].equals("todo")) {
                    Todo todo = new Todo(result);
                    list.add(todo);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:\n  " +
                            todo + "\n" +
                            "Now you have " + list.size() + " tasks in the list.\n" +
                            "____________________________________________________________\n" +
                            "\n"
                    );
                } else if (parts[0].equals("deadline")) {
                    String[] descriptionBy = result.split("/by");
                    String description = descriptionBy[0];
                    String by = descriptionBy[1];
                    Deadline deadline = new Deadline(description, by);
                    list.add(deadline);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:\n  " +
                            deadline + "\n" +
                            "Now you have " + list.size() + " tasks in the list.\n" +
                            "____________________________________________________________\n" +
                            "\n"
                    );
                } else if (parts[0].equals("event")) {
                    String[] splitByFrom = result.split("/from");
                    String description = splitByFrom[0];
                    String[] splitByTo = splitByFrom[1].split("/to");
                    String from = splitByTo[0];
                    String to = splitByTo[1];
                    Event event = new Event(description, from, to);
                    list.add(event);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:\n  " +
                            event + "\n" +
                            "Now you have " + list.size() + " tasks in the list.\n" +
                            "____________________________________________________________\n" +
                            "\n"
                    );
                }
            } else {
                // add a new task
                list.add(new Task(command));
                System.out.println("____________________________________________________________\n" +
                        "added: " + command +
                        "\n" +
                        "____________________________________________________________\n" +
                        "\n"
                );
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // check if user ended session
        boolean finished = false;
        // list of items to do
        ArrayList<Task> list = new ArrayList<>();

        // introduction to chatbot
        System.out.println("""
            ____________________________________________________________
             Hello! I'm DemureBot
             What can I do for you?
            ____________________________________________________________

            """
        );

        // while user hasn't ended session
        while (!finished) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                // end session
                finished = true;
            } else if (command.equals("list")) {
                // list all tasks
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println((i + 1) + "." + task);
                }
            } else {
                DemureBot.check(command, list);
            }
        }

        // close scanner and end session
        scanner.close();
        System.out.println("""
            ____________________________________________________________
             Bye. Hope to see you again soon!
            ____________________________________________________________

            """
        );
    }
}
