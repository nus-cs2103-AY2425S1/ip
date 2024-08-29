package Parser; /**
 * Provides the Parsing functionality that allows Delphi to process different types of input
 *
 * @author jordanchan
 */

import Commands.*;
import Exceptions.DelphiException;
import Exceptions.InvalidInputException;
import Exceptions.InvalidListItemException;
import TaskList.TaskList;
import UI.UI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
public class Parser {
    //TaskList t;

    public Parser() {
        //this.t = t;
    }

    public Command parseInput(String input) throws DelphiException {
        if (input.equals("bye")) {
            return new BreakCommand();
        }
        if (checkStringPrefix(input, 4, "mark")) {
            return new MarkTaskCommand(input);
            //t.markTaskAsDone(Integer.parseInt(String.valueOf(input.charAt(5))));
            //UI.markingTask(true, t.getTask(Integer.parseInt(String.valueOf(input.charAt(5)))));
        } else if (checkStringPrefix(input, 6, "unmark")) {
            return new UnmarkTaskCommand(input);
            //t.markTaskAsUndone(Integer.parseInt(String.valueOf(input.charAt(7))));
            //UI.markingTask(false, t.getTask(Integer.parseInt(String.valueOf(input.charAt(7)))));
        } else if (checkStringPrefix(input, 6, "delete")) {
            return new DeleteTaskCommand(input);
            /*
            try {
                UI.removingTask(this.t, Integer.parseInt(String.valueOf(input.charAt(7))), t.getSize());
            } catch (InvalidListItemException e) {
                System.out.println(e.getMessage());
            }
             */
        } else if (input.equals("list")) {
            return new ListCommand(input);
        } else if (checkStringPrefix(input, 4, "todo")) {
            return new AddTodoCommand(input);
        } else if (checkStringPrefix(input, 8, "deadline")) {
            return new AddDeadlineComand(input);
        } else if (checkStringPrefix(input, 5, "event")) {
            return new AddEventCommand(input);
        } else {
            throw new InvalidInputException();
        }
    }


/**
 * Checks if the first part of the input matches a certain string up to a given number of characters
 * @param original
 * @param index
 * @param comparison
 * @return
 */
public boolean checkStringPrefix(String original, int index, String comparison) {
    // Ensure the index is within the bounds of the original string
    if (index > original.length()) {
        index = original.length();
    }

    // Get the substring from the original string up to the specified index
    String prefix = original.substring(0, index);

    // Compare the prefix with the comparison string
    return prefix.equals(comparison);
}

public static String formatStringDeadline(String input) {
    // Extract the parts using regex
    String regex = "(.*) \\(by: (.*)\\)";
    String result = input.replaceAll(regex, "$1 /by $2");
    return result;
}

public static String formatStringEvent(String input) {
    // Extract the parts using regex
    String regex = "(.*) \\(from: (.*) to: (.*)\\)";
    String result = input.replaceAll(regex, "$1 /from $2 /to $3");
    return result;
}
}
