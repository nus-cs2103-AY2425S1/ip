import java.util.ArrayList;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public static void addEvent(TaskList list, ArrayList<String> tokens) {
        if (tokens.size() == 1 || tokens.get(1).equals("/from") || tokens.get(1).equals("/to")) {
            UI.response("Failed. Specify a task for your event task!!! D:");
        } else if (!(tokens.contains("/from")) || !(tokens.contains("/to")) ||
                (tokens.indexOf("/from") > tokens.indexOf("/to")) ||
                (tokens.indexOf("/from") + 1 == tokens.indexOf("/to")) ||
                tokens.indexOf("/to") == tokens.size() - 1) {
            UI.response("Failed. Add /from [DATE] /to [DATE] to specify the duration of your event!!! ;=;");
        } else {
            String taskDescription = "";
            String start = "";
            String end = "";
            int len = tokens.size();
            int i = 1;
            while (i < len && !(tokens.get(i).equals("/from"))) {
                taskDescription += tokens.get(i) + " ";
                i += 1;
            }
            i += 1;
            while (i < len && !(tokens.get(i).equals("/to"))) {
                start += tokens.get(i) + " ";
                i += 1;
            }
            i += 1;
            while (i < len) {
                end += tokens.get(i) + " ";
                i += 1;
            }
            Event event = new Event(taskDescription, start, end);
            list.addTask(event);
        }
    }

    @Override
    public String toString() {
        String str = "[E]" + super.toString() + String.format("( from: %sto: %s)", this.start, this.end);
        return str;
    }
}
