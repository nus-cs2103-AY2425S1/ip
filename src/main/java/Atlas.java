import Commands.Commands;
import java.util.ArrayList;
import java.util.Scanner;

public class Atlas {
    public static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        // @@author patorjk.com
        // Reused from https://patorjk.com/software/taag/#p=display&f=Isometric1&t=Atlas
        // with minor modifications
        String logo = """
                     ___           ___           ___       ___           ___
                    /\\  \\         /\\  \\         /\\__\\     /\\  \\         /\\  \\
                   /::\\  \\        \\:\\  \\       /:/  /    /::\\  \\       /::\\  \\
                  /:/\\:\\  \\        \\:\\  \\     /:/  /    /:/\\:\\  \\     /:/\\ \\  \\
                 /::\\~\\:\\  \\       /::\\  \\   /:/  /    /::\\~\\:\\  \\   _\\:\\~\\ \\  \\
                /:/\\:\\ \\:\\__\\     /:/\\:\\__\\ /:/__/    /:/\\:\\ \\:\\__\\ /\\ \\:\\ \\ \\__\\
                \\/__\\:\\/:/  /    /:/  \\/__/ \\:\\  \\    \\/__\\:\\/:/  / \\:\\ \\:\\ \\/__/
                     \\::/  /    /:/  /       \\:\\  \\        \\::/  /   \\:\\ \\:\\__\\
                     /:/  /     \\/__/         \\:\\  \\       /:/  /     \\:\\/:/  /
                    /:/  /                     \\:\\__\\     /:/  /       \\::/  /
                    \\/__/                       \\/__/     \\/__/         \\/__/
                """;

        System.out.println("Hello from\n" + logo);
        Atlas.greet();

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextCommandLine = scanner.nextLine();
            String command = nextCommandLine.split(" ")[0].toUpperCase();
            try {
                // Solution below inspired by https://stackoverflow.com/questions/10387329/using-string-representations-of-enum-values-in-switch-case
                switch (Commands.valueOf(command)) {
                    case BYE:
                        Atlas.exit();
                        return;
                    case LIST:
                        listTaskItems(taskList, nextCommandLine);
                        break;
                    case MARK:
                        markItem(taskList, nextCommandLine);
                        break;
                    case UNMARK:
                        unmarkItem(taskList, nextCommandLine);
                        break;
                    case TODO:
                        addToDo(taskList, nextCommandLine);
                        break;
                    case DEADLINE:
                        addDeadline(taskList, nextCommandLine);
                        break;
                    case EVENT:
                        addEvent(taskList, nextCommandLine);
                        break;
                    case DELETE:
                        deleteTask(taskList, nextCommandLine);
                        break;
                    default:
                        throw new AtlasException("Unknown command.");
                }
            } catch (AtlasException e) {
                Atlas.print(e.getMessage());
            }
        }
    }

    // Prints messages with lines underneath them
    public static void print(String message) {
        System.out.println('\n' + Atlas.LINE);
        System.out.println(message);
        System.out.println(Atlas.LINE + '\n');
    }

    public static void greet() {
        Atlas.print("Hello! I'm Atlas\n" + "What can I do for you?");
    }

    public static void exit() {
        Atlas.print("Bye. Hope to see you again soon!");
    }

    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void listTaskItems(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split(" ");
        if (commandsArray.length > 1) {
            throw new AtlasException("To view the list, the list command should not be called with any additional arguments.");
        }

        if (taskList.isEmpty()) {
            Atlas.print("No items have been added to the task list.");
        } else {
            StringBuilder listOutput = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                listOutput.append(String.format("%d: ", i + 1)).append(taskList.get(i));
                if (i < taskList.size() - 1) {
                    listOutput.append('\n');
                }
            }
            Atlas.print(listOutput.toString());
        }
    }

    public static void markItem(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split(" ");
        if (commandsArray.length == 1) {
            throw new AtlasException("Marking a task as done requires the task number.");
        } else if (!isNumber(commandsArray[1])) {
            throw new AtlasException("Task number provided is not a number.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Marking a task as done only requires the task number without any additional arguments.");
        }

        int markIndex = Integer.parseInt(commandsArray[1]) - 1;
        if (markIndex < 0 || markIndex >= taskList.size()) {
            throw new AtlasException("Task number does not exist.");
        }

        Task taskToBeMarked = taskList.get(markIndex);
        taskToBeMarked.setIsDone();
        Atlas.print(String.format("Nice! I've marked this task as done:\n \t%s", taskToBeMarked));
    }

    public static void unmarkItem(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split(" ");
        if (commandsArray.length == 1) {
            throw new AtlasException("Unmarking a task as undone requires the task number.");
        } else if (!isNumber(commandsArray[1])) {
            throw new AtlasException("Task number provided is not a number.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Unmarking a task as undone only requires the task number without any additional arguments.");
        }

        int unmarkIndex = Integer.parseInt(commandsArray[1]) - 1;
        if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) {
            throw new AtlasException("Task number does not exist.");
        }

        Task taskToBeUnmarked = taskList.get(unmarkIndex);
        taskToBeUnmarked.setIsDone();
        taskList.get(unmarkIndex).setIsNotDone();
        Atlas.print(String.format("OK, I've marked this task as not done yet:\n \t%s", taskToBeUnmarked));
    }

    public static void addTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
        String addMessage = String.format("Got it. I've added this task:\n\t%s\n Now you have %s tasks in the list.", task, taskList.size());
        Atlas.print(addMessage);
    }

    public static void deleteTask(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split(" ");
        if (commandsArray.length == 1) {
            throw new AtlasException("Deleting a task as undone requires the task number.");
        } else if (!isNumber(commandsArray[1])) {
            throw new AtlasException("Task number provided is not a number.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Deleting a task only requires the task number without any additional arguments.");
        }

        int deleteIndex = Integer.parseInt(commandsArray[1]) - 1;
        if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
            throw new AtlasException("Task number does not exist.");
        }

        Task task = taskList.get(deleteIndex);
        taskList.remove(deleteIndex);
        String addMessage = String.format("Noted. I've removed this task:\n\t%s\n Now you have %s tasks in the list.", task, taskList.size());
        Atlas.print(addMessage);
    }

    public static void addToDo(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split("todo ");
        if (commandsArray.length <= 1) {
            throw new AtlasException("The description of a todo cannot be empty.");
        }

        ToDo todo = new ToDo(commandsArray[1]);
        Atlas.addTask(taskList, todo);
    }

    public static void addDeadline(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split("deadline | /by ");
        if (commandsArray.length == 1) {
            throw new AtlasException("The description of a deadline cannot be empty.");
        } else if (commandsArray.length < 3) {
            throw new AtlasException("A deadline requires a description and a date, in that order.");
        }

        Deadline deadline = new Deadline(commandsArray[1], commandsArray[2]);
        Atlas.addTask(taskList, deadline);
    }

    public static void addEvent(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split("event | /from | /to ");
        if (commandsArray.length == 1) {
            throw new AtlasException("The description of an event cannot be empty.");
        } else if (commandsArray.length < 4) {
            throw new AtlasException("An event requires a description, start time and end time, in that order.");
        }

        Event event = new Event(commandsArray[1], commandsArray[2], commandsArray[3]);
        Atlas.addTask(taskList, event);
    }
}
