import java.util.ArrayList;
import java.util.Scanner;

public class Luna {

    enum Command {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        INVALID
    }

    public static void main(String[] args) {
        String greetings = "Hello! I'm Luna\n" +
                "What can I do for you?";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean start = true;

        while (start) {
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

                String[] str = input.split(" ", 2);
                String commandStr = str[0];
                Command command;

                try {
                    command = Command.valueOf(commandStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    command = Command.INVALID;
                }

                switch (command) {
                    case BYE:
                        String exit = "Bye! Hope to see you again soon!";
                        System.out.println(exit);
                        start = false;
                        break;

                    case LIST:
                        System.out.println("Here are the tasks in your list:");

                        for (int i = 0; i < tasks.size(); i++) {
                            String taskStr = String.format("%d.%s",
                                    i + 1, tasks.get(i).toString());

                            System.out.println(taskStr);
                        }
                        break;

                    case MARK:
                        if (str.length == 1) {
                            throw new LunaException("Indicate the task number to mark as done e.g. mark 2");
                        }

                        int taskToMark;

                        try {
                            taskToMark = Integer.parseInt(str[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new LunaException("Invalid task reference. Use integers only.");
                        }

                        if (taskToMark >= tasks.size() || taskToMark < 0) {
                            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
                        }

                        tasks.get(taskToMark).markAsDone();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(taskToMark).toString());
                        break;

                    case UNMARK:
                        if (str.length == 1) {
                            throw new LunaException("Indicate the task number to unmark e.g. unmark 2");
                        }

                        int taskToUnmark;

                        try {
                            taskToUnmark = Integer.parseInt(str[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new LunaException("Invalid task reference. Use integers only.");
                        }

                        if (taskToUnmark >= tasks.size() || taskToUnmark < 0) {
                            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
                        }

                        tasks.get(taskToUnmark).unmark();

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(taskToUnmark).toString());
                        break;

                    case DELETE:
                        if (str.length == 1) {
                            throw new LunaException("Indicate the task number to delete e.g. delete 2");
                        }

                        int taskToDelete;

                        try {
                            taskToDelete = Integer.parseInt(str[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new LunaException("Invalid task reference. Use integers only.");
                        }

                        if (taskToDelete >= tasks.size() || taskToDelete < 0) {
                            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
                        }

                        Task removed = tasks.remove(taskToDelete);

                        System.out.println("Noted, I've removed this task:\n" +
                                "  " + removed.toString() + "\n" +
                                "Now you have " + tasks.size() + " tasks in the list.");
                        break;

                    case TODO:
                        if (str.length == 1 || str[1].trim().isEmpty()) {
                            throw new LunaException("Enter description for todo e.g. todo [description]");
                        }

                        ToDo todo = new ToDo(str[1]);
                        tasks.add(todo);

                        String s = String.format("Now you have %d tasks in the list.", tasks.size());
                        System.out.println("Got it. I've added this task:\n" +
                                "  " + todo + "\n" + s);
                        break;

                    case DEADLINE:
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

                        Deadline deadlineTask = new Deadline(deadline[0], deadline[1].substring(3));
                        tasks.add(deadlineTask);

                        String deadlineString = String.format("Now you have %d tasks in the list.", tasks.size());
                        System.out.println("Got it. I've added this task:\n" +
                                "  " + deadlineTask + "\n" + deadlineString);
                        break;

                    case EVENT:
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

                        Event eventTask = new Event(event[0],
                                event[1].substring(5),
                                event[2].substring(3));
                        tasks.add(eventTask);

                        String eventString = String.format("Now you have %d tasks in the list.", tasks.size());
                        System.out.println("Got it. I've added this task:\n" +
                                "  " + eventTask + "\n" + eventString);
                        break;

                    case INVALID:
                        throw new LunaException("""
                                        Invalid Command!
                                        Please enter one of the following commands:
                                        "todo", "deadline", "event"
                                        "mark", "unmark", "delete"
                                        "bye"
                                        """);
                }
            } catch (LunaException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
