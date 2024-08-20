import java.util.Scanner;

public class Luna {

    public static void main(String[] args) {
        String greetings = "Hello! I'm Luna\n" +
                "What can I do for you?";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskNum = 0;

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.isEmpty()) {
                    throw new LunaException("""
                            Please enter one of the task type followed by the task description:
                            "todo": todo return book
                            "deadline": deadline return book /by Sunday
                            "event": event project meeting /from Mon 2pm /to 4pm
                            """);
                }

                if (input.equals("bye")) {
                    String exit = "Bye! Hope to see you again soon!";
                    System.out.println(exit);
                    break;
                }

                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskNum; i++) {
                        String taskStr = String.format("%d.%s",
                                i + 1, tasks[i].toString());
                        System.out.println(taskStr);
                    }
                } else {
                    String[] str = input.split(" ", 2);
                    String command = str[0];

                    if (command.equals("mark")) {
                        if (str.length == 1) {
                            throw new LunaException("Indicate the task number to mark as done e.g. mark 2");
                        }

                        int i = Integer.parseInt(str[1]) - 1;

                        if (i >= taskNum || i < 0) {
                            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
                        }

                        tasks[i].markAsDone();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[i].toString());

                    } else if (command.equals("unmark")) {
                        if (str.length == 1) {
                            throw new LunaException("Indicate the task number to unmark e.g. unmark 2");
                        }

                        int i = Integer.parseInt(str[1]) - 1;

                        if (i >= taskNum || i < 0) {
                            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
                        }

                        tasks[i].unmark();

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[i].toString());

                    } else {
                        Task task = null;

                        switch (command) {
                            case "todo":
                                if (str.length == 1) {
                                    throw new LunaException("Enter description for todo e.g. todo [description]");
                                }

                                task = new ToDo(str[1]);
                                tasks[taskNum] = task;
                                break;

                            case "deadline":
                                if (str.length == 1 || str[1].trim().indexOf("/") == 0) {
                                    throw new LunaException("Enter description for deadline e.g. " +
                                                            "deadline return book /by Sunday");
                                }

                                String[] deadline = str[1].split(" /");

                                if (deadline.length == 1) {
                                    throw new LunaException("Enter deadline for task " +
                                                            "e.g. deadline [task] /by [deadline]");
                                }

                                if (!deadline[1].contains("by ") && deadline[1].length() <= 3) {
                                    throw new LunaException("Enter deadline for task " +
                                                            "e.g. deadline [task] /by [deadline]");
                                }

                                task = new Deadline(deadline[0], deadline[1].substring(3));
                                tasks[taskNum] = task;
                                break;

                            case "event":
                                if (str.length == 1 || str[1].trim().indexOf("/") == 0) {
                                    throw new LunaException("Enter description for event e.g. " +
                                                            "event project meeting /from Mon 2pm /to 4pm");
                                }

                                if (!str[1].contains("/from ") || !str[1].contains("/to ")) {
                                    throw new LunaException("Enter start and end time for event " +
                                                            "e.g. event [task] /from [startTime] /to [endTime]");
                                }

                                String[] event = str[1].split(" /");

                                if (!(event[1].contains("from ") && event[1].length() > 5) ||
                                    !(event[2].contains("to ") && event[2].length() > 3)) {
                                    throw new LunaException("Enter start and end time for event using the format: " +
                                                            "event [task] /from [startTime] /to [endTime]");
                                }

                                task = new Event(event[0],
                                        event[1].substring(5),
                                        event[2].substring(3));
                                tasks[taskNum] = task;
                                break;

                            default:
                                throw new LunaException("""
                                        Invalid Command!
                                        Please enter one of the task type followed by the task description:
                                        "todo": todo return book
                                        "deadline": deadline return book /by Sunday
                                        "event": event project meeting /from Mon 2pm /to 4pm
                                        """);
                        }

                        taskNum++;
                        String s = String.format("Now you have %d tasks in the list.", taskNum);
                        System.out.println("Got it. I've added this task:\n" +
                                "  " + task.toString() + "\n" + s);
                    }
                }
            } catch (LunaException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();

    }
}
