package killjoy.processing;

import killjoy.main.KillJoy;
import killjoy.task.Task;
import killjoy.main.UserInterface;


import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessTasks {
    private KillJoy kj;
    private UserInterface ui;
    private Parser parser = new Parser(ui);

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

        if (taskType != Task.TaskType.TODO && inputSplitBySlash.length == 1) {
            ui.printLine();
            ui.displayInvalidCommandFormatMessage();
            ui.printLine();
            return;
        }

        if (inputSplitBySlash[0].split(" ").length == 1) {
            ui.printLine();
            ui.displayInvalidCommandFormatMessage();
            ui.printLine();
            return;
        }

        ui.printLine();
        switch (taskType) {
        case TODO: {
            String description = Parser.getDescription(input);
            kj.addTask(description);
            break;
        }
        case DEADLINE: {
            String description = Parser.getDescription(input);
            String by = Parser.getBy(input);
            LocalDateTime byDateTime = Parser.parseDateTime(by);
            if (byDateTime == null) {
                ui.displayInvalidCommandFormatMessage();
                ui.printLine();
                return;
            }
            kj.addTask(description, byDateTime);
            break;
            }
        case EVENT: {
            String description = Parser.getDescription(input);
            String from = Parser.getFrom(input);
            LocalDateTime fromDateTime = Parser.parseDateTime(from);
            String to = Parser.getTo(input);
            LocalDateTime toDateTime = Parser.parseDateTime(to);
            if (fromDateTime == null || toDateTime == null) {
                ui.displayInvalidCommandFormatMessage();
                ui.printLine();
                return;
            }
            kj.addTask(description, fromDateTime, toDateTime);
            break;
        }
        }

        ui.displayAddedTaskMessage();
    }

    public void markOrDelete(String input) {
        String[] parsedInput = Parser.parseMarkUnmarkDelete(input);

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
        String[] parts = Parser.parseTaskInfo(taskInfo);
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
            LocalDateTime by = Parser.parseDateTime(parts[3]);
            kj.addTask(description, by);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.valueOf(parts[1]));
            break;
        }
        case EVENT: {
            String description = parts[2];
            LocalDateTime from = Parser.parseDateTime(parts[3]);
            LocalDateTime to = Parser.parseDateTime(parts[4]);
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