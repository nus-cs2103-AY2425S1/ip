import java.util.Scanner;
import java.util.ArrayList;

public class DemureBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // check if user ended session
        boolean finished = false;
        // list of items to do
        ArrayList<Task> list = new ArrayList<>();

        // introduction to chat bot
        System.out.println("____________________________________________________________\n" +
            " Hello! I'm DemureBot\n" +
            " What can I do for you?\n" +
            "____________________________________________________________\n" +
            "\n"
        );

        // while user hasn't ended session
        while (!finished) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            if (parts.length == 2) {
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
                    } else if (parts[0].equals("unmark")) {
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
            } else {
                switch (command) {
                    // end session
                    case "bye":
                        finished = true;
                        break;

                    // list all tasks
                    case "list":
                        for (int i = 0; i < list.size(); i++) {
                            Task task = list.get(i);
                            System.out.println((i + 1) + ".[" + task.getStatusIcon() + "] " + task);
                        }
                        break;

                    // add a new task
                    default:
                        list.add(new Task(command));
                        System.out.println("____________________________________________________________\n" +
                                "added: " + command +
                                "\n" +
                                "____________________________________________________________\n" +
                                "\n"
                        );
                        break;
                }
            }
        }
        // close scanner and end session
        scanner.close();
        System.out.println("____________________________________________________________\n" +
            " Bye. Hope to see you again soon!\n" +
            "____________________________________________________________\n" +
            "\n"
        );
    }
}
