//package rapgod.tasks;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ToDoTest {
//
//    @Test
//    public void testToDoInitialization() {
//        // Test with a description
//        String description = "Finish homework";
//        Task todo = new ToDo(description);
//
//        assertEquals(description, todo.getDescription(), "Description should be set correctly.");
//
//        assertFalse(todo.isMarkedDone(), "New ToDo task should be marked as not done.");
//    }
//
//    @Test
//    public void testToStringNotDone() {
//        String description = "Finish homework";
//        ToDo todo = new ToDo(description);
//        String expected = "[T] [ ] Finish homework";
//
//        assertEquals(expected, todo.toString(), "toString() should return the correct format when task is not done.");
//    }
//
//    @Test
//    public void testToStringDone() {
//        String description = "Finish homework";
//        Task todo = new ToDo(description);
//        todo.setIsDone(true);
//        String expected = "[T] [X] Finish homework";
//
//        assertEquals(expected, todo.toString(), "toString() should return the correct format when task is done.");
//    }
//}
