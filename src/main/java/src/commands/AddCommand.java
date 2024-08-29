package src.commands;

import src.*;
import src.tasks.Deadline;
import src.tasks.Task;
import src.tasks.ToDo;
import src.exceptions.DukeException;
import src.tasks.Event;
import src.exceptions.InvalidInputException;
import src.exceptions.NoFollowUpException;

import java.util.ArrayList;

public class AddCommand extends Command {

    public enum TypeOfEvent {
        TODO,
        EVENT,
        DEADLINE
    }

    TypeOfEvent typeOfEvent;


    public AddCommand(boolean isActive, String input, TypeOfEvent typeOfEvent) {
        super(isActive, input);
        this.typeOfEvent = typeOfEvent;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        ArrayList<Task> taskList = tasks.getTasks();

        switch (typeOfEvent) {
            case TODO: {
                if (getInput().length() <= 5) {
                    throw new NoFollowUpException();
                }

                ToDo temp = new ToDo(getInput().substring(5));

                ui.showLine();
                taskList.add(temp);

                printAcknowledgeMessage(taskList.size(), ui, temp);

                break;
            }

            case DEADLINE: {

                Deadline temp = getDeadline();
                taskList.add(temp);

                printAcknowledgeMessage(taskList.size(), ui, temp);

                break;
            }

            case EVENT: {

                Event temp = getEvent();
                taskList.add(temp);

                printAcknowledgeMessage(taskList.size(), ui, temp);

                break;
            }
        }
    }

    private Event getEvent() throws NoFollowUpException, InvalidInputException {
        String[] parts = getInput().split(" ");

        if (getInput().length() <= 5) {
            throw new NoFollowUpException();
        }

        String from = "", to = "";

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].charAt(0) == '/') {
                int j = i + 1;
                while(parts[j].charAt(0) != '/') {
                    from += parts[j] + " ";
                    j++;
                }
                j++;
                while(j < parts.length) {
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
        Event temp = new Event(desc, from, to);
        return temp;
    }

    private Deadline getDeadline() throws NoFollowUpException {
        String[] parts = getInput().split(" ");

        if (getInput().length() <= 8) {
            throw new NoFollowUpException();
        }

        String by = "";
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].charAt(0) == '/') {
                for (int j = i + 1; j < parts.length; j++){
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
        Deadline temp = new Deadline(desc, by);
        return temp;
    }

    public void printAcknowledgeMessage(int taskListSize, Ui ui, Task task) {
        ui.showLine();
        System.out.println("Got it. I've added this task:\n" +
                task + "\n" +
                "Now you have " + taskListSize +" tasks in the list.");
        ui.showLine();
    }
}
