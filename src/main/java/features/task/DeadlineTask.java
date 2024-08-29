package features.task;

import java.time.LocalDate;

public class DeadlineTask extends Task {
	private LocalDate deadline;

	public DeadlineTask(String name, String deadline) {
		super(name);
		this.deadline = LocalDate.parse(deadline);
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + deadline + ")";
	}

	public String getDeadline() {
		return this.deadline.toString();
	}
}
