package nah.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nah.data.Deadlines;
import nah.data.Events;
import nah.data.Task;
import nah.data.ToDos;
import nah.exceptions.NahException;
/**
 * Handles the decoding of data of a file into a task object.
 */
public class Decoder {
    private static final String TASK_COMPLETE = "1";
    private static final String TASK_UNCOMPLETE = "0";
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";
    private static final String TODO = "T";
    private static final String TIME_PATTERN = "MMM d yyyy, h:mm a";
    /**
     * Checks if the string have the correct format for first three component,
     * in particular ".... | .... | ......"
     * @param s the string
     */
    public static boolean checkFirstThreeComponent(String s) {
        String[] command = s.split("\\|", 3);
        if (command.length < 3
                || (!command[1].trim().equals(TASK_UNCOMPLETE) && !command[1].trim().equals(TASK_COMPLETE))
                || command[2].trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the string represents complete or not complete task.
     * @param s the string to check
     * @return true or false
     * @throws NahException if the String is not TASK_COMPLETE or TASK_UNCOMPLETE
     */
    public static boolean isDone(String s) throws NahException {
        if (s.trim().equals(TASK_COMPLETE)) {
            return true;
        }
        if (s.trim().equals(TASK_UNCOMPLETE)) {
            return false;
        }
        throw new NahException.InvalidFileValueException();
    }
    /**
     * Returns a task from a String representation.
     *
     * @param s the String representation
     * @return a Task object
     * @throws NahException if something wrong happen while processing the String
     */
    public static Task decode(String s) throws NahException {
        if (!checkFirstThreeComponent(s)) {
            throw new NahException.InvalidFileValueException();
        }

        String[] command = s.split("\\|", 3);
        if (command[0].trim().equals(TODO)) {
            Task t = new ToDos(command[2].trim());
            if (isDone(command[1])) {
                t.mark();
            }
            return t;
        }
        if (command[0].trim().equals(DEADLINE)) {
            String[] des = command[2].split("\\|", 2);
            if (des.length < 2 || des[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            try {
                LocalDateTime time = LocalDateTime
                        .parse(des[1].trim(), DateTimeFormatter.ofPattern(TIME_PATTERN));
                Task t = new Deadlines(des[0].trim(), time);
                if (isDone(command[1])) {
                    t.mark();
                }
                return t;
            } catch (DateTimeParseException e) {
                throw new NahException.InvalidFileValueException();
            }

        }

        if (command[0].trim().equals(EVENT)) {
            String[] des = command[2].split("\\|", 2);
            if (des.length < 2 || des[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            String[] time = des[1].split("\\|", 2);
            if (time.length < 2 || time[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            try {
                LocalDateTime start = LocalDateTime
                        .parse(time[0].trim(), DateTimeFormatter.ofPattern(TIME_PATTERN));
                LocalDateTime end = LocalDateTime
                        .parse(time[1].trim(), DateTimeFormatter.ofPattern(TIME_PATTERN));
                assert start.isBefore(end) : "Start time must be before End time";
                Task t = new Events(des[0].trim(), start, end);
                if (isDone(command[1])) {
                    t.mark();
                }
                return t;
            } catch (DateTimeParseException e) {
                throw new NahException.InvalidFileValueException();
            }
        }
        throw new NahException.InvalidFileValueException();
    }
}
