package commands;

import exceptions.*;
import stringconstants.ReplyTextMessages;
import tasks.*;

import ui.MultilineStringBuilder;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

public class Commands {
    public static void printUserList(ArrayList<Task> userList) {
        if (userList.isEmpty()) {
            Ui.println("List is empty!");
            return;
        }

        MultilineStringBuilder msb = new MultilineStringBuilder();
        msb.addLine(ReplyTextMessages.LIST_TEXT);
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            msb.addLine(
                    String.format(
                            "%2d.%s",
                            i+1,
                            String.format(
                                    ReplyTextMessages.TASK_PRINT_TEXT_3s,
                                    t.getTaskTypeSymbol(),
                                    t.getTaskDoneCheckmark(),
                                    t
                            )
                    )
            );
        }
        msb.print();
    }
    public static int parseTaskNumberSelected(String userInputSlice, ArrayList<Task> userList) throws YappingBotOOBException, YappingBotInvalidTaskNumberException {
        int i = -1;
        try {
            i = Integer.parseInt(userInputSlice) - 1;
        } catch (NumberFormatException ex) {
            throw new YappingBotInvalidTaskNumberException(userInputSlice);
        }

        // OOB
        if (i < 0 || i >= userList.size()) {
            throw new YappingBotOOBException(i);
        }

        return i;
    }
    public static void changeTaskListStatus(int i, boolean isTaskDone, ArrayList<Task> userList) {
        Task t = userList.get(i);
        t.setTaskDone(isTaskDone);
        MultilineStringBuilder msb = new MultilineStringBuilder();
        if (isTaskDone) {
            msb.addLine(ReplyTextMessages.MARKED_TASK_AS_DONE_TEXT);
        } else {
            msb.addLine(ReplyTextMessages.UNMARKED_TASK_AS_DONE_TEXT);
        }
        msb.addLine(
                String.format(
                        ReplyTextMessages.TASK_PRINT_TEXT_3s,
                        t.getTaskTypeSymbol(),
                        t.getTaskDoneCheckmark(),
                        t
                )
        );
        msb.print();
    }
    public static void deleteTask(int i, ArrayList<Task> userList) {
        Task t = userList.get(i);
        userList.remove(i);
        MultilineStringBuilder msb = new MultilineStringBuilder();
        msb.addLine(ReplyTextMessages.DELETED_TEXT);
        msb.addLine(
                String.format(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                        t.getTaskTypeSymbol(),
                        t.getTaskDoneCheckmark(),
                        t)
        );
        msb.addLine(String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size()));
    }
    public static Task addTaskToList(String[] userInputSpliced, TaskTypes taskTypes, ArrayList<Task> userList) throws YappingBotIncorrectCommandException {
        Task newTask;
        String taskName = null;
        String command = null;
        StringBuilder sb = new StringBuilder();
        switch (taskTypes) {
            case TODO:
                if (userInputSpliced.length <= 1) {
                    throw new YappingBotIncorrectCommandException(ReplyTextMessages.TODO_USAGE, userInputSpliced[0]);
                }
                // pattern: ^[COMMAND] ( titles )$
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                    } else {
                        sb.append(s);
                        sb.append(" ");
                    }
                }
                taskName = sb.toString();
                newTask = new Todo(taskName.trim(), false);
                break;
            case DEADLINE:
                if (userInputSpliced.length <= 1) {
                    throw new YappingBotIncorrectCommandException(ReplyTextMessages.DEADLINE_USAGE, userInputSpliced[0]);
                }
                // pattern: ^[COMMAND] (titles) /by (date)$
                String deadline;
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                        continue;
                    } else if (taskName == null && s.equals("/by")) {
                        taskName = sb.toString();
                        sb = new StringBuilder();
                        continue;
                    }
                    sb.append(s);
                    sb.append(" ");
                }
                deadline = sb.toString();
                if (deadline.isEmpty() || taskName == null) {
                    throw YappingBotIncorrectCommandException.withUserInputArray(ReplyTextMessages.DEADLINE_USAGE, userInputSpliced);
                }
                newTask = new Deadline(taskName.trim(), false, deadline.trim());
                break;
            case EVENT:
                if (userInputSpliced.length <= 1) {
                    throw new YappingBotIncorrectCommandException(ReplyTextMessages.EVENT_USAGE, userInputSpliced[0]);
                }
                // pattern: ^[COMMAND] (titles) /from (date) /to ([date])$
                String fromTime = null;
                String toTime = null;
                // todo: regexify this to use capture groups
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                        continue;
                    } else if (taskName == null && (s.equals("/from") || s.equals("/to"))) {
                        taskName = sb.toString();
                        sb = new StringBuilder();
                        continue;
                    } else {
                        if (fromTime == null && s.equals("/to")) {
                            fromTime = sb.toString();
                            sb = new StringBuilder();
                            continue;
                        } else if (toTime == null && s.equals("/from")) {
                            toTime = sb.toString();
                            sb = new StringBuilder();
                            continue;
                        }
                    }
                    sb.append(s);
                    sb.append(" ");
                }
                if (toTime == null) {
                    toTime = sb.toString();
                } else if (fromTime == null) {
                    fromTime = sb.toString();
                }
                if (fromTime == null) {
                    throw YappingBotIncorrectCommandException.withUserInputArray(ReplyTextMessages.EVENT_USAGE, userInputSpliced);
                }
                newTask = new Event(taskName.trim(), false, fromTime.trim(), toTime.trim());
                break;
            default:
                throw YappingBotIncorrectCommandException.withUserInputArray(ReplyTextMessages.EVENT_USAGE, userInputSpliced);
        }
        MultilineStringBuilder msb = new MultilineStringBuilder();
        msb.addLine(ReplyTextMessages.ADDED_TEXT);
        msb.addLine(
                String.format(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                        newTask.getTaskTypeSymbol(),
                        newTask.getTaskDoneCheckmark(),
                        newTask)
        );
        msb.addLine(String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size()));
        msb.print();
        return newTask;
    }
}
