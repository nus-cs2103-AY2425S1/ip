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
//                tasks[count] = new Task(command);
//                System.out.println("added: " + command);
//                count++;
                if (command.startsWith("todo")) {
                    tasks[count] = new Todo(command);
                    System.out.println("Task added successfully");
                    count++;
                    System.out.printf("Number of Tasks: %d%n", count);
                } else if (command.startsWith("deadline")) {
                    String[] description = command.split(" /");
                    tasks[count] = new Deadline(
                            description[0].substring(
                                    description[0].indexOf(' ') + 1
                            ),
                            description[1].substring(
                                    description[1].indexOf(' ') + 1
                            )
                    );
                    System.out.println("Task added successfully");
                    count++;
                    System.out.printf("Number of Tasks: %d%n", count);
                } else if (command.startsWith("event")) {
                    String[] description = command.split(" /");
                    tasks[count] = new Event(
                            description[0].substring(
                                    description[0].indexOf(' ') + 1
                            ),
                            description[1].substring(
                                    description[1].indexOf(' ') + 1
                            ),
                            description[2].substring(
                                    description[2].indexOf(' ') + 1
                            )
                    );
                    System.out.println("Task added successfully");
                    count++;
                    System.out.printf("Number of Tasks: %d%n", count);
                }
            }
            System.out.println("______________________________________________");
        }
    }
}
