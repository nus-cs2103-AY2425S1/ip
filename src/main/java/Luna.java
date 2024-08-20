import java.util.ArrayList;
import java.util.Scanner;

public class Luna {

    public static void main(String[] args) {
        String greetings = "Hello! I'm Luna\n" +
                "What can I do for you?";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.isEmpty()) {
                    throw new LunaException("""
                            Please enter one of the following commands:
                            "todo", "deadline", "event"
                            "mark", "unmark", "delete"
                            "bye"
                            """);
                }

                if (input.equals("bye")) {
                    String exit = "Bye! Hope to see you again soon!";
                    System.out.println(exit);
                    break;
                }

                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < tasks.size(); i++) {
                        String taskStr = String.format("%d.%s",
                                i + 1, tasks.get(i).toString());

                        System.out.println(taskStr);
                    }
                } else {
                    String[] str = input.split(" ", 2);
                    String command = str[0];

                    if (command.equals("mark")) {
                        if (str.length == 1) {
                            throw new LunaException("Indicate the task number to mark as done e.g. mark 2");
                        }

                        int i;

                        try {
                            i = Integer.parseInt(str[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new LunaException("Invalid task reference. Use integers only.");
                        }

                        if (i >= tasks.size() || i < 0) {
                            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
                        }

                        tasks.get(i).markAsDone();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(i).toString());

                    } else if (command.equals("unmark")) {
                        if (str.length == 1) {
                            throw new LunaException("Indicate the task number to unmark e.g. unmark 2");
                        }

                        int i;

                        try {
                            i = Integer.parseInt(str[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new LunaException("Invalid task reference. Use integers only.");
                        }

                        if (i >= tasks.size() || i < 0) {
                            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
                        }

                        tasks.get(i).unmark();

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(i).toString());

                    } else if (command.equals("delete")) {
                        if (str.length == 1) {
                            throw new LunaException("Indicate the task number to delete e.g. delete 2");
                        }

                        int i;

                        try {
                            i = Integer.parseInt(str[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new LunaException("Invalid task reference. Use integers only.");
                        }

                        if (i >= tasks.size() || i < 0) {
                            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
                        }

                        Task removed = tasks.remove(i);

                        System.out.println("Noted, I've removed this task:\n" +
                                           "  " + removed.toString() + "\n" +
                                           "Now you have 4 tasks in the list.");
                    } else {
                        Task task;

                        switch (command) {
                            case "todo":
                                if (str.length == 1 || str[1].trim().isEmpty()) {
                                    throw new LunaException("Enter description for todo e.g. todo [description]");
                                }

                                task = new ToDo(str[1]);
                                tasks.add(task);
                                break;

                            case "deadline":
                                if (str.length == 1 || str[1].trim().isEmpty() || str[1].trim().indexOf("/") == 0) {
                                    throw new LunaException("Enter description for deadline e.g. " +
                                                            "deadline return book /by Sunday");
                                }

                                String[] deadline = str[1].split(" /");

                                if (deadline.length == 1) {
                                    throw new LunaException("Enter deadline for task " +
                                                            "e.g. deadline [task] /by [deadline]");
                                }

                                if (!deadline[1].contains("by ") || deadline[1].trim().length() <= 2) {
                                    throw new LunaException("Enter deadline for task " +
                                                            "e.g. deadline [task] /by [deadline]");
                                }

                                task = new Deadline(deadline[0], deadline[1].substring(3));
                                tasks.add(task);
                                break;

                            case "event":
                                if (str.length == 1 || str[1].trim().isEmpty() || str[1].trim().indexOf("/") == 0) {
                                    throw new LunaException("Enter description for event e.g. " +
                                                            "event project meeting /from Mon 2pm /to 4pm");
                                }

                                if (!str[1].contains("/from ") || !str[1].contains("/to ")) {
                                    throw new LunaException("Enter start and end time for event " +
                                                            "e.g. event [task] /from [startTime] /to [endTime]");
                                }

                                String[] event = str[1].split(" /");

                                if (!(event[1].contains("from ") && event[1].trim().length() > 5) ||
                                    !(event[2].contains("to ") && event[2].trim().length() > 3)) {
                                    throw new LunaException("Enter start and end time for event using the format: " +
                                                            "event [task] /from [startTime] /to [endTime]");
                                }

                                task = new Event(event[0],
                                        event[1].substring(5),
                                        event[2].substring(3));
                                tasks.add(task);
                                break;

                            default:
                                throw new LunaException("""
                                        Invalid Command!
                                        Please enter one of the following commands:
                                        "todo", "deadline", "event"
                                        "mark", "unmark", "delete"
                                        "bye"
                                        """);
                        }

                        String s = String.format("Now you have %d tasks in the list.", tasks.size());
                        System.out.println("Got it. I've added this task:\n" +
                                "  " + task + "\n" + s);
                    }
                }
            } catch (LunaException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();

    }
}
