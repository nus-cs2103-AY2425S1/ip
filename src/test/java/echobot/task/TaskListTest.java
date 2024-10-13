package echobot.task;

import echobot.exception.EchoBotException;
import echobot.exception.TaskNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    @Test
    @DisplayName("Add tasks into task list")
    public void init() {
        this.taskList = new TaskList();
        this.taskList.addTask(new TaskStub(true, "task1"));
        this.taskList.addTask(new TaskStub(false, "task2"));
        this.taskList.addTask(new TaskStub(false, "task3"));
        this.taskList.addTask(new TaskStub(true, "task4"));
        this.taskList.addTask(new TaskStub(false, "task5"));
        this.taskList.addTask(new TaskStub(false, "assignment 1"));
        this.taskList.addTask(new TaskStub(true, "assignment 2"));
    }

    @Nested
    class DeleteTasksByIndexTest {
        @Test
        @DisplayName("Delete a task")
        public void deleteTaskTest1() {
            try {
                TaskStub task = (TaskStub) TaskListTest.this.taskList.deleteTaskByIndex(0);
                assertAll(() -> assertTrue(task.equals(new TaskStub(true, "task1"))), () -> assertEquals(6, TaskListTest.this.taskList.size()));

            } catch (EchoBotException e) {
                fail();
            }
        }

        @Test
        @DisplayName("Delete a task")
        public void deleteTaskTest2() {
            try {
                TaskStub task = (TaskStub) TaskListTest.this.taskList.deleteTaskByIndex(2);
                assertAll(() -> assertTrue(task.equals(new TaskStub(false, "task3"))), () -> assertEquals(6, TaskListTest.this.taskList.size()));
            } catch (EchoBotException e) {
                fail();
            }
        }

        @Test
        @DisplayName("Delete a task with index out of bound")
        public void deleteTaskTest3() {
            try {
                TaskListTest.this.taskList.deleteTaskByIndex(100);
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(TaskNotFoundException.class, e), () -> assertEquals("Cannot find this task in the list!", e.getMessage()));
            }
        }

        @Test
        @DisplayName("Delete a task with negative integer")
        public void deleteTaskTest4() {
            try {
                TaskListTest.this.taskList.deleteTaskByIndex(-1);
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(TaskNotFoundException.class, e), () -> assertEquals("Cannot find this task in the list!", e.getMessage()));
            }
        }
    }

    @Nested
    class MarkTasksByIndexTest {
        @Test
        @DisplayName("Mark a undone task done")
        public void markTasksByIndexTest1() {
            try {
                TaskStub task = (TaskStub) TaskListTest.this.taskList.markTaskByIndex(1);
                assertTrue(new TaskStub(true, "task2").equals(task));
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Mark a done task done")
        public void markTasksByIndexTest2() {
            try {
                TaskStub task = (TaskStub) TaskListTest.this.taskList.markTaskByIndex(3);
                assertTrue(new TaskStub(true, "task4").equals(task));
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Mark a task with index out of bound")
        public void markTasksByIndexTest3() {
            try {
                TaskListTest.this.taskList.markTaskByIndex(10);
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(TaskNotFoundException.class, e), () -> assertEquals("Cannot find this task in the list!", e.getMessage()));
            }
        }
    }

    @Nested
    class UnMarkTasksByIndexTest {
        @Test
        @DisplayName("Unmark a done task undone")
        public void unmarkTasksByIndexTest1() {
            try {
                TaskStub task = (TaskStub) TaskListTest.this.taskList.unmarkTaskByIndex(0);
                assertTrue(new TaskStub(false, "task1").equals(task));
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Unmark a undone task done")
        public void unmarkTasksByIndexTest2() {
            try {
                TaskStub task = (TaskStub) TaskListTest.this.taskList.unmarkTaskByIndex(4);
                assertTrue(new TaskStub(false, "task5").equals(task));
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Unmark a task with index out of bound")
        public void unmarkTasksByIndexTest3() {
            try {
                TaskListTest.this.taskList.unmarkTaskByIndex(10);
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(TaskNotFoundException.class, e), () -> assertEquals("Cannot find this task in the list!", e.getMessage()));
            }
        }
    }

    @Nested
    class ListTaskByKeywordTest {
        @Test
        @DisplayName("Find tasks by keyword in the list")
        public void findTasksByKeyword1() {
            try {
                List<? super TaskStub> tasks = TaskListTest.this.taskList.findTasksByKeyword("assignment");
                assertAll(() -> assertEquals(2, tasks.size()), () -> assertTrue(new TaskStub(false, "assignment 1").equals((TaskStub) tasks.get(0))), () -> assertTrue(new TaskStub(true, "assignment 2").equals((TaskStub) tasks.get(1))));
            } catch (Exception ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Find tasks by keyword in the list")
        public void findTasksByKeyword2() {
            List<? super TaskStub> tasks = TaskListTest.this.taskList.findTasksByKeyword("asfsfas");
            assertEquals(0, tasks.size());
        }
    }
}
