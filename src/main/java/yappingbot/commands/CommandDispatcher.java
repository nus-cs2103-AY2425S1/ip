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
import yappingbot.ui.Ui;

/**
 * Class of implemented commands and their functions.
 */
public class CommandDispatcher {

    private final Ui ui;

    /**
     * Constructor for dispatcher to run commands.
     *
     * @param ui UI interface to output text
     */
    public CommandDispatcher(Ui ui) {
        this.ui = ui;
    }

    /**
     * Resets the currentUserList to default view if it is currently a filter view.
     *
     * @param currentUserList TaskList that needs to be resetted
     * @return TaskList of the whole task list, not filtered
     */
    public TaskList resetView(TaskList currentUserList) {
        // reset the view to main parent
        TaskList userList = currentUserList;
        while (userList instanceof TaskListFilterView) {
            userList = ((TaskListFilterView) userList).getParent();
        }
        ui.println(ReplyTextMessages.RESET_TEXT);
        return userList;
    }

    /**
     * Prints the contents of the task list in a human-friendly format.
     * Example: [T][X] Marked To-do task
     *
     * @param userList TaskList to be printed
     */
    public void printUserList(TaskList userList) {
        if (userList.isEmpty()) {
            ui.println("List is empty!");
        }


        StringBuilder sb = new StringBuilder();
        sb.append(ReplyTextMessages.LIST_TEXT);
        if (userList instanceof TaskListFilterView) {
            sb.append(String.format("\n(Filter: '%s')",
                                     ((TaskListFilterView) userList).getFilterString()));
        }
        sb.append("\n");
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            sb.append(
                    String.format(
                            "\n%2d.%s",
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
        ui.print(sb.toString());
    }

    /**
     * Marks or Unmarks a task in a task list.
     *
     * @param i integer index of task in list.
     * @param isTaskDone boolean of whether to mark or unmark the task.
     * @param userList TaskList to be searched.
     */
    public void changeTaskListStatus(int i, boolean isTaskDone, TaskList userList) {
        Task t = userList.get(i);
        t.setTaskDone(isTaskDone);
        StringBuilder sb = new StringBuilder();
        if (isTaskDone) {
            sb.append(ReplyTextMessages.MARKED_TASK_AS_DONE_TEXT);
        } else {
            sb.append(ReplyTextMessages.UNMARKED_TASK_AS_DONE_TEXT);
        }
        sb.append("\n");
        sb.append(
                String.format(
                        ReplyTextMessages.TASK_PRINT_TEXT_3s,
                        t.getTaskTypeSymbol(),
                        t.getTaskDoneCheckmark(),
                        t
                )
        );
        ui.print(sb.toString());
    }

    /**
     * Deletes a task in the user list, and prints that task out.
     *
     * @param i integer index of task in list.
     * @param userList TaskList to be searched.
     */
    public void deleteTask(int i, TaskList userList) {
        Task t = userList.deleteTask(i);
        ui.print(ReplyTextMessages.DELETED_TEXT
                 + "\n"
                 + String.format(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                                 t.getTaskTypeSymbol(),
                                 t.getTaskDoneCheckmark(),
                                 t)
                 + "\n"
                 + String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d,
                                 userList.size()));
    }


    /**
     * Creates a new task and inserts it into the task list.
     *
     * @param userInputSpliced String Array to be interpreted as the task to be created
     * @param taskTypes        taskTypes type of the task to be created.
     * @param userList         TaskList to be searched
     * @throws YappingBotIncorrectCommandException Exception if creating the task failed.
     */
    public void createNewTask(
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
        sb = new StringBuilder();
        sb.append("\n");
        sb.append(ReplyTextMessages.ADDED_TEXT);
        sb.append(
                String.format(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                              newTask.getTaskTypeSymbol(),
                              newTask.getTaskDoneCheckmark(),
                              newTask)
        );
        userList.addTask(newTask);
        sb.append("\n");
        sb.append(String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size()));
        ui.print(sb.toString());
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
    public TaskList findStringInTasks(String searchString, TaskList userList) {
        StringBuilder sb = new StringBuilder();
        String searchStringSanitized = searchString.replaceAll("\n", "");
        sb.append(String.format(ReplyTextMessages.FIND_STRING_INIT_1s, searchStringSanitized));
        TaskList newFilteredView = TaskListFilterView.createFilter(userList, searchString);
        sb.append("\n");
        if (newFilteredView.isEmpty()) {
            sb.append(String.format(ReplyTextMessages.FIND_STRING_FAIL_1s, searchString));
            ui.print(sb.toString());
            return userList;
        } else {
            sb.append(String.format(
                    ReplyTextMessages.FIND_STRING_FOUND_1d_1s,
                    newFilteredView.size(),
                    searchString));
            ui.print(sb.toString());
            printUserList(newFilteredView);
            return newFilteredView;
        }
    }
}
