package ned.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ned.exceptions.NedException;


public class ToDoTest {
    @Test
    public void createToDo_validDescription_toDoCreated() throws NedException {
        assertEquals(ToDo.createToDo("read", false), new ToDo("read", false));
    }

    @Test
    public void toTextForm_validDescription_savedProperly() throws NedException {
        assertEquals(ToDo.createToDo("read", false).toTextForm(), "todo|0|read");
    }

    @Test
    public void createToDo_blankDescription_nedExceptionThrown() {
        try {
            ToDo.createToDo("", false);
        } catch (NedException e) {
            assertEquals("M'lord, this saved todo task has no task description!", e.getMessage());
        }
    }
}
