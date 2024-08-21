public class Task {
    private boolean completed;
    private String name;

    public Task(String name) {
	this.completed = false;
	this.name = name;
    }

    public void toggleComplete() {
	this.completed = !this.completed;
    }

    private String completedStringRepresentation() {
	if (!completed) {
	    return " ";
	} else {
	    return "X";
	}
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completedStringRepresentation(), this.name);
    }
}
