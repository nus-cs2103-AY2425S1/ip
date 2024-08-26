import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sora {
    private static final String HORIZONTAL_LINE = "---------------------------------------------------";
    private static final String FILENAME = "savedtasks.txt";
    private final List<Task> taskList;
    private boolean isLive;

    public Sora() {
        this.taskList = new ArrayList<>();
        this.isLive = true;
    }

    public static void main(String[] args) {
        Sora sora = new Sora();
        Scanner commandScanner = new Scanner(System.in);
        sora.loadTaskList();

        System.out.println(greeting());

        while (sora.isLive) {
            ArrayList<String> parsedCommand = parse(commandScanner.nextLine().trim());

            String mainCommand = parsedCommand.get(0).toLowerCase();
            try {
                switch (mainCommand) {
                case "bye":
                    System.out.println(farewell());
                    sora.isLive = false;
                    break;
                case "list":
                    sora.displayList();
                    break;
                case "mark":
                    sora.markTask(parsedCommand.get(1));
                    break;
                case "unmark":
                    sora.unmarkTask(parsedCommand.get(1));
                    break;
                case "todo":
                    sora.addTask("todo", parsedCommand);
                    break;
                case "deadline":
                    sora.addTask("deadline", parsedCommand);
                    break;
                case "event":
                    sora.addTask("event", parsedCommand);
                    break;
                case "delete":
                    sora.deleteTask(parsedCommand.get(1));
                    break;
                case "":
                    throw new SoraException("\tPlease Enter a Command\n" + HORIZONTAL_LINE);
                default:
                    System.out.println("\tSora doesn't understand! Please Try Again!");
                }
                System.out.println(HORIZONTAL_LINE);
            } catch (SoraException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String greeting() {
        return HORIZONTAL_LINE + "\n" + "\tHello! I'm Sora!\n\tWhat can I do for you?\n" + HORIZONTAL_LINE;
    }

    private static String farewell() {
        return "\tBye. Hope to see you again soon!";
    }

    private static ArrayList<String> parse(String command) {
        ArrayList<String> parsedResult = new ArrayList<>();
        String[] parse_1 = command.split(" ", 2);
        parsedResult.add(parse_1[0]);
        if (parse_1.length > 1) {
            String[] parse_2 = parse_1[1].split(" /", 3);
            parsedResult.addAll(Arrays.stream(parse_2).toList());
        }
        return parsedResult;
    }

    private void displayList() {
        if (this.taskList.isEmpty()) {
            System.out.println("\tSeems like there are no tasks!");
            return;
        }
        for (Task t : this.taskList) {
            System.out.println("\t" + t.toString());
        }
    }

    private void markTask(String value) throws SoraException {
        try {
            Task task = this.taskList.get(Integer.parseInt(value) - 1);
            if (task.isDone) {
                System.out.println("\tSora has discovered this task is already done!");
                return;
            }
            task.markAsDone();
            saveTaskList();
            System.out.println("\tNice! Sora has marked this task as done:");
            System.out.println("\t" + task);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Mark (int)\n" + HORIZONTAL_LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + HORIZONTAL_LINE);
        }
    }

    private void unmarkTask(String value) throws SoraException {
        try {
            Task task = this.taskList.get(Integer.parseInt(value) - 1);
            if (!task.isDone) {
                System.out.println("\tSora has discovered this task is already not done!");
                return;
            }
            task.markAsNotDone();
            saveTaskList();
            System.out.println("\tOk, Sora has unmarked this task as not done:");
            System.out.println("\t" + task);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Mark (int)\n" + HORIZONTAL_LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + HORIZONTAL_LINE);
        }
    }

    private void addTask(String mainCommand, ArrayList<String> parsedCommand) throws SoraException {
        switch (mainCommand) {
            case "todo":
                try {
                    this.taskList.add(new ToDo(parsedCommand.get(1)));
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Todo (Description)");
                }
            case "deadline":
                try {
                    this.taskList.add(new Deadline(parsedCommand.get(1), parsedCommand.get(2).substring(3)));
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Deadline (Description) /by (by)");
                }
            case "event":
                try {
                    this.taskList.add(new Event(parsedCommand.get(1), parsedCommand.get(2).substring(5), parsedCommand.get(3).substring(3)));
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new SoraException("\tPlease Enter - Event (Description) /from (from) /to (to)");
                }
        }
        saveTaskList();
        System.out.println("\tGot it. Sora has added this task:");
        System.out.println("\t" + taskList.get(taskList.size() - 1));
        System.out.println("\tNow, you have " + taskList.size() + " tasks in your list");
    }

    private void deleteTask(String value) throws SoraException {
        try {
            int index = Integer.parseInt(value) - 1;
            Task deletedTask = this.taskList.remove(index);
            saveTaskList();
            System.out.println("\tNoted. Sora has removed this task:");
            System.out.println("\t" + deletedTask);
        } catch (NumberFormatException e) {
            throw new SoraException("\tPlease Enter - Delete (int)\n" + HORIZONTAL_LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("\tPlease Enter Integer Value within List Size\n" + HORIZONTAL_LINE);
        }
    }

    public String saveTaskString() {
        return taskList.stream()
                .map(Task::getTaskDetails)
                .map(x -> String.join(" | ", x) + "\n")
                .reduce("", (x, y) -> x + y);
    }

    private void saveTaskList() {
        try {
            FileWriter fileWriter = new FileWriter(FILENAME);
            fileWriter.write(saveTaskString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("\tSora is unable to write to file" + HORIZONTAL_LINE);
        }
    }

    private void loadTaskList() {
        File file = new File(FILENAME);
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            Scanner fileScanner = new Scanner(file);
            int lineNumber = 1;
            while (fileScanner.hasNext()) {
                try {
                    loadTaskListHelper(lineNumber, fileScanner.nextLine());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    lineNumber++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadTaskListHelper(int lineNumber, String fileTaskDetails) throws Exception {
        String[] parsedFileTaskDetails = fileTaskDetails.split(" \\| ", 5);
        switch (parsedFileTaskDetails[0]) {
        case "T":
            if (parsedFileTaskDetails.length != 3) {
                throw new Exception("\tSora is unable to read file from line " + String.valueOf(lineNumber));
            }
            taskList.add(new ToDo(parsedFileTaskDetails[2]));
            break;
        case "D":
            if (parsedFileTaskDetails.length != 4) {
                throw new Exception("\tSora is unable to read file from line " + String.valueOf(lineNumber));
            }
            taskList.add(new Deadline(parsedFileTaskDetails[2], parsedFileTaskDetails[3]));
            break;
        case "E":
            if (parsedFileTaskDetails.length != 5) {
                throw new Exception("\tSora is unable to read file from line " + String.valueOf(lineNumber));
            }
            taskList.add(new Event(parsedFileTaskDetails[2], parsedFileTaskDetails[3], parsedFileTaskDetails[4]));
            break;
        }
        if (parsedFileTaskDetails[1].equalsIgnoreCase("X")) {
            try {
                markTask(String.valueOf(lineNumber));
            } catch (SoraException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
