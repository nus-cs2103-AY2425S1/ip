package milo.command;

import milo.tasks.*;
import milo.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    private final TaskTypes.TaskType taskType;

    private final String[] taskDesc;
    private Task curTask;

    private boolean dateError = false;

    public AddCommand(String[] taskDesc, TaskTypes.TaskType taskType) {
        this.taskDesc = taskDesc;
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList taskList) {
        switch (this.taskType) {
            case TODO:
                // Check case where todos empty
                if (taskDesc.length == 1) {
                    super.hasError = true;
                    super.errorDesc = "The description of a todo cannot be empty";
                } else {
                    String desc = taskDesc[1].strip();
                    this.curTask = new Todo(desc);
                    taskList.add(curTask);
                }
                break;
            case DEADLINE:
                // Check case where deadline empty
                if (taskDesc.length == 1) {
                    super.hasError = true;
                    super.errorDesc = "The description of a deadline cannot be empty";
                } else {
                    // Check case where deadline command is not properly formatted
                    String[] deadlineDesc = taskDesc[1].split("/by", 2);
                    if (deadlineDesc.length != 2) {
                        super.hasError = true;
                        super.errorDesc = "Invalid deadline command\n Proper formatting: deadline <task description>"
                                + " + /by + <date description>";
                    } else {
                        try {
                            LocalDate curDate = LocalDate.parse(deadlineDesc[1].strip());
                            this.curTask = new Deadline(deadlineDesc[0].strip(), curDate);
                            taskList.add(this.curTask);
                        } catch (DateTimeParseException e) {
                            this.dateError = true;
                        }
                    }
                }
                break;
            case EVENT:
                // Check case where event empty
                if (taskDesc.length == 1) {
                    super.hasError = true;
                    super.errorDesc = "The description of an event cannot be empty";
                } else {
                    // Check case where event command is not properly formatted
                    String[] eventDesc = taskDesc[1].split("/from | /to", 3);
                    if (eventDesc.length != 3) {
                        super.hasError = true;
                        super.errorDesc = "Invalid event command\n Proper formatting: deadline <task description> + "
                                + "/from + <starting date description> + /to + <ending date description";
                    } else {
                        try {
                            LocalDate fromDate = LocalDate.parse(eventDesc[1].strip());
                            LocalDate toDate = LocalDate.parse(eventDesc[1].strip());
                            this.curTask = new Event(eventDesc[0].strip(), fromDate, toDate);
                            taskList.add(curTask);
                        } catch (DateTimeParseException e) {
                            this.dateError = true;
                        }
                    }
                }
                break;
            default:
                return;
        }
    }

    @Override
    public String commandToString(Ui ui, TaskList taskList) {
        switch (this.taskType) {
        case TODO:
            if (super.hasError) {
                return ui.printError(TaskTypes.TaskType.TODO, super.errorDesc);
            }
            return ui.printTask(curTask, taskList.getNumberOfTasks());
        case DEADLINE:
            if (this.dateError) {
                return ui.printError(TaskTypes.TaskType.DATE, "");
            }
            if (super.hasError) {
                return ui.printError(TaskTypes.TaskType.DEADLINE, super.errorDesc);
            }
            return ui.printTask(this.curTask, taskList.getNumberOfTasks());
        case EVENT:
            if (this.dateError) {
                return ui.printError(TaskTypes.TaskType.DATE, "");
            }
            if (super.hasError) {
                return ui.printError(TaskTypes.TaskType.EVENT, super.errorDesc);
            }
            return ui.printTask(this.curTask, taskList.getNumberOfTasks());
        default:
            return "";

        }
    }
}
