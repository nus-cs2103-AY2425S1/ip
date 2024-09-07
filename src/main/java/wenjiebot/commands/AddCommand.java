package wenjiebot.commands;

import java.util.ArrayList;

import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;
import wenjiebot.exceptions.InvalidInputException;
import wenjiebot.exceptions.NoFollowUpException;
import wenjiebot.exceptions.WenJieException;
import wenjiebot.tasks.Deadline;
import wenjiebot.tasks.Event;
import wenjiebot.tasks.Task;
import wenjiebot.tasks.ToDo;

/**
 * Represents a command to add a task to the task list.
 * The task can be a ToDo, Deadline, or Event based on the type of event specified.
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

    private TypeOfEvent typeOfEvent;

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
     * @throws WenJieException if there is an error during execution, such as invalid input/missing follow-up details.
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
            ui.setOutput(getAcknoledgeMessage(taskList.size(), ui, todo));

            break;


        case DEADLINE:

            Deadline deadline = getDeadline();
            taskList.add(deadline);

            printAcknowledgeMessage(taskList.size(), ui, deadline);
            ui.setOutput(getAcknoledgeMessage(taskList.size(), ui, deadline));

            break;


        case EVENT:

            Event event = getEvent();
            taskList.add(event);

            printAcknowledgeMessage(taskList.size(), ui, event);
            ui.setOutput(getAcknoledgeMessage(taskList.size(), ui, event));

            break;

        default:
            System.out.println("No matching types of tasks");
            break;
        }


    }

    /**
     * Retrieves the details of the Event from the user input and creates an Event object.
     *
     * @return the Event object created from the user input.
     * @throws NoFollowUpException if the event description is missing.
     * @throws InvalidInputException if the input format is invalid.
     */
    private Event getEvent() throws NoFollowUpException, InvalidInputException {
        String[] parts = getInput().split(" ");

        if (getInput().length() <= 5) {
            throw new NoFollowUpException();
        }

        String from = "";
        String to = "";

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].charAt(0) == '/') {
                int j = i + 1;
                while (parts[j].charAt(0) != '/') {
                    from += parts[j] + " ";
                    j++;
                }
                j++;
                while (j < parts.length) {
                    to += parts[j];
                    j++;
                }
                break;
            }
        }

        int endIndex = 0;
        for (int i = 0; i < getInput().length(); i++) {
            if (getInput().charAt(i) == '/') {
                endIndex = i;
                break;
            }
        }

        if (endIndex == 0) {
            throw new InvalidInputException();
        }

        String desc = getInput().substring(6, endIndex);
        return new Event(desc, from, to);
    }

    /**
     * Retrieves the details of the Deadline from the user input and creates a Deadline object.
     *
     * @return the Deadline object created from the user input.
     * @throws NoFollowUpException if the deadline description is missing.
     */
    private Deadline getDeadline() throws NoFollowUpException {
        String[] parts = getInput().split(" ");

        if (getInput().length() <= 8) {
            throw new NoFollowUpException();
        }

        String by = "";
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].charAt(0) == '/') {
                for (int j = i + 1; j < parts.length; j++) {
                    by += parts[j] + " ";
                }
                break;
            }
        }

        int endIndex = 0;
        for (int i = 0; i < getInput().length(); i++) {
            if (getInput().charAt(i) == '/') {
                endIndex = i;
                break;
            }
        }

        String desc = getInput().substring(9, endIndex);
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
        System.out.println("Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + taskListSize + " tasks in the list.");
        ui.showLine();
    }

    public String getAcknoledgeMessage(int taskListSize, Ui ui, Task task) {
        return ("Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + taskListSize + " tasks in the list.");
    }
}
