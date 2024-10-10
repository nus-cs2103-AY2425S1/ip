package kotori.tasklist;

import java.time.LocalDate;

import kotori.parser.InvalidNumberOfArgumentException;
import kotori.storage.CorruptedFileException;



/**
 * This class represents a task list.
 * */

public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    protected Task(boolean isDone, String description) {
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
    public static Task of(String descriptions) throws MissingInformationException, InvalidInputException,
            InvalidNumberOfArgumentException {
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
            return Event.constructEvent(descriptions);
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
    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            return this.toString().equals(object.toString());
        } else {
            return false;
        }
    }

}
