package bob.task;

import java.util.ArrayList;

import bob.exception.InvalidTaskException;
import bob.parser.Parser;
import bob.ui.Ui;

/**
 * Performs operations on the list of Tasks.
 */
public class TaskList {

    private ArrayList<Task> records;
    private int latestRecordedIndex;
    private Task latestDeletedRecord;

    /**
     * Initialises TaskList with an existing record.
     * @param recordedList Arraylist of existing records.
     */
    public TaskList(ArrayList<Task> recordedList) {
        this.records = recordedList;
        this.latestRecordedIndex = recordedList.size();
    }

    /**
     * Initialises a new TaskList.
     */
    public TaskList() {
        this.records = new ArrayList<>();
        this.latestRecordedIndex = 0;
    }


    /**
     * Prints a list of all recorded user inputs.
     */
    public String getListRecordsString() {
        String allRecords = "Here are the tasks in your list:\n\t";
        for (int i = 0; i < records.size(); i++) {
            int num = i + 1;
            Task currTask = records.get(i);
            if (num == records.size()) {
                allRecords += num + "." + currTask.getTaskListItem();
            } else {
                allRecords += num + "." + currTask.getTaskListItem() + "\n\t";
            }
        }
        Ui.showAllRecordsString(allRecords);
        return allRecords;
    }

    /**
     * Checks if the item indexed by user is a valid index in the records.
     * If records only have 5 items, itemIndex 5 is valid but itemIndex 6 is not.
     *
     * @param itemIndex Index of the item in the TaskList.
     * @return True if index is an index within TaskList, else false.
     */
    public boolean isValidRecord(int itemIndex) {
        return (itemIndex > 0 && itemIndex <= records.size());
    }

    /**
     * Returns the Task of the indicated index in records.
     *
     * @param index index of task seend by user in the current records,
     * @return
     */
    public Task getIndexedTask(int index) {
        assert this.records != null : "Records must be present to get indexed task.";
        return this.records.get(index - 1);
    }

    /**
     * Returns an arraylist of records.
     */
    public ArrayList<Task> getAllRecords() {
        assert this.records != null : "Records must be present to get all records.";
        return this.records;
    }

    /**
     * Updates the Task List when a new task is added.
     *
     * @param newTask
     */
    public void updateWithNewTask(Task newTask) {
        ArrayList<Task> allRecords = this.getAllRecords();
        allRecords.add(newTask);
        this.incrementLatestRecordedIndex();
    }

    /**
     * Removes the task at the index specified by the user.
     *
     * @param index Index that corresponds to the task visible to the user.
     */
    private void removeRecord(int index) {
        assert this.records != null : "Records must be present to remove a single record.";
        this.latestDeletedRecord = this.records.get(index - 1);
        this.records.remove(index - 1);
        this.latestRecordedIndex -= 1;
    }

    /**
     * Deletes task from task list and updates the task List
     *
     * @param input
     * @throws InvalidTaskException
     */
    public void deleteTaskFromTaskList(String input) throws InvalidTaskException {
        int taskNumber = Parser.parseTaskNumberFromInput(input);
        String[] inputArray = Parser.parseInputIntoStringArray(input);
        if (inputArray.length != 2) {
            throw new InvalidTaskException("Invalid format. Should be 'delete <integer>'.");
        }
        if (!isValidRecord(taskNumber)) {
            throw new InvalidTaskException("Task number provided is out of range.");
        }
        this.removeRecord(taskNumber);
    }

    /**
     * Returns the String representation of the task at specified index when task is deleted.
     *
     * @return
     */
    public String getDeletedTaskString() {
        String deletedTaskString = "Noted. I've removed this task:\n\t"
                + latestDeletedRecord.getTaskListItem()
                + "\n\t"
                + "Now you have "
                + (String.valueOf(latestRecordedIndex))
                + " tasks in the list.";
        return deletedTaskString;
    }

    /**
     * Returns the String representation of the task at specified index when task is added.
     *
     * @return
     */
    public String getAddedTaskString() {
        Task addedTask = this.getIndexedTask(this.getLatestRecordedIndex());
        String commandString = "Got it. I've added this task:\n\t"
                + addedTask.getTaskListItem()
                + "\n\t"
                + "Now you have "
                + (String.valueOf(this.getLatestRecordedIndex()))
                + " tasks in the list.";
        return commandString;
    }

    /**
     * Returns a String representation of the records that match with the search keyword.
     * @param matchingRecords
     * @return
     */
    public String getMatchingRecordsString(ArrayList<Task> matchingRecords) {
        String matchingRecordsString = "";
        int counter = 1;
        for (Task task: matchingRecords) {
            matchingRecordsString += "\t" + counter + "." + task.getTaskListItem() + "\n";
        }
        if (matchingRecordsString == "") {
            matchingRecordsString = "No matching results found.";
        }
        return matchingRecordsString;
    }
    /**
     * Increments the latest recorded index by 1.
     */
    public void incrementLatestRecordedIndex() {
        this.latestRecordedIndex++;
    }

    /**
     * Returns the latest recorded index in the current task list from the viewer's perspective.
     */
    public int getLatestRecordedIndex() {
        return this.latestRecordedIndex;
    }

    /**
     * Returns an Arraylist with all tasks that contain the keyword as part of its description.
     *
     * @param keyword
     * @return
     */
    public ArrayList<Task> searchKeywordInRecords(String keyword) {
        ArrayList<Task> matchingRecords = new ArrayList<>();
        for (Task x : getAllRecords()) {
            if (x.isTargetInDescription(keyword)) {
                matchingRecords.add(x);
            }
        }
        return matchingRecords;
    }

    /**
     * Marks a task as completed or uncompleted.
     *
     * @param input
     * @param isCompleted
     */
    public void markTaskInTaskList(String input, boolean isCompleted) throws InvalidTaskException {
        int taskNumber = Parser.parseTaskNumberFromInput(input);
        String[] inputArray = Parser.parseInputIntoStringArray(input);
        if (inputArray.length != 2) {
            throw new InvalidTaskException("Invalid format. Should be 'mark <integer>'.");
        }
        if (!isValidRecord(taskNumber)) {
            throw new InvalidTaskException("Task number provided is out of range.");
        }

        int taskIndex = Parser.parseTaskNumberFromInput(input);
        Task currTask = getIndexedTask(taskIndex);
        currTask.mark1Task(isCompleted);
    }

    public String getMarkedTaskStringFromTaskList(String input, boolean isCompleted) throws InvalidTaskException {
        int taskIndex = Parser.parseTaskNumberFromInput(input);
        Task currTask = getIndexedTask(taskIndex);
        return currTask.getMarkedTaskStringFromTask(isCompleted);
    }
}
