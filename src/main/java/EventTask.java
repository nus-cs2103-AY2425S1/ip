public class EventTask extends Task {
	private String startAt;
	private String endAt;

	public EventTask(String name, String startAt, String endAt) {
		super(name);
		this.startAt = startAt;
		this.endAt = endAt;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (from: " + this.startAt + " to: " + this.endAt + ")";
	}
}
