package sigmabot.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sigmabot.task.Deadline;
import sigmabot.task.Event;
import sigmabot.task.Task;
import sigmabot.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ListReaderTest {

	private File tempFile;

	@BeforeEach
	public void setUp() throws Exception {
		// Create a temporary file before each test
		tempFile = File.createTempFile("tasks", ".txt");
	}

	@AfterEach
	public void tearDown() throws Exception {
		// Delete the temporary file after each test
		if (tempFile.exists()) {
			tempFile.delete();
		}
	}

	@Test
	public void testReadTodoTask() throws Exception {
		String content = "[T] [ ] a\n\tDescription: b";
		writeToFile(tempFile, content);

		ListReader listReader = new ListReader();
		Map<String, Task> tasks = listReader.readTasksFromFile(tempFile.getAbsolutePath());

		assertEquals(1, tasks.size());
		assertTrue(tasks.containsKey("a"));

		Task task = tasks.get("a");
		assertTrue(task instanceof Todo);
		assertEquals("a", task.getName());
		assertEquals("b", task.getDescription());
		assertFalse(task.isDone());
	}

	@Test
	public void testReadMarkedTodoTask() throws Exception {
		String content = "[T] [X] a\n\tDescription: b";
		writeToFile(tempFile, content);

		ListReader listReader = new ListReader();
		Map<String, Task> tasks = listReader.readTasksFromFile(tempFile.getAbsolutePath());

		assertEquals(1, tasks.size());
		assertTrue(tasks.containsKey("a"));

		Task task = tasks.get("a");
		assertTrue(task instanceof Todo);
		assertEquals("a", task.getName());
		assertEquals("b", task.getDescription());
		assertTrue(task.isDone());
	}

	@Test
	public void testReadDeadlineTask() throws Exception {
		String content = "[D] [ ] d\n\tDescription: desc\n\tBy: 01/FEB/2099";
		writeToFile(tempFile, content);

		ListReader listReader = new ListReader();
		Map<String, Task> tasks = listReader.readTasksFromFile(tempFile.getAbsolutePath());

		assertEquals(1, tasks.size());
		assertTrue(tasks.containsKey("d"));

		Task task = tasks.get("d");
		assertTrue(task instanceof Deadline);
		assertEquals("d", task.getName());
		assertEquals("desc", task.getDescription());
		assertFalse(task.isDone());
		assertEquals(LocalDate.of(2099, 2, 1), ((Deadline) task).getByTime());
	}

	@Test
	public void testReadEventTask() throws Exception {
		String content = "[E] [ ] e\n\tDescription: desc\n\tStart Time: 01/JAN/2099\n\tEnd Time: 02/JAN/2099\n\tLocation: Somewhere";
		writeToFile(tempFile, content);

		ListReader listReader = new ListReader();
		Map<String, Task> tasks = listReader.readTasksFromFile(tempFile.getAbsolutePath());

		assertEquals(1, tasks.size());
		assertTrue(tasks.containsKey("e"));

		Task task = tasks.get("e");
		assertTrue(task instanceof Event);
		assertEquals("e", task.getName());
		assertEquals("desc", task.getDescription());
		assertFalse(task.isDone());
		assertEquals(LocalDate.of(2099, 1, 1), ((Event) task).getStartTime());
		assertEquals(LocalDate.of(2099, 1, 2), ((Event) task).getEndTime());
		assertEquals("Somewhere", ((Event) task).getLocation());
	}

	private void writeToFile(File file, String content) throws Exception {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
		}
	}
}