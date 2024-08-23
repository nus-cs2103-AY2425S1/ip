import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Assistinator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String command = "";

        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Assistinator");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");

        while(!Objects.equals(command, "bye")) {
            command = input.nextLine();
            System.out.println("______________________________________________");
            try {
                if (command.startsWith("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (Objects.equals(command, "list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1)+ "." + tasks.get(i).toString());
                    }
                } else if (command.startsWith("mark")) {
                    int index;
                    try {
                        index = Integer.parseInt(command.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AssitinatorExceptions("Please follow format: mark {task number}");
                    }
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1)+ "." + tasks.get(i).toString());
                        }
                    }
                } else if (command.startsWith("unmark")) {
                    int index;
                    try {
                        index = Integer.parseInt(command.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AssitinatorExceptions("Please follow format: unmark {task number}");
                    }
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsUndone();
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1)+ "." + tasks.get(i).toString());
                        }
                    }
                } else if (command.startsWith("todo")) {
                    String description = command.substring(command.indexOf(' ') + 1);
                    if (description.equals("todo")) {
                        throw new AssitinatorExceptions("Please follow format: todo {task description}");
                    }
                    tasks.add(new Todo(command.substring(command.indexOf(' ') + 1)));
                    System.out.println("Task added successfully");
                    System.out.printf("Number of Tasks: %d%n", tasks.size());
                } else if (command.startsWith("deadline")) {
                    String[] description = command.split(" /");
                    if (description.length != 2 || description[1].equals("by")) {
                        throw new AssitinatorExceptions(
                                "Please follow format: deadline {task description} /by {deadline}"
                        );
                    }
                    tasks.add(
                            new Deadline(
                                description[0].substring(
                                        description[0].indexOf(' ') + 1
                                ),
                                description[1].substring(
                                        description[1].indexOf(' ') + 1
                                )
                            )
                    );
                    System.out.println("Task added successfully");
                    System.out.printf("Number of Tasks: %d%n", tasks.size());
                } else if (command.startsWith("event")) {
                    String[] description = command.split(" /");
                    if (description.length != 3 ||
                            description[1].equals("from") || description[2].equals("to")) {
                        throw new AssitinatorExceptions(
                                "Please follow format: event {task description} /from {start} /to {end}"
                        );
                    }
                    tasks.add(
                            new Event(
                                description[0].substring(
                                        description[0].indexOf(' ') + 1
                                ),
                                description[1].substring(
                                        description[1].indexOf(' ') + 1
                                ),
                                description[2].substring(
                                        description[2].indexOf(' ') + 1
                                )
                            )
                    );
                    System.out.println("Task added successfully");
                    System.out.printf("Number of Tasks: %d%n", tasks.size());
                } else if (command.startsWith("delete")) {
                    int index;
                    try {
                        index = Integer.parseInt(command.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AssitinatorExceptions("Please follow format: delete {task number}");
                    }
                    tasks.remove(index);
                    System.out.printf(
                            "Task %d deleted successfully. Number of Tasks: %d%n", index + 1, tasks.size()
                    );
                } else {
                    throw new AssitinatorExceptions("Command does not exist");
                }
            } catch (AssitinatorExceptions e) {
                System.out.println(e.getMessage());
            }
            System.out.println("______________________________________________");
        }
    }
}
