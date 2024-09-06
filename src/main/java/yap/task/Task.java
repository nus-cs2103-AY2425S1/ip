package yap.task;

import yap.storage.BadDataFormatException;

public class Task {

    /**
     * Converts the task string that is stored on a hard disk into a Task object.
     *
     * @param taskString the task in the form of a string, extracted from the hard disk.
     * @return the task object created from the task string.
     * @throws BadDataFormatException if the task string is invalid and could not be parsed.
     */
    public static Task taskStringToTask(String taskString) throws BadDataFormatException {
        String[] taskDetails = taskString.split(" \\| ");
        try {
            String description;
            boolean isDone;
            switch (taskDetails[0].charAt(0)) {
            case 'T':
                description = taskDetails[2];
                isDone = Task.getCompletionStatusFromStoredTask(taskDetails[1].charAt(0));
                return new ToDo(description, isDone);
            case 'D':
                description = taskDetails[2];
                isDone = Task.getCompletionStatusFromStoredTask(taskDetails[1].charAt(0));
                String deadline = taskDetails[3];
                return new Deadline(description, deadline, isDone);
            case 'E':
                description = taskDetails[2];
                isDone = Task.getCompletionStatusFromStoredTask(taskDetails[1].charAt(0));
                String startTime = taskDetails[3];
                String endTime = taskDetails[4];
                return new Event(description, startTime, endTime, isDone);
            default:
                throw new BadDataFormatException("A task format in the Data file is not valid");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new BadDataFormatException("The format of the file is incorrect." +
                    "Did you accidentally modify it?");
        }
    }

    private static boolean getCompletionStatusFromStoredTask(char storedCharacter) throws BadDataFormatException {
        boolean completionStatus;
        if (storedCharacter == '1') {
            completionStatus = true;
        } else if (storedCharacter == '0') {
            completionStatus = false;
        } else {
            throw new BadDataFormatException("There was a invalid completion status in the data file");
        }
        return completionStatus;
    }

    private String description;

    private boolean isDone; // true for done, false for not done

    /**
     * Constructs a generic task.
     *
     * @param description a description of what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a generic task.
     *
     * @param description a description of what the task is.
     * @param isDone whether the task has been completed or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks a task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as uncompleted
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the details of the file into the format represented in the storage file.
     *
     * @return A string in the appropriate format, Completion Status (0 or 1) | Description
     */
    public String convertToFileString() {
        int completion = (isDone) ? 1 : 0;
        return String.format("%d | %s", completion, this.description);
    }

    public boolean matchesTaskDescription(String searchDescription) {
        return description.toLowerCase().contains(searchDescription.toLowerCase());
    }

    @Override
    public String toString() {
        String completion;
        if (this.isDone) {
            completion = "[X] ";
        } else {
            completion = "[ ] ";
        }
        return completion + this.description;
    }
}
