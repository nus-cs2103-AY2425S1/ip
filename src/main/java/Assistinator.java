import java.util.Objects;
import java.util.Scanner;

public class Assistinator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;
        String command = "";

        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Assistinator");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");

        while(!Objects.equals(command, "bye")) {
            command = input.nextLine();
            System.out.println("______________________________________________");
            if (Objects.equals(command, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (Objects.equals(command, "list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1)+ "." + tasks[i].toString());
                }
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                if (index >= 0 && index < count) {
                    tasks[index].markAsDone();
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1)+ "." + tasks[i].toString());
                    }
                }
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                if (index >= 0 && index < count) {
                    tasks[index].markAsUndone();
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1)+ "." + tasks[i].toString());
                    }
                }
            } else {
                tasks[count] = new Task(command);
                System.out.println("added: " + command);
                count++;
            }
            System.out.println("______________________________________________");
        }
    }
}
