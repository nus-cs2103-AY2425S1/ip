package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * The DateParser class provides utility functions to parse date strings in various formats.
 * It supports converting dates from formats with abbreviated month names to the standard "yyyy-MM-dd" format.
 *
 * Supported input formats include "d/MMM/yyyy" and "dd/MMM/yyyy" where:
 * - "d" or "dd" represents a day (1-31)
 * - "MMM" represents a three-letter month abbreviation (e.g., "JAN", "FEB")
 * - "yyyy" represents a four-digit year
 *
 * Example input: "1/FEB/2099", "01/FEB/2099"
 * Example output: "2099-02-01"
 */
public class DateParser {

	// Mapping of month abbreviations to month numbers
	private static final Map<String, String> MONTH_ABBREVIATIONS = new HashMap<>();

	static {
		MONTH_ABBREVIATIONS.put("JAN", "01");
		MONTH_ABBREVIATIONS.put("FEB", "02");
		MONTH_ABBREVIATIONS.put("MAR", "03");
		MONTH_ABBREVIATIONS.put("APR", "04");
		MONTH_ABBREVIATIONS.put("MAY", "05");
		MONTH_ABBREVIATIONS.put("JUN", "06");
		MONTH_ABBREVIATIONS.put("JUL", "07");
		MONTH_ABBREVIATIONS.put("AUG", "08");
		MONTH_ABBREVIATIONS.put("SEP", "09");
		MONTH_ABBREVIATIONS.put("OCT", "10");
		MONTH_ABBREVIATIONS.put("NOV", "11");
		MONTH_ABBREVIATIONS.put("DEC", "12");
	}

	/**
	 * Parses a date string from the format "d/MMM/yyyy" or "dd/MMM/yyyy" (e.g., "1/FEB/2099" or "01/FEB/2099")
	 * to the standard "yyyy-MM-dd" format.
	 *
	 * This method first splits the input date string by the "/" character, extracts and converts the day, month, and year,
	 * then constructs a date string in the "yyyy-MM-dd" format. It also validates the final date format using the
	 * standard ISO_LOCAL_DATE formatter.
	 *
	 * @param dateStr The date string in the format "d/MMM/yyyy" or "dd/MMM/yyyy".
	 * @return A formatted date string in the format "yyyy-MM-dd".
	 * @throws IllegalArgumentException If the date string cannot be parsed due to invalid format or unrecognized month abbreviation.
	 */
	public static String parseDate(String dateStr) {
		// Split the date string by "/"
		String[] parts = dateStr.split("/");

		// Check if the date string is in the expected format
		if (parts.length != 3) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}

		// Ensure the day is two digits
		String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
		String monthAbbreviation = parts[1].toUpperCase(); // Convert the month abbreviation to uppercase
		String year = parts[2]; // Extract the year part

		// Convert the month abbreviation to its numeric equivalent using the predefined map
		String month = MONTH_ABBREVIATIONS.get(monthAbbreviation);

		// Throw an exception if the month abbreviation is not recognized
		if (month == null) {
			throw new IllegalArgumentException("Invalid month abbreviation: " + monthAbbreviation);
		}

		// Construct the date in "yyyy-MM-dd" format
		String formattedDate = year + "-" + month + "-" + day;

		// Validate the formatted date using the ISO_LOCAL_DATE formatter
		try {
			LocalDate.parse(formattedDate, DateTimeFormatter.ISO_LOCAL_DATE);
		} catch (DateTimeParseException e) {
			// Throw an exception if the date format is invalid
			throw new IllegalArgumentException("Invalid date format: " + dateStr, e);
		}

		// Return the formatted date
		return formattedDate;
	}
}
