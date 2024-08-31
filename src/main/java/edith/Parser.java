import javafx.beans.binding.ObjectExpression;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Parser {

    /**
     * Returns command to be executed.
     * @param userInput to be deciphered
     * @return string of command
     */
    public static String getCommand(String userInput) {
        List<String> userInputs = Arrays.asList(userInput.split(" "));
        return userInputs.get(0);
    }

    /**
     * Returns the task number based on userInput
     * @param userInput to be deciphered
     * @return task number as an integer
     */
    public static int getTaskNumber(String userInput) throws MissingTaskNumberException {
        try {
            List<String> userInputs = Arrays.asList(userInput.split(" "));
            int taskNumber = Integer.parseInt(userInputs.get(1));
            return taskNumber;
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskNumberException();
        }
    }

    public static String getTaskDetails(String userInput, String taskType) throws MissingTaskNameException {
        try {
            List<String> userInputs = Arrays.asList(userInput.split(taskType + " "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskNameException();
        }
    }

    public static String getTaskName(String taskDetails, String taskType) {
        List<String> userInputs;
        if (Objects.equals(taskType, "deadline")) {
            userInputs = Arrays.asList(taskDetails.split(" /by "));
        } else {
            userInputs = Arrays.asList(taskDetails.split(" /from "));
        }
        return userInputs.get(0);
    }

    public static String getTaskDeadline(String taskDetails) throws MissingDeadlineException {
        try {
            List<String> userInputs = Arrays.asList(taskDetails.split(" /by "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingDeadlineException();
        }
    }

    public static String getTaskDuration(String taskDetails) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDetails.split(" /from "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

    public static String getTaskStart(String taskDuration) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDuration.split(" /to "));
            return userInputs.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

    public static String getTaskEnd(String taskDuration) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDuration.split(" /to "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

}
