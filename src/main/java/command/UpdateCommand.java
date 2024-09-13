package command;

import exceptions.BuddyException;
import storage.Storage;
import task.Deadlines;
import task.Events;
import task.Task;
import task.TaskList;
import ui.Ui;


public class UpdateCommand extends Command {

    enum TaskField {
        DESCRIPTION, START, END, DEADLINE, UNKNOWN;

        public static TaskField stringToTaskField(String field) {
            if (field.equalsIgnoreCase("description")) {
                return TaskField.DESCRIPTION;
            } else if (field.equalsIgnoreCase("start")) {
                return TaskField.START;
            } else if (field.equalsIgnoreCase("end")) {
                return TaskField.END;
            } else if (field.equalsIgnoreCase("deadline")) {
                return TaskField.DEADLINE;
            } else {
                return TaskField.UNKNOWN;
            }
        }
    }

    private final int taskIndex;

    private final TaskField field;

    private final String updatedValue;

    public UpdateCommand(int taskIndex, String field, String updatedValue) {
        this.taskIndex = taskIndex;
        this.field = TaskField.stringToTaskField(field);
        this.updatedValue = updatedValue;
        System.out.println(taskIndex);
        System.out.println(field);
        System.out.println(updatedValue);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        //find task in arraylist
        if (taskIndex >= tasks.getTasks().size()) {
            return ui.displayUnavailableItem();
        }

        Task task = tasks.getTasks().get(taskIndex);
        System.out.println(task);

        //use switch case according to field
        switch (field) {
            case DESCRIPTION:
                task.updateDesc(updatedValue);
                break;
            case DEADLINE:
                //check if task is deadline
                if (task instanceof Deadlines) {
                   ((Deadlines) task).updateDeadline(updatedValue);
                   break;
                } else {
                    throw new BuddyException("Are you sure that's for a deadline");
                }
            case START:
                if (task instanceof Events) {
                    ((Events) task).updateStartDate(updatedValue);
                    break;
                } else {
                    throw new BuddyException("Are you sure that's for an event");
                }
            case END:
                if (task instanceof Events) {
                    ((Events) task).updateEndDate(updatedValue);
                    break;
                } else {
                    throw new BuddyException("Are you sure that's for an event");
                }
            case UNKNOWN:
                throw new BuddyException("Which field would you like to update");
        }

        //update storage with storage.save(tasks)
        storage.save(tasks.getTasks());

        //print out success UI
        return ui.displayUpdateSuccess();

    }


}
