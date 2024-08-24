import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.loadTasks());
        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();

            try {
                if (input.isEmpty()) {
                    throw new LunaException("""
                            Please enter one of the following commands:
                            "todo", "deadline", "event"
                            "mark", "unmark", "delete"
                            "list", "bye"
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
                    isRunning = false;
                    break;

                case LIST:
                    tasks.list();
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

                    tasks.markTaskAsDone(taskToMark);
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

                    tasks.unmarkTask(taskToUnmark);
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

                    tasks.deleteTask(taskToDelete);
                    break;

                case TODO:
                    if (str.length == 1 || str[1].trim().isEmpty()) {
                        throw new LunaException("Enter description for todo e.g. todo [description]");
                    }

                    Todo todo = new Todo(str[1]);
                    tasks.addTask(todo);
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
                                "e.g. deadline [task] /by [dd/MM/yyyy HH:mm]");
                    }

                    LocalDateTime deadlineDateTime;

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        deadlineDateTime = LocalDateTime.parse(deadline[1].substring(3), formatter);

                        if (deadlineDateTime.isBefore(LocalDateTime.now())) {
                            throw new LunaException("Invalid task: Deadline is before current time");
                        }
                    } catch (DateTimeParseException e) {
                        throw new LunaException("Enter deadline using format: dd/MM/yyyy HH:mm. " +
                                "eg. 14/02/2024 14:30");
                    }

                    Deadline deadlineTask = new Deadline(deadline[0], deadlineDateTime);
                    tasks.addTask(deadlineTask);
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
                                "event [task] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
                    }

                    LocalDateTime startTime;
                    LocalDateTime endTime;

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        startTime = LocalDateTime.parse(event[1].substring(5), formatter);
                        endTime = LocalDateTime.parse(event[2].substring(3), formatter);

                        if (startTime.isAfter(endTime)) {
                            throw new LunaException("Invalid Event: Start is after End");
                        }

                        if (startTime.isBefore(LocalDateTime.now())) {
                            throw new LunaException("Invalid Event: Start is before current time");
                        }
                    } catch (DateTimeParseException e) {
                        throw new LunaException("Enter start and end time using format: dd/MM/yyyy HH:mm. " +
                                "eg. 14/02/2024 14:30");
                    }

                    Event eventTask = new Event(event[0], startTime, endTime);
                    tasks.addTask(eventTask);
                    break;

                case INVALID:
                    throw new LunaException("""
                                    Invalid Command!
                                    Please enter one of the following commands:
                                    "todo", "deadline", "event"
                                    "mark", "unmark", "delete"
                                    "list", "bye"
                                    """);
                }
            } catch (LunaException e) {
                System.out.println(e.getMessage());
            } finally {
                storage.saveTasks(tasks.getTasks());
            }
        }
        scanner.close();
    }
}
