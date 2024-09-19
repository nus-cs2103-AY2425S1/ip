package cstwooneohthree.glados.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cstwooneohthree.glados.enums.TaskType;
import cstwooneohthree.glados.exceptions.GladosException;
import cstwooneohthree.glados.exceptions.TaskNotFoundException;
import cstwooneohthree.glados.utils.ParsedInfo;
import cstwooneohthree.glados.utils.ParserStub;

public class TaskListTest {

    private TaskListWithStubbedParser taskList;

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

            //Removed saving to storage to avoid dependencies

            return new String[]{tasks.get(listIndex - 1).toString(), String.valueOf(listIndex)};
        }

        /**
         * Deletes task in tasks based on index.
         * If index is non applicable, exception is thrown.
         *
         * @param index Index of task to be deleted.
         * @return Deleted task description and updated index.
         * @throws TaskNotFoundException If index is outside of array list.
         */
        @Override
        public String[] delete(int index) throws TaskNotFoundException {

            assert listIndex == tasks.size();

            if (index - 1 < 0 || index - 1 >= listIndex) {
                throw new TaskNotFoundException();
            }

            Task task = tasks.remove(index - 1);
            listIndex--;

            //Removed saving to storage to avoid dependencies

            return new String[]{task.toString(), String.valueOf(listIndex)};
        }
    }

    @BeforeEach
    public void setUp() {
        taskList = new TaskListWithStubbedParser(false);
    }

    @Test
    public void testAddTodo() throws GladosException {
        assertArrayEquals(new String[]{"[T][ ] test todo", "1"}, taskList.add(TaskType.TODO, "test todo"));
    }

    @Test
    public void testAddEvent() throws GladosException {
        assertArrayEquals(
                new String[]{"[E][ ] test event (from: 2025-08-19 to: 2025-08-19)", "1"},
                taskList.add(TaskType.EVENT, "test event /from 2025-08-19 /to 2025-08-19"));
    }

    @Test
    public void testAddDeadline() throws GladosException {
        assertArrayEquals(
                new String[]{"[D][ ] test deadline (by: 2025-08-19)", "1"},
                taskList.add(TaskType.DEADLINE, "test deadline /by 2025-08-19"));
    }

    @Test
    public void testAddMultipleTasks() throws GladosException {
        taskList.add(TaskType.TODO, "test todo");
        taskList.add(TaskType.TODO, "test todo");
        assertArrayEquals(new String[]{"[T][ ] test todo", "3"}, taskList.add(TaskType.TODO, "test todo"));
    }

    @Test
    public void testDeleteValidTask() throws GladosException, TaskNotFoundException {
        taskList.add(TaskType.TODO, "test todo");
        taskList.add(TaskType.DEADLINE, "test deadline /by 2025-08-19");
        assertArrayEquals(new String[]{"[T][ ] test todo", "1"}, taskList.delete(1));
    }

    @Test
    public void testDeleteInvalidIndex() {
        try {
            taskList.add(TaskType.EVENT, "test event /from 2025-08-19 /to 2025-08-19");
        } catch (GladosException e) {
            fail("Unexpected exception thrown during add: " + e.getMessage());
        }
        assertThrows(TaskNotFoundException.class, () -> {
            taskList.delete(2);
        });
        assertThrows(TaskNotFoundException.class, () -> {
            taskList.delete(-1);
        });
    }

    @Test
    public void testDeleteMultipleTasks() throws GladosException, TaskNotFoundException {
        taskList.add(TaskType.TODO, "test todo");
        taskList.add(TaskType.TODO, "test todo");
        taskList.add(TaskType.TODO, "test todo");

        assertArrayEquals(new String[]{"[T][ ] test todo", "2"}, taskList.delete(2));
        assertArrayEquals(new String[]{"[T][ ] test todo", "1"}, taskList.delete(1));
    }
}

