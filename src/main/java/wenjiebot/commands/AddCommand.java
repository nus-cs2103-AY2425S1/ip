package wenjiebot.commands;

import java.util.ArrayList;

import wenjiebot.Parser;
import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;
import wenjiebot.exceptions.InvalidDateInputException;
import wenjiebot.exceptions.NoFollowUpException;
import wenjiebot.exceptions.WenJieException;
import wenjiebot.tasks.Deadline;
import wenjiebot.tasks.Event;
import wenjiebot.tasks.Task;
import wenjiebot.tasks.ToDo;

/**
 * Represents a command to add a task to the task list. The task can be a ToDo, Deadline, or Event
 * based on the type of event specified.
 */
public class AddCommand extends Command {

    /**
     * Enum representing the type of event (ToDo, Event, Deadline).
     */
    public enum TypeOfEvent {
        TODO,
        EVENT,
        DEADLINE
    }

    private final TypeOfEvent typeOfEvent;

    /**
     * Constructs an AddCommand with the specified activity status, input, and type of event.
     *
     * @param isExit boolean indicating whether the Bot will exit after this command executes.
     * @param input the input associated with this command.
     * @param typeOfEvent the type of event to be added (ToDo, Event, or Deadline).
     */
    public AddCommand(boolean isExit, String input, TypeOfEvent typeOfEvent) {
        super(isExit, input);
        this.typeOfEvent = typeOfEvent;
    }

    /**
     * Executes the AddCommand, which adds the specified type of task (ToDo, Deadline, or Event)
     * to the task list, saves the tasks to storage, and acknowledges the addition to the user.
     *
     * @param tasks the TaskList that contains all the tasks.
     * @param ui the Ui used for interaction with the user.
     * @param storage the Storage used to store and retrieve tasks.
     * @throws WenJieException if there is an error during execution, such as invalid input or missing followup details.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WenJieException {
        ArrayList<Task> taskList = tasks.getTasks();

        switch (typeOfEvent) {
        case TODO:
            if (getInput().length() <= 5) {
                throw new NoFollowUpException();
            }
            ToDo todo = new ToDo(getInput().substring(5));
            taskList.add(todo);
            printAcknowledgeMessage(taskList.size(), ui, todo);
            ui.setOutput(getAcknowledgeMessage(taskList.size(), ui, todo));
            break;

        case DEADLINE:
            Deadline deadline = getDeadline();
            taskList.add(deadline);
            printAcknowledgeMessage(taskList.size(), ui, deadline);
            ui.setOutput(getAcknowledgeMessage(taskList.size(), ui, deadline));
            break;

        case EVENT:
            Event event = getEvent();
            taskList.add(event);
            printAcknowledgeMessage(taskList.size(), ui, event);
            ui.setOutput(getAcknowledgeMessage(taskList.size(), ui, event));
            break;

        default:
            System.out.println("oh nyaa~~ theres no matching types of tasks");
            break;
        }
    }

    /**
     * Retrieves the details of the Event from the user input and creates an Event object.
     *
     * @return the Event object created from the user input.
     * @throws NoFollowUpException if the event description is missing.
     * @throws InvalidDateInputException if the input format is invalid.
     */
    private Event getEvent() throws NoFollowUpException, InvalidDateInputException {
        String input = getInput();

        if (input.length() <= 5) {
            throw new NoFollowUpException();
        }

        String from = Parser.parseFromDate(input);
        String to = Parser.parseToDate(input);

        int endIndex = findEndIndex(input);
        if (endIndex == 0) {
            throw new InvalidDateInputException();
        }

        String desc = input.substring(6, endIndex);
        assert(!desc.isEmpty());
        return new Event(desc, from, to);
    }
    /**
     * Finds the end index of the description in the input string.
     *
     * @param input the input string.
     * @return the end index of the description.
     */
    private int findEndIndex(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                return i;
            }
        }
        return 0;
    }

    /**
     * Retrieves the details of the Deadline from the user input and creates a Deadline object.
     *
     * @return the Deadline object created from the user input.
     * @throws NoFollowUpException if the deadline description is missing.
     * @throws InvalidDateInputException if the input format is invalid.
     */
    private Deadline getDeadline() throws NoFollowUpException, InvalidDateInputException {
        String input = getInput();
        if (input.length() <= 9) {
            throw new NoFollowUpException();
        }
        String by = Parser.parseByDate(input);
        int endIndex = findEndIndex(input);
        if (endIndex == 0) {
            throw new InvalidDateInputException();
        }

        String desc = input.substring(9, endIndex);
        return new Deadline(desc, by);
    }
    /**
     * Prints an acknowledgment message to the user after a task has been added to the list.
     *
     * @param taskListSize the size of the task list after adding the new task.
     * @param ui the Ui used for interaction with the user.
     * @param task the Task that was added to the list.
     */
    public void printAcknowledgeMessage(int taskListSize, Ui ui, Task task) {
        ui.showLine();
        System.out.println("Nya~~ I've added this task:\n"
                + task + "\n"
                + "Now you have " + taskListSize + " tasks in the list babe.");
        ui.showLine();
    }

    /**
     * Generates an acknowledgment message after a task has been added to the list.
     *
     * @param taskListSize the size of the task list after adding the new task.
     * @param ui the Ui used for interaction with the user.
     * @param task the Task that was added to the list.
     * @return the acknowledgment message.
     */
    public String getAcknowledgeMessage(int taskListSize, Ui ui, Task task) {
        return "Nya~~ I've added this task:\n"
                + task + "\n"
                + "Now you have " + taskListSize + " tasks in the list babe.";
    }
}
