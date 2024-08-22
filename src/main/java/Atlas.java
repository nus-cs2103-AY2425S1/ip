import java.util.ArrayList;
import java.util.Arrays;
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
            String[] commandsArray = nextCommandLine.split(" ");
            String command = commandsArray[0];
            try {
                switch (command) {
                    case "bye":
                        Atlas.exit();
                        return;
                    case "list":
                        listTaskItems(taskList, commandsArray);
                        break;
                    case "mark":
                        markItem(taskList, commandsArray);
                        break;
                    case "unmark":
                        unmarkItem(taskList, commandsArray);
                        break;
                    case "todo":
                        addToDo(taskList, commandsArray);
                        break;
                    case "deadline":
                        addDeadline(taskList, commandsArray);
                        break;
                    case "event":
                        addEvent(taskList, commandsArray);
                        break;
                    default:
                        break;
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

    public static void listTaskItems(ArrayList<Task> taskList, String[] commandsArray) throws AtlasException {
        if (commandsArray.length > 1) {
            throw new AtlasException("To view the list, the list command should not be called with any additional arguments.");
        }
        StringBuilder listOutput = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listOutput.append(String.format("%d: ", i + 1)).append(taskList.get(i));
            if (i < taskList.size() - 1) {
                listOutput.append('\n');
            }
        }
        Atlas.print(listOutput.toString());
    }

    public static void markItem(ArrayList<Task> taskList, String[] commandsArray) throws AtlasException {
        if (commandsArray.length == 1) {
            throw new AtlasException("Marking a task as done requires the task number.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Marking a task as done only requires the task number without any additional arguments.");
        }
        int markIndex = Integer.parseInt(commandsArray[1]) - 1;
        Task taskToBeMarked = taskList.get(markIndex);
        taskToBeMarked.setIsDone();
        Atlas.print(String.format("Nice! I've marked this task as done:\n \t%s", taskToBeMarked));
    }

    public static void unmarkItem(ArrayList<Task> taskList, String[] commandsArray) throws AtlasException {
        if (commandsArray.length == 1) {
            throw new AtlasException("Unmarking a task to be undone requires the task number.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Unmarking a task to be undone only requires the task number without any additional arguments.");
        }
        int unmarkIndex = Integer.parseInt(commandsArray[1]) - 1;
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

    public static void addToDo(ArrayList<Task> taskList, String[] commandsArray) throws AtlasException {
        if (commandsArray.length == 1) {
            throw new AtlasException("The description of a todo cannot be empty.");
        }
        String todoName = String.join(" ", Arrays.copyOfRange(commandsArray, 1, commandsArray.length));
        ToDo todo = new ToDo(todoName);
        Atlas.addTask(taskList, todo);
    }

    public static void addDeadline(ArrayList<Task> taskList, String[] commandsArray) throws AtlasException {
        if (commandsArray.length == 1) {
            throw new AtlasException("The description of a deadline cannot be empty.");
        }
        String deadlineName = String.format("%s %s", commandsArray[1], commandsArray[2]);
        String deadlineTime = String.join(" ", Arrays.copyOfRange(commandsArray, 4, commandsArray.length));
        Deadline deadline = new Deadline(deadlineName, deadlineTime);
        Atlas.addTask(taskList, deadline);
    }

    public static void addEvent(ArrayList<Task> taskList, String[] commandsArray) throws AtlasException {
        if (commandsArray.length == 1) {
            throw new AtlasException("The description of a event cannot be empty.");
        }
        String eventName = String.format("%s %s", commandsArray[1], commandsArray[2]);
        String startTime = "", endTime = "";
        for (int i = 4; i < commandsArray.length; i++) {
            if (commandsArray[i].equals("/to")) {
                startTime = String.join(" ", Arrays.copyOfRange(commandsArray, 4, i));
                endTime = String.join(" ", Arrays.copyOfRange(commandsArray, i + 1, commandsArray.length));
            }
        }

        Event event = new Event(eventName, startTime, endTime);
        Atlas.addTask(taskList, event);
    }
}
