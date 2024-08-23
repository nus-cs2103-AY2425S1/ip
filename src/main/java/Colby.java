import java.util.Scanner;

public class Colby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] store = new Task[100];
        int n = 0;

        String logo = "  ____      _ _           \n"
                    + " / ___|___ | | |__  _   _ \n"
                    + "| |   / _ \\| | '_ \\| | | |\n"
                    + "| |__| (_) | | |_) | |_| |\n"
                    + "\\____\\___/|_|_.__/ \\__, /\n"
                    + "                   |___/ \n";

        System.out.println("Hello! I'm \n"+ logo + "\n" + "What can I do for you?\n");
        while (true) {
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
                store[n] = new ToDo(task);
                String lastWord = "tasks";
                if (n == 0) {
                    lastWord = "task";
                }

                System.out.println("  Alright, I have added this task to the list: \n"
                        + "    " + store[n].toString() + "\n"
                        + "  Your list now has " + (n + 1) + " " + lastWord + " :)");
                n++;
            } else if (task.split(" ")[0].equalsIgnoreCase("deadline")){
                String[] temp = task.split("/");
                String end = task.split("/")[1].split(" ", 2)[1];
                store[n] = new Deadline(task, end);

                String lastWord = "tasks";
                if (n == 0) {
                    lastWord = "task";
                }

                System.out.println("  Alright, I have added this task to the list: \n"
                        + "    " + store[n].toString() + "\n"
                        + "  Your list now has " + (n + 1) + " " + lastWord + " :)");
                n++;
            } else if (task.split(" ")[0].equalsIgnoreCase("event")) {
                String[] temp = task.split("/");
                String start = temp[1].split( " ", 2)[1];
                String end = temp[2].split(" ", 2)[1];

                store[n] = new Event(task, start, end);

                String lastWord = "tasks";
                if (n == 0) {
                    lastWord = "task";
                }

                System.out.println("  Alright, I have added this task to the list: \n"
                        + "    " + store[n].toString() + "\n"
                        + "  Your list now has " + (n + 1) + " " + lastWord+ " :)");
                n++;
            }
        }
        scanner.close();
    }
}
