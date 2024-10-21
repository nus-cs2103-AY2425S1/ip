package timo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * Testing for Timo
 */
public class TimoTest {

    /**
     * Test the ui for todo command
     */
    @Test
    public void uiTodoTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //getting UI to use printTodo methods
        UI ui = new UI();
        //add testTask to testTaskList
        Todo testTask = new Todo(false, "return book");
        TaskList testTaskList = new TaskList();
        testTaskList.add(testTask);
        //test ui output
        String uiOutput = ui.printTodo(testTask, testTaskList);
        String expectedOutput = "----------------------------\n"
                + "Got it. I've added this task:\n"
                + testTask + "\nYou now have 1 tasks in your list\n"
                + "----------------------------";
        assertEquals(expectedOutput, uiOutput);
    }

    /**
     * check the mark command to see if task will be marked
     */
    @Test
    public void taskListMarkTest() {
        //marking the task and checking
        TaskList testTaskList = new TaskList();
        Todo todo = new Todo(false, "return book");
        testTaskList.add(todo);
        testTaskList.mark(1);
        Task test = testTaskList.showList().get(0);

        //create the data to compare
        Todo todoDummy = new Todo(true, "return book");
        assertEquals(test.toString(), todoDummy.toString());
    }
}
