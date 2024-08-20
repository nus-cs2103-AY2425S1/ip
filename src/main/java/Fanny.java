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

            //condition to exit when cmd is bye
            if (cmd.equals("bye")) {
                System.out.println("_____________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________________________");
                break;
            } else if (cmd.equals("list")) {
                System.out.println("Fanny: ");
                list.printList();
            } else if (cmd.equals("mark")) {
                int taskId = scanner.nextInt();
                System.out.println("Fanny: ");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.markAsDone(taskId));
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
