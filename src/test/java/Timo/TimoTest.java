package Timo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimoTest {

    @Test
    public void uiTodoTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //getting UI to use printTodo methods
        UI ui = new UI();
        Todo task = new Todo(false, "return book");
        ui.printTodo(task, 1);
        String a = "----------------------------" + System.lineSeparator() + "Got it. I've added this task:" +
                System.lineSeparator() + task + System.lineSeparator() + "Now you have 1 tasks in the list." +
                System.lineSeparator() + "----------------------------" + System.lineSeparator();
        assertEquals(a, outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void taskListMarkTest() {
        //marking the task and checking
        TaskList a = new TaskList();
        Todo todo = new Todo(false, "return book");
        a.add(todo);
        a.mark(1);
        Task test = a.showList().get(0);

        //create the data to compare
        Todo todoDummy = new Todo(true, "return book");

        assertEquals(test.toString(), todoDummy.toString());




    }
}
