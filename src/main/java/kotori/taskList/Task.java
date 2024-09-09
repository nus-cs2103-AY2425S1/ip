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
        if (descriptions.startsWith("todo ")) {
            if (descriptions.length() <= 5 || descriptions.charAt(5) == ' ') {
                throw new MissingInformationException("description", "todo");
            }
            return new ToDo(false, descriptions.substring(5));
        } else if (descriptions.startsWith("deadline ")) {
            if (descriptions.length() <= 9 || descriptions.charAt(9) == ' ') {
                throw new MissingInformationException("description", "deadline");
            }
            String[] strings = descriptions.substring(9).split("/");
            if (strings.length < 2 || !strings[1].startsWith("by ")) {
                throw new MissingInformationException("by time", "deadline");
            }
            return new DeadLine(false, strings[0], strings[1].substring(3));
        } else if (descriptions.startsWith("event ")) {
            if (descriptions.length() <= 6 || descriptions.charAt(6) == ' ') {
                throw new MissingInformationException("description", "event");
            }
            String[] strings = descriptions.substring(6).split("/");
            if (strings.length < 2 || !strings[1].startsWith("from ")) {
                throw new MissingInformationException("from time", "event");
            } else if (strings.length < 3 || !strings[2].startsWith("to ")) {
                throw new MissingInformationException("to time", "event");
            }
            return new Event(false, strings[0], strings[1].substring(5),
                    strings[2].substring(3));
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
        if (strings.length <= 2) {
            throw new CorruptedFileException("");
        } else if (strings[0].equals("T") && strings.length == 3) {
            return new ToDo(parseIcon(strings[1]), strings[2]);
        } else if (strings[0].equals("D") && strings.length == 4) {
            return new DeadLine(parseIcon(strings[1]), strings[2], strings[3]);
        } else if (strings[0].equals("E") && strings.length == 5) {
            return new Event(parseIcon(strings[1]), strings[2], strings[3], strings[4]);
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
