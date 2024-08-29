package features.task;

public class Task {
	private String name;
	private Integer id;
	private Boolean isMarked;

	public Task(String name) {
		this.name = name;
		this.id = -1;
		this.isMarked = false;
	}

	public Task(String name, boolean isMarked) {
		this(name);
		this.isMarked = isMarked;
	}

	public boolean getIsMarked() {
		return this.isMarked;
	}
	public void setIsMarked(boolean isMarked) { this.isMarked = isMarked; }

	public void mark() {
		setIsMarked(true);
	}

	public void unmark() {
		setIsMarked(false);
	}

	public Integer getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String check = this.isMarked ? "x" : " ";
		return "[" + check + "] " + this.name;
	}
}
