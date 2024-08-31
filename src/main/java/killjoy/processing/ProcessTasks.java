package killjoy.processing;

import killjoy.main.KillJoy;
import killjoy.task.Task;
import killjoy.main.UserInterface;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessTasks {
    private KillJoy kj;
    private UserInterface ui;
    private Parser parser = new Parser(kj, ui);

    public ProcessTasks(KillJoy kj, UserInterface ui) {
        this.kj = kj;
        this.ui = ui;
    }

    public void processUserInput(String input) {

        Task.TaskType taskType = parser.parseUserInput(input);

        if (taskType == null) {
            ui.displayUnknownCommandMessage();
            return;
        }

        String[] inputSplitBySlash = input.split("/");
        if (inputSplitBySlash[0].split(" ").length == 1) {
            ui.displayInvalidCommandFormatMessage();
            return;
        }

        ui.printLine();
        switch (taskType) {
        case TODO: {
            String description = parser.getDescription(input);
            kj.addTask(description);
            break;
        }
        case DEADLINE: {
            String description = parser.getDescription(input);
            String by = parser.getBy(input);
            if (by == null) {
                ui.displayInvalidCommandFormatMessage();
                ui.printLine();
                return;
            }
            kj.addTask(description, by);
            break;
            }
        case EVENT: {
            String description = parser.getDescription(input);
            String from = parser.getFrom(input);
            String to = parser.getTo(input);
            if (from == null || to == null) {
                ui.displayInvalidCommandFormatMessage();
                ui.printLine();
                return;
            }
            kj.addTask(description, from, to);
            break;
        }
        }

        ui.displayAddedTaskMessage();
    }

    public void markOrDelete(String input) {
        String[] parsedInput = parser.parseMarkUnmarkDelete(input);

        if (parsedInput == null) {
            return;
        }

        String inputCommand = parsedInput[0];
        int taskIndex = Integer.parseInt(parsedInput[1]) - 1;

        if (taskIndex + 1 > kj.getTaskCount() || taskIndex < 0) {
            ui.displayTaskDoesNotExistMessage();
            return;
        }

        Task task = kj.getTask(taskIndex);
        if (inputCommand.equals("mark")) {
            task.changeStatus();
            ui.printLine();
            System.out.println("    " + ui.getMarkString() + "\n        " + task);
            ui.printLine();
        } else if (inputCommand.equals("unmark")) {
            task.changeStatus();
            ui.printLine();
            System.out.println("    " + ui.getUnmarkString() + "\n        " + task);
            ui.printLine();
        } else if (inputCommand.equals("delete")) {
            kj.removeTask(taskIndex);
            kj.decreaseTaskCount();
            ui.printLine();
            System.out.println("    " + ui.getDeleteString() + "\n        " + task );
            if (kj.getTaskCount() == 1) {
                System.out.println("    Now you have " + kj.getTaskCount() + " task in the list.");
            } else {
                System.out.println("    Now you have " + kj.getTaskCount() + " tasks in the list.");
            }
            ui.printLine();
        }
    }

    public void createTasks(String taskInfo) {
        String[] parts = parser.parseTaskInfo(taskInfo);
        Task.TaskType taskType = Task.TaskType.valueOf(parts[0]);
        switch (taskType) {
        case TODO: {
            String description = parts[2];
            kj.addTask(description);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.valueOf(parts[1]));
            break;
        }
        case DEADLINE: {
            String description = parts[2];
            String by = parts[3];
            kj.addTask(description, by);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.valueOf(parts[1]));
            break;
        }
        case EVENT: {
            String description = parts[2];
            String from = parts[3];
            String to = parts[4];
            kj.addTask(description, from, to);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.valueOf(parts[1]));
            break;
        }
        }
    }

    private void changeStatusOfTask(Task task, int i) {
        if (i == 1) {
            task.changeStatus();
        }
    }

}