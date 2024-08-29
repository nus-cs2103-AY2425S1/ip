import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Talker {
    private static final String NAME = "Talker";
    private static final String LINE = "____________________________________________________________";
    private static ArrayList<Task> list = new ArrayList<>();
    private static Path directoryPath = Paths.get("./data");
    private static Path filePath = Paths.get("./data/talker.txt");
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static Ui ui = new Ui(NAME);

    public static void main(String[] args) {

        try {
            readFile();
        } catch (TalkerException e) {
            ui.printError(e);
        }

        ui.printWelcome();
        Scanner reader = new Scanner(System.in);
        // read user input
        String input = reader.nextLine();
        ui.printLine();

        // if command "bye" entered, exit
        while (!input.equals("bye")) {
            try {
                manageInputs(input);
                writeFile();
            } catch (TalkerException e) {
                ui.printError(e);
            } finally {
                ui.printLine();
                input = reader.nextLine();
                ui.printLine();
            }
        }
        ui.printGoodBye();
    }

    public static void printTasksOn(String date) throws TalkerException {
        LocalDate targetDate;
        try {
            String[] parsed = date.split(" ");
            targetDate = LocalDate.parse(parsed[1], INPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new TalkerException("Incorrect date format. Try again with: yyyy/MM/dd");
        }
        ui.printTasksOn(targetDate.format(OUTPUT_FORMAT));
        for (Task task: list) {
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getDeadline().toLocalDate();
                if (targetDate.isBefore(deadline) || targetDate.isEqual(deadline)) {
                    if (!task.isComplete()) {
                        ui.printTask(task);
                    }
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                LocalDate start = eventTask.getFrom().toLocalDate();
                LocalDate end = eventTask.getTo().toLocalDate();
                if ((targetDate.isAfter(start) || targetDate.isEqual(start))
                        && (targetDate.isBefore(end) || targetDate.isEqual(end))) {
                    if (!task.isComplete()) {
                        ui.printTask(task);
                    }
                }
            }
        }
    }

    public static void readFile() throws TalkerException {
        try {
            if (Files.exists(directoryPath) &&
                    Files.isDirectory(directoryPath) &&
                    Files.exists(filePath) &&
                    Files.isRegularFile(filePath)) {
                Scanner scanner = new Scanner(filePath);
                while (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    readTaskFromFile(taskString);
                }
            }
        } catch (IOException e) {
            throw new TalkerException("Unable to read file. Error occurred: " + e.getMessage());
        }
    }

    public static void readTaskFromFile(String taskString) throws TalkerException{
        String[] parsed = taskString.split(" \\| ");
        boolean isComplete;

        if (parsed[1].equals("X") || parsed[1].equals(" ")) {
            isComplete = parsed[1].equals("X");
        } else {
            throw new TalkerException("Invalid completion tag, corrupted file detected.");
        }
        switch (parsed[0]) {
        case "T":
            if (parsed.length != 3) {
                throw new TalkerException("Invalid ToDo Task, corrupted file detected.");
            }
            list.add(new ToDo(parsed[2], isComplete));
            break;
        case "D":
            if (parsed.length != 4) {
                throw new TalkerException("Invalid Deadline Task, corrupted file detected.");
            }
            list.add(new Deadline(parsed[2], parsed[3], isComplete));
            break;
        case "E":
            if (parsed.length != 5) {
                throw new TalkerException("Invalid Event Task, corrupted file detected.");
            }
            list.add(new Event(parsed[2], parsed[3], parsed[4], isComplete));
            break;
        default:
            throw new TalkerException("Invalid task type, corrupted file detected.");
        }

    }


    private static void manageInputs(String input) throws TalkerException {
        String[] parsed = input.split(" ");
        switch (parsed[0]) {
        case "list":
            listTasks();
            break;
        case "mark":
            markTaskComplete(parsed);
            break;
        case "unmark":
            unmarkTaskComplete(parsed);
            break;
        case "delete":
            deleteTask(parsed);
            break;
        case "todo":
            createToDo(input);
            break;
        case "deadline":
            createDeadline(input);
            break;
        case "event":
            createEvent(input);
            break;
        case "date":
            printTasksOn(input);
            break;
        default:
            throw new TalkerException("Unknown command!");
        }
    }

    private static void writeFile() throws TalkerException {
        try {
            if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
                Files.createFile(filePath);
            }
            FileWriter fileWriter = new FileWriter(filePath.toString());
            String[] taskList = Talker.getTaskListToSave();
            for (int i = 0; i < taskList.length; i++) {
                fileWriter.write(taskList[i]);
                if (i < taskList.length - 1) {
                    fileWriter.write(System.lineSeparator());
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new TalkerException("Unable to write to file. Error occurred: " + e.getMessage());
        }
    }

    private static String[] getTaskListToSave() {
        int size = Talker.list.size();
        String[] taskList = new String[size];
        for (int i = 0; i < size; i++) {
            taskList[i] = Talker.list.get(i).getSaveFormat();
        }
        return taskList;
    }

    private static void listTasks() throws TalkerException {
        if (list.isEmpty()) {
            throw new TalkerException("List is empty!");
        }
        ui.printTaskList(list);
    }

    private static void markTaskComplete(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            list.get(index).mark();
            ui.printTask(list.get(index));
        } catch (NumberFormatException e) {
            throw new TalkerException("Mark format wrong. Try again with: mark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
        throw new TalkerException("Task not found!");
        }
    }

    private static void unmarkTaskComplete(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            list.get(index).unmark();
            ui.printTask(list.get(index));
        } catch (NumberFormatException e) {
            throw new TalkerException("Unmark format wrong. Try again with: unmark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    private static void deleteTask(String[] parsed) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            Task removed = list.remove(index);
            ui.printTaskDelete(removed, list.size());
        } catch (NumberFormatException e) {
            throw new TalkerException("Delete format wrong. Try again with: delete <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    private static void createToDo(String input) throws TalkerException {
        try {
            String desc = input.substring(5);
            Task newTask = new ToDo(desc);
            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException("ToDo format wrong. Try again with: todo <description>");
        }
    }

    private static void createDeadline(String input) throws TalkerException {
        try {
            String contents = input.substring(9);

            String[] parsed = contents.split(" /by ", 2);
            String desc = parsed[0];
            String by = parsed[1];

            Task newTask = new Deadline(desc, by);
            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Deadline format wrong. Try again with: deadline <description> /by <dd-MM-yyyy HH:mm>");
        }
    }

    private static void createEvent(String input) throws TalkerException {
        try {
            String contents = input.substring(6);

            String[] parsed1 = contents.split(" /from ", 2);
            String[] parsed2 = parsed1[1].split(" /to ", 2);
            String desc = parsed1[0];
            String from = parsed2[0];
            String to = parsed2[1];

            Task newTask = new Event(desc, from, to);

            list.add(newTask);
            ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Event format wrong. Try again with: event <description> " +
                            "/from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>");
        }
    }
}

