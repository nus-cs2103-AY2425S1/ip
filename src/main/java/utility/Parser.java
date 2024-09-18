package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private static final int START_IDX = 7;
    private static final int DONE_IDX = 4;
    private static Todo parseTodoFromStorageFileLine(String line) throws InvalidStorageFileException {
        String taskDescription = line.substring(Parser.START_IDX);
        if (taskDescription.equals("")) {
            throw new InvalidStorageFileException();
        }

        return new Todo(taskDescription);
    }

    private static Deadline parseDeadlineFromStorageFileLine(String line) throws InvalidStorageFileException {
        String byDelimiter = " (by: ";
        int byIdx = line.indexOf(byDelimiter);
        if (byIdx == -1) {
            throw new InvalidStorageFileException();
        }
        String taskDescription = line.substring(Parser.START_IDX, byIdx);
        String deadline = line.substring(byIdx + byDelimiter.length(), line.length() - 1);

        return new Deadline(taskDescription, deadline);
    }

    private static Event parseEventFromStorageFileLine(String line) throws InvalidStorageFileException {
        String fromDelimiter = " (from: ";
        String toDelimiter = " to: ";
        int fromIdx = line.indexOf(fromDelimiter);
        int toIdx = line.indexOf(toDelimiter);
        if (fromIdx == -1 || toIdx == -1) {
            throw new InvalidStorageFileException();
        }
        String taskDescription = line.substring(Parser.START_IDX, fromIdx);
        String from = line.substring(fromIdx + fromDelimiter.length(), toIdx);
        String to = line.substring(toIdx, line.length() - 1);

        return new Event(taskDescription, from, to);
    }

    /**
     * Parses a line in the storage file to the corresponding Task
     * 
     * @param line String representing a line in the storage file
     * @return Task represented by the line
     * @throws InvalidStorageFileException if the line is in an invalid format
     */
    public static Task parseStorageFileLine(String line) throws InvalidStorageFileException {
        try {
            char taskType = line.charAt(1);

            Task t;
            switch (taskType) {
            case 'T':
                t = Parser.parseTodoFromStorageFileLine(line);
                break;
            case 'D':
                t = Parser.parseDeadlineFromStorageFileLine(line);
                break;
            case 'E':
                t = Parser.parseEventFromStorageFileLine(line);
                break;
            default:
                throw new InvalidStorageFileException();
            }

            char doneLabel = line.charAt(Parser.DONE_IDX);
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

    /**
     * Splits user input sentence into substrings by tokens
     *
     * @param userInput String representing user input
     * @param tokens Array of tokens to split user input by
     * @return Array of substrings that corresponds to each token's value
     */
    public static String[] parseInputByTokens(String userInput, String[] tokens) {
        String[] words = userInput.split(" ");
        List<String> wordList = Arrays.asList(words);
        List<Integer> indices = Arrays
                .stream(tokens)
                .map(token -> wordList.indexOf(token))
                .collect(Collectors.toList());

        String[] tokenValues = new String[tokens.length + 1];
        int lastTokenIdx = 0;
        int prev = 1;
        for (int i = 0; i < indices.size(); i++) {
            int currIdx = indices.get(i);
            if (currIdx == -1 || currIdx < prev) {
                tokenValues[i + 1] = "";
            } else {
                tokenValues[lastTokenIdx] = String.join(" ", Arrays.copyOfRange(words, prev, currIdx));
                prev = currIdx + 1;
                lastTokenIdx = i + 1;
            }
        }
        tokenValues[lastTokenIdx] = String.join(" ", Arrays.copyOfRange(words, prev, words.length));

        return tokenValues;
    }
}