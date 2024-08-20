import java.util.Objects;
import java.util.Scanner;

public class Assistinator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] tasks = new String[100];
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
                    System.out.println((i + 1)+ ". " + tasks[i]);
                }
            } else {
                tasks[count] = command;
                System.out.println("added: " + command);
                count++;
            }
            System.out.println("______________________________________________");
        }
    }
}
