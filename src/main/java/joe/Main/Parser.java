package joe.Main;

import joe.exceptions.CorruptedFileException;
import joe.exceptions.InvalidCommandException;
import joe.tasks.*;

public class Parser {

    private static final String splitRegex = " \\| ";
    private final TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user input and returns the respective Command object.
     *
     * @param userCmd the String representation of the user input
     * @return a Command object representing the user input
     */
    public void parseCommand(String userCmd) throws IllegalArgumentException, InvalidCommandException {
        if (userCmd.startsWith("mark")) {
            tasks.markDone(getDigits(userCmd));
        } else if (userCmd.startsWith("unmark")) {
            tasks.unmark(getDigits(userCmd));
        } else if (userCmd.startsWith("delete")) {
            tasks.deleteTask(getDigits(userCmd));
        } else if (userCmd.startsWith("todo")) {
            tasks.addTask(new ToDo(userCmd.substring(4)));
        } else if (userCmd.startsWith("deadline")) {
            String[] params = userCmd.substring(8).split(splitRegex);
            if (params.length < 2) {
                throw new IllegalArgumentException("Deadline: You did not provide a due date/time.");
            }
            tasks.addTask(new Deadline(params[0], params[1]));
        } else if (userCmd.startsWith("event")) {
            String[] params = userCmd.substring(5).split(splitRegex);
            if (params.length < 2) {
                throw new IllegalArgumentException("Event: Did not provide start and end date/time");
            } else if (params.length < 3) {
                throw new IllegalArgumentException("Event: Did not provide end date/time");
            }
            tasks.addTask(new Event(params[0], params[1], params[2]));
        } else if (userCmd.startsWith("query")) {
            tasks.queryTasksByDate(userCmd.substring(6));
        } else {
            throw new InvalidCommandException(userCmd);
        }
    }

    /**
     * Extracts the first contiguous numerical substring from a string.
     *
     * @param userCmd the String representation of the user input
     * @return the numerical substring as an integer
     */
    public int getDigits(String userCmd) {
        int idx = -1;
        int n = userCmd.length();
        for (int i = 0; i < n; i++) {
            while (i < n && Character.isDigit(userCmd.charAt(i))) {
                if (idx < 0) {
                    idx = 0;
                }
                idx = idx * 10 + (userCmd.charAt(i++) - '0');
            }
        }
        return idx;
    }

    /**
     * Parses the line from the joe.txt file and returns the respective Task object.
     *
     * @param line the line read from the joe.txt file
     * @return a Task object representing the line read
     * @throws CorruptedFileException if the file is corrupted
     */
    public static Task parseTask(String line) throws CorruptedFileException {
        String[] params = line.split(splitRegex);
        Task t;
        String type = params[0];
        boolean isDone = params[1].equals("1");

        switch (type) {
        case "T":
            t = new ToDo(params[2]);
            break;
        case "D":
            t = new Deadline(params[2], params[3]);
            break;
        case "E":
            t = new Event(params[2], params[3], params[4]);
            break;
        default:
            throw new CorruptedFileException();
        }

        t.setDone(isDone);
        System.out.println("Loaded: " + t);
        return t;
    }
}

