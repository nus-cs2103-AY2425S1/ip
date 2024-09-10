package sigmabot.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateParserTest {

	@Test
	public void testValidDates() {
		assertEquals("2099-02-01", DateParser.parseDate("1/FEB/2099"));
		assertEquals("2099-02-01", DateParser.parseDate("01/FEB/2099"));
		assertEquals("2024-12-31", DateParser.parseDate("31/DEC/2024"));
	}

	@Test
	public void testInvalidMonth() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("1/ABC/2099");
		});
		assertEquals("Invalid month abbreviation: ABC", exception.getMessage());
	}

	@Test
	public void testInvalidDateFormat() {
		Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("FEB/1/2099");
		});
		assertTrue(exception1.getMessage().startsWith("Invalid date format"));

		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("2099/02/01");
		});
		assertTrue(exception2.getMessage().startsWith("Invalid date format"));

		Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("31/DEC");
		});
		assertTrue(exception3.getMessage().startsWith("Invalid date format"));
	}

	@Test
	public void testNonExistentDate() {
		Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("31/FEB/2024");
		});
		assertTrue(exception1.getMessage().contains("Invalid date format"));

		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			DateParser.parseDate("32/JAN/2024");
		});
		assertTrue(exception2.getMessage().contains("Invalid date format"));
	}
}