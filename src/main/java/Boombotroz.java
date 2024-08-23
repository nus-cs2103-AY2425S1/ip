import java.util.Scanner;

public class Boombotroz {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Boombotroz" +
                "\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {

                    // print all task using static method in Task class
                    Task.printAll();
                    input = scanner.nextLine();

                } else if (input.startsWith("mark ")) {

                    // mark task using static method in Task class
                    Task.mark_task(input);
                    input = scanner.nextLine();

                } else if (input.startsWith("unmark ")) {

                    // unmark task using static method in Task class
                    Task.unmark_task(input);
                    input = scanner.nextLine();

                } else if (input.startsWith("delete ")) {

                    // delete task using static method in Task class
                    Task.delete_task(input);
                    input = scanner.nextLine();

                } else if (input.startsWith("todo ")) {
                    String toDoTask = input.substring(5);

                    // if there is no task to do
                    if (toDoTask.isEmpty()) {
                        throw new BoombotrozException(
                                "You can't do nothing !!");
                    }

                    Task created_task = new ToDo(false, toDoTask);
                    input = scanner.nextLine();

                } else if (input.startsWith("deadline ")) {
                    String dlTaskTime = input.substring(9);

                    // if there is no task
                    if (dlTaskTime.isEmpty()) {
                        throw new BoombotrozException(
                                "You can't do nothing !!");
                    }

                    // if there is no deadline
                    if (!dlTaskTime.contains(" /by ")) {
                        throw new BoombotrozException(
                                "You need a deadline !!");
                    }

                    String dlTask = dlTaskTime.split(" /by ")[0];
                    String time = dlTaskTime.split(" /by ")[1];
                    Task created_task = new Deadline(false, dlTask, time);
                    input = scanner.nextLine();

                } else if (input.startsWith("event ")) {
                    String eventTaskTime = input.substring(6);

                    // if there is no task
                    if (eventTaskTime.isEmpty()) {
                        throw new BoombotrozException(
                                "You can't do nothing !!");
                    }

                    // if there is not both a start and end time
                    if (!eventTaskTime.contains(" /from ")
                            || !eventTaskTime.contains(" /to ")) {
                        throw new BoombotrozException(
                                "You need a start and end !!");
                    }
                    String eventTask = eventTaskTime.split(" /from ")[0];
                    String time_start = eventTaskTime.split(" /from ")[1]
                            .split(" /to ")[0];
                    String time_end = eventTaskTime.split(" /from ")[1]
                            .split(" /to ")[1];
                    Task created_task = new Event(false, eventTask,
                            time_start, time_end);
                    input = scanner.nextLine();

                } else {
                    throw new BoombotrozException("That's nonsense !!");
                }

            } catch (BoombotrozException e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();

            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

