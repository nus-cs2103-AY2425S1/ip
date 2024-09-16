package parser;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatterboxexceptions.ChatterboxExceptions;
import command.AllTagsCommand;
import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TagCommand;
import command.TodoCommand;
import command.UnmarkCommand;



/**
 * Parser class has various methods to parse user input
 */
public class Parser {
    /**
     * Enum contains list of valid commands
     */
    public enum ValidCommand {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID, FIND, TAG, ALLTAGS;
    }


    private static final DateTimeFormatter DASHFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter SLASHFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private static final DateTimeFormatter DASHONLYDATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter SLASHONLYDATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter PRINTDATEFORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS =
            new DateTimeFormatter[] {DASHFORMATTER, SLASHFORMATTER, PRINTDATEFORMATTER};
    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS =
            new DateTimeFormatter[] {DASHONLYDATE, SLASHONLYDATE};


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
        return null;
    }

    /**
     * Parses a string of text to check if text has a valid command listed in ValidCommandS
     * by checking the first word
     * @param text to be parsed,
     * @return corresponding ValidCommand enum to the command in text
     */
    public ValidCommand parseCommand(String text) {



        if (text.startsWith("bye")) {
            return ValidCommand.BYE;
        } else if (text.startsWith("list")) {
            return ValidCommand.LIST;
        } else if (text.startsWith("mark")) {
            return ValidCommand.MARK;
        } else if (text.startsWith("unmark")) {
            return ValidCommand.UNMARK;
        } else if (text.startsWith("todo")) {
            return ValidCommand.TODO;
        } else if (text.startsWith("deadline")) {
            return ValidCommand.DEADLINE;
        } else if (text.startsWith("event")) {
            return ValidCommand.EVENT;
        } else if (text.startsWith("delete")) {
            return ValidCommand.DELETE;
        } else if (text.startsWith("find")) {
            return ValidCommand.FIND;
        } else if (text.startsWith("tag")) {
            return ValidCommand.TAG;
        } else if (text.startsWith("alltags")) {
            return ValidCommand.ALLTAGS;
        } else {
            return ValidCommand.INVALID;

        }

    }

    /**
     * Parses a string of text to check if text has a valid command listed in ValidCommandS
     * by checking the first word
     * @param text to be parsed,
     * @return corresponding Command object enum to the command in text
     */
    public Command parseCommandType(String text) {
        //TODO: Implement this
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
        } else if (text.startsWith("find")) {
            return new FindCommand();
        } else if (text.startsWith("tag")) {
            return new TagCommand();
        } else if (text.startsWith("alltags")) {
            return new AllTagsCommand();
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
        for (int i = 9; i < desc.length(); i++) {

            if (i < endDate) {
                plainDesc.append(desc.charAt(i));

            } else {
                if (desc.charAt(i) == '/') {
                    if (!commandEncountered) {
                        i += 3;
                        commandEncountered = true;
                    }
                }
                deadline.append(desc.charAt(i));
            }
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
        StringBuilder plainDesc = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();
        boolean fromCommandFound = false;
        boolean toCommandFound = false;
        //start with 5 to go past event
        for (int i = 5; i < desc.length(); i++) {
            if (i < fromStart) {
                plainDesc.append(desc.charAt(i));
            } else if (i < toStart) {
                if (desc.charAt(i) == '/') {
                    if (!fromCommandFound) {
                        i += 4;
                        fromCommandFound = true;
                        continue;
                    }

                }
                startDate.append(desc.charAt(i));

            } else {
                if (desc.charAt(i) == '/') {
                    if (!toCommandFound) {
                        i += 2;
                        toCommandFound = true;
                        continue;
                    }


                }
                endDate.append(desc.charAt(i));
            }
        }
        return new String[] {plainDesc.toString().trim(), startDate.toString().trim(), endDate.toString().trim()};
    }

    //    public String[] parseEvent(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {
    //
    //        int fromStart = desc.indexOf("/from");
    //        if (fromStart < 0) {
    //            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Event Start Date");
    //        }
    //
    //        int toStart = desc.indexOf("/to");
    //        if (toStart < 0) {
    //            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Event End Date");
    //        }
    //
    //        // Extract the event description
    //        String plainDesc = desc.substring(desc.indexOf('{') + 1, desc.indexOf('}')).trim();
    //
    //        // Extract the start date string after "/from"
    //        String startDate = desc.substring(fromStart + 5, toStart).trim();
    //
    //        // Extract the end date string after "/to"
    //        String endDate = desc.substring(toStart + 3).trim();
    //
    //        // Return the parsed elements
    //        return new String[] { plainDesc, startDate, endDate };
    //    }



    /**
     * Parses the tag text from a tag command
     * @param desc of format tag /i{index} /t{text}
     * @return the text after /t
     */
    public String parseTagText(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {

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
    public int parseTagIndex(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter {
        int start = desc.indexOf("/i");
        int end = desc.indexOf("/t");
        if (start < 0 || end < 0) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("tag / index missing");
        }
        if (start < 0 || end < 0) {
            end = desc.length();
        }
        return Integer.parseInt(desc.substring(start + 2, end).trim());

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
     * Returns a DateTimeFormatter used for printing LocalDateTime objects
     * @return PRINTDATEFORMATTER, standard formatter for dates in Chatterbox
     */
    public static DateTimeFormatter getPrintDateFormatter() {

        return PRINTDATEFORMATTER;
    }

}
