package yappingbot.commands;

import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Deadline;
import yappingbot.tasks.Event;
import yappingbot.tasks.Task;
import yappingbot.tasks.Todo;

import yappingbot.tasks.tasklist.TaskList;
import yappingbot.tasks.tasklist.TaskListFilterView;
import yappingbot.tasks.tasklist.TaskTypes;
import yappingbot.ui.MultilineStringBuilder;
import yappingbot.ui.Ui;

/**
 * Class of implemented commands and their functions.
 */
public class Commands {

    /**
     * Prints the contents of the task list in a human-friendly format.
     * Example: [T][X] Marked To-do task
     *
     * @param userList TaskList to be printed
     */
    public static void printUserList(TaskList userList) {
        if (userList.isEmpty()) {
            Ui.println("List is empty!");
            return;
        }


        MultilineStringBuilder msb = new MultilineStringBuilder();
        msb.addLine(ReplyTextMessages.LIST_TEXT);
        if (userList instanceof TaskListFilterView) {
            msb.addLine(String.format("(Filter: '%s')",
                                     ((TaskListFilterView) userList).getFilterString()));
        }
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            msb.addLine(
                    String.format(
                            "%2d.%s",
                            i + 1,
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

    /**
     * Marks or Unmarks a task in a task list.
     *
     * @param i integer index of task in list.
     * @param isTaskDone boolean of whether to mark or unmark the task.
     * @param userList TaskList to be searched.
     */
    public static void changeTaskListStatus(int i, boolean isTaskDone, TaskList userList) {
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

    /**
     * Deletes a task in the user list, and prints that task out.
     *
     * @param i integer index of task in list.
     * @param userList TaskList to be searched.
     */
    public static void deleteTask(int i, TaskList userList) {
        Task t = userList.delete(i);
        MultilineStringBuilder msb = new MultilineStringBuilder();
        msb.addLine(ReplyTextMessages.DELETED_TEXT);
        msb.addLine(
                String.format(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                              t.getTaskTypeSymbol(),
                              t.getTaskDoneCheckmark(),
                              t)
        );
        msb.addLine(String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size()));
        msb.print();
    }


    /**
     * Creates a new task and inserts it into the task list.
     *
     * @param userInputSpliced String Array to be interpreted as the task to be created
     * @param taskTypes        taskTypes type of the task to be created.
     * @param userList         TaskList to be searched
     * @throws YappingBotIncorrectCommandException Exception if creating the task failed.
     */
    public static void createNewTask(
            String[] userInputSpliced,
            TaskTypes taskTypes,
            TaskList userList)
    throws YappingBotIncorrectCommandException {
        Task newTask;
        String taskName = null;
        String command = null;
        StringBuilder sb = new StringBuilder();
        switch (taskTypes) {
        case TODO:
            if (userInputSpliced.length <= 1) {
                throw new YappingBotIncorrectCommandException(
                        ReplyTextMessages.TODO_USAGE,
                        userInputSpliced[0]);
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
                throw new YappingBotIncorrectCommandException(
                        ReplyTextMessages.DEADLINE_USAGE,
                        userInputSpliced[0]);
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
                throw YappingBotIncorrectCommandException.withUserInputArray(
                        ReplyTextMessages.DEADLINE_USAGE,
                        userInputSpliced);
            }
            newTask = new Deadline(taskName.trim(), false, deadline.trim());
            break;
        case EVENT:
            if (userInputSpliced.length <= 1) {
                throw new YappingBotIncorrectCommandException(
                        ReplyTextMessages.EVENT_USAGE,
                        userInputSpliced[0]);
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
                throw YappingBotIncorrectCommandException.withUserInputArray(
                        ReplyTextMessages.EVENT_USAGE,
                        userInputSpliced);
            }
            newTask = new Event(taskName.trim(), false, fromTime.trim(), toTime.trim());
            break;
        default:
            throw YappingBotIncorrectCommandException.withUserInputArray(
                    ReplyTextMessages.EVENT_USAGE,
                    userInputSpliced);
        }
        MultilineStringBuilder msb = new MultilineStringBuilder();
        msb.addLine(ReplyTextMessages.ADDED_TEXT);
        msb.addLine(
                String.format(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                              newTask.getTaskTypeSymbol(),
                              newTask.getTaskDoneCheckmark(),
                              newTask)
        );
        userList.add(newTask);
        msb.addLine(String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size()));
        msb.print();
    }

    /**
     * Returns a TaskList that is either the same userList given should there be no results found,
     * or a new TaskListFilterView that will act as a TaskList but updates the parent TaskList
     * that was passed into.
     *
     * @param searchString String to be searched in each Task.
     * @param userList TaskList to be searched.
     * @return TaskList of given userList if not found, or new TaskListFilterView with filtered
     *         Tasks.
     */
    public static TaskList findStringInTasks(String searchString, TaskList userList) {
        MultilineStringBuilder msb = new MultilineStringBuilder();
        String searchStringSanitized = searchString.replaceAll("\n","");
        msb.addLine(String.format(ReplyTextMessages.FIND_STRING_INIT_1s, searchStringSanitized));
        TaskList newFilteredView = TaskListFilterView.createFilter(userList, searchString);
        if (newFilteredView.isEmpty()) {
            msb.addLine(String.format(ReplyTextMessages.FIND_STRING_FAIL_1s, searchString));
            msb.print();
            return userList;
        } else {
            msb.addLine(String.format(
                    ReplyTextMessages.FIND_STRING_FOUND_1d_1s,
                    newFilteredView.size(),
                    searchString));
            msb.print();
            printUserList(newFilteredView);
            return newFilteredView;
        }
    }
}
