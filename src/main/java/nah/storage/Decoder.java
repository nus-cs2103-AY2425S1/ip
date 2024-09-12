package nah.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nah.data.Task;
import nah.exceptions.NahException;
/**
 * Handles the decoding of data of a file into a task object.
 */
public class Decoder {
    /**
     * Returns a task from a String representation.
     *
     * @param s the String representation
     * @return a Task object
     * @throws NahException if something wrong happen while processing the String
     */
    public static Task decode(String s) throws NahException {
        String[] command = s.split("\\|", 3);
        if (command[0].trim().equals("T")) {
            assert command[0].trim().equals("T") : "the command symbol should be T";
            if (command.length < 3
                    || (!command[1].trim().equals("0") && !command[1].trim().equals("1"))
                    || command[2].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            Task t = new Task.ToDos(command[2].trim());
            if (command[1].trim().equals("1")) {
                t.mark();
            }
            return t;
        }
        if (command[0].trim().equals("D")) {
            assert command[0].trim().equals("D") : "the command symbol should be D";
            if (command.length < 3
                    || (!command[1].trim().equals("0") && !command[1].trim().equals("1"))
                    || command[2].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            String[] des = command[2].split("\\|", 2);
            if (des.length < 2 || des[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            try {
                LocalDateTime time = LocalDateTime
                        .parse(des[1].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
                Task t = new Task.Deadlines(des[0].trim(), time);
                if (command[1].trim().equals("1")) {
                    t.mark();
                }
                return t;
            } catch (DateTimeParseException e) {
                throw new NahException.InvalidFileValueException();
            }

        }

        if (command[0].trim().equals("E")) {
            assert command[0].trim().equals("E") : "the command symbol should be E";
            if (command.length < 3
                    || (!command[1].trim().equals("0") && !command[1].trim().equals("1"))
                    || command[2].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }

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
                        .parse(time[0].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
                LocalDateTime end = LocalDateTime
                        .parse(time[1].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
                assert start.isBefore(end) : "Start time must be before End time";
                Task t = new Task.Events(des[0].trim(), start, end);
                if (command[1].trim().equals("1")) {
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
