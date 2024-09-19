package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains methods to test the Parser Class.
 */
public class ParserTest {

    private static final String REPLY_INVALID_TASK_NUMBER = "Invalid task number given.";
    private static final String REPLY_NO_TASK_WITH_NUMBER = "There is no task with the given task number.";
    private static final String REPLY_MISSING_TASK_NUMBER = "This command requires a task number to execute.";
    private static final String REPLY_EMPTY_TASK_DESCRIPTION = "The description of a task cannot be empty.";
    private static final String REPLY_MISSING_DEADLINE_DETAILS = "A deadline needs a description "
            + "and a date in YYYY-MM-DD format.";
    private static final String REPLY_MISSING_EVENT_DETAILS = "An event needs a description, "
            + "and both a start and end date or time in YYYY-MM-DD format.";
    private static final String REPLY_INVALID_FIND_KEYWORDS = "Cannot find an empty string.";
    private static final String REPLY_INVALID_COMMAND = "I don't recognise that command.";

    @Test
    public void parseCommand_invalidCommands_unsuccessful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);

        assertEquals(REPLY_INVALID_COMMAND, Parser.parseCommand(taskList, "lit"));
        assertEquals(REPLY_INVALID_COMMAND, Parser.parseCommand(taskList, "mar"));
        assertEquals(REPLY_INVALID_COMMAND, Parser.parseCommand(taskList, "und"));
    }

    @Test
    public void parseCommand_markTask_unsuccessful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);
        Task task = new Todo("quiz");
        TaskList.add(task);

        assertEquals(REPLY_MISSING_TASK_NUMBER, Parser.parseCommand(taskList, "mark"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "mark 0"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "mark     0"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "mark -1"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "mark 2"));
    }

    @Test
    public void parseCommand_unmarkTask_unsuccessful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);
        Task task = new Todo("quiz");
        TaskList.add(task);

        assertEquals(REPLY_MISSING_TASK_NUMBER, Parser.parseCommand(taskList, "unmark"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "unmark 0"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "unmark -1"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "unmark 2"));
    }

    @Test
    public void parseCommand_deleteTask_unsuccessful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);
        Task task = new Todo("quiz");
        TaskList.add(task);

        assertEquals(REPLY_MISSING_TASK_NUMBER, Parser.parseCommand(taskList, "delete"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "delete 0"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "delete -1"));
        assertEquals(REPLY_INVALID_TASK_NUMBER, Parser.parseCommand(taskList, "delete 2"));
    }

    @Test
    public void parseCommand_findTask_successful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);
        Task task = new Todo("quiz");
        TaskList.add(task);

        assertEquals(REPLY_INVALID_FIND_KEYWORDS, Parser.parseCommand(taskList, "find"));
    }

    @Test
    public void parseCommand_addTodoTask_successful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);
        Task task = new Todo("quiz");

        assertEquals(returnAddTaskReply(task,1),
                Parser.parseCommand(taskList, "todo quiz"));

        assertEquals(returnAddTaskReply(task,2),
                Parser.parseCommand(taskList, "todo     quiz"));

        assertEquals(returnAddTaskReply(task,3),
                Parser.parseCommand(taskList, "todo  quiz   "));
    }

    @Test
    public void parseCommand_addTodoTask_unsuccessful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);

        assertEquals(REPLY_EMPTY_TASK_DESCRIPTION, Parser.parseCommand(taskList, "todo"));
    }

    @Test
    public void parseCommand_addDeadlineTask_successful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);
        Task task = new Deadline("quiz", "2022-02-02");

        assertEquals(returnAddTaskReply(task,1),
                Parser.parseCommand(taskList, "deadline quiz /by 2022-02-02"));

        assertEquals(returnAddTaskReply(task,2),
                Parser.parseCommand(taskList, "deadline    quiz    /by 2022-02-02"));
    }

    @Test
    public void parseCommand_addDeadlineTask_unsuccessful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);

        assertEquals(REPLY_EMPTY_TASK_DESCRIPTION,
                Parser.parseCommand(taskList, "deadline"));

        assertEquals(REPLY_MISSING_DEADLINE_DETAILS,
                Parser.parseCommand(taskList, "deadline quiz"));

        assertEquals(REPLY_MISSING_DEADLINE_DETAILS,
                Parser.parseCommand(taskList, "deadline /by 2022-02-02"));

        assertEquals(REPLY_MISSING_DEADLINE_DETAILS,
                Parser.parseCommand(taskList, "deadline 2022-02-02"));
    }

    @Test
    public void parseCommand_addEventTask_successful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);

        Task task = new Deadline("quiz", "2022-02-02");

        assertEquals(returnAddTaskReply(task,1),
                Parser.parseCommand(taskList, "deadline quiz /by 2022-02-02"));

        assertEquals(returnAddTaskReply(task,2),
                Parser.parseCommand(taskList, "deadline    quiz    /by 2022-02-02"));
    }

    @Test
    public void parseCommand_addEventTask_unsuccessful() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);

        assertEquals(REPLY_EMPTY_TASK_DESCRIPTION, Parser.parseCommand(taskList, "event"));
        assertEquals(REPLY_MISSING_EVENT_DETAILS, Parser.parseCommand(taskList, "event lecture"));
        assertEquals(REPLY_MISSING_EVENT_DETAILS, Parser.parseCommand(taskList, "event /from 2022-02-02"));
        assertEquals(REPLY_MISSING_EVENT_DETAILS, Parser.parseCommand(taskList, "event /to 2022-02-02"));
        assertEquals(REPLY_MISSING_EVENT_DETAILS, Parser.parseCommand(taskList, "event lecture /from 2022-02-02"));
        assertEquals(REPLY_MISSING_EVENT_DETAILS, Parser.parseCommand(taskList, "event lecture /to 2022-02-02"));
        assertEquals(REPLY_MISSING_EVENT_DETAILS, Parser.parseCommand(taskList, "event /from 2022-02-02 /to 2022-02-02"));
    }

    private String returnAddTaskReply(Task task, int numberOfTasks) {
        return "Got it. I've added this task:\n  " + task.toString() + "\nNow you have " + numberOfTasks + " tasks in the list.";
    }

}
