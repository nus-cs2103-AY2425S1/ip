import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Edith {
    private static final String PROMPT = "Your next instruction: ";

    private final String LINE_BREAK = "\n_______________________________________________________________________\n";
    private final String GREETING = "Hello! I am EDITH, your personal chatbot companion:)"
            + "\nHow may I assist you?";
    private final String INDENTATION = "    ";
    private final String FILE_PATH = "./data/edith.txt";
    private final String EXIT = "It has been my pleasure helping you. Hope to see you again soon!";

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public Edith() {
    }

    public void greet() {
        load();
        System.out.println(LINE_BREAK + GREETING + LINE_BREAK);
    }

    public void exit() {
        System.out.println(INDENTATION + EXIT + LINE_BREAK);
    }

    public void add(String string) {
        Task task;

        if (string.startsWith("todo ")) {
            String taskString = string.substring(5);
            if (taskString.isEmpty()) {
                throw new EdithException("Invalid task as no description for this todo was provided.");
            }

            task = new ToDo(taskString);
            addHelper(task);
        }
        if (string.startsWith("deadline ")) {
            String[] parts = string.split(" /by ");
            String taskString = parts[0].substring(9).trim();
            String dueDate = parts[1].trim();
            if (taskString.isEmpty() || dueDate.isEmpty()) {
                throw new EdithException("Invalid task as no description or due date for this deadline was provided.");
            }

            task = new Deadline(taskString, dueDate);
            addHelper(task);
        }
        if (string.startsWith("event ")) {
            String[] parts = string.split(" /from | /to ");
            String taskString = parts[0].substring(6).trim();
            String startTime = parts[1].trim();
            String endTime = parts[2].trim();
            if (taskString.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                throw new EdithException("Invalid task as no description or start time or end time for this event was provided.");
            }

            task = new Event(taskString, startTime, endTime);
            addHelper(task);
        }
    }

    public void addHelper(Task task) {
        listOfTasks.add(task);
        save();
        int numOfTasks = listOfTasks.size();
        String message1 = "Got it. I've added this task:";
        String message2 = "There are now " + numOfTasks + " tasks in your list.";

        System.out.println(INDENTATION + message1);
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + message2 + LINE_BREAK);
    }

    public void delete(int index) {
        String deletedTask = listOfTasks.get(index).toString();
        listOfTasks.remove(index);
        save();

        int numOfTasks = listOfTasks.size();
        String message1 = "Certainly. I've removed this task:";
        String message2 = "There are now " + numOfTasks + " tasks in your list.";

        System.out.println(INDENTATION + message1);
        System.out.println(INDENTATION + deletedTask);
        System.out.println(INDENTATION + message2 + LINE_BREAK);
    }

    public void list() {
        String emptyListMessage = "Great news, you have no outstanding task! Have a break!";
        String filledListMessage = "Here are the tasks in your list:";

        if (listOfTasks.isEmpty()) {
            System.out.println(INDENTATION + emptyListMessage + LINE_BREAK);
        } else {
            System.out.println(INDENTATION + filledListMessage);
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println(INDENTATION + (i+1) + ") " + listOfTasks.get(i));
            }
            System.out.println(LINE_BREAK);
        }
    }

    public void mark(int index) {
        int num = index + 1;
        String message = "Alright, great job! I've marked task " + num + " as done:";
        listOfTasks.get(index).markTaskDone();
        save();

        System.out.println(INDENTATION + message);
        System.out.println(INDENTATION + listOfTasks.get(index) + LINE_BREAK);
    }

    public void unmark(int index) {
        int num = index + 1;
        String message = "Sure, I've marked task " + num + " as not done yet:";
        listOfTasks.get(index).markTaskUndone();
        save();

        System.out.println(INDENTATION + message);
        System.out.println(INDENTATION + listOfTasks.get(index) + LINE_BREAK);
    }

    public void save() {
        try {
            File file = new File(FILE_PATH);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task curr = listOfTasks.get(i);
                String typeOfTaskString = curr.typeOfTaskString();
                String statusString = curr.statusString();
                String taskString = curr.taskString();

                writer.write(typeOfTaskString + "| " + statusString + "| " + taskString);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(INDENTATION + "An error occurred while saving updated task list." + LINE_BREAK);
        }
    }

    public void load() {
        File file = new File(FILE_PATH);
        File directory = new File(file.getParent());

        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            Scanner scanner = new Scanner(file);
            listOfTasks.clear();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskData = line.split("\\|");
                String typeOfTaskString = taskData[0].trim();
                String statusString = taskData[1].trim();
                String taskString = taskData[2].trim();
                Task task;

                switch (typeOfTaskString) {
                case "[T]":
                    task = new ToDo(taskString);
                    break;
                case "[D]":
                    String[] deadlineParts = taskString.split(" \\(due: ");
                    String deadlineTask = deadlineParts[0].trim();
                    String dueDate = deadlineParts[1].replace(")", "").trim();
                    task = new Deadline(deadlineTask, dueDate);
                    break;
                case "[E]":
                    String[] eventParts = taskString.split(" \\(from: | to: ");
                    String eventTask = eventParts[0].trim();
                    String startTime = eventParts[1].trim();
                    String endTime = eventParts[2].replace(")", "").trim();
                    task = new Event(eventTask, startTime, endTime);
                    break;
                default:
                    throw new EdithException("Unknown task type found in saved task list.");
                }

                if (statusString.equals("[X]")) {
                    task.markTaskDone();
                }

                listOfTasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(INDENTATION + "An error occurred while loading saved task list." + LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(INDENTATION + "An error occurred while parsing the task list. Data might be corrupted." + LINE_BREAK);
        }
    }

    public String invalidIndexMessage(int index) {
        return INDENTATION + "Task " + index + " does not exist. Please enter a valid task number." + LINE_BREAK;
    }

    public String invalidTaskMessage() {
        String string1 = "Invalid task due to missing details which were not provided. ";
        String string2 = "Please provide a valid instruction with the correct relevant details.";
        return INDENTATION + string1 + string2 + LINE_BREAK;
    }

    public void invalidInstruction() {
        throw new EdithException("Sorry but that is not an instruction I can execute.");
    }

    public static void main(String[] args) {
        Edith edith = new Edith();
        edith.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println(PROMPT);
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                edith.exit();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                edith.list();
            } else if (userInput.startsWith("mark ")) {
                int index = Integer.parseInt(userInput.substring(5).trim()) - 1;
                try {
                    edith.mark(index);
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = edith.invalidIndexMessage(index + 1);
                    System.err.println(errorMessage);
                }
            } else if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
                try {
                    edith.unmark(index);
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = edith.invalidIndexMessage(index + 1);
                    System.err.println(errorMessage);
                }
            } else if (userInput.startsWith("delete ")) {
                int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
                try {
                    edith.delete(index);
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = edith.invalidIndexMessage(index + 1);
                    System.err.println(errorMessage);
                }
            } else if (userInput.startsWith("todo ") || userInput.startsWith("deadline ") || userInput.startsWith("event ")) {
                try {
                    edith.add(userInput);
                } catch (EdithException e) {
                    System.err.println(e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    String errorMessage = edith.invalidTaskMessage();
                    System.err.println(errorMessage);
                }
            } else {
                try {
                    edith.invalidInstruction();
                } catch (EdithException e) {
                    System.err.println(e);
                }
            }
        }

        scanner.close();
    }
}