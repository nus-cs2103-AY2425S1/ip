public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String addTaskToString() {
        return super.addTaskToString() + "\n" + " ".repeat(5) + "[E] [ ] " + description + " (" + start + " - " + end + ")";
    }

    @Override
    public String taskToString() {
        if (isDone) {
            return "[E] [X] " + description + " (" + start + " - " + end + ")";
        }
        return "[E] [ ] " + description + " (" + start + " - " + end + ")";
    }

    @Override
    public String markDoneToString() {
        return super.markDoneToString() + "\n" + " ".repeat(5) + "[E] [X] " + description + " (" + start + " - " + end + ")";
    }

    @Override
    public String unmarkDoneToString() {
        return super.unmarkDoneToString() + "\n" + " ".repeat(5) + "[E] [ ] " + description + " (" + start + " - " + end + ")";
    }

    @Override
    public String deleteTask() {
        if (isDone) {
            return super.deleteTask() + "\n" + " ".repeat(5) + "[E] [X] " + description + " (" + start + " - " + end + ")";
        }
        return super.deleteTask() + "\n" + " ".repeat(5) + "[E] [ ] " + description + " (" + start + " - " + end + ")";
    }

}