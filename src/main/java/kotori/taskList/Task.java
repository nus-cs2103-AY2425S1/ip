package kotori.taskList;

import java.time.LocalDate;

import kotori.storage.CorruptedFileException;



/**
 * This class represents a task list.
 * */

public abstract class Task implements Comparable<Task> {
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
        final int lengthOfEvent = 6;
        final int minimalLengthWithFrom = 2;
        final int minimalLengthWithTo = 3;
        final int descriptionPos = 0;
        final int fromPos = 1;
        final int lengthOfFrom = 5;
        final int toPos = 2;
        final int lengthOfTo = 3;
        if (descriptions.startsWith("todo ")) {
            return ToDo.constructToDo(descriptions);
        } else if (descriptions.startsWith("deadline ")) {
            return DeadLine.constructDeadLine(descriptions);
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
            throw new CorruptedFileException();
        } else if (strings[typePos].equals("T") && strings.length == todoLength) {
            return new ToDo(parseIcon(strings[iconPos]), strings[descriptionPos]);
        } else if (strings[typePos].equals("D") && strings.length == deadlineLength) {
            return new DeadLine(parseIcon(strings[iconPos]), strings[descriptionPos], strings[byPos]);
        } else if (strings[typePos].equals("E") && strings.length == eventLength) {
            return new Event(parseIcon(strings[iconPos]), strings[descriptionPos], strings[fromPos], strings[toPos]);
        } else {
            throw new CorruptedFileException();
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
            throw new CorruptedFileException();
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
        private static final int keyWordLength = 5;

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
        /**
         * Compare urgency with another task
         * */
        @Override
        public int compareTo(Task t) {
            if (t instanceof DeadLine || t instanceof Event) {
                return 1;
            } else {
                return 0;
            }
        }
        /**
         * Produce a instance of ToDo based on the description
         * @param descriptions description of task.
         * @return the task produced
         * @throws MissingInformationException if there is information missing in the description
         * */
        private static ToDo constructToDo(String descriptions) throws MissingInformationException {
            if (isMissingDescription(descriptions)) {
                throw new MissingInformationException("description", "todo");
            }
            return new ToDo(false, skipKeyWord(descriptions));
        }

        private static boolean isMissingDescription(String descriptions) {
            return descriptions.length() <= keyWordLength || descriptions.charAt(keyWordLength) == ' ';
        }

        private static String skipKeyWord(String descriptions) {
            return descriptions.substring(keyWordLength);
        }

    }

    private static class DeadLine extends Task {
        private static final int deadlineKeyWordLength = 9;
        private static final int byKeyWordLength = 3;
        private static final int byTimePos = 1;
        private static final int descriptionPos = 0;
        private static final int expectedParts = 2;
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

        /**
         * Compare urgency with another task
         * */
        @Override
        public int compareTo(Task t) {
            if (t instanceof DeadLine) {
                if (this.deadLine.isBefore(((DeadLine) t).deadLine)) {
                    return -1;
                } else if (((DeadLine) t).deadLine.isBefore(this.deadLine)) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (t instanceof Event) {
                if (this.deadLine.isBefore(((Event) t).to)) {
                    return -1;
                } else if (((Event) t).to.isBefore(this.deadLine)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return -1;
            }
        }
        /**
         * Constructs a deadline object based on the String input
         * @param descriptions string input.
         * @return The deadline object.
         * @throws MissingInformationException if there is information missing.
         * */
        private static DeadLine constructDeadLine(String descriptions) throws MissingInformationException {
            String[] splittedInput = splitInputs(descriptions);
            return new DeadLine(false, splittedInput[descriptionPos],
                    splittedInput[byTimePos].substring(byKeyWordLength));
        }
        /**
         * Split the String input
         * @param descriptions string input.
         * @return The splitted string.
         * @throws MissingInformationException if there is information missing.
         * */
        private static String[] splitInputs(String descriptions) throws MissingInformationException {
            if (isMissingDescription(descriptions)) {
                throw new MissingInformationException("description", "deadline");
            }
            String[] strings = descriptions.substring(deadlineKeyWordLength).split("/");
            if (isMissingByTime(strings)) {
                throw new MissingInformationException("by time", "deadline");
            }
            return strings;
        }
        /**
         * Check is the input Missing description.
         * @param descriptions the input.
         * @return is Missing description.
         * */
        private static boolean isMissingDescription(String descriptions) {
            return descriptions.length() <= deadlineKeyWordLength || descriptions.charAt(deadlineKeyWordLength) == ' ';
        }
        /**
         * Check is the input Missing by time.
         * @param splittedInputs the input.
         * @return is Missing by time.
         * */
        private static boolean isMissingByTime(String[] splittedInputs) {
            return splittedInputs.length < expectedParts || !splittedInputs[byTimePos].startsWith("by ");
        }
    }

    private static class Event extends Task {
        private static final int lengthOfEvent = 6;
        private static final int minimalLengthWithFrom = 2;
        private static final int minimalLengthWithTo = 3;
        private static final int descriptionPos = 0;
        private static final int fromPos = 1;
        private static final int lengthOfFrom = 5;
        private static final int toPos = 2;
        private static final int lengthOfTo = 3;
        private LocalDate from;
        private LocalDate to;
        /**
         * Construct a Event object.
         * */
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
        @Override
        public int compareTo(Task t) {
            if (t instanceof DeadLine) {
                if (this.to.isBefore(((DeadLine) t).deadLine)) {
                    return -1;
                } else if (((DeadLine) t).deadLine.isBefore(this.to)) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (t instanceof Event) {
                if (this.to.isBefore(((Event) t).to)) {
                    return -1;
                } else if (((Event) t).to.isBefore(this.to)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return -1;
            }
        }

        private static Event constructEvent(String descriptions) throws MissingInformationException {
            String[] splittedString = splitInputs(descriptions);
            return new Event(false, splittedString[descriptionPos],
                    splittedString[fromPos].substring(lengthOfFrom),
                    splittedString[toPos].substring(lengthOfTo));
        }

        /**
         * Split the String input
         * @param descriptions string input.
         * @return The splitted string.
         * @throws MissingInformationException if there is information missing.
         * */
        private static String[] splitInputs(String descriptions) throws MissingInformationException {
            if (isMissingDescription(descriptions)) {
                throw new MissingInformationException("description", "event");
            }
            String splitter = "/";
            String[] splittedInput = descriptions.substring(lengthOfEvent).split(splitter);
            if (isMissingFromTime(splittedInput)) {
                throw new MissingInformationException("from time", "event");
            } else if (isMissingToTime(splittedInput)) {
                throw new MissingInformationException("to time", "event");
            }
            return splittedInput;
        }
        /**
         * Check is the input Missing description
         * @param descriptions the input.
         * @return is Missing description.
         * */
        private static boolean isMissingDescription(String descriptions) {
            return descriptions.length() <= lengthOfEvent || descriptions.charAt(lengthOfEvent) == ' ';
        }
        /**
         * Check is the input Missing from time.
         * @param splittedInputs the input.
         * @return is Missing from time.
         * */
        private static boolean isMissingFromTime(String[] splittedInputs) {
            return splittedInputs.length < minimalLengthWithFrom || !splittedInputs[fromPos].startsWith("from ");
        }
        /**
         * Check is the input Missing to time.
         * @param splittedInputs the input.
         * @return is Missing to time.
         * */
        private static boolean isMissingToTime(String[] splittedInputs) {
            return splittedInputs.length < minimalLengthWithTo || !splittedInputs[toPos].startsWith("to ");
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

}
