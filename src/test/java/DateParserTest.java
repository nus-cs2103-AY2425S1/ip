import org.junit.jupiter.api.Test;
import util.DateParser;

import static org.junit.jupiter.api.Assertions.*;

public class DateParserTest {

	/**
	 * Tests valid date inputs and ensures they are correctly parsed.
	 */
	@Test
	public void testValidDates() {
		assertEquals("2099-02-01", DateParser.parseDate("1/FEB/2099"));
		assertEquals("2099-02-01", DateParser.parseDate("01/FEB/2099"));
		assertEquals("2024-12-31", DateParser.parseDate("31/DEC/2024"));
	}

	/**
	 * Tests an invalid month abbreviation and checks that the correct exception message is thrown.
	 */
	@Test
	public void testInvalidMonth() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("1/ABC/2099");
		});
		assertTrue(exception.getMessage().startsWith("Invalid date format"),
				"Expected exception message to start with 'Invalid date format' but got: " + exception.getMessage());
	}

	/**
	 * Tests several invalid date formats and ensures the correct exception messages are thrown.
	 */
	@Test
	public void testInvalidDateFormat() {
		Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("FEB/1/2099");
		});
		assertTrue(exception1.getMessage().startsWith("Invalid date format"),
				"Expected exception message to start with 'Invalid date format' but got: " + exception1.getMessage());

		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("2099/2/01");
		});
		assertTrue(exception2.getMessage().startsWith("Invalid date format"),
				"Expected exception message to start with 'Invalid date format' but got: " + exception2.getMessage());

		Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("31/DEC");
		});
		assertTrue(exception3.getMessage().startsWith("Invalid date format"),
				"Expected exception message to start with 'Invalid date format' but got: " + exception3.getMessage());
	}

	/**
	 * Tests non-existent dates (e.g., February 31st) and checks the exception message.
	 */
	@Test
	public void testNonExistentDate() {
		Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("31/FEB/2024");
		});
		assertTrue(exception1.getMessage().contains("Invalid date format"),
				"Expected exception message to contain 'Invalid date format' but got: " + exception1.getMessage());

		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("32/JAN/2024");
		});
		assertTrue(exception2.getMessage().contains("Invalid date format"),
				"Expected exception message to contain 'Invalid date format' but got: " + exception2.getMessage());
	}
}
