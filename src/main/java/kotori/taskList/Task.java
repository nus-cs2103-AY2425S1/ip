package kotori.taskList;

import java.time.LocalDate;

import kotori.storage.CorruptedFileException;



/**
 * This class represents a task list.
 * */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    private Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Produce a corresponding task item based on the input string
     *
     * @param descriptions description of the task.
     * @return the task item produced.
     * @throws MissingInformationException if some required information is missing.
     * @throws InvalidInputException if the input is invalid.
     * */
    public static Task of(String descriptions) throws MissingInformationException, InvalidInputException {
        if (descriptions.equals("todo") || descriptions.equals("deadline") || descriptions.equals("event")) {
            throw new MissingInformationException("description", descriptions);
        }
        final int lengthOfTodo = 5;
        final int lengthOfDeadline = 9;
        final int lengthOfEvent = 6;
        final int minimalLengthWithBy = 2;
        final int minimalLengthWithFrom = 2;
        final int minimalLengthWithTo = 3;
        final int descriptionPos = 0;
        final int byPos = 1;
        final int lengthOfBy = 3;
        final int fromPos = 1;
        final int lengthOfFrom = 5;
        final int toPos = 2;
        final int lengthOfTo = 3;
        if (descriptions.startsWith("todo ")) {
            if (descriptions.length() <= lengthOfTodo || descriptions.charAt(lengthOfTodo) == ' ') {
                throw new MissingInformationException("description", "todo");
            }
            return new ToDo(false, descriptions.substring(lengthOfTodo));
        } else if (descriptions.startsWith("deadline ")) {
            if (descriptions.length() <= lengthOfDeadline || descriptions.charAt(lengthOfDeadline) == ' ') {
                throw new MissingInformationException("description", "deadline");
            }
            String[] strings = descriptions.substring(lengthOfDeadline).split("/");
            if (strings.length < minimalLengthWithBy || !strings[byPos].startsWith("by ")) {
                throw new MissingInformationException("by time", "deadline");
            }
            return new DeadLine(false, strings[descriptionPos], strings[byPos].substring(lengthOfBy));
        } else if (descriptions.startsWith("event ")) {
            if (descriptions.length() <= lengthOfEvent || descriptions.charAt(lengthOfEvent) == ' ') {
                throw new MissingInformationException("description", "event");
            }
            String[] strings = descriptions.substring(lengthOfEvent).split("/");
            if (strings.length < minimalLengthWithFrom || !strings[fromPos].startsWith("from ")) {
                throw new MissingInformationException("from time", "event");
            } else if (strings.length < minimalLengthWithTo || !strings[toPos].startsWith("to ")) {
                throw new MissingInformationException("to time", "event");
            }
            return new Event(false, strings[descriptionPos], strings[fromPos].substring(lengthOfFrom),
                    strings[toPos].substring(lengthOfTo));
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Reads the task from a string array.
     *
     * @param strings strings that represent a task in a hard disk file.
     * @return the task read form the file
     * @throws CorruptedFileException if the file is corrupted.
     * */

    public static Task read(String[] strings) throws CorruptedFileException {
        final int minimalLength = 2;
        final int typePos = 0;
        final int iconPos = 1;
        final int descriptionPos = 2;
        final int byPos = 3;
        final int fromPos = 3;
        final int toPos = 4;
        final int todoLength = 3;
        final int deadlineLength = 4;
        final int eventLength = 5;
        if (strings.length <= minimalLength) {
            throw new CorruptedFileException("");
        } else if (strings[typePos].equals("T") && strings.length == todoLength) {
            return new ToDo(parseIcon(strings[iconPos]), strings[descriptionPos]);
        } else if (strings[typePos].equals("D") && strings.length == deadlineLength) {
            return new DeadLine(parseIcon(strings[iconPos]), strings[descriptionPos], strings[byPos]);
        } else if (strings[typePos].equals("E") && strings.length == eventLength) {
            return new Event(parseIcon(strings[iconPos]), strings[descriptionPos], strings[fromPos], strings[toPos]);
        } else {
            throw new CorruptedFileException("");
        }
    }

    /**
     * Gets the storage message of the task.
     *
     * @return the storage message.
     * */

    public abstract String getStorageMessage();
    public boolean hasDescription(String description) {
        return this.description.contains(description);
    };
    /**
     * reads the status from a Icon.
     *
     * @param icon the status icon.
     * @return the status of task
     * @throws CorruptedFileException if the file is corrupted
     * */
    private static boolean parseIcon(String icon) throws CorruptedFileException {
        if (icon.equals("X")) {
            return true;
        } else if (icon.equals(" ")) {
            return false;
        } else {
            throw new CorruptedFileException("");
        }
    }

    /**
     * Gets the icon for complete status.
     *
     * @return status icon.
     * */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Marks this task.
     * */

    public void mark() throws IncorrectStateException {
        if (this.isDone) {
            throw new IncorrectStateException("mark");
        }
        this.isDone = true;
    }


    /**
     * Unmarks this task.
     * */

    public void unmark() throws IncorrectStateException {
        if (!this.isDone) {
            throw new IncorrectStateException("unmark");
        }
        this.isDone = false;
    }

    /**
     * Checks if a task is related to a time.
     *
     * @param date the date.
     * @return is related or not.
     * */

    public abstract boolean isRelatedToDate(LocalDate date);

    private static class ToDo extends Task {

        public ToDo(boolean isDone, String description) {
            super(isDone, description);
        }

        @Override
        public String toString() {
            return String.format("[T][%s] %s", getStatusIcon(), description);
        }

        /**
         * Gets the storage message of the task.
         *
         * @return the storage message.
         * */

        @Override
        public String getStorageMessage() {
            return String.format("T | %s | %s", getStatusIcon(), description);
        }

        /**
         * Checks if a task is related to a time.
         *
         * @param date the date.
         * @return is related or not  .
         * */

        @Override
        public boolean isRelatedToDate(LocalDate date) {
            return false;
        }

    }

    private static class DeadLine extends Task {
        private LocalDate deadLine;

        public DeadLine(boolean isDone, String description, String deadLine) {
            super(isDone, description);
            this.deadLine = LocalDate.parse(deadLine);
        }

        @Override
        public String toString() {
            return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, deadLine);
        }

        /**
         * Gets the storage message of the task.
         *
         * @return the storage message.
         * */

        @Override
        public String getStorageMessage() {
            return String.format("D | %s | %s | %s", getStatusIcon(), description, deadLine);
        }

        /**
         * Checks if a task is related to a time.
         *
         * @param date the date.
         * @return is related or not  .
         * */

        @Override
        public boolean isRelatedToDate(LocalDate date) {
            return date.isBefore(deadLine);
        }

    }

    private static class Event extends Task {
        private LocalDate from;
        private LocalDate to;


        private Event(boolean isDone, String description, String from, String to) {
            super(isDone, description);
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);

        }

        @Override
        public String toString() {
            return String.format("[E][%s] %s (from: %s to: %s)", getStatusIcon(), description, from, to);
        }

        /**
         * Gets the storage message of the task.
         *
         * @return the storage message.
         * */

        @Override
        public String getStorageMessage() {
            return String.format("E | %s | %s | %s | %s", getStatusIcon(), description, from, to);
        }

        /**
         * Checks if a task is related to a time.
         *
         * @param date the date.
         * @return is related or not  .
         * */

        public boolean isRelatedToDate(LocalDate date) {
            return date.isAfter(from) && date.isBefore(to);

        }






    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            return this.toString().equals(object.toString());
        } else {
            return false;
        }
    }
    //...
}
