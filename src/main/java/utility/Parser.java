package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.UserCommand;
import exception.InvalidCommandException;
import exception.InvalidStorageFileException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * The Parser class provides utility functions
 * to parse input data into useful formats
 */

public class Parser {
    /**
     * Parses a line in the storage file to the corresponding Task
     * 
     * @param line String representing a line in the storage file
     * @return Task represented by the line
     * @throws InvalidStorageFileException if the line is in an invalid format
     */
    public static Task parseStorageFileLine(String line) throws InvalidStorageFileException {
        try {
            int startIdx = 7;
            char taskType = line.charAt(1);
            Task t;
            String taskDescription;

            switch (taskType) {
            case 'T':
                taskDescription = line.substring(startIdx);
                if (taskDescription.equals("")) {
                    throw new InvalidStorageFileException();
                }
                t = new Todo(taskDescription);
                break;
            case 'D':
                String byDelimiter = " (by: ";
                int byIdx = line.indexOf(byDelimiter);
                if (byIdx == -1) {
                    throw new InvalidStorageFileException();
                }
                taskDescription = line.substring(startIdx, byIdx);
                String deadline = line.substring(byIdx + byDelimiter.length(), line.length() - 1);
                t = new Deadline(taskDescription, deadline);
                break;
            case 'E':
                String fromDelimiter = " (from: ";
                String toDelimiter = " to: ";
                int fromIdx = line.indexOf(fromDelimiter);
                int toIdx = line.indexOf(toDelimiter);
                if (fromIdx == -1 || toIdx == -1) {
                    throw new InvalidStorageFileException();
                }
                taskDescription = line.substring(startIdx, fromIdx);
                String from = line.substring(fromIdx + fromDelimiter.length(), toIdx);
                String to = line.substring(toIdx, line.length() - 1);
                t = new Event(taskDescription, from, to);
                break;
            default:
                throw new InvalidStorageFileException();
            }

            char doneLabel = line.charAt(4);
            if (doneLabel == 'X') {
                t.mark();
            }

            return t;
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidStorageFileException();
        }
    }

    /**
     * Parses user input command to generate the appropriate UserCommand to be executed
     * 
     * @param userInput String representing the user input command
     * @return UserCommand to be executed
     * @throws InvalidCommandException
     */
    public static UserCommand parseUserCommand(String userInput) throws InvalidCommandException {
        String commandName = userInput.split(" ")[0];
        return UserCommand.toCommand(commandName);
    }

    /**
     * Parses user input date from yyyy-mm-dd hhmm format to d mmm yyyy format
     *
     * @param date User input string representing the date
     * @return Output string representing date in d mmm yyyy format
     * @throws ParseException
     */
    public static String parseInputDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");

        LocalDateTime ldt = LocalDateTime.parse(date, inputFormatter);
        return ldt.format(outputFormatter);
    }
}