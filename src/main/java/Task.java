public class Task {
	private String name;
	private Integer id;
	private Boolean isMarked;

	public Task(String name) {
		this.name = name;
		this.id = id;
		this.isMarked = false;
	}

	public void mark() {
		this.isMarked = true; 
	}

	public void unmark() {
		this.isMarked = false;
	}

	public Integer getId() {
		return this.id;
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
