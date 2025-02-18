package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KorolevTodoTest {
    @Test
    public void todoTest_markEvent_success() {
        KorolevTodo expected = new KorolevTodo("test");
        expected.markTask();
        assertEquals(expected.toString(), "[T][X] test  [tag:]");
        expected.unmarkTask();
        assertEquals(expected.toString(), "[T][ ] test  [tag:]");
    }
}
