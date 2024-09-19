package cstwooneohthree.glados.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import cstwooneohthree.glados.enums.TaskType;
import cstwooneohthree.glados.exceptions.GladosException;
import cstwooneohthree.glados.utils.ParsedInfo;
import cstwooneohthree.glados.utils.ParserStub;

public class TaskListTest {

    private class TaskListWithStubbedParser extends TaskList {

        public TaskListWithStubbedParser(boolean shouldLoadTasks) {
            super(shouldLoadTasks);
        }

        /**
         * Adds a new task to tasks based on taskType.
         *
         * @param taskType Type of task to be added.
         * @param input Arguments after add comand.
         * @return Added task description and updated index in String array.
         * @throws GladosException If there is a parsing or task list error.
         */
        @Override
        public String[] add(TaskType taskType, String input) throws GladosException {

            assert taskType == TaskType.TODO || taskType == TaskType.DEADLINE || taskType == TaskType.EVENT;

            switch (taskType) {
            case TODO:
                ParsedInfo parsedTodoInputs = ParserStub.parseTask(taskType, input);
                tasks.add(new Todo(
                        parsedTodoInputs.getDescription()));
                break;
            case EVENT:
                ParsedInfo parsedEventInputs = ParserStub.parseTask(taskType, input);
                LocalDate[] eventDates = parsedEventInputs.getDates();
                tasks.add(new Event(
                        parsedEventInputs.getDescription(),
                        eventDates[0],
                        eventDates[1]));
                break;
            case DEADLINE:
                ParsedInfo parsedDeadlineInputs = ParserStub.parseTask(taskType, input);
                tasks.add(new Deadline(
                        parsedDeadlineInputs.getDescription(),
                        parsedDeadlineInputs.getDates()[0]));
                break;
            default:
                break;
            }
            listIndex++;

            return new String[]{tasks.get(listIndex - 1).toString(), String.valueOf(listIndex)};
        }
    }

    @Test
    public void testAddTodo() throws GladosException {
        TaskListWithStubbedParser taskList = new TaskListWithStubbedParser(false);
        assertArrayEquals(new String[]{"[T][ ] test todo", "1"}, taskList.add(TaskType.TODO, "test todo"));
    }

    @Test
    public void testAddEvent() throws GladosException {
        TaskListWithStubbedParser taskList = new TaskListWithStubbedParser(false);
        assertArrayEquals(
                new String[]{"[E][ ] test event (from: 2025-08-19 to: 2025-08-19)", "1"},
                taskList.add(TaskType.EVENT, "test event /from 2025-08-19 /to 2025-08-19"));
    }

    @Test
    public void testAddDeadline() throws GladosException {
        TaskListWithStubbedParser taskList = new TaskListWithStubbedParser(false);
        assertArrayEquals(
                new String[]{"[D][ ] test deadline (by: 2025-08-19)", "1"},
                taskList.add(TaskType.DEADLINE, "test deadline /by 2025-08-19"));
    }

    @Test
    public void testAddMultipleTasks() throws GladosException {
        TaskListWithStubbedParser taskList = new TaskListWithStubbedParser(false);
        taskList.add(TaskType.TODO, "test todo");
        taskList.add(TaskType.TODO, "test todo");
        assertArrayEquals(new String[]{"[T][ ] test todo", "3"}, taskList.add(TaskType.TODO, "test todo"));
    }
}

