import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> items;

    private static final String TODO_STRING = "todo";
    private static final String DEADLINE_STRING = "deadline";
    private static final String EVENT_STRING = "event";
    private static final String DEADLINE_DATE_SEPARATOR = "/by";
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";

    public TaskList() {
        this.items = new ArrayList<>();
    }

    private Pair<String, Integer> formSubSection(
            String[] splitDescrption,
            int startIdx,
            String terminalString
    ) {
        String formedSection = "";
        int currIdx = startIdx;
        while (currIdx < splitDescrption.length && !splitDescrption[currIdx].equals(terminalString)) {
            if (!formedSection.isEmpty()) {
                formedSection += " ";
            }
            formedSection += splitDescrption[currIdx];
            currIdx++;
        }
        return new Pair<>(formedSection, currIdx);
    }

    public String add(String description) throws EmptyDescriptionException {
        String[] splitDescription = description.split(" ");
        String taskType = splitDescription[0];
        String taskDescription;
        Task task;
        Pair<String, Integer> taskDescriptionIdxPair;
        switch (taskType) {
            case TODO_STRING:
                taskDescriptionIdxPair = formSubSection(splitDescription, 1, "");
                taskDescription = taskDescriptionIdxPair.getFirst();
                task = new ToDo(taskDescription);
                break;
            case DEADLINE_STRING:
                taskDescriptionIdxPair = formSubSection(splitDescription, 1, DEADLINE_DATE_SEPARATOR);
                taskDescription = taskDescriptionIdxPair.getFirst();
                Pair<String, Integer> deadlineIdxPair = formSubSection(
                        splitDescription, taskDescriptionIdxPair.getSecond() + 1, "");
                String deadline = deadlineIdxPair.getFirst();
                task = new Deadline(taskDescription, deadline);
                break;
            case EVENT_STRING:
                taskDescriptionIdxPair = formSubSection(splitDescription, 1, EVENT_FROM_SEPARATOR);
                taskDescription = taskDescriptionIdxPair.getFirst();
                Pair<String, Integer> fromIdxPair = formSubSection(
                        splitDescription, taskDescriptionIdxPair.getSecond() + 1, EVENT_TO_SEPARATOR);
                Pair<String, Integer> toIdxPair = formSubSection(
                        splitDescription, fromIdxPair.getSecond() + 1, "");
                String from = fromIdxPair.getFirst();
                String to = toIdxPair.getFirst();
                task = new Event(taskDescription, from, to);
                break;
            default:
                taskDescription = description;
                task = new Task(description);
        }
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.items.add(task);
        String message = "     Got it. I've added this task:\n" +
                String.format("       %s\n", task.toString()) +
                String.format("     Now you have %d %s in the list.",
                        this.items.size(), (this.items.size() == 1 ? "task" : "tasks"));
        return message;
    }

    public String list() {
        String message = "     Here are the tasks in your list:\n";
        for (int i = 1; i <= this.items.size(); i++) {
            message += String.format(
                "     %d.%s" + (i == this.items.size() ? "" : "\n"),
                i, this.items.get(i - 1)
            );
        }
        return message;
    }

    public String mark(int itemIdx) throws IndexOutOfBoundsException {
        String message = "     Nice! I've marked this task as done:\n";
        if (itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.items.get(itemIdx - 1).markAsDone();
        message += String.format("       %s", this.items.get(itemIdx - 1));
        return message;
    }

    public String unmark(int itemIdx) throws IndexOutOfBoundsException {
        String message = "     OK, I've marked this task as not done yet:\n";
        if (itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.items.get(itemIdx - 1).markAsUndone();
        message += String.format("       %s", this.items.get(itemIdx - 1));
        return message;
    }

    public String delete(int itemIdx) throws IndexOutOfBoundsException {
        Task deletedTask = this.items.remove(itemIdx - 1);
        String message = "     Noted. I've removed this task:\n" +
                String.format("       %s\n", deletedTask.toString()) +
                String.format("     Now you have %d %s in the list.",
                    this.items.size(), (this.items.size() == 1 ? "task" : "tasks"));
        return message;
    }

    public int getNumItems() {
        return this.items.size();
    }
}
