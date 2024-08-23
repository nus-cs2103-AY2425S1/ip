import java.util.Scanner;

public class Colby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] store = new Task[100];
        int n = 0;

        String logo = "  ____      _ _\n"
                + " / ___|___ | | |__  _   _\n"
                + "| |   / _ \\| | '_ \\| | | |\n"
                + "| |__| (_) | | |_) | |_| |\n"
                + "\\____\\___/|_|_.__/ \\__, /\n"
                + "                   |___/\n";

        System.out.println("Hello! I'm\n" + logo + "\n" + "What can I do for you?\n");

        while (true) {
            try {
                String task = "";
                if (scanner.hasNextLine()) {
                    task = scanner.nextLine();
                } else {
                    break;
                }

                if (task.equalsIgnoreCase("bye")) {
                    System.out.println("  Bye bye! Hope to see you again soon! :)");
                    System.out.println("_________________________________________________");
                    break;

                } else if (task.equalsIgnoreCase("list")) {
                    System.out.println("Here's all the tasks you have to do:");

                    for (int i = 0; i < (n); i++) {
                        System.out.println("  " + (i + 1) + ". " + store[i].toString());
                    }

                } else if (task.split(" ")[0].equalsIgnoreCase("mark")) {
                    Integer change = Integer.parseInt(task.split(" ")[1]);

                    store[change - 1].markAsDone();

                    System.out.println("  Great job! I have now marked this task as done!");
                    System.out.println("    [" + store[change - 1].getStatusIcon() + "] " + store[change - 1].description);
                } else if (task.split(" ")[0].equalsIgnoreCase("unmark")) {
                    Integer change = Integer.parseInt(task.split(" ")[1]);

                    store[change - 1].markAsUndone();

                    System.out.println("  Alright, I have marked this task as not done yet.");
                    System.out.println("    [" + store[change - 1].getStatusIcon() + "] " + store[change - 1].description);
                } else if (task.split(" ")[0].equalsIgnoreCase("todo")) {
                    if (task.length() < 5) {
                        throw new EmptyDescriptionException("WAIT!! You have to add in the description of your task as well");
                    }

                    store[n] = new ToDo(task);
                    String lastWord = "tasks";
                    if (n == 0) {
                        lastWord = "task";
                    }

                    System.out.println("  Alright, I have added this task to the list:\n"
                            + "    " + store[n].toString() + "\n"
                            + "  Your list now has " + (n + 1) + " " + lastWord + " :)");
                    n++;
                } else if (task.split(" ")[0].equalsIgnoreCase("deadline")) {
                    String[] parts = task.split("/");
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("WAIT!! You have to add in the description of your task as well");
                    }

                    String[] temp = task.split("/");
                    String end = task.split("/")[1].split(" ", 2)[1];
                    store[n] = new Deadline(task, end);

                    String lastWord = "tasks";
                    if (n == 0) {
                        lastWord = "task";
                    }

                    System.out.println("  Alright, I have added this task to the list:\n"
                            + "    " + store[n].toString() + "\n"
                            + "  Your list now has " + (n + 1) + " " + lastWord + " :)");
                    n++;
                } else if (task.split(" ")[0].equalsIgnoreCase("event")) {
                    String[] parts = task.split("/");
                    if (parts.length < 3) {
                        throw new EmptyDescriptionException("WAIT!! You have to add in the description of your task as well");
                    }

                    String[] temp = task.split("/");
                    String start = temp[1].split(" ", 2)[1];
                    String end = temp[2].split(" ", 2)[1];

                    store[n] = new Event(task, start, end);

                    String lastWord = "tasks";
                    if (n == 0) {
                        lastWord = "task";
                    }

                    System.out.println("  Alright, I have added this task to the list:\n"
                            + "    " + store[n].toString() + "\n"
                            + "  Your list now has " + (n + 1) + " " + lastWord + " :)");
                    n++;
                } else if (!task.startsWith("todo") && !task.startsWith("deadline") && !task.startsWith("event")) {
                    throw new RandomWordException("Sorry!! I'm not sure how to add that to the list for you, try specifying the type of task!");
                }

            } catch (Exception e) {
                String message = e.getMessage();
                System.out.println(message);
            }
        }
        scanner.close();
    }
}
