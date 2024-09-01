package FRIDAY;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class ToDoTest {
     @Test
     void testConstructor() {
         // Test that a ToDo object is created correctly
         String description = "Buy milk";
         int type = 1;
         ToDo todo = new ToDo(description, type);
         assertEquals(description, todo.getDescription());
         assertEquals(true, todo.getType());
     }

     @Test
     void testStorageDisplay() {
         // Test that storageDisplay returns the correct format
         ToDo todo = new ToDo("Read book", 1);
         String expectedStorageDisplay = "T | 1 | Read book";
         assertEquals(expectedStorageDisplay, todo.storageDisplay());
     }

     @Test
     void testToString() {
         // Test that toString returns the correct format
         ToDo todo = new ToDo("Finish homework", 1);
         String expectedToString = "[T][X] Finish homework";
         assertEquals(expectedToString, todo.toString());
     }
}
