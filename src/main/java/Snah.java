import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Snah {

    private final static String EXIT_INPUT = "bye";
    private final static String LIST_INPUT = "list";
    private final static String MARK_DONE_STRING = "mark";
    private final static String UNMARK_DONE_STRING = "unmark";
    private final static String DEADLINE_INPUT = "deadline";
    private final static String EVENT_INPUT = "event";
    private final static String TODO_INPUT = "todo";
    private final static String DELETE_INPUT = "delete";
    private final static String CHAT_NAME = "Snah";
    private final static String CLEAR_TASKS = "clear";
    private final static String START_DIVIDER = "___________________________________________";
    private final static String END_DIVIDER = "___________________________________________\n";

    public static void main(String[] args) {
        greet();
        chatLoop(getTaskListFromSaveFile());
    }

    public static void chatbotPrint(String message) {
        System.out.printf("\t%s\n", message);
    }

    public static void greet() {
        chatbotPrint(START_DIVIDER);
        chatbotPrint(String.format("Hello! I'm %s", CHAT_NAME));
        chatbotPrint("What can I do for you?");
        chatbotPrint(END_DIVIDER);
    }

    public static void saveTaskToSaveFile(ArrayList<Task> tasks) {
        try {
            Files.newBufferedWriter(Paths.get("snah.txt"),
                    StandardOpenOption.TRUNCATE_EXISTING).close();

            for (Task task : tasks) {
                String content = task.toSaveFile() + System.lineSeparator();
                Files.write(Paths.get("snah.txt"), content.getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            chatbotPrint("An error occurred while writing to the file.");
        }
    }

    public static ArrayList<Task> getTaskListFromSaveFile() {

        ArrayList<Task> tasksList = new ArrayList<>();
        try {
            Files.createFile(Paths.get("snah.txt"));
        } catch (IOException e) {
            chatbotPrint("Save file already exists, loading tasks from save file");
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get("snah.txt"));
            for (String line : lines) {
                String[] data = line.split(":");

                if (data[0].startsWith("T")) {
                    ToDo newTask = new ToDo(data[2]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                } else if (data[0].startsWith("D")) {
                    Task newTask = new Deadline(data[2], data[3]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                } else if (data[0].startsWith("E")) {
                    Task newTask = new Event(data[2], data[3], data[4]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                }

            }

        } catch (IOException e) {
            chatbotPrint("No save file found, starting with a clean slate");
        }

        return tasksList;
    }

    public static void chatLoop(ArrayList<Task> tasksList) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (true) {
            userInput = scanner.nextLine();

            chatbotPrint(START_DIVIDER);
            if (userInput.equals(EXIT_INPUT)) {
                chatbotPrint("Goodbye! See you sooooonnn!");
                break;
            } else if (userInput.startsWith(LIST_INPUT)) {
                chatbotPrint("Here are the tasks in your list:");
                for (int i = 0; i < tasksList.size(); i++) {
                    chatbotPrint(String.format("%d. %s", i + 1, tasksList.get(i)));
                }
            } else if (userInput.startsWith("clear")) {
                tasksList.clear();
                chatbotPrint("Tasks cleared");
            } else if (userInput.startsWith(MARK_DONE_STRING)) {
                String[] splitInput = userInput.split(" ");
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;

                if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                    chatbotPrint("Oi, you're trying to mark a task that doesn't exist");
                    continue;
                }

                tasksList.get(taskIndex).markAsDone();

                chatbotPrint(String.format("Alright, I will mark the task as done"));
                chatbotPrint(String.format("  %s", tasksList.get(taskIndex)));

            } else if (userInput.startsWith(UNMARK_DONE_STRING)) {
                String[] splitInput = userInput.split(" ");
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;

                if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                    chatbotPrint("Oi, you're trying to unmark a task that doesn't exist");
                    continue;
                }

                tasksList.get(taskIndex).unmarkAsDone();

                chatbotPrint(String.format("Walao, why you press wrong; Will mark the task as NOT done"));
                chatbotPrint(String.format("  %s", tasksList.get(taskIndex)));

            } else if (userInput.startsWith(TODO_INPUT)) {

                if (userInput.length() < TODO_INPUT.length() + 1) {
                    chatbotPrint("Oi, you need to provide a description for the todo");
                    chatbotPrint("Format as such:");
                    chatbotPrint("  todo <description>");
                    continue;
                }

                String taskDescription = userInput.substring(TODO_INPUT.length() + 1);

                if (taskDescription.length() == 0) {
                    chatbotPrint("Oi, you need to provide a description for the todo");
                    chatbotPrint("Format as such:");
                    chatbotPrint("  todo <description>");
                    continue;
                }

                tasksList.add(new ToDo(taskDescription));
                chatbotPrint("Added todo to list");
                chatbotPrint(String.format("  %s", tasksList.get(tasksList.size() - 1)));
            } else if (userInput.startsWith(DEADLINE_INPUT)) {

                if (userInput.length() < DEADLINE_INPUT.length() + 1) {
                    chatbotPrint("Oi, you need to provide a description and a deadline for the deadline");
                    chatbotPrint("Format as such:");
                    chatbotPrint("  deadline <description> /by <deadline>");
                    continue;
                }

                String taskDescription = userInput.substring(DEADLINE_INPUT.length() + 1);
                String[] splitInput = taskDescription.split(" /by ");

                if (splitInput.length != 2) {
                    chatbotPrint("Oi, you need to provide a description and a deadline for the deadline");
                    chatbotPrint("Format as such:");
                    chatbotPrint("  deadline <description> /by <deadline>");
                    continue;
                } else if (splitInput[1].length() == 0) {
                    chatbotPrint("Oi, you need to provide a deadline for the deadline");
                    continue;
                } else if (splitInput[0].length() == 0) {
                    chatbotPrint("Oi, you need to provide a description for the deadline");
                    continue;
                }

                tasksList.add(new Deadline(splitInput[0], splitInput[1]));
                chatbotPrint("Added deadline to list");
                chatbotPrint(String.format("  %s", tasksList.get(tasksList.size() - 1)));
            } else if (userInput.startsWith(EVENT_INPUT)) {

                if (userInput.length() < EVENT_INPUT.length() + 1) {
                    chatbotPrint("Oi, you need to provide a description, a start time and an end time for the event");
                    chatbotPrint("Format as such:");
                    chatbotPrint("  event <description> /from <start time> /to <end time>");
                    continue;
                }

                String taskDescription = userInput.substring(EVENT_INPUT.length() + 1);
                String[] splitInput = taskDescription.split(" /from ");

                if (splitInput.length != 2) {
                    chatbotPrint("Oi, you need to provide a description, a start time and an end time for the event");
                    chatbotPrint("Format as such:");
                    chatbotPrint("  event <description> /from <start time> /to <end time>");
                    continue;
                }

                String[] finalSplit = splitInput[1].split(" /to ");

                if (splitInput.length != 2 || finalSplit.length != 2) {
                    chatbotPrint("Oi, you need to provide a description, a start time and an end time for the event");
                    chatbotPrint("Format as such:");
                    chatbotPrint("  event <description> /from <start time> /to <end time>");
                    continue;
                } else if (splitInput[0].length() == 0) {
                    chatbotPrint("Oi, you need to provide a description for the event");
                    continue;
                } else if (finalSplit[0].length() == 0) {
                    chatbotPrint("Oi, you need to provide a start time for the event");
                    continue;
                } else if (finalSplit[1].length() == 0) {
                    chatbotPrint("Oi, you need to provide an end time for the event");
                    continue;
                }

                tasksList.add(new Event(splitInput[0], finalSplit[0], finalSplit[1]));
                chatbotPrint("Added event to list");
                chatbotPrint(String.format("  %s", tasksList.get(tasksList.size() - 1)));
            } else if (userInput.startsWith(DELETE_INPUT)) {
                String[] splitInput = userInput.split(" ");
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;

                if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                    chatbotPrint("Oi, you're trying to delete a task that doesn't exist");
                    continue;
                }

                Task deletedTask = tasksList.remove(taskIndex);

                chatbotPrint(String.format("Alright, task is removed"));
                chatbotPrint(String.format("  %s", deletedTask));
            } else {
                String command = userInput.split(" ")[0];
                String[] commandList = { EXIT_INPUT, LIST_INPUT, MARK_DONE_STRING, UNMARK_DONE_STRING, DEADLINE_INPUT,
                        EVENT_INPUT, TODO_INPUT, DELETE_INPUT };
                chatbotPrint(String.format("Oi, no such command \"%s\". Try these instead", command));
                for (String commandString : commandList) {
                    chatbotPrint(String.format("- %s", commandString));
                }
            }
            chatbotPrint(END_DIVIDER);

            saveTaskToSaveFile(tasksList);

        }
        scanner.close();
    }
}
