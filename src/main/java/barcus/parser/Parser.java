package barcus.parser;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import barcus.command.AddDeadlineCommand;
import barcus.command.AddEventCommand;
import barcus.command.AddTodoCommand;
import barcus.command.Command;
import barcus.command.DeleteCommand;
import barcus.command.ExitCommand;
import barcus.command.FindCommand;
import barcus.command.ListCommand;
import barcus.command.MarkCommand;
import barcus.command.TagCommand;
import barcus.command.UnknownCommand;
import barcus.command.UnmarkCommand;
import barcus.command.UntagCommand;
import barcus.exception.BarcusException;

/**
 * Class to parse the user input
 */
public class Parser {
    private static final DateTimeFormatter FROM_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter TO_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Returns the correct command with the correct arguments
     * @param reply String of user input
     * @return Command that user needs
     * @throws BarcusException if there is an error parsing input
     */
    public static Command parse(String reply) throws BarcusException {
        String[] words = reply.split(" ");
        if (words.length < 1) {
            return parseUnknown(words);
        } else if (reply.equals("bye")) {
            return parseExit(words);
        } else if (reply.equals("list")) {
            return parseList(words);
        } else if (words[0].equals("unmark")) {
            return parseUnmark(words);
        } else if (words[0].equals("mark")) {
            return parseMark(words);
        } else if (words[0].equals("todo")) {
            return parseTodo(words);
        } else if (words[0].equals("deadline")) {
            return parseDeadline(words);
        } else if (words[0].equals("event")) {
            return parseEvent(words);
        } else if (words[0].equals("delete")) {
            return parseDelete(words);
        } else if (words[0].equals("find")) {
            return parseFind(words);
        } else if (words[0].equals("tag")) {
            return parseTag(words);
        } else if (words[0].equals("untag")) {
            return parseUntag(words);
        } else {
            return parseUnknown(words);
        }
    }

    /**
     * Parses words for Unknown command
     * @param words String array of user input
     * @return Unknown command
     */
    private static Command parseUnknown(String[] words) {
        return new UnknownCommand();
    }

    /**
     * Parses words for Exit command
     * @param words String array of user input
     * @return Exit command
     */
    private static Command parseExit(String[] words) {
        return new ExitCommand();
    }

    /**
     * Parses words for List command
     * @param words String array of user input
     * @return List command
     */
    private static Command parseList(String[] words) throws BarcusException {
        return new ListCommand();
    }

    /**
     * Parses words for unmark command
     * @param words String array of user input
     * @return Unmark command
     * @throws BarcusException When integer is not used
     */
    private static Command parseUnmark(String[] words) throws BarcusException {
        if (words.length == 1) {
            throw new BarcusException("please have an integer after 'unmark'");
        }
        try {
            int[] pos = new int[words.length - 1];
            for (int i = 1; i < words.length; i++) {
                pos[i - 1] = Integer.parseInt(words[i]);
            }
            return new UnmarkCommand(pos);
        } catch (NumberFormatException e) {
            throw new BarcusException("please have an integer after 'unmark'");
        }
    }

    /**
     * Parses words for mark command
     * @param words String array of user input
     * @return Mark command
     * @throws BarcusException When integer is not used
     */
    private static Command parseMark(String[] words) throws BarcusException {
        if (words.length == 1) {
            throw new BarcusException("please have an integer after 'mark'");
        }
        try {
            int[] pos = new int[words.length - 1];
            for (int i = 1; i < words.length; i++) {
                pos[i - 1] = Integer.parseInt(words[i]);
            }
            return new MarkCommand(pos);
        } catch (NumberFormatException e) {
            throw new BarcusException("please have an integer after 'mark'");
        }
    }

    /**
     * Parses words for todo command
     * @param words String array of user input
     * @return Todo command
     * @throws BarcusException When description is not specified
     */
    private static Command parseTodo(String[] words) throws BarcusException {
        if (words.length < 2) {
            throw new BarcusException("please include a description of the todo");
        }
        return new AddTodoCommand(String.join(" ", Arrays.copyOfRange(words, 1, words.length)));
    }

    /**
     * Parses words for deadline command
     * @param words String array of user input
     * @return Deadline command
     * @throws BarcusException When details are not specified
     */
    private static Command parseDeadline(String[] words) throws BarcusException {
        List<String> wordsList = Arrays.asList(words);
        if (!wordsList.contains("/by")) {
            throw new BarcusException("please include '/by' and deadline after it");
        }

        int byI = wordsList.indexOf("/by");

        if (byI == 1) {
            throw new BarcusException("please include a task description");
        }

        return new AddDeadlineCommand(
                String.join(" ", Arrays.copyOfRange(words, 1, byI)),
                String.join(" ", Arrays.copyOfRange(words, byI + 1, words.length)));
    }

    /**
     * Parses words for event command
     * @param words String array of user input
     * @return Event command
     * @throws BarcusException When details are not specified
     */
    private static Command parseEvent(String[] words) throws BarcusException {
        List<String> wordsList = Arrays.asList(words);
        if (!wordsList.contains("/from") || !wordsList.contains("/to")) {
            throw new BarcusException("please include '/from' and '/to' "
                    + "as well as dates after each of those words");
        }
        int fromI = wordsList.indexOf("/from");
        int toI = wordsList.indexOf("/to");

        if (fromI == 1) {
            throw new BarcusException("please include a task description");
        }

        return new AddEventCommand(
                String.join(" ", Arrays.copyOfRange(words, 1, fromI)),
                String.join(" ", Arrays.copyOfRange(words, fromI + 1, toI)),
                String.join(" ", Arrays.copyOfRange(words, toI + 1, words.length)));
    }

    /**
     * Parses words for delete command
     * @param words String array of user input
     * @return Delete command
     * @throws BarcusException When integer is not used
     */
    private static Command parseDelete(String[] words) throws BarcusException {
        if (words.length == 1) {
            throw new BarcusException("please have an integer after 'delete'");
        }
        try {
            //int[] pos = new int[words.length - 1];
            List<Integer> pos = new ArrayList<>();
            for (int i = 1; i < words.length; i++) {
                //pos[i - 1] = Integer.parseInt(words[i]);
                int temp = Integer.parseInt(words[i]);
                if (!pos.contains(temp)) {
                    pos.add(temp);
                }
            }
            Collections.sort(pos, Collections.reverseOrder());
            int[] convertedPos = new int[pos.size()];
            for (int i = 0; i < pos.size(); i++) {
                convertedPos[i] = pos.get(i);
            }
            return new DeleteCommand(convertedPos);
        } catch (NumberFormatException e) {
            throw new BarcusException("please have an integer after 'delete'");
        }
    }

    /**
     * Parses words for find command
     * @param words String array of user input
     * @return Find command
     * @throws BarcusException When description is not specified
     */
    private static Command parseFind(String[] words) throws BarcusException {
        if (words.length == 1) {
            throw new BarcusException("please include what word(s) you want to find after 'find'");
        }
        return new FindCommand(String.join(" ", Arrays.copyOfRange(words, 1, words.length)));
    }

    /**
     * Parses words for tag command
     * @param words String array of user input
     * @return Tag command
     * @throws BarcusException When index and tag are not given
     */
    private static Command parseTag(String[] words) throws BarcusException {
        if (words.length != 3) {
            throw new BarcusException("please have an integer and a single word tag after 'tag'");
        }
        try {
            int pos = Integer.parseInt(words[1]);
            return new TagCommand(pos, words[2]);
        } catch (NumberFormatException e) {
            throw new BarcusException("please have an integer and a single word tag after 'tag'");
        }
    }

    /**
     * Parses words for untag command
     * @param words String array of user input
     * @return Untag command
     * @throws BarcusException When index and tag are not given
     */
    private static Command parseUntag(String[] words) throws BarcusException {
        if (words.length != 3) {
            throw new BarcusException("please have an integer and a single word tag after 'untag'");
        }
        try {
            int pos = Integer.parseInt(words[1]);
            return new UntagCommand(pos, words[2]);
        } catch (NumberFormatException e) {
            throw new BarcusException("please have an integer and a single word tag after 'untag'");
        }
    }
}
