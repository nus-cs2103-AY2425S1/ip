package kotori.tasklist;

import java.time.LocalDate;
/**
 * This class represent an object of todo object.
 * */
class ToDo extends Task {
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
    protected static ToDo constructToDo(String descriptions) throws MissingInformationException {
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
