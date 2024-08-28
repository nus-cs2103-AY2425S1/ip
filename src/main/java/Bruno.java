import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bruno {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Task> taskList = new ArrayList<>();
    enum TaskType {
        TODO, DEADLINE, EVENT
    }
    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public static void main(String[] args) {
        String userResponse;
        String directoryPath = "src/data/";
        String filePath = directoryPath + "bruno.txt";
        
        ensureDirectoryExists(directoryPath);
        ensureFileExists(filePath);

        loadFromFile(filePath);

        printGreetingMessage();

        boolean running = true;
        while (running) {
            userResponse = input.nextLine();
            String firstWord;
            String restOfString = "";

            if (userResponse.contains(" ")) {
                firstWord = userResponse.substring(0, userResponse.indexOf(" "));
                restOfString = userResponse.split(" ", 2)[1];
            } else {
                firstWord = userResponse;
            }

            try {
                if (userResponse.equals("bye")) {
                    running = false;
                    printByeMessage();
                } else if (userResponse.equals("list")) {
                    printList();
                } else if (firstWord.equals("mark")) {
                    markTask(filePath, restOfString);
                } else if (firstWord.equals("unmark")) {
                    unmarkTask(filePath, restOfString);
                } else if (firstWord.equals("delete")) {
                    deleteTask(filePath, restOfString);
                } else if (firstWord.equals("todo")) {
                    addTask(filePath, restOfString, TaskType.TODO);
                } else if (firstWord.equals("deadline")) {
                    addTask(filePath, restOfString, TaskType.DEADLINE);
                } else if (firstWord.equals("event")) {
                    addTask(filePath, restOfString, TaskType.EVENT);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (BrunoException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }

    public static void printGreetingMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bruno\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String filePath, String str, TaskType type) throws BrunoException {
        if (str.trim().isEmpty()) {
            throw new EmptyTaskException();
        }

        Task task = null;
        boolean recognized = true;
        if (type.equals(TaskType.TODO)) {
            task = new ToDo(str);
        } else if (type.equals(TaskType.DEADLINE)) {
            if (!str.contains("/by")) {
                throw new MissingFieldException();
            }
            String description = str.substring(0, str.indexOf("/by")).trim();
            String byString = str.substring(str.indexOf("/by") + 4).trim();
            try {
                LocalDateTime by = LocalDateTime.parse(byString, formatter1);
                task = new Deadline(description, by);
            } catch (DateTimeParseException e) {
                throw new BrunoException("Invalid date format. Please use 'yyyy-MM-dd HH:mm'");
            }
        } else if (type.equals(TaskType.EVENT)) {
            if (!str.contains("/from") || !str.contains("/to")) {
                throw new MissingFieldException();
            }
            String description = str.substring(0, str.indexOf("/from")).trim();
            String fromString = str.substring(str.indexOf("/from") + 6, str.indexOf("/to")).trim();
            String toString = str.substring(str.indexOf("/to") + 4).trim();
            try {
                LocalDateTime from = LocalDateTime.parse(fromString, formatter1);
                LocalDateTime to = LocalDateTime.parse(toString, formatter1);
                task = new Event(description, from, to);
            } catch (DateTimeParseException e) {
                throw new BrunoException("Invalid date format. Please use 'yyyy-MM-dd HH:mm'");
            }
        } else {
            recognized = false;
        }

        if (recognized) {
            taskList.add(task);
            updateFile(filePath);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:\n" + task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            throw new UnknownCommandException();
        }
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTask(String filePath, String num) throws BrunoException {
        try {
            Task task = taskList.get(Integer.parseInt(num) - 1);
            task.complete();
            updateFile(filePath);
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:\n" + task);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public static void unmarkTask(String filePath, String num) throws BrunoException {
        try {
            Task task = taskList.get(Integer.parseInt(num) - 1);
            task.uncomplete();
            updateFile(filePath);
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've unmarked this task as done:\n" + task);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public static void deleteTask(String filePath, String num) throws BrunoException {
        try {
            Task task = taskList.remove(Integer.parseInt(num) - 1);
            updateFile(filePath);
            System.out.println("____________________________________________________________");
            System.out.println("Noted! I've removed this task:\n" + task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public static void updateFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String listAsString = "";
            for (Task task : taskList) {
                listAsString += task.toString() + "\n";
            }
            fw.write(listAsString);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadFromFile(String filePath) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String line = s.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] lineParts = line.split("\\|");

                String type = lineParts[0].trim();
                boolean done = lineParts[1].trim().charAt(1) == 'X';
                String description = lineParts[2].trim();

                if (type.equals("T")) {
                    taskList.add(new ToDo(description, done));
                } else if (type.equals("D")) {
                    String byString = lineParts[3].substring(lineParts[3].indexOf("by:") + 3).trim();
                    LocalDateTime by = LocalDateTime.parse(byString, formatter2);
                    taskList.add(new Deadline(description, by, done));
                } else if (type.equals("E")) {
                    String fromString = lineParts[3].substring(6, lineParts[3].indexOf("to")).trim();
                    String toString = lineParts[3].substring(lineParts[3].indexOf("to") + 3).trim();
                    LocalDateTime from = LocalDateTime.parse(fromString, formatter2);
                    LocalDateTime to = LocalDateTime.parse(toString, formatter2);
                    taskList.add(new Event(description, from, to, done));
                } else {
                    System.out.println("There was a problem when loading some task");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created to store data file");
            }
        }
    }

    public static void ensureFileExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Data file created");
                }
            } catch (IOException e) {
                System.out.println("Something went wrong when creating the data file");
            }
        }
    }
}