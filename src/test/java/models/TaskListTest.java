package models;

import lib.FileDbDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import lib.DbDriverInterface;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;
    private DbDriverInterface dbDriver;

    @BeforeEach
    void setUp() {
        dbDriver = new FileDbDriver(); // Assuming DbDriverImplementation is the actual implementation of DbDriverInterface
        taskList = new TaskList(dbDriver); // Assuming TaskList is the class containing the deserialiseRawData method
    }

    @AfterEach
    void tearDown() {
        // Resetting the DB by writing an empty string
        dbDriver.save(""); // Assuming write method is available to reset the database
    }

    @Test
    void testDeserialiseRawData_TodoTask() {
        // Arrange: Setting up raw data for a Todo task
        String rawData = "T|0|Buy milk\n"; // "T" for Todo, "0" for incomplete, "Buy milk" is the description
        dbDriver.save(rawData); // Writing raw data to the driver

        // Act: Run the method
        taskList.deserialiseRawData();

        // Assert: Verify the tasks list has one Todo task and validate its properties
        List<Task> tasks = taskList.getTasks(); // Assuming getTasks() method to retrieve tasks
        assertEquals(1, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));
        Todo todo = (Todo) tasks.get(0);
        assertEquals("Buy milk", todo.getDescription());
        assertFalse(todo.getIsDone());
    }

    @Test
    void testDeserialiseRawData_EventTask() {
        // Arrange: Setting up raw data for an Event task
        String rawData = "E|1|Meeting|2024-10-10|2024-10-11\n"; // "E" for Event, "1" for complete, details for description, date, and time
        dbDriver.save(rawData); // Writing raw data to the driver

        // Act
        taskList.deserialiseRawData();

        // Assert
        List<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Event);
        Event event = (Event) tasks.get(0);
        assertEquals("Meeting", event.getDescription());
        assertTrue(event.getIsDone());
    }

    @Test
    void testDeserialiseRawData_DeadlineTask() {
        // Arrange: Setting up raw data for a Deadline task
        String rawData = "D|0|Submit report|2024-09-15\n"; // "D" for Deadline, "0" for incomplete, description, and deadline date
        dbDriver.save(rawData); // Writing raw data to the driver

        // Act
        taskList.deserialiseRawData();

        // Assert
        List<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.get(0));
        Deadline deadline = (Deadline) tasks.get(0);
        assertEquals("Submit report", deadline.getDescription());
        assertFalse(deadline.getIsDone());
        assertEquals("2024-09-15", deadline.getBy().toString());
    }
}
