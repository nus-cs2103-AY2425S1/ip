package barcus.parser;

import barcus.command.*;
import barcus.exception.BarcusException;
import java.util.Arrays;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Parser {
    private final static DateTimeFormatter FROM_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final static DateTimeFormatter TO_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public static Command parse(String reply) throws BarcusException {
        String[] words = reply.split(" ");
        if (words.length < 1) {
            return new UnknownCommand();
        } else if (reply.equals("bye")) {
            return new ExitCommand();
        } else if (reply.equals("list")) {
            return new ListCommand();
        } else if (words[0].equals("unmark")) {
            if (words.length != 2) {
                throw new BarcusException("please have an integer after 'unmark'");
            } else {
                try {
                    int pos = Integer.parseInt(words[1]);
                    return new UnmarkCommand(pos);
                } catch (NumberFormatException e) {
                    throw new BarcusException("please have an integer after 'unmark'");
                }
            }
        } else if (words[0].equals("mark")) {
            if (words.length != 2) {
                throw new BarcusException("please have an integer after 'mark'");
            } else {
                try {
                    int pos = Integer.parseInt(words[1]);
                    return new MarkCommand(pos);
                } catch (NumberFormatException e) {
                    throw new BarcusException("please have an integer after 'mark'");
                }
            }
        } else if (words[0].equals("todo")) {
            if (words.length < 2) {
                throw new BarcusException("please include a description of the todo");
            } else {
                return new AddTodoCommand(String.join(" ", Arrays.copyOfRange(words, 1, words.length)));
            }
        } else if (words[0].equals("deadline")) {
            List<String> wordsList = Arrays.asList(words);
            if (!wordsList.contains("/by")) {
                throw new BarcusException("please include '/by' and deadline after it");
            } else {
                int byI = wordsList.indexOf("/by");
                return new AddDeadlineCommand(
                        String.join(" ", Arrays.copyOfRange(words, 1, byI)),
                        String.join(" ", Arrays.copyOfRange(words, byI + 1, words.length)));
            }
        } else if (words[0].equals("event")) {
            List<String> wordsList = Arrays.asList(words);
            if (!wordsList.contains("/from") || !wordsList.contains("/to")) {
                throw new BarcusException("please include '/from' and '/to' as well as dates after each of those words");
            } else {
                int fromI = wordsList.indexOf("/from");
                int toI = wordsList.indexOf("/to");
                return new AddEventCommand(
                        String.join(" ", Arrays.copyOfRange(words, 1, fromI)),
                        String.join(" ", Arrays.copyOfRange(words, fromI + 1, toI)),
                        String.join(" ", Arrays.copyOfRange(words, toI + 1, words.length)));
            }
        } else if (words[0].equals("delete")) {
            if (words.length != 2) {
                throw new BarcusException("please have an integer after 'delete'");
            } else {
                try {
                    int pos = Integer.parseInt(words[1]);
                    return new DeleteCommand(pos);
                } catch (NumberFormatException e) {
                    throw new BarcusException("please have an integer after 'delete'");
                }
            }
        }

        return new UnknownCommand();
    }
}
