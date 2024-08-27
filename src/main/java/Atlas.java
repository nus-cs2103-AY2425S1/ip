import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

import command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Atlas {
    public static final String LINE = "____________________________________________________________";
    public static final String FILEPATH = "./data/atlas.txt";
    public static void main(String[] args) {
        Atlas.greet();
        ArrayList<Task> taskList = loadTaskList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String nextCommandLine = scanner.nextLine();
            String command = nextCommandLine.split(" ")[0].toUpperCase();
            try {
                // Solution below inspired by https://stackoverflow.com/questions/10387329/using-string-representations-of-enum-values-in-switch-case
                switch (Command.valueOf(command)) {
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
                }
            } catch (AtlasException e) {
                Atlas.print(e.getMessage());
            } catch (IllegalArgumentException e) {
                Atlas.print("Unknown command.");
            }
        }
    }

    public static ArrayList<Task> loadTaskList() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(FILEPATH);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdir();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String regex = Pattern.quote(" | ");
                String[] sections = scanner.nextLine().split(regex);
                Atlas.addTaskFromFile(taskList, sections);
            }

        } catch (FileNotFoundException e) {
            Atlas.print("No saved file.");
        } catch (IOException e) {
            Atlas.print(e.getMessage());
        }

        return taskList;
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
        } else if (isNotNumber(commandsArray[1])) {
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
        Atlas.save(taskList);
        Atlas.print(String.format("Nice! I've marked this task as done:\n \t%s", taskToBeMarked));
    }

    public static void unmarkItem(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split(" ");
        if (commandsArray.length == 1) {
            throw new AtlasException("Unmarking a task as undone requires the task number.");
        } else if (isNotNumber(commandsArray[1])) {
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
        Atlas.save(taskList);
        Atlas.print(String.format("OK, I've marked this task as not done yet:\n \t%s", taskToBeUnmarked));
    }

    public static void addTaskFromFile(ArrayList<Task> taskList, String[] sections) {
        Task task;
        if (sections[0].equals("T")) {
            task = new ToDo(sections[2]);
        } else if (sections[0].equals("D")) {
            task = new Deadline(sections[2], sections[3]);
        } else {
            String[] times = sections[3].split("-");
            task = new Event(sections[2], times[0], times[1]);
        }

        if (sections[1].equals("1")) {
            task.setIsDone();
        }

        taskList.add(task);
    }

    public static void addTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
        Atlas.save(taskList);
        String addMessage = String.format("Got it. I've added this task:\n\t%s\n Now you have %s tasks in the list.", task, taskList.size());
        Atlas.print(addMessage);
    }

    public static void deleteTask(ArrayList<Task> taskList, String nextCommandLine) throws AtlasException {
        String[] commandsArray = nextCommandLine.split(" ");
        if (commandsArray.length == 1) {
            throw new AtlasException("Deleting a task as undone requires the task number.");
        } else if (isNotNumber(commandsArray[1])) {
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
        Atlas.save(taskList);
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

    public static void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write(Atlas.formatTasks(taskList));
            fw.close();
        } catch (IOException e) {
            Atlas.print("Failed to save. " + e.getMessage());
        }
    }

    public static String formatTasks(ArrayList<Task> taskList) {
        StringBuilder listOutput = new StringBuilder();
        for (Task task : taskList) {
            listOutput.append(task.toFileString()).append('\n');
        }

        return listOutput.toString();
    }

    public static void greet() {
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
        Atlas.print("Hello! I'm Atlas\n" + "What can I do for you?");
    }

    public static void print(String message) {
        System.out.println('\n' + Atlas.LINE);
        System.out.println(message);
        System.out.println(Atlas.LINE + '\n');
    }

    public static void exit() {
        Atlas.print("Bye. Hope to see you again soon!");
    }

    public static boolean isNotNumber(String s) {
        try {
            Integer.parseInt(s);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
