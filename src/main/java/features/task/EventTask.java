package features.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end time.
 */
public class EventTask extends Task {
	private LocalDateTime startAt;
	private LocalDateTime endAt;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	/**
	 * Constructs an EventTask with the given name, start time, and end time.
	 * The start and end times are parsed from strings in the format "yyyy-MM-dd HH:mm".
	 * Throws an exception if the start time is after the end time.
	 *
	 * @param name the name of the task
	 * @param startAt the start time of the task in "yyyy-MM-dd HH:mm" format
	 * @param endAt the end time of the task in "yyyy-MM-dd HH:mm" format
	 * @throws Exception if the start time is after the end time
	 */
	public EventTask(String name, String startAt, String endAt) throws Exception {
		super(name);
		this.startAt = LocalDateTime.parse(startAt, formatter);
		this.endAt = LocalDateTime.parse(endAt, formatter);
		if (this.startAt.isAfter(this.endAt)) {
			throw new Exception("Start date must be before end date.");
		}
	}

	/**
	 * Returns a string representation of the EventTask.
	 *
	 * @return a string representation of the EventTask in the format "[E][x] name (from: startAt to: endAt)"
	 */
	@Override
	public String toString() {
		return "[E]" + super.toString() + " (from: " + this.startAt.toString() + " to: " + this.endAt.toString() + ")";
	}

	/**
	 * Returns the start time of the task in "yyyy-MM-dd'T'HH:mm:ss" format.
	 *
	 * @return the start time of the task
	 */
	public String getStartAt() {
		return this.startAt.toString().replace("T", " ");
	}

	/**
	 * Returns the end time of the task in "yyyy-MM-dd'T'HH:mm:ss" format.
	 *
	 * @return the end time of the task
	 */
	public String getEndAt() {
		return this.endAt.toString().replace("T", " ");
	}
}
