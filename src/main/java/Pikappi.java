import java.util.Scanner;

public class Pikappi {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Pika! I'm Pikappi!\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");
        Scanner reader = new Scanner(System.in);
        String command;
        String[] tasks = new String[100];
        int numTasks = 0;
        while (true) {
            command = reader.nextLine();
            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Pi-kapi! See you again~\n");
                System.out.println("____________________________________________________________");
                return;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                if (numTasks == 0) {
                    System.out.println("Pika-ka! You have no tasks!");
                }
                for (int i = 0; i < numTasks; i++) {
                    if (tasks[i] != null) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    } else {
                        break;
                    }
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + command);
                tasks[numTasks] = command;
                numTasks++;
                System.out.println("____________________________________________________________");
            }
        }
    }
}
