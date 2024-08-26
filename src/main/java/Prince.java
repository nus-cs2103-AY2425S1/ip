import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Prince {

    private static final String LINE = "    ___________________________________________";
    private static final Path FILE_PATH = Paths.get("data", "storage.txt");

    // flag to control input printing when running automated tests
    private static boolean DEBUG = false;

    public static void main(String[] args) throws PrinceException {

        // check if debug argument is passed during automated text ui testing
        if (args.length > 0 && args[0].equals("debug")) {
            DEBUG = true;
        }

        // initialise scanner for user input
        Scanner sc = new Scanner(System.in);

        // initialise array for input storage
        ArrayList<Task> tasksArray = loadFromFile();

        printline();
        System.out.println("    Hello! I'm Prince");
        System.out.println("    What can I do for you?");
        printline();

        while (true) {
            // get input from the user
            String input = sc.nextLine();

            // prints input if in automated testing mode
            if (DEBUG) {
                System.out.println(input);
            }

            printline();

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    printList(tasksArray);
                } else if (input.contains("unmark")) {
                    unmark(input, tasksArray);
                    // updateFile("U", input, tasksArray);
                } else if (input.contains("mark")) {
                    mark(input, tasksArray);
                    // updateFile("M", input, tasksArray);
                } else if (input.contains("delete")) {
                    deleteFromFile(input, tasksArray);
                    delete(input, tasksArray);
                } else if (input.equals("todo") || input.equals("deadline") ||
                        input.equals("event")) {
                    throw new PrinceException("    Please describe your '" + input +
                            "' task in more detail!");
                } else if (input.contains("todo") || input.contains("deadline") ||
                        input.contains("event")) {
                    if (input.contains("todo")) {
                        Task task = handleTodo(input, tasksArray);
                        saveToFile(task, tasksArray);
                    }
                    if (input.contains("deadline")) {
                        Task task = handleDeadline(input, tasksArray);
                        saveToFile(task, tasksArray);
                    }
                    if (input.contains("event")) {
                        Task task = handleEvent(input, tasksArray);
                        saveToFile(task, tasksArray);
                    }
                    printline();
                } else {
                    throw new PrinceException("    Sorry, I am not sure what '" + input +
                            "' means. Please try again!");
                }
            } catch (PrinceException err) {
                System.out.println(err.toString());
                printline();
            }
        }

        // close and cleanup scanner
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /*
     * HELPER FUNCTIONS
     */

    // method to print a line
    private static void printline() {
        System.out.println(LINE);
    }

    /*
     * Methods related to printing out list format of tasks
     */

    private static void printList(ArrayList<Task> tasksArray) {
        System.out.println("    Here are the tasks in your list:");
        int length = tasksArray.size();
        // print the list of inputs
        for (int i = 0; i < length; i++) {
            Task task = tasksArray.get(i);
            // formatting for numbering of list
            int listNum = i + 1;
            String numDot = listNum + ".";
            System.out.println("    " + numDot + task.toString());
        }
        printline();
    }

    /*
     * Methods related to handling TODo tasks
     */

    // method to get description of the todo input
    private static String getTodo(String input) {
        String[] arr = input.split("todo");
        String todo = arr[1].trim();
        return todo;
    }

    private static Task handleTodo(String input, ArrayList<Task> tasksArray) {
        System.out.println("    Got it. I've added this task:");
        String desc = getTodo(input);
        Todo todo = new Todo(desc);
        tasksArray.add(todo);
        System.out.println("      " + todo.toString());
        System.out.println("    Now you have " + tasksArray.size() +
                " tasks in the list.");
        return todo;
    }

    /*
     * Methods related to handling DEADLINE tasks
     */

    // method to get description of the deadline input
    private static String getDeadline(String input) {
        String[] arr = input.split("deadline|/by");
        String deadline = arr[1].trim();
        return deadline;
    }

    // method to get the deadline of deadline task input
    private static String getBy(String input) {
        String[] arr = input.split("/by");
        String by = arr[1].trim();
        return by;
    }

    private static Task handleDeadline(String input, ArrayList<Task> tasksArray) {
        System.out.println("    Got it. I've added this task:");
        String desc = getDeadline(input);
        String by = getBy(input);
        Deadline deadlineTask = new Deadline(desc, by);
        tasksArray.add(deadlineTask);
        System.out.println("      " + deadlineTask.toString());
        System.out.println("    Now you have " + tasksArray.size() +
                " tasks in the list.");
        return deadlineTask;
    }

    /*
     * Methods related to handling EVENT tasks
     */

    // method to get description of the event input
    private static String getEvent(String input) {
        String[] arr = input.split("event|/from|/to");
        String event = arr[1].trim();
        return event;
    }

    // method to get from of the event input
    private static String getFrom(String input) {
        String[] arr = input.split("/from|/to");
        String from = arr[1].trim();
        return from;
    }

    // method to get to of the event input
    private static String getTo(String input) {
        String[] arr = input.split("/from|/to");
        String to = arr[2].trim();
        return to;
    }

    private static Task handleEvent(String input, ArrayList<Task> tasksArray) {
        System.out.println("    Got it. I've added this task:");
        String desc = getEvent(input);
        String from = getFrom(input);
        String to = getTo(input);
        Event event = new Event(desc, from, to);
        tasksArray.add(event);
        System.out.println("      " + event.toString());
        System.out.println("    Now you have " + tasksArray.size() +
                " tasks in the list.");
        return event;
    }

    /*
     * Methods related to UNMARK, MARK and DELETE
     */

    // method to get the integer when inputting unmark, mark or delete
    private static int getIndex(String input) {
        if (input.contains("unmark")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("mark")) {
            // get character value of index in input
            String indexAsString = input.substring(5);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("delete")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else {
            // should not reach here
            return -1;
        }
    }

    private static void unmark(String input, ArrayList<Task> tasksArray) {
        // extra check to make sure the start of input is "unmark"
        String checkUnmark = input.substring(0, 6);
        if (checkUnmark.equals("unmark")) {
            int index = getIndex(input);
            Task task = tasksArray.get(index);
            task.markAsNotDone();
            System.out.println("      " + task.toString());
        }
        printline();
    }

    private static void mark(String input, ArrayList<Task> tasksArray) {
        // extra check to make sure the start of input is "mark"
        String checkMark = input.substring(0, 4);
        if (checkMark.equals("mark")) {
            int index = getIndex(input);
            Task task = tasksArray.get(index);
            task.markAsDone();
            System.out.println("      " + task.toString());
        }
        printline();
    }

    private static void delete(String input, ArrayList<Task> tasksArray) {
        // extra check to make sure the start of input is "delete"
        String checkDelete = input.substring(0, 6);
        if (checkDelete.equals("delete")) {
            int index = getIndex(input);
            Task task = tasksArray.get(index);
            task.delete(); // prints "the noted i removed this task" string
            tasksArray.remove(index);
            System.out.println("      " + task.toString());
            System.out.println("    Now you have " + tasksArray.size() +
                    " tasks in the list.");
        }
        printline();
    }

    /*
     * Methods relating to reading and writing inputs to files
     */

    private static ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasksArray = new ArrayList<>();

        try {

            if (Files.notExists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }

            // read from file path
            List<String> lines = Files.readAllLines(FILE_PATH);

            // for each line, need to splice it according to the format
            // create new tasks in taskarray based on each line
            for (String stringTask : lines) {
                String[] arr = stringTask.split(" .. ");
                String taskType = "";
                String description = "";
                String status = "";
                String byFrom = "";
                String to = "";
                // for each line in the file, splice and extract relevant fields
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] != null) {
                        String trimmed = arr[i].trim();
                        if (i == 0) {
                            taskType = trimmed;
                        }
                        if (i == 1) {
                            status = trimmed;
                        }
                        if (i == 2) {
                            description = trimmed;
                        }
                        if (i == 3) {
                            byFrom = trimmed;
                        }
                        if (i == 4) {
                            to = trimmed;
                        }
                    }
                }

                // create new task based on each line in the file
                if (taskType.equals("T")) {
                    Task todoTask = new Todo(description);
                    tasksArray.add(todoTask);
                    if (status.equals("1")) {
                        todoTask.markAsDone();
                    }
                }
                if (taskType.equals("D")) {
                    Task deadlineTask = new Deadline(description, byFrom);
                    tasksArray.add(deadlineTask);
                    if (status.equals("1")) {
                        deadlineTask.markAsDone();
                    }
                }
                if (taskType.equals("E")) {
                    Task eventTask = new Event(description, byFrom, to);
                    tasksArray.add(eventTask);
                    if (status.equals("1")) {
                        eventTask.markAsDone();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasksArray;
    }

    private static void saveToFile(Task task, ArrayList<Task> tasksArray) {
        try {

            if (Files.notExists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }

            BufferedWriter bw = Files.newBufferedWriter(FILE_PATH, StandardOpenOption.APPEND);

            String taskString = task.toFileFormat();
            bw.write(taskString);
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFromFile(String input, ArrayList<Task> tasksArray) {
        try {
            int index = getIndex(input);
            Task task = tasksArray.get(index);
            // open the old file for reading
            BufferedReader reader = Files.newBufferedReader(FILE_PATH);
            // open a new (temporary) file for writing
            Path tempPath = Paths.get("data", "temp.txt");
            BufferedWriter writer = Files.newBufferedWriter(tempPath);
            // iterate over the lines in the old file (probably using a BufferedReader)
            String lineToRemove = task.toFileFormat();
            String currLine;
            // for each line, check if it matches what you are supposed to remove
            while ((currLine = reader.readLine()) != null) {
                if (currLine.equals(lineToRemove)) {
                    continue;
                }
                // if it doesn't match, write it to the temporary file
                writer.write(currLine);
                writer.newLine();
            }

            // close both files
            reader.close();
            writer.close();

            // delete the old file
            Files.delete(FILE_PATH);
            // copy temp file to old file path
            Files.copy(tempPath, FILE_PATH);
            // delete temp file
            Files.delete(tempPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
