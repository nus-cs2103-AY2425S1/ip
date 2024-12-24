package colress;

import java.time.LocalDate;

import colress.tasklist.TaskList;

/**
 * An abstract class for the Beginner mode Ui of the Colress chatbot.
 * The Beginner mode Ui uses multi-line command format, with prompts along each step.
 */
public abstract class UiBeginner extends Ui {
    public UiBeginner(Chatbot colress) {
        super(colress);
    }

    /**
     * Sets status of the UI to expect a keyword for the user's next input and returns a prompt to the user
     * for a keyword to find in the list of tasks.
     */
    public String promptKeyword(TaskList taskList) {
        setStatus(Status.KEYWORD);
        return PROMPT_KEYWORD;
    }

    /**
     * Sets status of the UI to expect a task type for the user's next input and returns a prompt to the user
     * for a task type to add to the list of tasks.
     */
    public String promptTaskType() {
        setStatus(Status.TASKTYPE);
        return PROMPT_TASK_TYPE;
    }

    /**
     * Sets status of the UI to expect a task description for the user's next input and returns a prompt to the user
     * for a description of the task to add to the list of tasks. The prompt returned depends on the type of task
     * to be added, indicated by the TaskType argument.
     */
    public String promptDescription(TaskType taskType) {
        setStatus(Status.DESCRIPTION);
        switch (taskType) {
        case DEADLINE:
            return PROMPT_DEADLINE_DESCRIPTION;
        case EVENT:
            return PROMPT_EVENT_DESCRIPTION;
        default:
            return PROMPT_TASK_DESCRIPTION;
        }
    }

    /**
     * Sets status of the UI to expect a date for the user's next input and returns a prompt to the user
     * for a date. The prompt returned depends on the current command to be executed and the type of task to be added.
     *
     * @param taskType The type of the task to be added.
     * @param taskList The TaskList to add the task to.
     */
    public String promptDate(TaskType taskType, TaskList taskList) {
        setStatus(Status.DATE);
        switch (taskType) {
        case DEADLINE:
            return PROMPT_DEADLINE;
        case EVENT:
            return PROMPT_EVENT_DATE;
        default:
            return PROMPT_DATE;
        }
    }

    /**
     * Checks whether a start time or an end time is expected using the timeType argument and sets the status of the UI
     * to expect the right time. The corresponding prompt is then returned.
     */
    public String promptTime(String timeType) {
        if (timeType.equals("from")) {
            setStatus(Status.STARTTIME);
            return PROMPT_EVENT_START_TIME;
        } else {
            setStatus(Status.ENDTIME);
            return PROMPT_EVENT_END_TIME;
        }
    }

    /**
     * Prompts the user to enter the task number of the task to operate on, reads it using its Parser object
     * and returns it.
     */
    public String promptTaskNumber(TaskList taskList) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        setStatus(Status.TASKNUMBER);
        return PROMPT_TASK_NUMBER;
    }

    /**
     * Sets status of the UI to expect a command for the user's next input and returns a String illustration of the list
     * of tasks in the given ColressTaskList that falls on the specified date.
     */
    @Override
    public String printTasks(TaskList taskList, LocalDate date) {
        setStatus(Status.COMMAND);
        return super.printTasks(taskList, date);
    }

    /**
     * Sets status of the UI to expect a command for the user's next input and returns a String illustration of the list
     * of tasks in the given ColressTaskList whose description contains the specified keyword.
     */
    @Override
    public String printTasks(TaskList taskList, String keyword) {
        setStatus(Status.COMMAND);
        return super.printTasks(taskList, keyword);
    }

    public abstract String processCommand(String input, TaskList taskList);
    public abstract String processKeyword(String input, TaskList taskList);
    public abstract String processTaskType(String input);
    public abstract String processDescription(String input, TaskList taskList);
    public abstract String processDate(String input, TaskList taskList);
    public abstract String processStartTime(String input);
    public abstract String processEndTime(String input, TaskList taskList);
    public abstract String processTaskNumber(String input, TaskList taskList);
}
