/**
 * Provides the Parsing functionality that allows Delphi to process different types of input
 *
 * @author jordanchan
 */
import Exceptions.DelphiException;
import Exceptions.InvalidListItemException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
public class Parser {
    TaskList t;
    public class DateParser {
        /**
         * list of possible date-time formats
         */
        private static final DateTimeFormatter[] INPUT_FORMATTERS = {
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("d/MM/yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH),
        };

        /**
         * Parses a date and time string and converts it to a formatted string using default output format.
         * @param dateTimeString The date and time string to parse.
         * @return A formatted date and time string, or null if parsing fails.
         */
        public static String parseAndFormatDateTime(String dateTimeString) {
            return parseAndFormatDateTime(dateTimeString, null); // Calls the overloaded method with default format
        }

        /**
         * Parses a date and time string and converts it to a formatted string using the specified output format.
         * @param dateTimeString The date and time string to parse.
         * @param outputPattern The output format pattern.
         * @return A formatted date and time string, or null if parsing fails.
         */
        public static String parseAndFormatDateTime(String dateTimeString, String outputPattern) {
            DateTimeFormatter defaultOutputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma", Locale.ENGLISH);
            DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);

            for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
                try {
                    boolean hasTime = dateTimeString.contains(" ") && dateTimeString.split(" ").length > 1;
                    if (hasTime) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                        DateTimeFormatter outputFormatter = (outputPattern == null || outputPattern.isEmpty())
                                ? defaultOutputFormatter
                                : DateTimeFormatter.ofPattern(outputPattern, Locale.ENGLISH);
                        String formattedDateTime = dateTime.format(outputFormatter);
                        int day = dateTime.getDayOfMonth();
                        String ordinalDay = day + OrdinalSuffix.getSuffix(day);
                        formattedDateTime = formattedDateTime.replaceFirst("\\d+", ordinalDay);
                        return formattedDateTime.toLowerCase(); // Convert "AM/PM" to "am/pm"
                    } else {
                        LocalDate date = LocalDate.parse(dateTimeString, formatter);
                        DateTimeFormatter outputFormatter = (outputPattern == null || outputPattern.isEmpty())
                                ? defaultDateFormatter
                                : DateTimeFormatter.ofPattern(outputPattern, Locale.ENGLISH);
                        String formattedDateTime = date.format(outputFormatter);
                        int day = date.getDayOfMonth();
                        String ordinalDay = day + OrdinalSuffix.getSuffix(day);
                        formattedDateTime = formattedDateTime.replaceFirst("\\d+", ordinalDay);
                        return formattedDateTime.toLowerCase();
                    }
                } catch (DateTimeParseException e) {
                    // empty
                }
            }
            return dateTimeString;
        }

        /**
         * Enum representing ordinal suffixes for dates.
         */
        public enum OrdinalSuffix {
            ST("st"), ND("nd"), RD("rd"), TH("th");

            private final String suffix;

            OrdinalSuffix(String suffix) {
                this.suffix = suffix;
            }

            public String getSuffix() {
                return suffix;
            }

            /**
             * Returns the ordinal suffix for a given day of the month.
             * @param day The day of the month.
             * @return The ordinal suffix ("st", "nd", "rd", "th").
             */
            public static String getSuffix(int day) {
                if (day >= 11 && day <= 13) {
                    return TH.getSuffix();
                }
                switch (day % 10) {
                    case 1:
                        return ST.getSuffix();
                    case 2:
                        return ND.getSuffix();
                    case 3:
                        return RD.getSuffix();
                    default:
                        return TH.getSuffix();
                }
            }
        }
    }
    public Parser(TaskList t) {
        this.t = t;
    }

    public boolean parseInput(String input) {
        if (input.equals("bye")) {
            return true;
        }
        if (checkStringPrefix(input, 4, "mark")) {
            t.markTaskAsDone(Integer.parseInt(String.valueOf(input.charAt(5))));
            UI.markingTask(true, t.getTask(Integer.parseInt(String.valueOf(input.charAt(5)))));
        } else if (checkStringPrefix(input, 6, "unmark")) {
            t.markTaskAsUndone(Integer.parseInt(String.valueOf(input.charAt(7))));
            UI.markingTask(false, t.getTask(Integer.parseInt(String.valueOf(input.charAt(7)))));
        } else if (checkStringPrefix(input, 6, "delete")) {
            try {
                UI.removingTask(this.t, Integer.parseInt(String.valueOf(input.charAt(7))), t.getSize());
            } catch (InvalidListItemException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            try {
                t.addTask(input);
            } catch (DelphiException e){
                System.out.println("    " + e.getMessage());
            }
        }
        return false;
    }
    /**
     * Checks if the first part of the input matches a certain string up to a given number of characters
     * @param original
     * @param index
     * @param comparison
     * @return
     */
    public static boolean checkStringPrefix(String original, int index, String comparison) {
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
