package ned.tasks;
import ned.exceptions.NedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {
    @Test
    public void createToDo_validDescription_ToDoCreated() throws NedException {
        assertEquals(ToDo.createToDo("read", false), new ToDo("read", false));
    }

    @Test
    public void toTextForm_validDescription_savedProperly() throws NedException {
        assertEquals(ToDo.createToDo("read", false).toTextForm(), "todo|0|read");
    }

    @Test
    public void createToDo_blankDescription_NedExceptionThrown(){
        try {
            ToDo.createToDo("", false);
        } catch (NedException e) {
            assertEquals("M'lord, this saved todo task has no task description!", e.getMessage());
        }
    }
}
