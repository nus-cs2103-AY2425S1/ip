package processes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.EmptyTagException;
import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;
import exceptions.SpaceInTagException;
import exceptions.TaskOutOfBoundsError;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;



/**
 * TaskList class stores the state of the programme.
 * It stores user inputs in previous runs of the programme (provided they are loaded in).
 * Contains various methods to manipulate the tasks
 */
public class TaskList {

    private final ArrayList<Task> taskList;


    /**
     * Constructor for TaskList object.
     * Creates the data structure used to store the user's tasks.
     * The data structure used is an ArrayList
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }


    /**
     * Getter method that returns the current list of tasks
     *
     * @return The current list of tasks
     *
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }


    /**
     * Getter method that returns the size of the current list of tasks
     *
     * @return The size of current list of tasks
     *
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Taking in the user input, create a ToDo class and add it to the current list of tasks.
     *
     * @param arg The string received from the user.
     * @return The ToDo that was created.
     * @throws InvalidTaskNameException If no task name is provided.
     * @throws EmptyTagException If tag provided is empty, usually because it's all whitespace
     * @throws SpaceInTagException If tag provided contains whitespace.
     */
    public Task addToDo(String arg) throws InvalidTaskNameException, EmptyTagException, SpaceInTagException {
        String input = arg.substring(5).trim();
        ToDo newToDo = new ToDo(input);
        taskList.add(newToDo);
        return newToDo;
    }

    /**
     * Taking in the user input, create a DeadLine object and add it to the current list of tasks.
     *
     * @param arg The string received from the user.
     * @return The DeadLine that was created
     * @throws InvalidTaskNameException If no task name is provided.
     * @throws InvalidDateException If invalid date/no date is provided.
     * @throws EmptyTagException If tag provided is empty, usually because it's all whitespace
     * @throws SpaceInTagException If tag provided contains whitespace.
     */
    public Task addDeadline(String arg)
            throws InvalidTaskNameException, InvalidDateException, EmptyTagException, SpaceInTagException {
        String input = arg.substring(9).trim();
        Task newDeadline = new DeadLine(input);
        taskList.add(newDeadline);
        return newDeadline;
    }


    /**
     * Taking in the user input, create an Event object and add it to the current list of tasks.
     *
     * @param arg The string received from the user.
     * @return The Event object that was created.
     * @throws InvalidTaskNameException If no task name is provided.
     * @throws InvalidDateException If invalid date/no date is provided.
     * @throws EmptyTagException If tag provided is empty, usually because it's all whitespace
     * @throws SpaceInTagException If tag provided contains whitespace.
     */
    public Task addEvent(String arg)
            throws InvalidDateException, InvalidTaskNameException, EmptyTagException, SpaceInTagException {
        String input = arg.substring(6).trim();
        Task newEvent = new Event(input);
        taskList.add(newEvent);
        return newEvent;
    }



    /**
     * Removes the task at the specified index from the current list of tasks.
     *
     * @param command The user's delete command.
     * @return The task that was deleted.
     * @throws TaskOutOfBoundsError If the index provided was outside of the taskList bounds.
     * @throws NumberFormatException If the user command provided does not contain a valid index.
     */
    public Task deleteTask(String command) throws TaskOutOfBoundsError, NumberFormatException {
        String indexString = command.substring(7).trim();
        int index = Integer.parseInt(indexString);
        if (index < 1 || index > taskList.size()) {
            throw new TaskOutOfBoundsError(index);
        }
        index--;
        Task currTask = taskList.get(index);
        taskList.remove(index);
        return currTask;
    }


    /**
     * Receives the user's command and decides which task to unmark.
     * If command is valid, the task at the specified index will be unmarked.
     *
     * @param command The user's command.
     * @return The task that was unmarked
     * @throws TaskOutOfBoundsError If index provided is not within taskList.
     * @throws NumberFormatException If the command that the user provided does not contain the index
     * of the task to mark at the correct index.
     */
    public Task unMark(String command) throws TaskOutOfBoundsError, NumberFormatException {
        String stringIndex = command.substring(7).trim();
        int index = Integer.parseInt(stringIndex);
        if (index < 1 || index > taskList.size()) {
            throw new TaskOutOfBoundsError(index);
        }
        Task curr = taskList.get(--index);
        curr.unMark();
        return curr;
    }

    /**
     * Receives the user's command and decides which task to mark.
     * If command is valid, the task at the specified index will be marked.
     *
     * @param command The user's command.
     * @return The task that was marked
     * @throws TaskOutOfBoundsError If index provided is not within taskList.
     * @throws NumberFormatException If the command that the user provided does not contain the index
     * of the task to mark at the correct index.
     */
    public Task mark(String command) throws TaskOutOfBoundsError, NumberFormatException {
        String stringIndex = command.substring(5).trim();
        int index = Integer.parseInt(stringIndex);
        if (index < 1 || index > taskList.size()) {
            throw new TaskOutOfBoundsError(index);
        }
        Task curr = taskList.get(--index);
        curr.mark();
        return curr;
    }

    /**=
     * Receive the command from the user and extracts the prompt.
     * Then, search the current list of tasks for task names that contain the prompt.
     * After getting the list of matching tasks, print them out to the terminal
     *
     * @param command The command provided by the user.
     * @return The output array of tasks that contains the prompt in their names.
     *
     */
    public ArrayList<Task> find(String command) {
        String prompt = command.substring(5).trim();
        Stream<Task> stream = this.taskList.stream().filter(t -> t.getName().contains(prompt));
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }





    /**
     * Receive the prompt from the user and adds tag to the target task.
     *
     * @param command The command provided by the user.
     * Contains index of task to tag, as well as the tags to add.
     *
     * @return The target task that the tags were added to
     * @throws TaskOutOfBoundsError When the index provided by the user is out of bounds.
     * @throws NumberFormatException When the user did not provide a valid index.
     * @throws EmptyTagException When the tag provided by the user is empty or only contains white space.
     * @throws SpaceInTagException When the tag provided by the user contains white space.
     */
    public Task tag(String command)
            throws TaskOutOfBoundsError, NumberFormatException, EmptyTagException, SpaceInTagException {
        String[] inputs = command.substring(4).split("#");
        ArrayList<String> tags = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim();
            if (i == 0) {
                continue;
            }
            if (inputs[i].isEmpty()) {
                throw new EmptyTagException();
            } else if (inputs[i].contains(" ")) {
                throw new SpaceInTagException();
            }
            tags.add(inputs[i]);
        }
        int index = Integer.parseInt(inputs[0]);

        if (index < 1 || index > taskList.size()) {
            throw new TaskOutOfBoundsError(index);
        }
        Task taskToAddTags = taskList.get(--index);
        taskToAddTags.addTags(tags);
        return taskToAddTags;
    }

    /**
     * Receive the prompt from the user and removes the tags from the target task.
     *
     * @param command The command provided by the user.
     * Contains index of task to tag, as well as the tags to remove.
     *
     * @return The target task that the tags were removed from
     */
    public Task removeTags(String command) throws TaskOutOfBoundsError, NumberFormatException {
        String[] inputs = command.substring(12).split("#");
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim();
        }
        int index = Integer.parseInt(inputs[0]);

        if (index < 1 || index > taskList.size()) {
            throw new TaskOutOfBoundsError(index);
        }
        Task taskToRemoveTags = taskList.get(--index);
        taskToRemoveTags.removeTags(Arrays.copyOfRange(inputs, 1, inputs.length));
        return taskToRemoveTags;
    }
}
