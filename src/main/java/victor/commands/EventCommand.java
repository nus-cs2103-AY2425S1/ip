package victor.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import victor.messages.ReturnMessage;
import victor.tasks.Event;

/**
 * Event command extends command class, ensures valid input and creates event object.
 */
public class EventCommand extends Command {
    private Event event;
    public EventCommand(String[] additionalInput) {
        super(additionalInput);
    }

    /**
     * Overrides the execute method from the Command class. Processes user input and handles
     * inputs with invalid date formats by prompting the user to enter a valid range and format of dates.
     * Calls the addTask method of task list to add the task to the program-wide task list.
     * @return A return message with the event action summary (successful) or a prompt to the user (unsuccessful).
     */
    @Override
    public ReturnMessage execute() {
        String[] eventParts = getEventParts();
        String taskNameString = eventParts[0];
        String startString = eventParts[1];
        String endString = eventParts[2];

        try {
            if (taskNameString.isBlank()) {
                return new ReturnMessage("  ~  Please give a name for the event.",
                        "  ~  The format should be \"event"
                        + " (description) /from (start, in format yyyy-mm-dd or dd-mm-yyyy) /to"
                        + " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
            } else if (startString.isBlank()) {
                return new ReturnMessage("  ~  Please give a start time for the event.",
                        "  ~  The format should be \"event"
                        + " (description) /from (start, in format yyyy-mm-dd or dd-mm-yyyy) /to"
                        + " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
            } else if (endString.isBlank()) {
                return new ReturnMessage("  ~  Please give an end time for the event.",
                        "  ~  The format should be \"event"
                        + " (description) /from (start, in format yyyy-mm-dd or dd-mm-yyyy) /to"
                        + " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
            }
            this.event = new Event(taskNameString, startString, endString);
            return new ReturnMessage(super.taskList.addTask(event));
        } catch (DateTimeParseException parseException) {
            return new ReturnMessage("  ~  Please format the event as \"event"
                + " (description) /from (start, in format yyyy-mm-dd or dd-mm-yyyy) /to"
                + " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
        } catch (DateTimeException dateException) {
            return new ReturnMessage("  ~  Please ensure end time is later than start time!",
                    "  ~  Format the event as \"event (description) /from"
                        + " (start, in format yyyy-mm-dd or dd-mm-yyyy) /to"
                        + " (end, in format yyyy-mm-dd or dd-mm-yyyy)\"");
        }
    }

    /**
     * Extracts parts of additionalInput to get task name, start, and end.
     * @return A string array containing task name, start, and end.
     */
    private String[] getEventParts() {
        String taskNameString = "";
        String startString = "";
        String endString = "";

        boolean isStart = false;
        boolean isEnd = false;

        for (int i = 1; i < additionalInput.length; i++) {
            if (additionalInput[i].startsWith("/")) {
                if (isStart) {
                    isEnd = true;
                } else {
                    isStart = true;
                }
                continue;
            }
            if (!isStart) {
                taskNameString += " " + additionalInput[i];
            } else if (!isEnd) {
                startString += " " + additionalInput[i];
            } else {
                endString += " " + additionalInput[i];
            }
        }
        // Trim so that blank space cannot be counted as name for task, start or end
        taskNameString = taskNameString.trim();
        startString = startString.trim();
        endString = endString.trim();
        return new String[] {taskNameString, startString, endString};
    }

    /**
     * Overrides the generic write method in the parent Command class. Handles the case where the event is null
     * (has not been set or incorrectly generated) by not writing anything. Otherwise, calls the writeToFile method
     * from the TaskList class with the given file path. Appends to file instead of overwriting.
     * @param filePath The file path, relative to the project root directory, where to write the changes.
     */
    @Override
    public void write(Path filePath) {
        try {
            if (this.event == null) {
                return;
            }
            this.event.writeToFile(filePath);
        } catch (IOException writeException) {
            throw new RuntimeException("Problem writing to file.");
        }
    }
}
