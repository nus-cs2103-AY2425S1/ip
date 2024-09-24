package bestie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bestie.task.Priority;
import bestie.task.Todo;

public class UiTest {

    @Test
    void displayTasks_normalInput_displaysCorrectly() {
        Todo todo = new Todo("Quiz", Priority.HIGH);
        Ui ui = new Ui();
        String expectedOuput = "Gotcha! I've added the following task to your list: \n" + todo.toString() + "\n"
                + "Now you have 1 tasks in your list.";
        assertEquals(expectedOuput, ui.showTaskAdded(todo, 1));
    }

    @Test
    void showTaskMarked_normalInput_displaysCorrectly() {
        Todo todo = new Todo("Quiz", Priority.HIGH);
        Ui ui = new Ui();
        String expectedOuput = "Amazing! I've marked this task as done.\n"
                + "  " + todo.toString();
        assertEquals(expectedOuput, ui.showTaskMarked(todo));
    }


}
