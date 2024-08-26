import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

enum Command {
    BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, UNKNOWN;

    static Command fromString(String input) {
        String commandString = input.toUpperCase();
        try {
            return valueOf(commandString);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
public class Assistinator {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            ArrayList<Task> tasks = Storage.loadTasks();
            String command = "";

            System.out.println("______________________________________________");
            System.out.println("Hello! I'm Assistinator");
            System.out.println("What can I do for you?");
            System.out.println("______________________________________________");

            while(!Objects.equals(command, "bye")) {
                command = input.nextLine();
                System.out.println("______________________________________________");
                    Command cmd = Command.fromString(command.split(" ")[0]);
                    switch (cmd) {
                        case BYE:
                            System.out.println("Bye. Hope to see you again soon!");
                            break;
                        case LIST:
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + "." + tasks.get(i).toString());
                            }
                            break;
                        case MARK:
                        case UNMARK:
                            int index;
                            try {
                                index = Integer.parseInt(command.split(" ")[1]) - 1;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new AssitinatorExceptions("Please follow format: " + command.split(" ")[0] + " {task number}");
                            }
                            if (index >= 0 && index < tasks.size()) {
                                if (cmd == Command.MARK) {
                                    tasks.get(index).markAsDone();
                                } else {
                                    tasks.get(index).markAsUndone();
                                }
                                for (int i = 0; i < tasks.size(); i++) {
                                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                                }
                                Storage.saveTasks(tasks);
                            }
                            break;
                        case TODO:
                            String description = command.substring(command.indexOf(' ') + 1);
                            if (description.equalsIgnoreCase("todo")) {
                                throw new AssitinatorExceptions("Please follow format: todo {task description}");
                            }
                            tasks.add(new Todo(description));
                            System.out.println("Task added successfully");
                            System.out.printf("Number of Tasks: %d%n", tasks.size());
                            Storage.saveTasks(tasks);
                            break;
                        case DEADLINE:
                            String[] deadlineDesc = command.split(" /");
                            if (deadlineDesc.length != 2 || deadlineDesc[1].equals("by")) {
                                throw new AssitinatorExceptions(
                                        "Please follow format: deadline {task description} /by {deadline}"
                                );
                            }
                            tasks.add(
                                    new Deadline(
                                            deadlineDesc[0].substring(deadlineDesc[0].indexOf(' ') + 1),
                                            deadlineDesc[1].substring(deadlineDesc[1].indexOf(' ') + 1)
                                    )
                            );
                            System.out.println("Task added successfully");
                            System.out.printf("Number of Tasks: %d%n", tasks.size());
                            Storage.saveTasks(tasks);
                            break;
                        case EVENT:
                            String[] eventDesc = command.split(" /");
                            if (eventDesc.length != 3 || eventDesc[1].equals("from") || eventDesc[2].equals("to")) {
                                throw new AssitinatorExceptions(
                                        "Please follow format: event {task description} /from {start} /to {end}"
                                );
                            }
                            tasks.add(
                                    new Event(
                                            eventDesc[0].substring(eventDesc[0].indexOf(' ') + 1),
                                            eventDesc[1].substring(eventDesc[1].indexOf(' ') + 1),
                                            eventDesc[2].substring(eventDesc[2].indexOf(' ') + 1)
                                    )
                            );
                            System.out.println("Task added successfully");
                            System.out.printf("Number of Tasks: %d%n", tasks.size());
                            Storage.saveTasks(tasks);
                            break;
                        case DELETE:
                            int deleteIndex;
                            try {
                                deleteIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new AssitinatorExceptions("Please follow format: delete {task number}");
                            }
                            tasks.remove(deleteIndex);
                            System.out.printf(
                                    "Task %d deleted successfully. Number of Tasks: %d%n", deleteIndex + 1, tasks.size()
                            );
                            Storage.saveTasks(tasks);
                            break;
                        default:
                            throw new AssitinatorExceptions("Command does not exist");
                    }
                System.out.println("______________________________________________");
            }
        } catch (AssitinatorExceptions e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
