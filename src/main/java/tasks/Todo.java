package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String line, boolean completed) {
        super(line, completed);
    }

    @Override
    public String getSaveFormat() {
        return String.format("T | %d | %s", super.intComplete(), super.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", completedStringRepresentation(), super.getDescription());
    }

    public static Task load(String input) {
        assert !input.isEmpty();
        String[] parameters = input.split("\\|");
        assert parameters.length == 3;
        boolean completed = parameters[1].trim().equals("1");
        return new Todo(parameters[2].trim(), completed);
    }

}
