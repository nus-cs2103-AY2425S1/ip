package shrimp.exception;

import shrimp.utility.AnsiCode;
import shrimp.utility.Parser;

public class ShrimpException extends Exception {

    //fields used by shrimp.task, formatted
    private static final String description = AnsiCode.ITALIC + "description" + AnsiCode.RESET + AnsiCode.RED;
    private static final String by = AnsiCode.ITALIC + "by" + AnsiCode.RESET + AnsiCode.RED;
    private static final String from = AnsiCode.ITALIC + "from" + AnsiCode.RESET + AnsiCode.RED;
    private static final String to = AnsiCode.ITALIC + "to" + AnsiCode.RESET + AnsiCode.RED;

    private final String errorCode;

    ShrimpException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static class InvalidCommandException extends ShrimpException {
        private static final String errorCode = "ERR001";
        private static final String errorMessage = "I don't recognise that, can you try again?";
        public InvalidCommandException() {
            super(errorMessage, errorCode);
        }
    }

    public static class MissingArgumentException extends ShrimpException {
        private static final String errorCode = "ERR002";
        private static final String errorMessage_toDo = "I need a " + description + " to make a TODO...";
        private static final String errorMessage_mark = "You didn't indicate which task to mark...";
        private static final String errorMessage_unmark = "You didn't indicate which task to unmark...";
        private static final String errorMessage_deadline = "I need a " + description + " and a " + by + " to make a DEADLINE...";
        private static final String errorMessage_event = "I need a " + description + ", a " + from + " and a " + to + " to make an EVENT...";
        private static final String errorMessage_delete = "You didn't indicate which task to delete...";
        private static final String errorMessage_find = "I need something to search for...";
        private static final String errorMessage_default = "There seems to be an issue somewhere! :<";

        public MissingArgumentException(Parser.CommandType command) {
            super(switchErrorMessage(command), errorCode);
        }

        private static String switchErrorMessage(Parser.CommandType command) {
            return switch (command) {
                case ADD -> errorMessage_toDo;
                case MARK -> errorMessage_mark;
                case UNMARK -> errorMessage_unmark;
                case DEADLINE -> errorMessage_deadline;
                case EVENT -> errorMessage_event;
                case DELETE -> errorMessage_delete;
                case FIND  -> errorMessage_find;
                default -> errorMessage_default;
            };
        }
    }

    public static class NumberFormatException extends ShrimpException {
        private static final String errorCode = "ERR003";
        private static final String errorMessage = "Your values seems wrong, maybe try again?";
        public NumberFormatException() {
            super(errorMessage, errorCode);
        };
    }

    public static class EmptyArrayException extends ShrimpException {
        private static final String errorCode = "ERR004";
        private static final String errorMessage = "There's no tasks for me to interact with...";
        public EmptyArrayException() {
            super(errorMessage, errorCode);
        };
    }

    public static class ArrayIndexOutOfBoundException extends ShrimpException {
        private static final String errorCode = "ERR005";
        private static final String errorMessage = "I cannot find the task that you're looking for... ";
        public ArrayIndexOutOfBoundException() {
            super(errorMessage, errorCode);
        };
    }

    public static class InvalidDateTimeException extends ShrimpException {
        private static final String errorCode = "ERR006";
        private static final String errorMessage = "The datetime format you inserted is wrong (DD/MM/YYYY)... ";
        public InvalidDateTimeException() {
            super(errorMessage, errorCode);
        };
    }
}
