import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bob {
    private static final String LINE = "____________________________________________________________";
    private static final String NAME = "Bob";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Bob.readFromFile();
        } catch (ChatBotException e) {
            System.out.println(" Error: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(Bob.LINE);
        System.out.printf("Hello! I'm %s!\n", Bob.NAME);
        System.out.println("What can I do for you?");
        System.out.println(Bob.LINE);

        String phrase = scanner.nextLine();

        while (!phrase.equals("bye")) {
            try {
                System.out.println(Bob.LINE);
                Bob.handleInput(phrase);
                Bob.writeToFile();
            } catch (ChatBotException e) {
                System.out.println(" Error: " + e.getMessage());
            } finally {
                System.out.println(Bob.LINE);
                phrase = scanner.nextLine();
            }
        }

        System.out.println(Bob.LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Bob.LINE);
    }

    private static void handleInput(String phrase) throws ChatBotException {
        String[] tmp = phrase.split(" ");
        switch (tmp[0]) {
        case "list":
            Bob.listTasks();
            break;
        case "mark":
            Bob.markTask(tmp);
            break;
        case "unmark":
            Bob.unmarkTask(tmp);
            break;
        case "todo":
            Bob.addToDo(phrase);
            break;
        case "deadline":
            Bob.addDeadline(phrase);
            break;
        case "event":
            Bob.addEvent(phrase);
            break;
        case "delete":
            Bob.deleteTask(tmp);
            break;
        default:
            throw new ChatBotException("I'm sorry, but I don't know what that means.");
        }
    }

    private static void readFromFile() throws ChatBotException {
        try {
            String directoryPath = "./data";
            String filePath = "./data/bob.txt";
            if (Files.exists(Paths.get(directoryPath)) &&
                    Files.isDirectory(Paths.get(directoryPath)) &&
                    Files.exists(Paths.get(filePath)) &&
                    Files.isRegularFile(Paths.get(filePath))) {
                Scanner s = new Scanner(Paths.get(filePath));
                while (s.hasNext()) {
                    String taskString = s.nextLine();
                    Bob.addTaskFromFile(taskString);
                }
            }
        } catch (IOException e) {
            System.out.println("Read failed. Something went wrong: " + e.getMessage());
        }
    }

    private static void writeToFile() {
        try {
            String directoryPath = "./data";
            if (!Files.exists(Paths.get(directoryPath)) ||
                    !Files.isDirectory(Paths.get(directoryPath))) {
                Files.createDirectory(Paths.get(directoryPath));
            }

            String filePath = "./data/bob.txt";
            if (!Files.exists(Paths.get(filePath)) ||
                    !Files.isRegularFile(Paths.get(filePath))) {
                Files.createFile(Paths.get(filePath));
            }

            FileWriter fw = new FileWriter(filePath);
            String[] tasksToSave = Bob.getTasksToSave();
            for (int i = 0; i < tasksToSave.length; i++) {
                fw.write(tasksToSave[i] + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Save failed. Something went wrong: " + e.getMessage());
        }
    }

    private static void addTaskFromFile(String taskString) throws ChatBotException {
        String[] tmp = taskString.split(" \\| ");
        boolean isDone = !tmp[1].equals("0");

        if (!tmp[1].equals("0") && !tmp[1].equals("1")) {
            throw new ChatBotException("Corrupted task marking entry.");
        }

        switch (tmp[0]) {
        case "T":
            if (tmp.length != 3) {
                throw new ChatBotException("Corrupted todo task entry.");
            }
            Bob.tasks.add(new ToDo(tmp[2], isDone, TaskType.TODO));
            break;
        case "D":
            if (tmp.length != 4) {
                throw new ChatBotException("Corrupted deadline task entry.");
            }
            Bob.tasks.add(new Deadline(tmp[2], isDone, tmp[3], TaskType.DEADLINE));
            break;
        case "E":
            if (tmp.length != 5) {
                throw new ChatBotException("Corrupted event task entry.");
            }
            Bob.tasks.add(new Event(tmp[2], isDone, tmp[3], tmp[4], TaskType.EVENT));
            break;
        default:
            throw new ChatBotException("I'm sorry, there was an error reading the file. Invalid task Type.");
        }
    }

    private static String[] getTasksToSave() {
        String[] taskArr = new String[Bob.tasks.size()];
        for (int i = 0; i < Bob.tasks.size(); i++) {
            taskArr[i] = Bob.tasks.get(i).formatToSave();
        }
        return taskArr;
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Bob.tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, Bob.tasks.get(i));
        }
    }

    private static void markTask(String[] tmp) throws ChatBotException {
        try {
            int num = Integer.parseInt(tmp[1]) - 1;
            System.out.println(Bob.tasks.get(num).mark());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for marking.");
        }
    }

    private static void unmarkTask(String[] tmp) throws ChatBotException {
        try {
            int num = Integer.parseInt(tmp[1]) - 1;
            System.out.println(Bob.tasks.get(num).unmark());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for unmarking.");
        }
    }

    private static void deleteTask(String[] tmp) throws ChatBotException {
        try {
            int num = Integer.parseInt(tmp[1]) - 1;
            System.out.println(Bob.tasks.get(num).delete());
            Bob.tasks.remove(num);
            System.out.printf("Now you have %d tasks in the list.\n", Bob.tasks.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for deleting.");
        }
    }

    private static void addToDo(String phrase) throws ChatBotException {
        if (phrase.length() <= 5) {
            throw new ChatBotException("The description of a todo cannot be empty.");
        }
        String description = phrase.substring(5);
        Bob.tasks.add(new ToDo(description, TaskType.TODO));
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", Bob.tasks.get(tasks.size() - 1));
        System.out.printf("Now you have %d tasks in the list.\n", Bob.tasks.size());
    }

    private static void addDeadline(String phrase) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /by ");
            String description = parts[0].substring(9);
            String by = parts[1];
            Bob.tasks.add(new Deadline(description, by, TaskType.DEADLINE));
            System.out.println("Got it. I've added this task:");
            System.out.printf("  %s\n", Bob.tasks.get(Bob.tasks.size() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", Bob.tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid format for deadline. Correct format: deadline <description> /by <date>");
        }
    }

    private static void addEvent(String phrase) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /from | /to ");
            String description = parts[0].substring(6);
            String from = parts[1];
            String to = parts[2];
            Bob.tasks.add(new Event(description, from, to, TaskType.EVENT));
            System.out.println("Got it. I've added this task:");
            System.out.printf("  %s\n", Bob.tasks.get(tasks.size() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", Bob.tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid format for event. Correct format: event <description> /from <start> /to <end>");
        }
    }
}