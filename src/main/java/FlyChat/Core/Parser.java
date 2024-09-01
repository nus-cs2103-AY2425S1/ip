package FlyChat.Core;

import java.io.IOException;

public class Parser {

    public String parseCommand(String inputString) {
        String filteredString = inputString.replaceAll("\\s.*", "");
        return filteredString;
    }
    
    public String getTaskDescription(String inputString) {
        return inputString.replaceFirst("^\\S*\\s*", "").replaceAll("\\s*/.*", "");
    }

    public String getDeadlineEndDate(String inputString) {
        return inputString.replaceAll(".*/by\\s*", "").trim();
    }

    public String getEventStartTime(String inputString) {
        return inputString.replaceAll(".*/from\\s*|\\s*/to.*",""); 
    }

    public String getEventEndTime(String inputString) {
        return inputString.replaceAll(".*/to\\s*", "").trim();
    }

    public String getTaskTypeFromFile(String inputString) {
        return inputString.substring(0, 3);
    }

    public boolean checkTaskCompletedFromFile(String inputString) throws IOException{
        try {
            return inputString.charAt(4) == 'X';
        } catch (IndexOutOfBoundsException e) {
            throw new IOException("Save file has been corrupted. Save progress will be reset");
        }
    }

    public String getTaskDescriptionFromFile(String inputString) {
        return inputString.replaceAll(".*]\\s|\\s\\(.*", "");
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

    public int getTargetTaskIndex(String inputString) {
        return Integer.valueOf(inputString.replaceAll("[^0-9]", ""));
    }
}
