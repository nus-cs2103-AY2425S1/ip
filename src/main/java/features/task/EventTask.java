package features.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
	private LocalDateTime startAt;
	private LocalDateTime endAt;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public EventTask(String name, String startAt, String endAt) throws Exception {
		super(name);
		this.startAt = LocalDateTime.parse(startAt, formatter);
		this.endAt = LocalDateTime.parse(endAt, formatter);
		if (this.startAt.isAfter(this.endAt)) {
			throw new Exception("Start date must be before end date.");
		}
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (from: " + this.startAt.toString() + " to: " + this.endAt.toString() + ")";
	}

	public String getStartAt() {
		return this.startAt.toString();
	}

	public String getEndAt() {
		return this.endAt.toString();
	}
}
