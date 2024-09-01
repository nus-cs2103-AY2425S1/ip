<<<<<<< HEAD
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


=======
import java.util.Scanner;
import sigma.*;
@SuppressWarnings("FieldMayBeFinal")
>>>>>>> branch-more-oop
public class Sigma {
//    public static ArrayList<Task> items = new ArrayList<>();
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

//    private FileWriter writer;

    public Sigma(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser = new Parser();
    }

<<<<<<< HEAD
    // convert lines read from file to Task objects
    public static void readTasksFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("data/sigma.txt"));
            // handle empty file case
            if (lines.isEmpty()) {
                return;
            }
            for (String line : lines) {
                // parse each line
                String[] parts = line.split(" ", 2); // Split at the first space
                String status = parts[0];
                String taskName = parts[1];
                boolean isDone = status.equals("[X]");
                Task task = new Task(taskName, isDone);
                items.add(task);
            }
        } catch (IOException e) {
            System.err.println("error reading lines from file: " + e.getMessage());
        }
    }

    public static String toPrettyList(List<Task> itemsArray) throws IOException {
//        StringBuilder itemsFromFile = new StringBuilder();
//        for (String line: Files.readAllLines(Paths.get("data/sigma.txt"))) {
//            itemsFromFile.append(line);
//        }
//        StringBuilder itemsFromFile = new StringBuilder();
        int i = 1;
//        for (String line: Files.readAllLines(Paths.get("data/sigma.txt"))) {
//            itemsFromFile.append(i).append(". ").append(line).append("\n");
//            i++;
//        }
//        if (itemsArray.isEmpty()) {
//            return "";
//        }
        StringBuilder result = new StringBuilder(); // this is a terrible time complexity
        for (Task item : itemsArray) {
            result.append(i).append(". ").append(item.toString()).append("\n");
            i++;
        }
        return result.toString();
    }

    // update this also
    public static void handleMarkUnmark(String userInput) {
        Pattern pattern = Pattern.compile("(mark|unmark) (\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String action = matcher.group(1);
            int taskNumber = Integer.parseInt(matcher.group(2)) - 1;

            if (taskNumber >= 0 && taskNumber < items.size()) {
                Task task = items.get(taskNumber);
                if (action.equals("mark")) {
                    task.setStatus(true);
                    System.out.println("task marked as done:\n" + task);
                } else if (action.equals("unmark")) {
                    task.setStatus(false);
                    System.out.println("task unmarked:\n" + task);
                }
            }
        }
    }

    // need to update this
    public static void handleDelete(String userInput) {
        Pattern pattern = Pattern.compile("(delete) (\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            int taskNumber = Integer.parseInt(matcher.group(2)) - 1;
            if (taskNumber >= 0 && taskNumber < items.size()) {
                Task task = items.get(taskNumber);
                items.remove(task);
                System.out.println("task removed:\n" + task.toString() + "\nNow you have " + items.size() + " tasks in the list");
            }
        }
    }

    public static String stringToDate(String date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        return LocalDateTime.parse(date, formatter);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedDateTime = LocalDateTime.parse(date, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
        return parsedDateTime.format(outputFormatter);
    }

    public static void main(String[] args) throws IOException {
//        items = new ArrayList<>();
=======
    public void run() {
>>>>>>> branch-more-oop
        Scanner scanner = new Scanner(System.in);
        storage.readTasksFromFile();

        System.out.println(ui.welcome());
        System.out.println(TaskList.toPrettyList()); // kiv

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                System.out.println(TaskList.toPrettyList());
                continue;
            }

            if (userInput.equals("bye")) {
                parser.goodbye();
                break;
            }

            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                parser.handleMarkUnmark(userInput);
                continue;
            }
            if (userInput.startsWith("delete")) {
                taskList.handleDelete(userInput);
                continue;
            }
            if (userInput.startsWith("todo")) {
                parser.handleTodo(userInput);
                continue;
            }
            if (userInput.startsWith("deadline")) {
<<<<<<< HEAD
                Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
                Matcher matcher = pattern.matcher(userInput);
                if (matcher.find()) {
                    String description = matcher.group(1);
                    String by = matcher.group(2);
                    String dateBy = stringToDate(by);
                    Task task = new Deadline(description, false, dateBy);
                    items.add(task);
                    try {
                        writer.write(task + "\n");
                    } catch (IOException exception) {
                        System.err.println("an error occurred writing to file");
                    }
                    System.out.println("added deadline task:\n  [D][ ] " + description + " (by: " + dateBy + ")");
                } else {
                    System.out.println("is the deadline in the room with us? let's try again");
                }
=======
                parser.handleDeadline(userInput);
>>>>>>> branch-more-oop
                continue;
            }
            if (userInput.startsWith("event")) {
<<<<<<< HEAD
                Pattern pattern = Pattern.compile("event (.+) /from (.+) /to (.+)");
                Matcher matcher = pattern.matcher(userInput);
                if (matcher.find()) {
                    String description = matcher.group(1);
                    String from = matcher.group(2);
                    String fromDate = stringToDate(from);
                    String to = matcher.group(3);
                    String toDate = stringToDate(to);
                    Task task = new Event(description, false, fromDate, toDate);
                    items.add(task);
                    try {
                        writer.write(task + "\n");
                    } catch (IOException e) {
                        System.err.println("an error occurred writing to file: " + e.getMessage());
                    }
                    System.out.println("added event task:\n  [E][ ] " + description + " (from: " + fromDate + " to: " + toDate + ")");
                } else {
                    System.out.println("bro really thinks bro can make an empty event and get away with it, lets try again");
                }
=======
                parser.handleEvent(userInput);
>>>>>>> branch-more-oop
                continue;
            }
            ui.dontRecognise();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Sigma sigma = new Sigma("data/sigma.txt");
        sigma.run();
    }
}
