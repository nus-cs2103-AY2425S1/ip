package nah.storage;
import nah.data.Task;
import nah.exceptions.NahException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Decoder {
    /**
     * Return a task from a String representation
     * @param s
     * @return
     * @throws NahException
     */
    public static Task decode(String s) throws NahException {
        String[] Command = s.split("|", 2);
        if (Command[0].trim() == "T") {
            if (Command.length < 2 || Command[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            return new Task.ToDos(Command[1].trim());
        }
        if (Command[0].trim() == "D") {
            if (Command.length < 2 || Command[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            String[] des = Command[1].split("|", 2);
            try {
                LocalDateTime time = LocalDateTime.
                        parse(des[1].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
                return new Task.Deadlines(des[0].trim(), time);
            } catch (DateTimeParseException e) {
                throw new NahException.InvalidFileValueException();
            }

        }

        if (Command[0].trim() == "E") {
            if (Command.length < 2 || Command[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            String[] des = Command[1].split("|", 2);
            if (des.length < 2 || des[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            String[] time = des[1].split("|", 2);
            if (time.length < 2 || time[1].trim().isEmpty()) {
                throw new NahException.InvalidFileValueException();
            }
            try {
                LocalDateTime start = LocalDateTime.
                        parse(time[0].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
                LocalDateTime end = LocalDateTime.
                        parse(time[1].trim(), DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
                return new Task.Events(des[0].trim(), start, end);
            } catch (DateTimeParseException e) {
                throw new NahException.InvalidFileValueException();
            }
        }
        throw new NahException.InvalidFileValueException();
    }
}
