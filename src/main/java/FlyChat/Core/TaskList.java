package flychat.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import flychat.Deadline;
import flychat.tasks.Event;
import flychat.tasks.Task;
import flychat.tasks.Todo;

/**
 * Contains methods that create and edit tasks.
 */
public class TaskList {
    private ArrayList<Task> storageList;
    private Parser parser;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        storageList = new ArrayList<>();
        parser = new Parser();
    }

    /**
     * Adds a new task to the task list according to the user input.
     *
     * @param inputString String containing user input
     * @return String containing announcement of command completion.
     */
    public String addTask(String inputString) {
        if (parser.parseCommand(inputString).equals("todo")) {
            try {
                Todo newToDo = Todo.createNewTodo(parser.getTaskDescription(inputString), false);
                addToList(newToDo);
                return "Task added:\n  " + newToDo + "\nNow you have " + storageList.size()
                        + " tasks in the list. HAVE FUN ^o^";
            } catch (InputMismatchException e) {
                return "Please ensure your todo has a description TT";
            }

        } else if (parser.parseCommand(inputString).equals("event")) {
            try {
                Event newEvent = Event.createNewEvent(parser.getTaskDescription(inputString),
                        parser.getEventStartTime(inputString), parser.getEventEndTime(inputString), false);
                addToList(newEvent);
                return "Task added:\n  " + newEvent + "\nNow you have " + storageList.size()
                        + " tasks in the list. HAVE FUN ^o^";
            } catch (InputMismatchException e) {
                return "Please ensure your event has a description, a start and end time TT";
            }

        } else { /* remaining case is deadline task cases */
            try {
                Deadline newDeadline = Deadline.createNewDeadline(parser.getTaskDescription(inputString),
                        parser.getDeadlineEndDate(inputString), false);
                addToList(newDeadline);
                return "Task added:\n  " + newDeadline + "\nNow you have " + storageList.size()
                        + " tasks in the list. HAVE FUN ^o^";
            } catch (InputMismatchException e) {
                return e.getMessage();
            }
        }
    }

    /**
     * Announces all the items currently in the task list.
     *
     * @return String containing information on all tasks in the task list.
     */
    public String announceItems() {
        String finalString = "";
        //i is used to print out the index of each item so it has to be incremented
        for (int i = 0; i < storageList.size() - 1; i++) {
            finalString += ((i + 1) + ". " + storageList.get(i) + "\n");
        }
        return finalString + storageList.size() + ". " + storageList.get(storageList.size() - 1);
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
        storageList.get(index - 1).uncompleteTask();
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
        return "Task removed:\n  " + targetTask + "\nNow you have " + storageList.size()
                + " tasks in the list. SOLDIER ON! ^o^";
    }

    /**
     * Finds tasks that contain the corresponding key word in their descriptions.
     *
     * @param keyWord
     * @return String announcing results of the search.
     */
    public String find(String keyWord) {
        int counter = 1;
        String finalString = "Here are the matching tasks in your list:\n";

        if (keyWord.equals("")) {
            return finalString;
        }

        for (Task t : storageList) {
            if (t.getDescription().contains(keyWord)) {
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
        boolean isCompleted = false;
        isCompleted = parser.checkTaskCompletedFromFile(str);

        //format the task string properly using regex
        if (parser.getTaskTypeFromFile(str).equals("[T]")) {
            //To-do case
            addToList(Todo.createNewTodo(parser.getTaskDescriptionFromFile(str), isCompleted));
        } else if (parser.getTaskTypeFromFile(str).equals("[D]")) {
            //Deadline case
            addToList(Deadline.createNewDeadline(parser.getTaskDescriptionFromFile(str),
                    parser.getDeadlineEndDateFromFile(str), isCompleted));
        } else if (parser.getTaskTypeFromFile(str).equals("[E]")) {
            //Event case
            addToList(Event.createNewEvent(parser.getTaskDescriptionFromFile(str),
                    parser.getEventStartTimeFromFile(str), parser.getEventEndTimeFromFile(str),
                    isCompleted));
        } else {
            throw new IOException("Save file has been corrupted. Save progress will be reset");
        }
    }

    /**
     * Formats information from all tasks in the task list to be saved into the save file.\
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
    private void addToList(Task task) {
        storageList.add(task);
    }
}
