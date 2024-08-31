package utility;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import command.UserCommand;
import exception.*;
import task.*;

/**
 * The Parser class provides utility functions
 * to parse input data into useful formats
 */

public class Parser {
    /**
     * Parses a line in the storage file to the corresonding Task
     * @param line String representing a line in the storage file
     * @return Task represented by the line
     * @throws InvalidStorageFileException if the line is in an invalid format
     */
    public static Task parseStorageFileLine(String line) throws InvalidStorageFileException {
        try {
            char START_IDX = 7;
            char taskType = line.charAt(1);
            Task t;
            String taskDescription;
            
            switch (taskType) {
                case 'T':
                    taskDescription = line.substring(START_IDX);
                    if (taskDescription.equals("")) {
                        throw new InvalidStorageFileException();
                    }
                    t = new Todo(taskDescription);
                    break;
                case 'D':
                    String byDelimeter = " (by: ";
                    int byIdx = line.indexOf(byDelimeter);
                    if (byIdx == -1) {
                        throw new InvalidStorageFileException();
                    }
                    taskDescription = line.substring(START_IDX, byIdx);
                    String deadline = line.substring(byIdx + byDelimeter.length(), line.length() - 1);
                    t = new Deadline(taskDescription, deadline);
                    break;
                case 'E':
                    String fromDelimeter = " (from: ";
                    String toDelimeter = " to: ";
                    int fromIdx = line.indexOf(fromDelimeter);
                    int toIdx = line.indexOf(toDelimeter);
                    if (fromIdx == -1 || toIdx == -1) {
                        throw new InvalidStorageFileException();
                    }
                    taskDescription = line.substring(START_IDX, fromIdx);
                    String from = line.substring(fromIdx + fromDelimeter.length(), toIdx);
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
     * @param userInput String representing the user input command
     * @return UserCommand to be executed
     * @throws InvalidCommandException
     */
    public static UserCommand parseUserCommand(String userInput) throws InvalidCommandException {
        String commandName = userInput.split(" ")[0];
        return UserCommand.toCommand(commandName);
    }

    /**
     * Parses user input date from yyyy-mm-dd hhmm format to d mmm yyyy hhmm format
     * @param date User input string representing the date
     * @return Output string representing date in d mmm yyyy format
     * @throws ParseException if an invalid date is given
     */
    public static String parseInputDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        
        LocalDateTime ldt = LocalDateTime.parse(date, inputFormatter);
        return ldt.format(outputFormatter);
    }
}