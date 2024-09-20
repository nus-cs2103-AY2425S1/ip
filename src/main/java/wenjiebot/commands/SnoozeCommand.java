package wenjiebot.commands;

import java.util.ArrayList;

import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;
import wenjiebot.exceptions.*;
import wenjiebot.tasks.Task;



public class SnoozeCommand extends Command {

    private static String regexToDo = "todo .*";
    private static String regexDeadline = "deadline .* /by .*";
    private static String regexEvent = "event .* /.* /.*";

    /**
     * Enum representing the type of event (ToDo, Event, Deadline).
     */
    private enum TypeOfEvent {
        TODO,
        EVENT,
        DEADLINE
    }

    private TypeOfEvent typeOfEvent;

    /**
     * Constructs an SnoozeCommand with the specified activity status, input, and type of event.
     *
     * @param isExit boolean indicating whether the Bot will exit after this command executes.
     * @param input the input associated with this command.
     */
    public SnoozeCommand(boolean isExit, String input) {
        super(isExit, input);
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WenJieException {
        String[] parts = getInput().split(" ");
        ArrayList<Task> taskList = tasks.getTasks();

        if (parts.length <= 1) {
            throw new NoNumberInputtedException();
        }

        int taskNo = Integer.parseInt(parts[1]) - 1;
        int descIndex = getInput().indexOf(parts[2]);
        String desc = getInput().substring(descIndex);

        if (taskNo + 1 > taskList.size()) {
            throw new OutOfBoundsException();
        }
        taskList.get(taskNo).setDateTime(desc);
        ui.showLine();
        System.out.println("Nyess master~~, I've snoozed this task:\n" + taskList.get(taskNo));
        ui.showLine();

        ui.setOutput("Yes master~~, I've snoozed this task:\n" + taskList.get(taskNo));
    }
}
