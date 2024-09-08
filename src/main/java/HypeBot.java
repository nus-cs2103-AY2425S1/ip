import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The chatbot which the user interacts with.
 * @author Youngseo Park (@youngseopark05)
 */
public class HypeBot {
    private static final String BUFFER_LINE = "____________________________________________________________________\n";
    private static final ArrayList<Task> TASKS = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static File tasklistTextFile;
    private static boolean canExit = false;

    /**
     * Returns message formatted with buffer lines.
     *
     * @param message String to be reformatted.
     * @return Reformatted string with buffer lines.
     */
    private static String addBufferLine(String message) {
        return BUFFER_LINE + message + BUFFER_LINE;
    }

    /**
     * Returns error message formatted with buffer lines.
     *
     * @param message Error message to be reformatted.
     * @return Reformatted string with buffer lines.
     */
    private static String addBufferLineError(String message) {
        return BUFFER_LINE + "I might be tripping bro, my bad, my bad - \n" + message + BUFFER_LINE;
    }

    /**
     * Greets the user upon init and prompts the user for a command.
     */
    private static void greet() {
        String logo = """
                 ('-. .-.               _ (`-.    ('-. .-. .-')                .-') _  \s
                ( OO )  /              ( (OO  ) _(  OO)\\  ( OO )              (  OO) ) \s
                ,--. ,--.  ,--.   ,--._.`     \\(,------.;-----.\\  .-'),-----. /     '._\s
                |  | |  |   \\  `.'  /(__...--'' |  .---'| .-.  | ( OO'  .-.  '|'--...__)
                |   .|  | .-')     /  |  /  | | |  |    | '-' /_)/   |  | |  |'--.  .--'
                |       |(OO  \\   /   |  |_.' |(|  '--. | .-. `. \\_) |  |\\|  |   |  |  \s
                |  .-.  | |   /  /\\_  |  .___.' |  .--' | |  \\  |  \\ |  | |  |   |  |  \s
                |  | |  | `-./  /.__) |  |      |  `---.| '--'  /   `'  '-'  '   |  |  \s
                `--' `--'   `--'      `--'      `------'`------'      `-----'    `--'  \s
                """;
        System.out.println(addBufferLine("AYO WHAT'S UP IT'S ME YOUR\n\n" + logo
                + "\nLOADING YOUR TASKS IN /data/tasklist.txt...\n\nWhat can I do for you, my wonderful homie?\n"));
    }

    /**
     * Saves tasks stored in TASKS to /data/tasklist.txt in text form.
     * If file /data/tasklist.txt not found, informs user tasks couldn't be saved.
     */
    private static void saveTasksToLocalDisk() {
        System.out.println(addBufferLine("Alright homie, saving your tasks to your drive...\n"));
        try {
            FileWriter tasklistWriter = new FileWriter(tasklistTextFile);
            tasklistWriter.write("");
            for (Task task : TASKS) {
                tasklistWriter.append(task.toFileString());
            }
            tasklistWriter.close();
        } catch (IOException e) {
            System.out.println(addBufferLineError("but I couldn't save your tasks to the drive.\n"));
        }
    }

    /**
     * Greets the user goodbye upon the command 'bye'.
     */
    private static void exit() {
        SCANNER.close();
        saveTasksToLocalDisk();
        System.out.println(addBufferLine("""
                Alright homie, it's been a BLAST hanging out with you. \
                Have a wonderful
                day, and catch you soon again you ABSOLUTE BALLER!
                """));
    }

    /**
     * Prints out all the tasks user has saved in TASKS.
     */
    private static String list() {
        StringBuilder list = new StringBuilder("ALRIGHT, Here's that list!\n");
        for (int i = 0; i < TASKS.size(); i++) {
            list.append(i + 1).append(". ").append(TASKS.get(i)).append("\n");
        }
        return addBufferLine(list.toString());
    }

    /**
     * Adds task requested by user to TASKS.
     * Informs user how many total tasks saved.
     *
     * @param task task.Task to add to TASKS.
     */
    private static String add(Task task) {
        TASKS.add(task);
        return addBufferLine("HECK YEAH, ADDED: " + task + "!\nYOU'VE NOW GOT " + TASKS.size() + " TASKS TO GO!\n");
    }

    /**
     * Takes in task number and marks corresponding task as incomplete.
     * If task number not found in list, throws IndexOutOfBoundsException.
     *
     * @param idx task.Task number to mark incomplete.
     * @throws IndexOutOfBoundsException Thrown if task number invalid (too low / too high).
     */
    private static String unmark(int idx) throws IndexOutOfBoundsException {
        TASKS.get(idx).unmark();
        return addBufferLine("AIGHT, LET'S GET READY TO CONQUER THIS TASK:\n  " + TASKS.get(idx) + "\n");
    }

    /**
     * Takes in task number and marks corresponding task as complete.
     * If task number not found in list, throws IndexOutOfBoundsException.
     *
     * @param idx task.Task number to mark complete.
     * @throws IndexOutOfBoundsException Thrown if task number invalid (too low / too high).
     */
    private static String mark(int idx) throws IndexOutOfBoundsException {
        TASKS.get(idx).mark();
        return addBufferLine("AIGHT, ABSOLUTELY CONQUERED THIS TASK:\n  " + TASKS.get(idx) + "\n");
    }

    /**
     * Takes in task number and deletes corresponding task from TASKS.
     * If task number not found in list, throws IndexOutOfBoundsException.
     *
     * @param idx task.Task number to delete.
     * @throws IndexOutOfBoundsException Thrown if task number invalid (too low / too high).
     */
    private static String delete(int idx) throws IndexOutOfBoundsException {
        Task removed = TASKS.remove(idx);
        return addBufferLine("Say no more, BABY BYE BYE BYE to this task:\n " + removed + "!\nYOU'VE NOW GOT "
                + TASKS.size() + " TASKS TO GO!\n");
    }

    /**
     * Takes in a string array containing task description in text form and creates the corresponding task.
     *
     * @param taskTextLineElements String array containing task description in text form.
     * @return task.Task object corresponding to task description given in array.
     */
    private static Task loadTask(String[] taskTextLineElements) {
        String taskType = taskTextLineElements[0];
        String taskName = taskTextLineElements[2];
        Task newTask = null;
        switch (taskType) {
        case "T":
            newTask = new ToDo(taskName);
            break;
        case "D":
            String dueDate = taskTextLineElements[3];
            newTask = new Deadline(taskName, dueDate);
            break;
        case "E":
            String startTime = taskTextLineElements[3];
            String endTime = taskTextLineElements[4];
            newTask = new Event(taskName, startTime, endTime);
            break;
        }
        return newTask;
    }

    /**
     * Loads tasks in text form line-by-line from /data/tasklist.txt.
     * If file /data/tasklist.txt not found, informs user tasks couldn't be loaded.
     */
    private static void loadTasks() {
        try {
            Scanner tasklistFileScanner = new Scanner(tasklistTextFile);
            while (tasklistFileScanner.hasNextLine()) {
                String taskTextLine = tasklistFileScanner.nextLine();
                String[] taskTextLineElements = taskTextLine.split(" , ");
                Task newTask = loadTask(taskTextLineElements);
                if (newTask != null && taskTextLineElements[1].equals("1")) {
                    newTask.mark();
                }
                TASKS.add(newTask);
            }
            tasklistFileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(addBufferLineError("but I couldn't find the file with your saved tasks."));
        }
    }

    /**
     * Locates /data/tasklist.txt on local working directory and assigns to File object tasklistFile.
     * If /data/tasklist.txt not found, creates new File /data/tasklist.txt on local working directory.
     */
    private static void createOrLocateTasklistTextFile() {
        Path tasklistPath = Paths.get("data", "tasklist.txt");
        try {
            Files.createDirectories(Paths.get("data")); // ensures directory /data exists.
            File tempTasklistTextFile = tasklistPath.toFile();
            tempTasklistTextFile.createNewFile(); // ensures /data/tasklist.txt exists.
            tasklistTextFile = tempTasklistTextFile;
        } catch (IOException e) { // should not be reached
            System.out.println(addBufferLineError("but I couldn't load your saved tasks."));
        }
    }

    /**
     * Runs the actual event loop that continuously takes in commands until 'bye' entered by user.
     */
    private static void runEventLoop() {
        while (!canExit) {
            String line = SCANNER.nextLine();

            // If user hasn't entered anything, take in the next line
            if (line.trim().isEmpty()) {
                continue;
            }

            /*
            Parse user entry.
            If command entered is todo, deadline, event: [{Task_Type} {Name}, {Deadline/Start Time}, {End Time}]
            If command entered is mark, unmark, delete: [{Command_Type} {Index}]
            If command entered is list, bye: [{Command_Type}]
             */
            String[] splitLineForDates = line.split("/");
            String[] commandAndTaskName = splitLineForDates[0].split(" ");
            String command = commandAndTaskName[0];
            StringBuilder taskNameBuilder = new StringBuilder();
            for (int i = 1; i < commandAndTaskName.length; i++) {
                taskNameBuilder.append(commandAndTaskName[i]).append(" ");
            }
            String taskName = taskNameBuilder.toString();

            switch (command) {
            case "bye":
                canExit = true;
                break;
            case "list":
                System.out.println(list());
                break;
            case "todo":
                if (taskName.isEmpty()) {
                    System.out.println(addBufferLineError("drop the name of the task, bro I gotta know!\n"));
                    break;
                }
                ToDo newTodo = new ToDo(taskName);
                System.out.println(add(newTodo));
                break;
            case "deadline":
                if (splitLineForDates.length < 2) {
                    System.out.println(addBufferLineError("make sure you got the due date for that SWAGGIN' "
                            + "deadline you got!\n"));
                    break;
                }
                System.out.println(splitLineForDates[1]);
                Deadline newDeadline = new Deadline(taskName, splitLineForDates[1]);
                System.out.println(add(newDeadline));
                break;
            case "event":
                if (splitLineForDates.length < 3) {
                    System.out.println(addBufferLineError("make sure you got that start time AND end time for "
                            + "that AWESOME event you got!\n"));
                    break;
                }
                Event newEvent = new Event(taskName, splitLineForDates[1], splitLineForDates[2]);
                System.out.println(add(newEvent));
                break;
            case "mark":
                try {
                    int idx = Integer.parseInt(taskName.strip()) - 1;
                    System.out.println(mark(idx));
                } catch (NumberFormatException e) {
                    System.out.println(addBufferLineError("try indicating the index of the task you wanna mark "
                            + "CONQUERED as a number!\n"));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(addBufferLineError("try indicating the index of an existing task you wanna "
                            + "mark CONQUERED!\n"));
                }
                break;
            case "unmark":
                try {
                    int idx = Integer.parseInt(taskName.strip()) - 1;
                    System.out.println(unmark(idx));
                } catch (NumberFormatException e) {
                    System.out.println(addBufferLineError("try indicating the index of the task you wanna TAKE "
                            + "ON AGAIN as a number!\n"));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(addBufferLineError("try indicating the index of an existing task you wanna "
                            + "TAKE ON AGAIN!\n"));
                }
                break;
            case "delete":
                try {
                    int idx = Integer.parseInt(taskName.strip()) - 1;
                    System.out.println(delete(idx));
                } catch (NumberFormatException e) {
                    System.out.println(addBufferLineError("try indicating the index of the task you wanna delete "
                            + "as a number!\n"));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(addBufferLineError("try indicating the index of an existing task you wanna "
                            + "delete!\n"));
                }
                break;
            default:
                System.out.println(addBufferLineError("but I don't think we're vibing when you say '" + command
                        + "'.\nMind if I ask you for anything else, homie?\n"));
                break;
            }
        }
    }

    /**
     * Invokes all methods associated with initiation, event loop, termination of HypeBot interaction.
     * Takes in command line arguments, but does not do anything with them.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        createOrLocateTasklistTextFile();
        loadTasks();
        greet();
        runEventLoop();
        exit();
    }
}
