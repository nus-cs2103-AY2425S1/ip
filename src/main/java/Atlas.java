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
                      ___           ___           ___       ___           ___    \s
                     /\\  \\         /\\  \\         /\\__\\     /\\  \\         /\\  \\   \s
                    /::\\  \\        \\:\\  \\       /:/  /    /::\\  \\       /::\\  \\  \s
                   /:/\\:\\  \\        \\:\\  \\     /:/  /    /:/\\:\\  \\     /:/\\ \\  \\ \s
                  /::\\~\\:\\  \\       /::\\  \\   /:/  /    /::\\~\\:\\  \\   _\\:\\~\\ \\  \\\s
                 /:/\\:\\ \\:\\__\\     /:/\\:\\__\\ /:/__/    /:/\\:\\ \\:\\__\\ /\\ \\:\\ \\ \\__\\
                 \\/__\\:\\/:/  /    /:/  \\/__/ \\:\\  \\    \\/__\\:\\/:/  / \\:\\ \\:\\ \\/__/
                      \\::/  /    /:/  /       \\:\\  \\        \\::/  /   \\:\\ \\:\\__\\ \s
                      /:/  /     \\/__/         \\:\\  \\       /:/  /     \\:\\/:/  / \s
                     /:/  /                     \\:\\__\\     /:/  /       \\::/  /  \s
                     \\/__/                       \\/__/     \\/__/         \\/__/   \s
                """;

        System.out.println("Hello from\n" + logo);
        Atlas.greet();

        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String nextCommandLine = scanner.nextLine();
            String[] commandsArray = nextCommandLine.split(" ");
            String command = commandsArray[0];
            switch (command) {
                case "bye":
                    Atlas.exit();
                    return;
                case "list":
                    StringBuilder listOutput = new StringBuilder();
                    for (int i = 0; i < taskList.size(); i++) {
                        listOutput.append(String.format("%d: ", i + 1)).append(taskList.get(i));
                        if (i < taskList.size() - 1) {
                            listOutput.append('\n');
                        }
                    }
                    Atlas.print(listOutput.toString());
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(commandsArray[1]) - 1;
                    Task taskToBeMarked = taskList.get(markIndex);
                    taskToBeMarked.setIsDone();
                    Atlas.print(String.format("Nice! I've marked this task as done:\n \t%s", taskToBeMarked));
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(commandsArray[1]) - 1;
                    Task taskToBeUnmarked = taskList.get(unmarkIndex);
                    taskToBeUnmarked.setIsDone();
                    taskList.get(unmarkIndex).setIsNotDone();
                    Atlas.print(String.format("OK, I've marked this task as not done yet:\n \t%s", taskToBeUnmarked));
                    break;
                case "todo":
                    String todoName = String.format("%s %s", commandsArray[1], commandsArray[2]);
                    ToDo todo = new ToDo(todoName);
                    Atlas.addTask(taskList, todo);
                    break;
                case "deadline":
                    String deadlineName = String.format("%s %s", commandsArray[1], commandsArray[2]);
                    String deadlineTime = String.join(" ", Arrays.copyOfRange(commandsArray, 4, commandsArray.length));
                    Deadline deadline = new Deadline(deadlineName, deadlineTime);
                    Atlas.addTask(taskList, deadline);
                    break;
                case "event":
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
                    break;
                default:
                    break;
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
        Atlas.print("Hello! I'm Atlas \n" + "What can I do for you?");
    }

    public static void exit() {
        Atlas.print("Bye. Hope to see you again soon!");
    }

    public static void addTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
        String addMessage = String.format("Got it. I've added this task: \n\t%s\n Now you have %s tasks in the list.", task, taskList.size());
        Atlas.print(addMessage);
    }
}
