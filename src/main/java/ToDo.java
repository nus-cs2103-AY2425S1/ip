public class ToDo extends Task {

    public ToDo(String name) {
        super(validateString(name));
    }

    private static String validateString(String input) {
        if (input.isEmpty()) {
            throw new TarsException("Try again. Next time add a task name to tell me what you're trying to do");
        }

        if (input.contains("/by")) {
            throw new TarsException("Use a deadline instead.\nC'mon I can't be expected to do all the thinking around here");
        }

        if (input.contains("/from")) {
            throw new TarsException("Maybe you should use an event?\nBut what do I know I'm just a robot");
        }

        return input;
    }
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
