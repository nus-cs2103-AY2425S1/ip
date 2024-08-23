import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Strand {

    private static boolean running = false;

    private static final String horizontalLine = "～～～～～～～～～～～～～～～～～～～～～～～～><>";

    private static final ArrayList<Task> strandList = new ArrayList<>();

    private static final String FILENAME = "./data/strand.txt";
    enum Commands {
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        MARK,
        UNMARK,
        BYE,
        LIST
    }

    /**
     * Prints a string with an indentation of 4 spaces.
     *
     * @param s The string to be printed.
     */
    private static void print(String s) {
        System.out.println(s.indent(4));
    }

    /**
     * Prints a string surrounded by horizontal lines for output formatting.
     *
     * @param s The string to be printed between the horizontal lines.
     */
    private static void output(String s) {
        System.out.println(horizontalLine.indent(4));
        print(s);
        System.out.println(horizontalLine.indent(4));
    }

    /**
     * Initializes the chat by setting the running flag to true and printing a welcome message.
     */
    private static void chatStart() {
        running = true;
        output("ヾ(⌐■_■)ノ♪ Welcome! I'm Strand\nWhat can I do for you?");
    }

    /**
     * Generates a string representation of all tasks in the strandList.
     *
     * @return A string listing all tasks, each with its index and status icon.
     */
    private static String listAllTasks() {
        return(
                strandList.stream()
                        .map((x) -> (strandList.indexOf(x) + 1) + "." + x.toString() + "\n")
                        .reduce((a, b) -> a + b).orElse("")
        );
    }

    private static void writeToFile() {
        try {
            File file = new File(FILENAME);
            if(!file.exists()) {
                File parentFile = file.getParentFile();
                if(parentFile != null && !parentFile.exists()) {
                    if(!parentFile.mkdir()) {
                        throw new IOException("Error creating parent file");
                    }
                }
                if(!file.createNewFile()) {
                    throw new IOException("Error creating data file");
                }

            }
            FileWriter fileWriter = new FileWriter(FILENAME);
            String fileContents = listAllTasks();
            fileWriter.write(fileContents);
            fileWriter.close();
        } catch (IOException e) {
            output("An error has occurred while writing to file: " + e.getMessage());
        }

    }

    /**
     * Marks a task as done or undone
     *
     * @param input The user command to mark or unmark a task.
     * @throws StrandException If the input is invalid or the task index is out of bounds.
     */
    private static void mark(String input) throws StrandException {
        String[] split = input.split("\\s+");
        if (split.length < 2) {
            throw new StrandNumberNotFoundException(split[0]);
        } else {
            try {
                int index = Integer.parseInt(split[1]);
                if (index > strandList.size() || index < 1) {
                    throw new StrandWrongIndexException(strandList.size());
                }
                Task t = strandList.get(index - 1);
                String str;
                if (Objects.equals(split[0], "mark")) {
                    t.markAsDone();
                    str = "( ﾟヮﾟ) You finished a task?! Congrats! I've marked this task as done:\n";
                } else {
                    t.markAsNotDone();
                    str = "ಠ_ಠ ...OK, I've marked this task as not done yet:\n";
                }
                output(str + t);

            } catch (NumberFormatException e) {
                throw new StrandNumberNotFoundException(split[0]);
            }
        }
    }

    /**
     * Adds a new task to the strandList based on the input command.
     *
     * @param input The user command to add a task.
     * @throws StrandException If the task description or necessary parameters are missing or incorrect.
     */
    private static void addTask(String input) throws StrandException {
        String[] split = input.split(" ", 2);
        String type = split[0];
        if (split.length < 2) {
            throw new StrandDescNotFoundException("Description", type);
        }
        String desc = split[1].trim();
        if (desc.isEmpty()) {
            throw new StrandDescNotFoundException("Description", type);
        }
        String uppercaseInput = type.toUpperCase();
        Commands command;
        try {
            command = Commands.valueOf(uppercaseInput);
        } catch (IllegalArgumentException e) {
            throw new StrandWrongCommandException();
        }
        switch (command) {
            case TODO: {
                strandList.add(new Todo(desc));
                break;
            }
            case DEADLINE: {
                if (!desc.contains(" /by ")) {
                    throw new StrandDescNotFoundException("Deadline", type);
                }
                String description = desc.substring(0, desc.indexOf(" /by ")).trim();
                String deadline = desc.substring(desc.indexOf(" /by ") + 5).trim();
                strandList.add(new Deadline(description, deadline));
                break;
            }
            case EVENT: {
                if (!desc.contains(" /from ")) {
                    throw new StrandDescNotFoundException("Start time", type);
                }
                if (!desc.contains(" /to ")) {
                    throw new StrandDescNotFoundException("End time", type);
                }
                String description = desc.substring(0, desc.indexOf(" /from ")).trim();
                String start = desc.substring(desc.indexOf(" /from ") + 7, desc.indexOf(" /to ") + 1).trim();
                String end = desc.substring(desc.indexOf(" /to ") + 5).trim();
                strandList.add(new Event(description, start, end));
                break;
            }
        }
        output("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: Task added:\n  "
                + strandList.get(strandList.size() - 1).toString()
                + "\nNow you have " + strandList.size() + " tasks in the list.");
    }

    /**
     * Delete task from the strandList
     *
     * @param input The user command to add a task.
     * @throws StrandException If the task description or necessary parameters are missing or incorrect.
     */
    private static void deleteTask(String input) throws StrandException {
        String[] split = input.split("\\s+");
        if (split.length < 2) {
            throw new StrandNumberNotFoundException(split[0]);
        } else {
            try {
                int index = Integer.parseInt(split[1]);
                if (index > strandList.size() || index < 1) {
                    throw new StrandWrongIndexException(strandList.size());
                }
                Task t = strandList.get(index - 1);
                String taskString = t.toString();
                strandList.remove(index - 1);
                output("(☞ﾟ∀ﾟ)☞ Task removed:\n" + taskString
                        + "\nNow you have " + strandList.size() + " tasks in the list.");

            } catch (NumberFormatException e) {
                throw new StrandNumberNotFoundException(split[0]);
            }
        }
    }


    /**
     * Processes the user input and executes the corresponding command.
     *
     * @param input The user command.
     * @throws StrandException If the command is invalid or an error occurs during processing.
     */
    private static void inputs(String input) throws StrandException {
        String[] split = input.toUpperCase().split("\\s+");
        if(split.length == 0) {
            throw new StrandWrongCommandException();
        }
        String uppercaseInput = split[0];
        Commands command;
        try {
            command = Commands.valueOf(uppercaseInput);
        } catch (IllegalArgumentException e) {
            throw new StrandWrongCommandException();
        }

        switch (command) {
            case TODO, DEADLINE, EVENT: {
                addTask(input);
                writeToFile();
                break;
            }
            case DELETE: {
                deleteTask(input);
                writeToFile();
                break;
            }
            case MARK, UNMARK : {
                mark(input);
                writeToFile();
                break;
            }
            case BYE: {
                output("Adios. Hope to see you again soon! ヾ(＾ ∇ ＾)");
                running = false;
                break;
            }
            case LIST : {
                output(listAllTasks());
                break;
            }
            default: {
                throw new StrandWrongCommandException();
            }
        }
    }

    public static void main(String[] args) {
        chatStart();
        Scanner scan = new Scanner(System.in);
        while (running && scan.hasNextLine()) {
            String userInput = scan.nextLine();
            try {
                inputs(userInput);
            } catch (StrandException e) {
                output(e.toString());
            }
        }
    }
}
