package flychat.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import flychat.Deadline;
import flychat.command.AddDeadlineCommand;
import flychat.command.AddEventCommand;
import flychat.command.AddTodoCommand;
import flychat.command.Command;
import flychat.command.InvalidCommand;
import flychat.core.FlyChat.CommandType;
import flychat.tasks.Event;
import flychat.tasks.Task;
import flychat.tasks.Todo;

/**
 * Contains methods that create and edit tasks.
 */
public class TaskList {
    private final ArrayList<Task> storageList;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {

        storageList = new ArrayList<>();
        parser = new Parser();
        ui = new Ui();
    }

    /**
     * Adds a new task to the task list according to the user input.
     *
     * @param inputString String containing user input
     * @return String containing announcement of command completion.
     */
    public String addTask(String inputString) {
        CommandType commandType = getCommandType(parser.parseCommand(inputString));
        Command command = getAddTaskCommand(commandType);

        try {
            return command.execute(this, ui, parser, inputString);
        } catch (InputMismatchException e) {
            return e.getMessage();
        }
    }

    private Command getAddTaskCommand(CommandType commandType) {
        switch (commandType) {
        case TODO:
            return new AddTodoCommand();
        case EVENT:
            return new AddEventCommand();
        case DEADLINE:
            return new AddDeadlineCommand();
        default:
            return new InvalidCommand();
        }
    }

    private CommandType getCommandType(String commandString) {
        switch (commandString) {
        case "todo":
            return CommandType.TODO;
        case "event":
            return CommandType.EVENT;
        case "deadline":
            return CommandType.DEADLINE;
        default:
            return CommandType.INVALID;
        }
    }

    /**
     * Tags the task specified by the task index with the given tag.
     *
     * @param index The index of the task to be tagged.
     * @param tag The tag to be added.
     * @return String announcing command completion.
     * @throws IndexOutOfBoundsException If task index is out of bounds of the task list.
     */
    public String tag(int index, String[] tags) throws IndexOutOfBoundsException, IllegalArgumentException {
        // index - 1 because task list displayed to user starts from 1
        Task task = storageList.get(index - 1);

        for (String t : tags) {
            task.addTag(t);
        }
        return "Done:\n" + task.toString();
    }

    /**
     * Announces all the items currently in the task list.
     *
     * @return String containing information on all tasks in the task list.
     */
    public String announceItems() {
        String finalString = "";

        if (getSize() == 0) {
            return finalString;
        }

        //i is used to print out the index of each item so it has to be incremented
        for (int i = 0; i < getSize() - 1; i++) {
            finalString += ((i + 1) + ". " + storageList.get(i) + "\n");
        }
        return finalString + getSize() + ". " + storageList.get(getSize() - 1);
    }

    /**
     * Marks the task specified by the task index as completed.
     *
     * @return String announces command completion.
     * @throws IndexOutOfBoundsException If task index is out of bounds of the task list.
     */
    public String mark(int index) throws IndexOutOfBoundsException {
        //index - 1 because task list displayed to user starts from 1
        storageList.get(index - 1).completeTask();
        return "I've marked the task as done ^o^ :\n  " + storageList.get(index - 1).toString();
    }

    /**
     * Marks the task specified by the task index as not completed.
     *
     * @return String announces command completion.
     * @throws IndexOutOfBoundsException If task index is out of bounds of the task list.
     */
    public String unmark(int index) throws IndexOutOfBoundsException {
        //index - 1 because task list displayed to user starts from 1
        storageList.get(index - 1).undoCompleteTask();
        return "I've marked the task as not done T.T :\n  " + storageList.get(index - 1).toString();
    }

    /**
     * Deletes the task specified by the task index from the task list.
     *
     * @return String announcing task completion.
     * @throws IndexOutOfBoundsException If task index is out of bounds of the task list.
     */
    public String deleteTask(int index) throws IndexOutOfBoundsException {
        Task targetTask = storageList.get(index - 1);
        storageList.remove(targetTask);
        return "Task removed:\n  " + targetTask + "\nNow you have " + getSize()
                + " tasks in the list. SOLDIER ON! ^o^";
    }

    /**
     * Finds tasks that contain the corresponding key word in their descriptions.
     *
     * @param keyWord
     * @return String announcing results of the search.
     */
    public String find(String keyWord) throws IllegalArgumentException {

        if (keyWord.equals("")) {
            throw new IllegalArgumentException("Please enter a keyword to search for.");
        }

        int counter = 1;
        String finalString = "Here are the matching tasks in your list:\n";

        if (keyWord.equals("")) {
            return finalString;
        }

        for (Task t : storageList) {
            if (t.getDescription().contains(keyWord) || t.getTags().contains(keyWord)) {
                finalString += counter + ". " + t.toString() + "\n";
                counter++;
            }
        }

        return finalString;
    }

    /**
     * Reads the task from the save file and adds it to the task list.
     *
     * @param str Line from save file containing task information.
     * @throws IOException If save file is corrupted and unable to be read.
     */
    public void addTaskFromFile(String str) throws IOException {
        Task createdTask;
        boolean isCompleted = parser.checkTaskCompletedFromFile(str);
        IOException saveFileCorruptedException = new IOException(
                "Save file has been corrupted. Save progress will be reset");

        //format the task string properly using regex
        if (parser.getTaskTypeFromFile(str).equals("[T]")) {
            //To-do case
            createdTask = Todo.createNewTodo(parser.getTaskDescriptionFromFile(str), isCompleted);
        } else if (parser.getTaskTypeFromFile(str).equals("[D]")) {
            //Deadline case
            createdTask = Deadline.createNewDeadline(parser.getTaskDescriptionFromFile(str),
                    parser.getDeadlineEndDateFromFile(str), isCompleted);
        } else if (parser.getTaskTypeFromFile(str).equals("[E]")) {
            //Event case
            createdTask = Event.createNewEvent(parser.getTaskDescriptionFromFile(str),
                    parser.getEventStartTimeFromFile(str), parser.getEventEndTimeFromFile(str),
                    isCompleted);
        } else {
            throw saveFileCorruptedException;
        }

        try {
            String[] tags = parser.getTagsFromFile(str);
            for (String tag : tags) {
                createdTask.addTag(tag);
            }
        } catch (IllegalArgumentException e) {
            throw saveFileCorruptedException;
        }

        addToList(createdTask);
    }

    /**
     * Formats information from all tasks in the task list to be saved into the save file.
     *
     * @return String to be saved into the save file.
     */
    public String formatSaveString() {
        String saveString = "";
        for (Task t : storageList) {
            saveString += (t.formatStringForSaving() + System.lineSeparator());
        }
        return saveString;
    }
    public void addToList(Task task) {
        storageList.add(task);
    }

    public int getSize() {
        return storageList.size();
    }
}
