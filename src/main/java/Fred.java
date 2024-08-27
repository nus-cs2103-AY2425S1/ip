import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Fred {
    static String line = "____________________________________________________________";
    static String name = "Fred";
    static ArrayList<Task> taskList = new ArrayList<>();
    static File dataFile;

    public static void main(String[] args) {
        dataFile = getDataFile();
        loadFromDataFile();
        greet();
        getInput();
    }

    public static void say(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    private static void greet() {
        String message = "Hello! I'm " + name + "\n" +
                "What can I do for you?";
        say(message);
    }

    private static void sayFarewell() {
        String message = "Bye. Hope to see you again soon!";
        say(message);
    }

    private static void exit(){
        System.exit(0);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            say(input);
        }
        sayFarewell();
        exit();
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            try {
                determineAction(input);
            } catch (Exception e) {
                say(e.getMessage());
            }
        }
    }

    private static void determineAction(String input) throws FredException {
        input = input.strip();
        String[] inputParts = input.split(" ", 2);
        if (inputParts.length == 1) {
            if (inputParts[0].isEmpty()) {
                throw new EmptyInputException();
            } else if (inputParts[0].equals("bye")) {
                sayFarewell();
                exit();
            } else if (inputParts[0].equals("list")) {
                printTaskList();
            } else if (inputParts[0].equals("mark") || inputParts[0].equals("unmark")) {
                throw new InvalidTaskNumberException();
            } else if (inputParts[0].equals("todo") || inputParts[0].equals("deadline") || inputParts[0].equals("event")) {
                throw new EmptyTaskDescriptionException();
            } else {
                throw new UnknownCommandException();
            }
        } else if (inputParts.length == 2) {
            String message;
            inputParts[1] = inputParts[1].strip();
            if (inputParts[0].equals("mark") || inputParts[0].equals("unmark") || inputParts[0].equals("delete")) {
                int taskNumber;
                Task task;
                try {
                    taskNumber = Integer.parseInt(inputParts[1]) - 1;
                    task = taskList.get(taskNumber);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new InvalidTaskNumberException();
                }
                if (inputParts[0].equals("mark")) {
                    markTaskAsDone(task, taskNumber);
                    message = String.format("Nice! I've marked this task as done:\n" +
                            "   %s", taskList.get(taskNumber));
                } else if (inputParts[0].equals("unmark")) {
                    markTaskAsNotDone(task, taskNumber);
                    message = String.format("OK, I've marked this task as not done yet:\n" +
                            "   %s", taskList.get(taskNumber));
                } else {
                    deleteFromTaskList(task);
                    message = String.format("Noted. I've removed this task:\n" +
                            "   %s", task);
                }
                rewriteDataFile();
                say(message);
            } else if (inputParts[0].equals("todo") || inputParts[0].equals("deadline") || inputParts[0].equals("event")) {
                if (inputParts[1].isEmpty()) {
                    throw new EmptyTaskDescriptionException();
                }
                Task task = createTask(inputParts[0], inputParts[1]);
                addToTaskList(task);
                message = String.format("Got it. I've added this task:\n" +
                        "   %s\n" +
                        "Now you have %d tasks in the list.", task, taskList.size());
                appendToDataFile(task);
                say(message);
            } else {
                throw new UnknownCommandException();
            }
        }
    }

    private static Task createTask(String taskType, String taskDetails) {
        Task task;
        System.out.println(taskDetails);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] taskDetailsArr = taskDetails.split(" /", 3);
        String description = taskDetailsArr[0];
        if (taskType.equals("todo")) {
            task = new Todo(description);
        } else if (taskType.equals("deadline")) {
            String byStr = taskDetailsArr[1].substring(3);
            LocalDateTime by = LocalDateTime.parse(byStr, formatter);
            task = new Deadline(description, by);
        } else {
            String fromStr = taskDetailsArr[1].substring(5);
            String toStr = taskDetailsArr[2].substring(3);
            LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
            LocalDateTime to = LocalDateTime.parse(toStr, formatter);
            task = new Event(description, from, to);
        }
        return task;
    }

    private static void addToTaskList(Task task) {
        taskList.add(task);
    }

    private static void printTaskList() {
        int index = 1;
        System.out.println(line);
        for (Task task : taskList) {
            System.out.println(String.format("%s.%s", index++, task));
        }
        System.out.println(line);
    }

    private static void markTaskAsDone(Task task, int taskNumber) {
        task.markAsDone();
    }

    private static void markTaskAsNotDone(Task task, int taskNumber) {
        task.markAsNotDone();
    }

    private static void deleteFromTaskList(Task task) {
        taskList.remove(task);
    }

    private static File getDataFile() {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists() || !dataDirectory.isDirectory()) {
            dataDirectory.mkdir();
        }
        File dataFile = new File("./data/fred.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataFile;
    }

    private static void loadFromDataFile() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineParts = line.split(" \\| ");
            Task task = null;
            switch (lineParts[0]) {
            case "T":
                task = createTask("todo", lineParts[2]);
                break;
            case "D":
                task = createTask("deadline", lineParts[2] + " /by " + lineParts[3]);
                break;
            case "E":
                String[] fromTo = lineParts[3].split(" - ");
                task = createTask("event", lineParts[2] + " /from " + fromTo[0] + " /to " + fromTo[1]);
                break;
            }
            addToTaskList(task);
            if (lineParts[1].equals("1")) {
                task.markAsDone();
            } else if (lineParts[1].equals("0")) {
                task.markAsNotDone();
            }
        }
    }

    private static void rewriteDataFile() {
        try {
            FileWriter writer = new FileWriter(dataFile);
            for (Task task : taskList) {
                writer.write(task.getDataFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendToDataFile(Task task) {
        try {
            FileWriter writer = new FileWriter(dataFile, true);
            writer.write(task.getDataFormat() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

