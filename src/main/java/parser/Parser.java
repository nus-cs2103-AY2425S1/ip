package parser;

import chatterboxexceptions.ChatterboxExceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class has various methods to parse user input
 */
public  class Parser {
    public enum ValidCommand {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;
    }

    private static final DateTimeFormatter DASHFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter SLASHFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private static final DateTimeFormatter DASHONLYDATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter SLASHONLYDATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter PRINTDATEFORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = new DateTimeFormatter[] {DASHFORMATTER, SLASHFORMATTER, PRINTDATEFORMATTER};
    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS = new DateTimeFormatter[] {DASHONLYDATE, SLASHONLYDATE};


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

            }
        }
        return null;
    }

    /**
     * Parses a string of text to check if text has a valid command listed in ValidCommandS by checking sthe started of text
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
        } else {
            return ValidCommand.INVALID;
        }

    }

    /**
     * Extracts the index for mark and unmark
     * @param input of format mark/unmark {int}
     * @return the int in the input string
     */
    public int extractNum(String input) {
        int length = input.length();
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
    public String parseTODO(String desc) {
        return desc.substring(4).trim();
    }

    /** parses a string to obtain text for deaadline without white space
     *
     * @param desc of format deadline text /by text
     * @return String[] with the [0] as the description and [1] as by {text}
     * @throws chatterboxexceptions.ChatterboxExceptions.ChatterBoxNoInput if no text is found
     */
    public String[] parseDeadline(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter{


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
                        i += 2;
                        commandEncountered = true;
                        continue;

                    }


                }
                deadline.append(desc.charAt(i));
            }
        }
        return new String[] {plainDesc.toString().trim(), deadline.toString().trim()} ;
    }

    /** Parses the event string for the desc, from and to time
     *
     * @param desc takes in a string of format text /from text /to text
     * @return String[] with 0 being the first text, 1 the from text and 2 being the to text
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter if any of parameters not detected
     */
    public String[] parseEvent(String desc) throws ChatterboxExceptions.ChatterBoxMissingParameter{


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

    /**
     * Returns a DateTimeFormatter used for printing LocalDateTime objects
     * @return PRINTDATEFORMATTER, standard formatter for dates in Chatterbox
     */
    public static DateTimeFormatter getPrintDateFormatter() {

        return PRINTDATEFORMATTER;
    }

}