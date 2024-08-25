package wiggly.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class EventTest {
	@Test
	public void testToFileFormat() {
		Event test = new Event("get a job", LocalDate.of(2024, 8, 8),
				LocalDate.of(2044, 8,8));
		assertEquals("E|false|get a job|2024-08-08|2044-08-08", test.toFileFormat());
	}

	@Test
	public void testToString() {
		Event test = new Event("get a job", LocalDate.of(2024, 8, 8),
				LocalDate.of(2044, 8,8));
		assertEquals("[E][ ] get a job (from: 8 Aug 2024 to: 8 Aug 2044)", test.toString());
	}
}