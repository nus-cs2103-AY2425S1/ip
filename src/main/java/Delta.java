import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Delta is a chatbot to assist in task management.
 * The permitted commands are:
 *      * todo [description]
 *      * deadline [description] /by [date/time]
 *      * event [description] /from [start] /to [end]
 *      * mark [index of task]
 *      * unmark [index of task]
 *      * delete [index of task]
 */
public class Delta {
    private static final String SAVE_DIR_PATH = "./data";
    private static final String SAVE_FILE_PATH = SAVE_DIR_PATH + "/DeltaList.txt";
    /** List to store all tasks */
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Returns hello message from Delta.
     *
     * @return String Hello message.
     */
    public static String sayHello() {
        return """
                \t____________________________________________________________
                \t Hello! I'm Delta
                \t What can I do for you?
                \t____________________________________________________________""";
    }

    /**
     * Returns goodbye message from Delta.
     *
     * @return String Goodbye message.
     */
    public static String sayBye() {
        return """
                \t____________________________________________________________
                \t Bye. Hope to see you again soon!
                \t____________________________________________________________""";
    }

    /**
     * Adds task to list.
     *
     * @param task Task to be added to list.
     * @return String Task added message.
     */
    public static String addTask(Task task) {
        list.add(task);
        return "\t____________________________________________________________\n" +
                "\t Got it. I've added this task:\n" +
                "\t   " + task.toString() + "\n" +
                "\t Now you have " + list.size() + " task" + (list.size() > 1 ? "s" : "") + " in the list.\n" +
                "\t____________________________________________________________";
    }

    /**
     * Marks task as done in list.
     *
     * @param i Index of task to be marked as done (one-based indexing).
     * @return String Task marked as done message.
     * @throws DeltaException If list is empty, task not found in list or task is already marked as done.
     */
    public static String markTask(int i) throws DeltaException{
        if (list.isEmpty()) {
            throw new DeltaException("OOPS!!! List is empty, there is no task to mark.");
        } else if (i < 1 || i > list.size()) {
            throw new DeltaException("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task to mark.""");
        }
        Task task = list.get(i - 1);
        if (task.getStatusIcon().equals("X")) {
            throw new DeltaException("OOPS!!! Task is already marked as done.");
        }
        task.markAsDone();
        list.set(i - 1, task);
        return "\t____________________________________________________________\n" +
                "\t Nice! I've marked this task as done:\n" +
                "\t   " + task + "\n" +
                "\t____________________________________________________________";
    }

    /**
     * Marks task as not done yet in list.
     *
     * @param i Index of task to be marked as not done yet (one-based indexing).
     * @return String Task marked as not done yet message.
     * @throws DeltaException If list is empty, task not found in list or task is already marked as not done yet.
     */
    public static String unmarkTask(int i) throws DeltaException {
        if (list.isEmpty()) {
            throw new DeltaException("OOPS!!! List is empty, there is no task to unmark.");
        } else if (i < 1 || i > list.size()) {
            throw new DeltaException("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task to unmark.""");
        }
        Task task = list.get(i - 1);
        if (task.getStatusIcon().equals(" ")) {
            throw new DeltaException("OOPS!!! Task is already marked as not done.");
        }
        task.markAsNotDone();
        list.set(i - 1, task);
        return "\t____________________________________________________________\n" +
                "\t Ok, I've marked this task as not done yet:\n" +
                "\t   " + task + "\n" +
                "\t____________________________________________________________";
    }

    /**
     * Deletes task from list.
     *
     * @param i Index of task to be deleted (one-based indexing).
     * @return String Task deleted message.
     * @throws DeltaException If list is empty or task not found in list.
     */
    public static String deleteTask(int i) throws DeltaException {
        if (list.isEmpty()) {
            throw new DeltaException("OOPS!!! List is empty, there is no task to delete.");
        } else if (i < 1 || i > list.size()) {
            throw new DeltaException("""
                    OOPS!!! Task not found in list.
                    \t Please provide a valid Task to delete.""");
        }
        Task task = list.get(i - 1);
        list.remove(i - 1);
        return "\t____________________________________________________________\n" +
                "\t Noted. I've removed this task:\n" +
                "\t   " + task + "\n" +
                "\t Now you have " + list.size() + " tasks" + " in the list.\n" +
                "\t____________________________________________________________";
    }

    /**
     * Prints all tasks in list.
     *
     * @return String List of all tasks currently in list.
     * @throws DeltaException If list is empty.
     */
    public static String printTasks() throws DeltaException {
        String output = "\t____________________________________________________________\n";
        if (list.isEmpty()) {
            throw new DeltaException("There are no tasks in your list.");
        } else {
            output += "\t Here are the tasks in your list:\n";
        }
        for (int i = 0; i < list.size(); i++) {
            output += String.format("\t %d.%s\n", i + 1, list.get(i));
        }
        output += "\t____________________________________________________________";
        return output;
    }

    /**
     * Formats error message.
     *
     * @param message Message to be formatted.
     * @return String Message after being properly formatted.
     */
    public static String printError(String message) {
        return "\t____________________________________________________________\n" +
                "\t " + message + "\n" +
                "\t____________________________________________________________";
    }

    /**
     * Checks if date/time input by user is the correct format to be stored as a LocalDateTime object.
     *
     * @param input Date/time input by user.
     * @return LocalDateTime object Object that contains the date/time according to the user input.
     * @throws DeltaException If format of user input is wrong.
     */
    public static LocalDateTime formatDateTime(String input) throws DeltaException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, formatter);
        }
        catch (DateTimeParseException e) {
            throw new DeltaException("""
                    OOPS!!! The format used for date/time is wrong!
                    \t Please follow the proper format:
                    \t yyyy-MM-dd HHmm
                    \t eg. 2024-08-25 1800""");
        }
    }

    /**
     * Saves tasks in list to SAVE_FILE_PATH location.
     *
     * @throws DeltaException If directory or file unable to be created.
     */
    public static void saveTasks() throws DeltaException {
        File saveDirectory = new File(SAVE_DIR_PATH);
        if (!saveDirectory.exists()) {
            boolean directoryCreatedSuccessfully = saveDirectory.mkdir();
            if (!directoryCreatedSuccessfully) {
                throw new DeltaException("""
                        OOPS!!! Save Directory unable to be created!
                        \t Please check Save Directory path:
                        \t """ + saveDirectory.getAbsolutePath());
            }
        }

        File saveFile = new File(SAVE_FILE_PATH);
        if (!saveFile.exists()) {
            try {
                boolean fileCreatedSuccessfully = saveFile.createNewFile();
                if (!fileCreatedSuccessfully) {
                    throw new DeltaException("""
                            OOPS!!! Save File unable to be created!
                            \t Please check Save File path:
                            \t """ + saveFile.getAbsolutePath());
                }
            }
            catch (IOException e) {
                throw new DeltaException("""
                        OOPS!!! Save File unable to be created!
                        \t Please check Save File path:
                        \t """ + saveFile.getAbsolutePath());
            }
        }
        String fileContents = "";
        for (Task task : list) {
            fileContents += task.saveDetails() + "\n";
        }

        try {
            FileWriter fw = new FileWriter(saveFile);
            fw.write(fileContents);
            fw.close();
        }
        catch (IOException e) {
            throw new DeltaException("""
                    OOPS!!! List unable to save!
                    \t Please type command to manually save:
                    \t * save""");
        }
    }

    /**
     * Loads tasks from SAVE_FILE_PATH and adds tasks into list.
     *
     * @return String List of all tasks from previous session.
     * @throws DeltaException If save file not found.
     */
    public static String loadTasks() throws DeltaException {
        String output = """
                \t____________________________________________________________
                \t Loading the list of tasks from your previous session...
                \t____________________________________________________________
                """;
        File loadFile = new File(SAVE_FILE_PATH);
        if (loadFile.exists()) {
            try {
                Scanner sc = new Scanner(loadFile);
                while (sc.hasNextLine()) {
                    String[] details = sc.nextLine().replace("\n", "").split(" \\| ");
                    if (details[0].equals("T")) {
                        addTask(new Todo(details[2]));
                    } else if (details[0].equals("D")) {
                        addTask(new Deadline(details[2], details[3]));
                    } else {
                        addTask(new Event(details[2], details[3], details[4]));
                    }
                    if (details[1].equals("1")) {
                        markTask(list.size());
                    }
                }
            }
            catch (FileNotFoundException e) {
                throw new DeltaException("""
                        OOPS!!! Save File not found!
                        \t Please check Save File path:
                        \t """ + loadFile.getAbsolutePath());
            }
        }
        output += printTasks();
        return output;
    }

    /**
     * Runs Delta Chatbot.
     *
     * @param args User input following formats:
     *             * todo [description]
     *             * deadline [description] /by [date/time]
     *             * event [description] /from [start] /to [end]
     *             * mark [index of task]
     *             * unmark [index of task]
     *             * delete [index of task]
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load Tasks from previous session
        try {
            System.out.println(loadTasks());
        }
        catch (DeltaException e) {
            System.out.println("""
                    \t____________________________________________________________
                    \t Loading the list of tasks from your previous session...
                    \t____________________________________________________________
                    """ + printError(e.getMessage()));
        }

        // Hello
        System.out.println(sayHello());

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] description = userInput.strip().split(" ", 2);
            String task = description[0];
            String output = "";

            try {
                // Bye
                if (task.equalsIgnoreCase("bye")) {
                    output = sayBye();
                    break;

                // Print entire list
                } else if (task.equalsIgnoreCase("list")) {
                    output = printTasks();

                // Save Tasks to list
                } else if (task.equalsIgnoreCase("save")) {
                    saveTasks();

                // Add Todo to list
                } else if (task.equalsIgnoreCase("todo")) {
                    if (description.length == 2) {
                        String taskName = description[1].strip();
                        output = addTask(new Todo(taskName));
                    } else {
                        throw new DeltaException("""
                                OOPS!!! Description of todo cannot be left blank!
                                \t Please follow the proper format:
                                \t * unmark [index of task]""");
                    }

                // Add Deadline to list
                } else if (task.equalsIgnoreCase("deadline")) {
                    if (description.length == 2) {
                        String[] details = description[1].strip().split(" /by ");
                        if (details.length == 2) {
                            String taskName = details[0].strip();
                            LocalDateTime by = formatDateTime(details[1].strip());
                            output = addTask(new Deadline(taskName, by));
                        } else {
                            throw new DeltaException("""
                                    OOPS!!! The format for deadline is wrong!
                                    \t Please follow the proper format:
                                    \t * deadline [description] /by [date/time]""");
                        }
                    } else {
                        throw new DeltaException("""
                                OOPS!!! Description of deadline cannot be left blank!
                                \t Please follow the proper format:
                                \t * deadline [description] /by [date/time]""");
                    }

                // Add Event to list
                } else if (task.equalsIgnoreCase("event")) {
                    if (description.length == 2) {
                        String[] details = description[1].strip().split(" /from ");
                        if (details.length == 2) {
                            String taskName = details[0].strip();
                            String[] timings = details[1].strip().split(" /to ");
                            if (timings.length == 2) {
                                String start = timings[0].strip();
                                String end = timings[1].strip();
                                output = addTask(new Event(taskName, start, end));
                            } else {
                                throw new DeltaException("""
                                        OOPS!!! The format for event is wrong!
                                        \t Please follow the proper format:
                                        \t * event [description] /from [start] /to [end]""");
                            }
                        } else {
                            throw new DeltaException("""
                                    OOPS!!! The format for event is wrong!
                                    \t Please follow the proper format:
                                    \t * event [description] /from [start] /to [end]""");
                        }
                    } else {
                        throw new DeltaException("""
                                OOPS!!! Description of event cannot be left blank!
                                \t Please follow the proper format:
                                \t * event [description] /from [start] /to [end]""");
                    }


                // Mark Task as done
                } else if (task.equalsIgnoreCase("mark")) {
                    if (description.length == 2) {
                        int taskIdx = Integer.parseInt(description[1].strip());
                        output = markTask(taskIdx);
                    } else {
                        throw new DeltaException("""
                                OOPS!!! The format to mark tasks is wrong!
                                \t Please follow the proper format:
                                \t * mark [index of task]""");
                    }

                // Mark Task as not done yet
                } else if (task.equalsIgnoreCase("unmark")) {
                    if (description.length == 2) {
                        int taskIdx = Integer.parseInt(description[1].strip());
                        output = unmarkTask(taskIdx);
                    } else {
                        throw new DeltaException("""
                                OOPS!!! The format to unmark tasks is wrong!
                                \t Please follow the proper format:
                                \t * unmark [index of task]""");
                    }

                // Delete Task from list
                } else if (task.equalsIgnoreCase("delete")) {
                    if (description.length == 2) {
                        int taskIdx = Integer.parseInt(description[1].strip());
                        output = deleteTask(taskIdx);
                    } else {
                        throw new DeltaException("""
                                OOPS!!! The format to delete tasks is wrong!
                                \t Please follow the proper format:
                                \t * delete [index of task]""");
                    }

                // Unknown Action
                } else {
                    throw new DeltaException("""
                            OOPS!!! I'm sorry, but I don't know what that means :-(
                            \t Please follow the proper formats:
                            \t * todo [description]
                            \t * deadline [description] /by [date/time]
                            \t * event [description] /from [start] /to [end]
                            \t * mark [index of task]
                            \t * unmark [index of task]
                            \t * delete [index of task]""");
                }

                saveTasks();
            }
            catch (DeltaException e) {
                System.out.println(printError(e.getMessage()));
            }
            // Catches error when mark, unmark or delete task methods do not receive an integer for index.
            catch (NumberFormatException e) {
                System.out.println(printError("OOPS!!! Index of task must be an integer!"));
            }

            System.out.println(output);
        }

        sc.close();
    }
}
