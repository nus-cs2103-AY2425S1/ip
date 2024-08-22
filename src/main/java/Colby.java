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
            String task = scanner.nextLine();

            if (task.equalsIgnoreCase("bye")) {
                System.out.println("  Bye bye! Hope to see you again soon! :)");
                System.out.println("_________________________________________________");
                break;

            } else if (task.equalsIgnoreCase("list")) {
                System.out.println("Here's all the tasks you have to do:");


                for (int i = 1; i < (n + 1); i++) {
                    System.out.println("  " + i + ". [" + store[i - 1].getStatusIcon() + "] " + store[i - 1].description);
                }
            } else if (task.split(" ")[0].equalsIgnoreCase("mark")) {
                Integer change = Integer.parseInt(task.split(" ")[1]);
                store[change - 1].markAsDone();
                System.out.println("Great job! I have now marked this task as done!");
                System.out.println("  " + (change) + ". [" + store[change - 1].getStatusIcon() + "] " + store[change - 1].description);
            } else if (task.split(" ")[0].equalsIgnoreCase("unmark")){
                Integer change = Integer.parseInt(task.split(" ")[1]);
                store[change - 1].markAsUndone();
                System.out.println("Alright, I have marked this task as not done yet.");
                System.out.println("  " + (change) + ". [" + store[change - 1].getStatusIcon() + "] " + store[change - 1].description);
            } else {
                Task newTask = new Task(task);
                store[n] = newTask;
                n++;
                System.out.println("  added: " + task);
            }
        }
        scanner.close();
    }
}
