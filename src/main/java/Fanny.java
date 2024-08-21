import java.util.Scanner;

public class Fanny {

    public static void main(String[] args) {

        TaskList list = new TaskList();

        System.out.println("_____________________________________________");
        System.out.println("Hello! I'm Fanny");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________________");

        Scanner scanner = new Scanner(System.in);

        //while ensure continuous reading of user inputs
        while (true) {
            System.out.println("User: ");
            String cmd = scanner.nextLine();
            String[] cmdParts =   cmd.split(" ");
            String action = cmdParts[0];

            //condition to exit when cmd is bye
            if (action.equals("bye")) {
                System.out.println("_____________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________________________");
                break;
            } else if (action.equals("list")) {
                System.out.println("Fanny: ");
                list.printList();
            } else if (action.equals("mark")) {
                System.out.println("_____________________________________________");
                if (cmdParts.length > 1) {
                    int taskId = Integer.parseInt(cmdParts[1]);
                    System.out.println("Fanny: ");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.markAsDone(taskId - 1));
                }
                System.out.println("_____________________________________________");
            } else if (action.equals("unmark")) {
                System.out.println("_____________________________________________");
                if (cmdParts.length > 1) {
                    int taskId = Integer.parseInt(cmdParts[1]);
                    System.out.println("Fanny: ");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.markAsNotDone(taskId - 1));
                }
                System.out.println("_____________________________________________");
            } else {
                System.out.println("_____________________________________________");
                System.out.println("Fanny: ");
                System.out.println("Added: " + cmd);
                System.out.println("_____________________________________________");
                list.add(new Task(cmd));
            }

        }

        scanner.close();
    }
}
