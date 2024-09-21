package flychat.core;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Contains methods involved in parsing user input.
 */
public class Parser {
    /**
     * Parses through the user input and returns the type of command requested by the user.
     *
     * @param inputString String containing user input.
     * @return The specific command requested by the user.
     */
    public String parseCommand(String inputString) {
        String filteredString = inputString.replaceAll("\\s.*", "");
        return filteredString;
    }

    /**
     * Extracts the key word to find tasks with from the user input text.
     *
     * @return The key words.
     */
    public String getKeyWord(String inputString) {
        return inputString.length() == 4 ? "" : inputString.substring(5); // check for "find" case
    }

    public String getTaskDescription(String inputString) {
        return inputString.replaceFirst("^\\S*\\s*", "").replaceAll("\\s*/.*", "");
    }

    public String getDeadlineEndDate(String inputString) {
        return inputString.replaceAll(".*/by\\s*", "").trim();
    }

    public String getEventStartTime(String inputString) {
        return inputString.replaceAll(".*/from\\s*|\\s*/to.*", "");
    }

    public String getEventEndTime(String inputString) {
        return inputString.replaceAll(".*/to\\s*", "").trim();
    }

    /**
     * Extracts the tags from the user input text.
     *
     * @return The tags.
     */
    public String[] getTags(String inputString) {
        if (!inputString.contains("#")) {
            throw new IllegalArgumentException("Please input at least 1 tag");
        }

        String tagsString = inputString.replaceAll("^.*\\s*#|\\s*#", " #").trim();
        String tagsArray[] = tagsString.split(" ");
        return tagsArray;
    }

    public String getTaskTypeFromFile(String inputString) {
        return inputString.substring(0, 3);
    }

    /**
     * Checks if the task saved in the save file has been marked as completed.
     *
     * @param inputString String containing info on the task saved in the save file.
     * @return boolean indicating whether the task has been marked.
     * @throws IOException If save file has been corrupted and cannot be read.
     */
    public boolean checkTaskCompletedFromFile(String inputString) throws IOException {
        try {
            return inputString.charAt(4) == 'X';
        } catch (IndexOutOfBoundsException e) {
            throw new IOException("Save file has been corrupted. Save progress will be reset");
        }
    }

    public String getTaskDescriptionFromFile(String inputString) {
        return inputString.replaceAll(".*]\\s|\\s/tags.*", "");
    }

    public String getDeadlineEndDateFromFile(String inputString) {
        return inputString.replaceAll(".*by:\\s|\\)", "");
    }

    public String getEventStartTimeFromFile(String inputString) {
        return inputString.replaceAll(".*from:\\s|\\sto:.*", "");
    }

    public String getEventEndTimeFromFile(String inputString) {
        return inputString.replaceAll(".*to:\\s|\\)", "");
    }

    public String[] getTagsFromFile(String inputString) {
        String tagsString = inputString.replaceAll("^.*[|\\s*].*", "");
        String tagsArray[] = tagsString.split(" ");
        return tagsArray;
    }

    public int getTargetTaskIndex(String inputString) throws InputMismatchException{
        try {
            return Integer.valueOf(inputString.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Please enter a valid task number TT");
        }
    }
}
