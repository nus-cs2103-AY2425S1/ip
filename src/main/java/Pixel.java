import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class Pixel {
    public final static String DIRECTORY_PATH = "../data";
    public final static String FILE_PATH = DIRECTORY_PATH + "/pixel.txt";
    public static String LINE = "\t" + "------------------------------------";
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void printLine(String line) {
        System.out.println(LINE);
        System.out.println("\t" + line);
        System.out.println(LINE);
    }
    public static void ensureFileExists() {
        File file = new File(FILE_PATH);
        File directory = new File(file.getParent());

        // Create directory if it does not exist
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                printLine("New directory created: " + directory.getPath());
            } else {
                printLine("Failed to create new directory: " + directory.getPath());
            }
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    printLine("New file created: " + file.getPath());
                } else {
                    printLine("Failed to create new file: " + file.getPath());
                }
            } catch (IOException e) {
                printLine("Error creating new File");
            }
        }
    }
    public static void createListFromCurrentData() throws FileNotFoundException {
        ensureFileExists();
        File file = new File(FILE_PATH); // create a File for the given file path
        Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
        while (scanner.hasNext()) {
            String dataLine = scanner.nextLine();
            String[] inputs = dataLine.split("\\|", 0);
            String taskType = inputs[0].trim();
            String doneStatus = inputs[1].trim();
            String taskDescription = inputs[2].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            switch(taskType) {
            case "T":
                ToDo currentTask = new ToDo(taskDescription);
                if (doneStatus.equals("1")) {
                    currentTask.markAsDone();
                }
                tasks.add(currentTask);
                break;
            case "D":
                LocalDateTime deadline = LocalDateTime.parse(inputs[3].trim(), formatter);
                Deadline currentDeadline = new Deadline(taskDescription, deadline);
                if (doneStatus.equals("1")) {
                    currentDeadline.markAsDone();
                }
                tasks.add(currentDeadline);
                break;
            case "E":
                String fromToDate = inputs[3].trim();
                String[] fromToSplit = fromToDate.split("to");
                LocalDateTime from = LocalDateTime.parse(fromToSplit[0].trim(), formatter);
                LocalDateTime to = LocalDateTime.parse(fromToSplit[1].trim(), formatter);
                Event currentEvent = new Event(taskDescription, from, to);
                if (doneStatus.equals("1")) {
                    currentEvent.markAsDone();
                }
                tasks.add(currentEvent);
                break;
            default:
                System.out.println("\t" + "There is an error in the file!");
            }
        }
    }

    public static void updateFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task currentTask : tasks) {
                fileWriter.write(currentTask.getFileString()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            printLine("Something went wrong: " + e.getMessage());
        }
    }

    public static void printFile() {
        try {
            File file = new File(FILE_PATH); // create a File for the given file path
            Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
            System.out.println(LINE);
            while (scanner.hasNext()) {
                System.out.println("\t" + scanner.nextLine());
            }
            System.out.println(LINE);
        } catch (FileNotFoundException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but I can't find the data for the ToDos!");
            System.out.println("\t" + "Please specify the correct file path.");
            System.out.println(LINE);
        }
    }
    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println("\t" + "Hello! I'm Pixel!");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void printExit() {
        System.out.println(LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void printList() {
        System.out.println(LINE);
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int index = 1; index <= tasks.size(); index++) {
            System.out.println("\t" + index + "." + tasks.get(index-1));
        }
        System.out.println(LINE);
    }

    public static void printAddConfirmation(String newTaskDescription) {
        System.out.println(LINE);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + newTaskDescription);
        System.out.println("\t" + "Now you have " + tasks.size() + " task(s) in the list.");
        System.out.println(LINE);
    }

    public static void processResponse() throws
            InvalidCommandException,
            EmptyDescriptionException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("list")) {
                printList();
            } else if (command.equals("file")) {
                printFile();
            } else if (command.startsWith("todo")) {
                String description = command.replace("todo", "").trim();
                if (description.equals("")) {
                    throw new EmptyDescriptionException("Description not found");
                } else {
                    ToDo newToDo = new ToDo(description);
                    tasks.add(newToDo);
                    updateFile();
                    printAddConfirmation(newToDo.toString());
                }
            } else if (command.startsWith("deadline")) {
                String[] stringArray = command.split("/by", 0);
                String description = stringArray[0].replace("deadline", "").trim();
                String byString = stringArray[1].replace("by", "").trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime by = LocalDateTime.parse(byString, formatter);
                Deadline newDeadline = new Deadline(description, by);
                tasks.add(newDeadline);
                updateFile();
                printAddConfirmation(newDeadline.toString());
            } else if (command.startsWith("event")) {
                String[] stringArray = command.split("/from", 0);
                String description = stringArray[0].replace("event", "").trim();
                String fromAndToString = stringArray[1].trim();
                String[] fromAndToArray = fromAndToString.split("/to", 0);
                String fromString = fromAndToArray[0].trim();
                String toString = fromAndToArray[1].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime from = LocalDateTime.parse(fromString, formatter);
                LocalDateTime to = LocalDateTime.parse(toString, formatter);

                Event newEvent = new Event(description, from, to);
                tasks.add(newEvent);
                updateFile();
                printAddConfirmation(newEvent.toString());
            } else if (command.startsWith("mark")) {
                String[] stringArray = command.split(" ", 0);
                Task currentTask = tasks.get(Integer.parseInt(stringArray[1]) - 1);
                currentTask.markAsDone();
                updateFile();
                System.out.println(LINE);
                System.out.println("\t" + "Nice! I've marked this task as done:");
                System.out.println("\t  " + currentTask);
                System.out.println(LINE);
            } else if (command.startsWith("unmark")) {
                String[] stringArray = command.split(" ", 0);
                Task currentTask = tasks.get(Integer.parseInt(stringArray[1]) - 1);
                currentTask.markAsUndone();
                updateFile();
                System.out.println(LINE);
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                System.out.println("\t  " + currentTask);
                System.out.println(LINE);
            } else if (command.startsWith("delete")) {
                String[] stringArray = command.split(" ", 0);
                Integer index = Integer.parseInt(stringArray[1]) - 1;
                Task removedTask = tasks.get(index);
                tasks.remove(removedTask);
                updateFile();
                System.out.println(LINE);
                System.out.println("\t" + "Noted. I've removed this task:");
                System.out.println("\t  " + removedTask);
                System.out.println("\t" + "Now you have " + tasks.size() + " task(s) in the list.");
                System.out.println(LINE);
            } else if (command.equals("bye")) {
                printExit();
                break;
            } else {
                throw new InvalidCommandException("Command not found");
            }
        }
    }
    public static void main(String[] args) {
        try {
            createListFromCurrentData();
            printGreeting();
            processResponse();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but there is no such index that exists");
            System.out.println("\t" + "Type in a valid index!");
            System.out.println(LINE);
        } catch (InvalidCommandException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but I don't know what that means");
            System.out.println("\t" + "Type in a valid command!");
            System.out.println(LINE);
        } catch (EmptyDescriptionException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but I can't add a task if the description is empty!");
            System.out.println("\t" + "Type in a valid description!");
            System.out.println(LINE);
        } catch (FileNotFoundException e) {
            System.out.println(LINE);
            System.out.println("\t" + "I'm sorry, but I can't find the data for the ToDos!");
            System.out.println("\t" + "Please specify the correct file path.");
            System.out.println(LINE);
        }
    }
}
