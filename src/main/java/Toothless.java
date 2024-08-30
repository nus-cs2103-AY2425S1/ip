import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Toothless represents a simple chat application.
 */
public class Toothless {

    private final static String TOOTHLESS_LOGO =
            """
                     _____            _   _     _
                    |_   _|___   ___ | |_| |__ | | ___  ___ ___
                      | |/ _ \\ / _ \\| __| '_ \\| |/ _ \\/ __/ __|
                      | | (_) | (_) | |_| | | | |  __/\\__ \\__ \\
                      |_|\\___/ \\___/ \\__|_| |_|_|\\___||___/___/
                    """;
    private final static String DIVIDER = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
    private static final String DATA_FILE_PATH = "src/data/tasks.txt";
    private final Scanner sc = new Scanner(System.in);
    private final ArrayList<Task> list;

    /**
     * Constructor for Toothless.
     * Initializes the task list.
     */
    private Toothless() {
        this.list = new ArrayList<>();
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Toothless:\n" +
                "Until next time, dragon rider!\n" +
                "Toothless the Night Fury, signing off.\n" +
                "See you soon!\n\n" + DIVIDER);
    }

    /**
     * Prints the tasks on the task list.
     */
    public void printTask() {
        System.out.println("Toothless:\nHere are the tasks on the quest board:\n\n" +
                "|-------------Quest Board -----------------|\n");

        if (list.isEmpty()) {
            System.out.println("There are no quests on the quest board!");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i).toString());
            }
        }

        System.out.println("\n|------------------------------------------|\n\n" + DIVIDER);
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     */
    public void markDone(int index) {
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        currentTask.markAsDone();
        System.out.println("Toothless:\nGood job! You had completed this quest!\n" +
              currentTask + "\n\n" + DIVIDER);
    }

    /**
     * Mark a task as undone.
     * @param index The index of the task to be marked as undone.
     */
    public void markUndone(int index) {
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        currentTask.markAsUndone();
        System.out.println("Toothless:\nOops! Quest is back in play!\n" +
                currentTask + "\n\n" + DIVIDER);
    }

    /**
     * Adds a task to the task list.
     * @param taskType The type of task to be added.
     * @param description The description of the task.
     * @throws ToothlessExceptions If the command is invalid.
     */
    public void addTask(String taskType, String description) throws ToothlessExceptions {
        switch(taskType) {
        case "todo":
            if(description.isEmpty()) {
                throw new NoDescription("todo", "todo <description>");
            }
            list.add(new ToDos(description));
            break;
        case "deadline":
            if(description.isEmpty()) {
                throw new NoDescription("deadline", "deadline <description> /by <timing>");
            } else if(!description.contains("/by")) {
                throw new NoTimeline("deadline", "deadline <description> /by <timing>");
            }
            String[] splitDeadline = description.split("/by");
            list.add(new Deadline(splitDeadline[0], splitDeadline[1]));
            break;
        case "event":
            if(description.isEmpty()) {
                throw new NoDescription("event", "event <description> /from <start time> /to <end time>");
            } else if(!description.contains("/from") || !description.contains("/to")) {
                throw new NoTimeline("event", "event <description> /from <start time> /to <end time>");
            }
            String[] splitEvent = description.split("/from");
            String[] splitEventTime = splitEvent[1].split("/to");
            list.add(new Events(splitEvent[0], splitEventTime[0], splitEventTime[1]));
            break;
        default:
            throw new ToothlessExceptions("I don't understand that command.\n" +
                    "Please enter a valid command. :)\n\n" + DIVIDER);
        }

        System.out.println("Toothless:\nYour task\n\t\t" +
                list.getLast() +
                "\nadded to the quest board!\n\n" +
                "Now there is " + list.size() + " quests in your quest board.\n\n" + DIVIDER);

    }

    /**
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted.
     * @throws ToothlessExceptions If the index is out of range.
     */
    public void deleteTask(int index) throws ToothlessExceptions {
        if(index > list.size() || index < 1) {
            throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                    DIVIDER);
        }
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        int newTaskSize = list.size() - 1;
        System.out.println("Toothless:\nThe quest\n\t\t" +
                currentTask + "\nis removed from the quest board!\n\n" +
                "Now there is " + newTaskSize + " quests in your quest board.\n\n" + DIVIDER);
        list.remove(fixedIndex);
    }

    /**
     * Executes the commands given by the user.
     * @param input The command given by the user.
     * @throws ToothlessExceptions If the command is invalid.
     */
    public void commands(String input) throws ToothlessExceptions {
        String[] splitInput = input.split(" ", 2);
        CommandType command = CommandType.getCommandType(splitInput[0]);
        String description = splitInput.length < 2? "" : splitInput[1];

        switch(command) {
            case TODO :
            case DEADLINE :
            case EVENT :
                addTask(command.name().toLowerCase(), description);
                saveTask();
                break;
            case LIST :
                printTask();
                break;
            case MARK :
                if(description.isEmpty()) {
                    throw new MissingIndex("mark", "mark <index>");
                }
                int markIndex = Integer.parseInt(description);
                if(markIndex > list.size() || markIndex < 1) {
                    throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                            DIVIDER);
                }
                markDone(markIndex);
                saveTask();
                break;
            case UNMARK :
                if(description.isEmpty()) {
                    throw new MissingIndex("unmark", "unmark <index>");
                }
                int unmarkIndex = Integer.parseInt(description);
                if(unmarkIndex > list.size() || unmarkIndex < 1) {
                    throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                            DIVIDER);
                }
                markUndone(unmarkIndex);
                saveTask();
                break;
            case DELETE :
                if(description.isEmpty()) {
                    throw new MissingIndex("delete", "delete <index>");
                }
                int deleteIndex = Integer.parseInt(description);
                if(deleteIndex > list.size() || deleteIndex < 1) {
                    throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                            DIVIDER);
                }
                deleteTask(deleteIndex);
                saveTask();
                break;
            default:
                throw new ToothlessExceptions("I don't understand that command.\n" +
                        "Please enter a valid command. :)\n\n" + DIVIDER);
        }
    }

    /**
     * Saves the tasks to a file.
     */
    public void saveTask() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for(Task task : list) {
                writer.write(getTaskData(task));
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("Toothless:\nOh no! There is an error saving quests to file.");
        }
    }

    /**
     * Returns the data of the task to save in the text file.
     * @param task The task to be saved.
     * @return The data of the task to be saved in the text file.
     */
    private String getTaskData(Task task) {
        String taskType = task instanceof ToDos ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Events ? "E" : "";
        String baseInfo = taskType + " | " + (task.isDone ? "1" : "0") + " | " + task.description;

        if (task instanceof Deadline) {
            return baseInfo + " | " + ((Deadline) task).deadline;
        } else if (task instanceof Events) {
            Events event = (Events) task;
            return baseInfo + " | " + event.eventStart + " | " + event.eventEnd;
        }
        return baseInfo;
    }

    /**
     * Loads the tasks from a file.
     */
    public void loadTasks() {
        list.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseDataToTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Toothless:\nOh no! There is an error loading quests from file.");
        }
    }

    /**
     * Parses the data from the file to a task.
     * @param taskData The data of the task.
     * @return The task parsed from the data.
     */
    private Task parseDataToTask(String taskData) {
        String[] splitData = taskData.split(" \\| ");

        String taskType = splitData[0];
        boolean isDone = splitData[1].equals("1");
        String description = splitData[2];

        switch(taskType) {
            case "T":
                return new ToDos(description, isDone);
            case "D":
                return new Deadline(description, splitData[3], isDone);
            case "E":
                return new Events(description, splitData[3], splitData[4], isDone);
            default:
                return null;
        }
    }

    /**
     * Begins the chat application.
     * The user can add tasks to the task list.
     */
    public void beginChat() throws ToothlessExceptions {
        loadTasks();

        System.out.println("Welcome to the dragon's den!\n" + TOOTHLESS_LOGO);
        System.out.println("Toothless:\n" +
                "Greetings, Dragon Rider!\n\n" +
                "I'm Toothless, your friendly dragon companion.\n" +
                "What adventure shall we embark on today?\n\n" + DIVIDER);

        while(true) {
            System.out.println("You :");
            String userInput = sc.nextLine();
            System.out.println("\n" + DIVIDER);

            if (userInput.equals("bye")) {
                printGoodbyeMessage();
                break;
            }

            try {
                commands(userInput);
            } catch (ToothlessExceptions e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Main method for Toothless.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Toothless toothless = new Toothless();
        try {
            toothless.beginChat();
        } catch (ToothlessExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}