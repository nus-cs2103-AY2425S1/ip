import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class James {

    public static void loadSavedData(ArrayList<Task> taskList, String filepath) {
        File savedData = new File(filepath);
        File dataDirectory = savedData.getParentFile();

        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        if (!savedData.exists()) {
            try {
                savedData.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
                return;
            }
        }

        try (Scanner s = new Scanner(savedData)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isMarked = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        taskList.add(new Todo(description, isMarked));
                        break;

                    case "D":
                        if (parts.length < 4) {
                            throw new MissingDescriptionException("Missing deadline information in line: " + line);
                        }
                        String deadline = parts[3];
                        taskList.add(new Deadline(description, isMarked, deadline));
                        break;

                    case "E":
                        if (parts.length < 5) {
                            throw new MissingDescriptionException("Missing event time information in line: " + line);
                        }
                        String start = parts[3];
                        String end = parts[4];
                        taskList.add(new Event(description, isMarked, start, end));
                        break;

                    default:
                        System.out.println("Unknown task type found: " + type);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file could not be found after attempting to create it: " + e.getMessage());
        } catch (MissingDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello Big Boy! I'm James \n" +
                "How can I assist you today? \n";
        String exitMessage = "Goodbye. Come back anytime! \n";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        loadSavedData(tasks, "data/james.txt");

        try {
            while (true) {
                System.out.print("> ");
                String command = scanner.nextLine();
                String[] words = command.split(" ");
                String action = words[0].toLowerCase();

                try {
                    switch (action) {
                        case "bye":
                            System.out.println(exitMessage);
                            return;

                        case "list":
                            for (int i = 0; i < tasks.size(); i++) {
                                String task = String.format("%d. %s", (i + 1), tasks.get(i).printTask());
                                System.out.println(task);
                            }
                            break;

                        case "mark":
                            int markTaskNum = Integer.parseInt(command.substring(command.length() - 1));
                            tasks.get(markTaskNum - 1).mark();
                            // Fallthrough if required, else break;
                            break;

                        case "unmark":
                            int unmarkTaskNum = Integer.parseInt(command.substring(command.length() - 1));
                            tasks.get(unmarkTaskNum - 1).unMark();
                            break;

                        case "todo":
                            String todoDescription = command.substring(4).trim();
                            Task todoTask = new Todo(todoDescription, false);
                            tasks.add(todoTask);
                            System.out.println("Task added:" + "\n" + todoTask.printTask());
                            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                            break;

                        case "deadline":
                            String deadlineDescription = command.substring(8, command.lastIndexOf("/by")).trim();
                            String deadline = command.substring(command.lastIndexOf("/by") + 4).trim();
                            Task deadlineTask = new Deadline(deadlineDescription, false, deadline);
                            tasks.add(deadlineTask);
                            System.out.println("Task added:" + "\n" + deadlineTask.printTask());
                            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                            break;

                        case "event":
                            String eventDescription = command.substring(5, command.lastIndexOf("/from")).trim();
                            String start = command.substring(command.lastIndexOf("/from") + 6,
                                    command.lastIndexOf("/to")).trim();
                            String end = command.substring(command.lastIndexOf("/to") + 4).trim();
                            Task eventTask = new Event(eventDescription, false, start, end);
                            tasks.add(eventTask);
                            System.out.println("Task added:" + "\n" + eventTask.printTask());
                            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                            break;

                        case "delete":
                            int deleteTaskNum = Integer.parseInt(command.substring(command.length() - 1));
                            System.out.println("Task removed:" + "\n" + tasks.get(deleteTaskNum - 1).printTask());
                            tasks.remove(deleteTaskNum - 1);
                            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
                            break;

                        default:
                            throw new CommandNotFoundException("Sorry! I don't understand what you mean by " +
                                    "(" + command + ") please try a different command!");
                    }
                } catch (JamesException e) {
                    System.out.println(e.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
    }
}
