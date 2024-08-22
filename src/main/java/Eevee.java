import java.util.ArrayList;
import java.util.Scanner;

public class Eevee {
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

        public static Command enumerate(String command) {
            return Command.valueOf(command.toUpperCase());
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm Eevee\nWhat can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.print(divider + greeting + divider);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            System.out.print(divider);

            try {
                Command command = Command.enumerate(input);
                switch (command) {
                case BYE:
                    System.out.println(exit);
                    return;
                case LIST:
                    if (tasks.isEmpty()) {
                        throw new EeveeException("No tasks yet! Start adding tasks :)");
                    }
                    System.out.println("Here are your tasks:");
                    tasks.forEach((task) ->
                            System.out.println((tasks.indexOf(task) + 1) + ". " + task)
                    );
                    break;
                case MARK: {
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > tasks.size()) {
                        throw new EeveeException("No task under the given task number. Did you type the wrong number?");
                    }
                    Task t = tasks.get(taskNumber - 1);
                    if (t.isDone) {
                        throw new EeveeException("Task has already been marked as done.");
                    }
                    t.markAsDone();
                    System.out.println("Congratulations! I've marked the following task as done:\n  " + t);
                    break;
                }
                case UNMARK: {
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > tasks.size()) {
                        throw new EeveeException("No task under the given task number. Did you type the wrong number?");
                    }
                    Task t = tasks.get(taskNumber - 1);
                    if (!t.isDone) {
                        throw new EeveeException("Task is not marked as done. "
                                + "Needs to be marked done in order to unmark it.");
                    }
                    t.unmarkAsDone();
                    System.out.println("Ok! Task no longer marked as done:\n  " + t);
                    break;
                }
                case DELETE: {
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > tasks.size()) {
                        throw new EeveeException("No task under the given task number. Did you type the wrong number?");
                    }
                    Task t = tasks.get(taskNumber - 1);
                    tasks.remove(taskNumber - 1);
                    System.out.println("As you wish, this task has been removed:\n " + t);
                    break;
                }
                case TODO: {
                    String s = scanner.nextLine().trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( Please input the task details and description correctly");
                    }
                    Todo t = new Todo(s);
                    tasks.add(t);
                    System.out.println("Added the following task to your list:\n" + t);
                    break;
                }
                case DEADLINE: {
                    String s = scanner.nextLine().trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( Please input the task details and description correctly");
                    }
                    String[] info = s.split("/", 2);
                    if (info.length < 2) {
                        throw new EeveeException("Deadline not given for task type 'deadline'. "
                                + "Please input a deadline denoted by '/by' or use task type 'todo' instead.");
                    }
                    Deadline d = new Deadline(info[0], info[1]);
                    tasks.add(d);
                    System.out.println("Added the following task to your list:\n" + d);
                    break;
                }
                case EVENT: {
                    String s = scanner.nextLine().trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( Please input the task details and description correctly");
                    }
                    String[] info = s.split("/", 3);
                    if (info.length < 3) {
                        throw new EeveeException("Event start and/or end timings not provided."
                                + "Please input a start time denoted by '/from' "
                                + "and an end time denoted by '/to' when using task type Event");
                    }
                    Event e = new Event(info[0], info[1], info[2]);
                    tasks.add(e);
                    System.out.println("Added the following task to your list:\n" + e);
                    break;
                }
                default:
                    throw new EeveeException("You seemed to have typed wrong. This is not a valid command.");
                }
                System.out.println("You now have " + tasks.size() + " task(s).");
            } catch (EeveeException e) {
                System.out.println(e.getMessage());
            }
            System.out.print(divider);
        }
    }
}
