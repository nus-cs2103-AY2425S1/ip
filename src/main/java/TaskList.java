import javafx.util.Pair;

public class TaskList {

    private static final int MAX_TASKS = 100;
    private Task[] items;
    private int numItems;

    private static final String TODO_STRING = "todo";
    private static final String DEADLINE_STRING = "deadline";
    private static final String EVENT_STRING = "event";
    private static final String DEADLINE_DATE_SEPARATOR = "/by";
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";

    public TaskList() {
        this.items = new Task[MAX_TASKS];
        this.numItems = 0;
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

    public String add(String description) {
        String[] splitDescription = description.split(" ");
        String taskType = splitDescription[0];
        String taskDescription;
        Task task;
        Pair<String, Integer> taskDescriptionIdxPair;
        switch (taskType) {
            case TODO_STRING:
                taskDescriptionIdxPair = formSubSection(splitDescription, 1, "");
                taskDescription = taskDescriptionIdxPair.getKey();
                task = new ToDo(taskDescription);
                break;
            case DEADLINE_STRING:
                taskDescriptionIdxPair = formSubSection(splitDescription, 1, DEADLINE_DATE_SEPARATOR);
                taskDescription = taskDescriptionIdxPair.getKey();
                Pair<String, Integer> deadlineIdxPair = formSubSection(
                        splitDescription, taskDescriptionIdxPair.getValue() + 1, "");
                String deadline = deadlineIdxPair.getKey();
                task = new Deadline(taskDescription, deadline);
                break;
            case EVENT_STRING:
                taskDescriptionIdxPair = formSubSection(splitDescription, 1, EVENT_FROM_SEPARATOR);
                taskDescription = taskDescriptionIdxPair.getKey();
                Pair<String, Integer> fromIdxPair = formSubSection(
                        splitDescription, taskDescriptionIdxPair.getValue() + 1, EVENT_TO_SEPARATOR);
                Pair<String, Integer> toIdxPair = formSubSection(
                        splitDescription, fromIdxPair.getValue() + 1, "");
                String from = fromIdxPair.getKey();
                String to = toIdxPair.getKey();
                task = new Event(taskDescription, from, to);
                break;
            default:
                task = new Task(description);
        }
        this.items[this.numItems] = task;
        this.numItems++;
        String message = "     Got it. I've added this task:\n" +
                String.format("       %s\n", task.toString()) +
                String.format("     Now you have %d %s in the list.\n",
                        this.numItems, (this.numItems == 1 ? "task" : "tasks"));
        return message;
    }

    public String list() {
        String message = "     Here are the tasks in your list:\n";
        for (int i = 1; i <= this.numItems; i++) {
            message += String.format("     %d.%s\n", i, this.items[i - 1]);
        }
        return message;
    }

    public String mark(int itemIdx) {
        String message = "     Nice! I've marked this task as done:\n";
        this.items[itemIdx - 1].markAsDone();
        message += String.format("       %s\n", this.items[itemIdx - 1]);
        return message;
    }

    public String unmark(int itemIdx) {
        String message = "     OK, I've marked this task as not done yet:\n";
        this.items[itemIdx - 1].markAsUndone();
        message += String.format("       %s\n", this.items[itemIdx - 1]);
        return message;
    }
}
