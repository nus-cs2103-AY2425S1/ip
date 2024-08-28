import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> toDoList = new ArrayList<Task>();
    private static int counter = 1;
    private static final String FILE_PATH = "duke.txt";
    private static final String DATA_DIR = "data/";

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            int serial = i + 1;
            Task task = toDoList.get(i);
            System.out.println(serial + "." + task.toString());
        }
    }

    private static void mark(int index) throws InvalidIndexException {
        if (index - 1 < 0 || index - 1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index - 1);
        task.markAsDone();
        replaceLineInFile(index - 1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    private static void unmark(int index) throws InvalidIndexException {
        if (index - 1 < 0 || index - 1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index - 1);
        task.unmarkAsUndone();
        replaceLineInFile(index - 1);
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    private static void delete(int index) throws InvalidIndexException {
        if (index - 1 < 0 || index - 1 >= toDoList.size()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        Task task = toDoList.get(index - 1);
        toDoList.remove(index - 1);
        deleteLineFromFile(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        counter -= 1;
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    private static void writeToFile(Task task) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(DATA_DIR + FILE_PATH, true))) {
            String taskString = convertTaskToString(task);
            writer.write(taskString);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String convertTaskToString(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() +
                    " | " + ((Deadline) task).getBy().toString();
        } else if (task instanceof Event) {
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() +
                    " | " + ((Event) task).getStart().toString() +
                    " | " + ((Event) task).getEnd().toString();
        }
        return "";
    }

    private static Task convertStringToTask(String line) {
        Task task;
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        if (taskType.equals("T")) {
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            task = todo;
        } else if (taskType.equals("D")) {
            String[] removeT = parts[3].split("T", 2);
            String timeToConvert = removeT[0] + " " + removeT[1];
            Deadline deadline = new Deadline(description, convertStringToDate(timeToConvert));
            if (isDone) {
                deadline.markAsDone();
            }
            task = deadline;
        } else {
            String[] removeTFrom = parts[3].split("T", 2);
            String timeToConvertFrom = removeTFrom[0] + " " + removeTFrom[1];
            String[] removeTTo = parts[4].split("T", 2);
            String timeToConvertTo = removeTTo[0] + " " + removeTTo[1];
            Event event = new Event(description, convertStringToDate(timeToConvertFrom),
                    convertStringToDate(timeToConvertTo));
            if (isDone) {
                event.markAsDone();
            }
            task = event;
        }
        return task;
    }

    private static LocalDateTime convertStringToDate(String dateTimeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date-time format. " +
                    "Please provide the date-time in 'yyyy-MM-dd HH:mm' format. " +
                    "Time should be in 24 hours format.");
        }
        return null;
    }

    public static void replaceLineInFile(int index) {
        File inputFile = new File(DATA_DIR + FILE_PATH);
        File tempFile = new File(DATA_DIR + "temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int currentIdx = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentIdx != index) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    String replacedLine = convertTaskToString(toDoList.get(index));
                    writer.write(replacedLine);
                    writer.newLine();
                }
                currentIdx++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }

    public static void deleteLineFromFile(int index) {
        File inputFile = new File(DATA_DIR + FILE_PATH);
        File tempFile = new File(DATA_DIR + "temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int currentIdx = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentIdx != index) {
                    writer.write(currentLine);
                    writer.newLine();
                }
                currentIdx++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }

    private static void loadFile(ArrayList<Task> toDoList) {
        try {
            File fileDir = new File(DATA_DIR);
            if (!fileDir.exists()) {
                boolean isDirCreated = fileDir.mkdir();
            }
            File file = new File(DATA_DIR + FILE_PATH);
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(DATA_DIR + FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                toDoList.add(convertStringToTask(line));
                counter += 1;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Todo parseTodoCommand(String command) {
        try {
            String description = command.substring(4).trim(); // Extract description
            if (description.isEmpty()) {
                throw new IllegalArgumentException("Description for 'todo' cannot be empty.");
            }
            return new Todo(description);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Deadline parseDeadlineCommand(String command) {
        try {
            String[] parts = command.split("/by");
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        "Deadline command must contain '/by' followed by a date-time.");
            }

            String description = parts[0].substring(8).trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException("Description for 'deadline' cannot be empty.");
            }

            String dateTimeString = parts[1].trim();
            LocalDateTime by = convertStringToDate(dateTimeString);
            if (by == null) {
                return null;
            }
            return new Deadline(description, by);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Event parseEventCommand(String command) {
        try {
            String[] partsFrom = command.split("/from");
            if (partsFrom.length != 2) {
                throw new IllegalArgumentException(
                        "Event command must contain '/from' followed by a start date-time.");
            }

            String[] partsTo = partsFrom[1].split("/to");
            if (partsTo.length != 2) {
                throw new IllegalArgumentException(
                        "Event command must contain '/to' followed by an end date-time.");
            }

            String description = partsFrom[0].substring(5).trim();
            if (description.isEmpty()) {
                throw new IllegalArgumentException("Description for 'event' cannot be empty.");
            }

            String fromDateTimeString = partsTo[0].trim(); // Extract start date-time
            String toDateTimeString = partsTo[1].trim(); // Extract end date-time

            LocalDateTime from = convertStringToDate(fromDateTimeString);
            LocalDateTime to = convertStringToDate(toDateTimeString);
            if (from == null || to == null) {
                return null;
            }
            return new Event(description, from, to);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) throws DukeException {
        loadFile(toDoList);
        greet();
        Scanner sc = new Scanner(System.in);
        String command;
        label:
        while (sc.hasNext()) {
            command = sc.nextLine();
            String[] getInstr = command.split(" ", 2);
            String instr = getInstr[0];
            switch (instr) {
            case "mark":
                try {
                    int index;
                    if (getInstr.length <= 1) {
                        index = -1;
                    } else {
                        index = Integer.parseInt(getInstr[1]);
                    }
                    mark(index);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
                break;
            case "unmark":
                try {
                    int index;
                    if (getInstr.length <= 1) {
                        index = -1;
                    } else {
                        index = Integer.parseInt(getInstr[1]);
                    }
                    unmark(index);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
                break;
            case "delete":
                try {
                    int index;
                    if (getInstr.length <= 1) {
                        index = -1;
                    } else {
                        index = Integer.parseInt(getInstr[1]);
                    }
                    delete(index);
                } catch (InvalidIndexException e) {
                    System.out.println(e.toString());
                }
                break;
            case "list":
                printList();
                break;
            case "bye":
                break label;
            case "todo":
                Task todo = parseTodoCommand(command);
                if (todo != null) {
                    toDoList.add(todo);
                    writeToFile(todo);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todo.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    counter += 1;
                }
                break;
            case "deadline":
                Task deadline = parseDeadlineCommand(command);
                if (deadline != null) {
                    toDoList.add(deadline);
                    writeToFile(deadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(deadline.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    counter += 1;
                }
                break;
            case "event":
                Task event = parseEventCommand(command);
                if (event != null) {
                    toDoList.add(event);
                    writeToFile(event);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(event.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    counter += 1;
                }
                break;
            default:
                try {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        sc.close();
        bye();
    }
}