package parser;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import chatterboxexceptions.ChatterboxExceptions;
import command.AllTagsCommand;
import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.FindTagCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.RemoveTagCommand;
import command.TagCommand;
import command.TodoCommand;
import command.UnmarkCommand;



/**
 * Parser class has various methods to parse user input
 */
public class Parser {

    private static final DateTimeFormatter DASHFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter SLASHFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter SINGLEDIGITDATE = new DateTimeFormatterBuilder()
            .appendPattern("d/M/yyyy") // Single or double-digit day and month
            .toFormatter()
            .withResolverStyle(ResolverStyle.SMART);

    private static final DateTimeFormatter SINGLEDIGITDATETIME = new DateTimeFormatterBuilder()
            .appendPattern("d/M/yyyy") // Single or double-digit day and month
            .appendPattern(" HHmm")    // Time in 24-hour format, without a separator
            .toFormatter()
            .withResolverStyle(ResolverStyle.SMART);
    private static final DateTimeFormatter DASHONLYDATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter SLASHONLYDATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter PRINTDATEFORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS =
            new DateTimeFormatter[] {DASHFORMATTER, SLASHFORMATTER, PRINTDATEFORMATTER, SINGLEDIGITDATETIME};
    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS =
            new DateTimeFormatter[] {DASHONLYDATE, SLASHONLYDATE, SINGLEDIGITDATE};


    /**
     * Takes in a string, trims it, and parses LocalDateTime if possible, if not null
     *
     * @param dateTimeString String that should contain a date time
     * @return LocalDateTime representing time in string
     *
     */

    public LocalDateTime parseDateTime(String dateTimeString) {

        dateTimeString = dateTimeString.trim();

        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {

                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                //do nothing, try next one
            }
        }
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            try {
                return LocalDate.parse(dateTimeString, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                //do nothing try next one
            }
        }
        //if no matching format is found

        return null;
    }



    /**
     * Parses a string of text to check if text has a valid command listed in ValidCommandS
     * by checking the first word
     * @param input to be parsed,
     * @return corresponding Command object enum to the command in text
     */
    public Command parseCommandType(String input) {
        String text = input.trim().toLowerCase();
        if (text.startsWith("bye")) {
            return new ByeCommand();
        } else if (text.startsWith("list")) {
            return new ListCommand();
        } else if (text.startsWith("mark")) {
            return new MarkCommand();
        } else if (text.startsWith("unmark")) {
            return new UnmarkCommand();
        } else if (text.startsWith("todo")) {
            return new TodoCommand();
        } else if (text.startsWith("deadline")) {
            return new DeadlineCommand();
        } else if (text.startsWith("event")) {
            return new EventCommand();
        } else if (text.startsWith("delete")) {
            return new DeleteCommand();
        } else if (text.startsWith("findtag")) {
            return new FindTagCommand();
        } else if (text.startsWith("find")) {
            return new FindCommand();
        } else if (text.startsWith("tag")) {
            return new TagCommand();
        } else if (text.startsWith("alltags")) {
            return new AllTagsCommand();
        } else if (text.startsWith("removetag")) {
            return new RemoveTagCommand();
        } else {
            return new InvalidCommand();

        }
    }
    /**
     * Extracts the index for mark and unmark
     * @param input of format mark/unmark {int}
     * @return the int in the input string
     */
    public int extractNum(String input) {
        assert input != null;

        int length = input.length();
        assert length > 0;
        StringBuilder numberBuild = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            char currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                numberBuild.insert(0, currentChar);
            } else {
                break;
            }
        }
        return Integer.parseInt(numberBuild.toString());
    }

    /**
     * parses a string to obtain text for todo without white space
     * @param desc of format todo {text}
     * @return the text description
     */
    public String parseTodo(String desc) {
        return desc.substring(4).trim();
    }

    /**
     * parses a string to obtain text for deaadline without white space
     *
     * @param desc of format deadline text /by text
     * @return String[] with the [0] as the description and [1] as by {text}
     * @throws chatterboxexceptions.ChatterboxExceptions.ChatterBoxNoInput if no text is found
     */
    public String[] parseDeadline(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {


        int endDate = desc.indexOf("/by");
        if (endDate < 0) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Deadline date");
        }
        StringBuilder plainDesc = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        boolean commandEncountered = false;
        for (int i = 8; i < desc.length(); i++) {

            if (i < endDate) {
                plainDesc.append(desc.charAt(i));
                continue;

            }
            if (desc.charAt(i) == '/' && !commandEncountered) {
                i += 2;
                commandEncountered = true;
                continue;
            }
            deadline.append(desc.charAt(i));

        }
        return new String[] {plainDesc.toString().trim(), deadline.toString().trim()};
    }


    /**
     * Parses the event string for the desc, from and to time
     *
     * @param desc takes in a string of format text /from text /to text
     * @return String[] with 0 being the first text, 1 the from text and 2 being the to text
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter if any of parameters not detected
     */
    public String[] parseEvent(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {


        int fromStart = desc.indexOf("/from");
        if (fromStart < 0) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Event Start Date");
        }
        int toStart = desc.indexOf("/to");
        if (toStart < 0) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Event End Date");
        }
        if (toStart < fromStart) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Wrong argument order");
        }
        StringBuilder plainDesc = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();
        boolean fromCommandFound = false;
        boolean toCommandFound = false;
        //start with 5 to go past event
        for (int i = 5; i < desc.length(); i++) {
            if (i < fromStart) {
                plainDesc.append(desc.charAt(i));
                continue;
            }
            if (i < toStart) {
                if (desc.charAt(i) == '/' && !fromCommandFound) {
                    i += 4;
                    fromCommandFound = true;
                    continue;
                }
                startDate.append(desc.charAt(i));
                continue;
            }


            if (desc.charAt(i) == '/' && !toCommandFound) {
                i += 2;
                toCommandFound = true;
                continue;
            }
            endDate.append(desc.charAt(i));
        }

        return new String[] {plainDesc.toString().trim(), startDate.toString().trim(), endDate.toString().trim()};
    }

    /**
     * Parses the tag name from a tag command
     * @param desc of format findtag {text}
     * @return the text
     */
    public String findTagParseTagName(String desc) {
        return desc.substring(7).trim();
    }



    /**
     * Parses the tag text from a tag command
     * @param desc of format tag /i{index} /t{text}
     * @return the text after /t
     */
    public String tagCommandParseTagName(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {

        int start = desc.indexOf("/t");
        if (start < 0) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Tag text missing");
        }

        return desc.substring(start + 2).trim();
    }

    /**
     * Parses the tag index from a tag command
     * @param desc of format tag /i{index} /t{text}
     * @return the index after /i
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter if no index is found
     */
    public int tagCommandParseTaskIndex(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {
        int start = desc.indexOf("/i");
        int end = desc.indexOf("/t");
        if (start < 0 || end < 0) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("tag / index missing");
        }
        String sub = desc.substring(start + 2, end).trim();
        if (sub.isEmpty() || !sub.matches("\\d+")) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("missing index");
        }

        return Integer.parseInt(sub.trim());

    }


    /**
     * Parses for keywords in a find command
     * @param command starting with find
     * @return the keywords after find
     */
    public String parseFind(String command) {
        return command.substring(4);
    }


    /**
     * Parses for index in a delete command
     * @param desc input of format removetag /i {index} /t {text}
     * @return the index of task to delete tag from
     */
    public int parseRemoveTagIndex(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {
        int start = desc.indexOf("/i");
        int end = desc.indexOf("/t");
        if (start > end) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Wrong argument order");
        }

        if (start < 0 || end < 0) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Tag input missing");
        }

        String sub = desc.substring(start + 2, end).trim();
        if (sub.isEmpty() || !sub.matches("\\d+")) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("missing tag index");
        }

        return Integer.parseInt(sub);
    }

    /**
     * Parses for tag name in a remove tag command
     * @param desc input of format removetag /i {index} /t {text}
     * @return the tag name
     */
    public String parseRemoveTagName(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {
        int start = desc.indexOf("/t");
        if (start < 0) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Tag text missing");
        }
        return desc.substring(start + 2).trim();
    }
    /**
     * Returns a DateTimeFormatter used for printing LocalDateTime objects
     * @return PRINTDATEFORMATTER, standard formatter for dates
     */
    public static DateTimeFormatter getPrintDateFormatter() {

        return PRINTDATEFORMATTER;
    }

}
