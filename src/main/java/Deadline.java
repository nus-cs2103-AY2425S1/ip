import java.util.ArrayList;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }
    public static void addDeadline(TaskList list, ArrayList<String> tokens) {
        if (tokens.size() == 1 || tokens.get(1).equals("/by")) {

            UI.response("Failed. Specify a task for your deadline task!!! D:");
        } else if (!(tokens.contains("/by")) || tokens.indexOf("/by") == tokens.size() - 1) {
            UI.response("Failed. Add /by [DATE] to specify when to complete your task by!!! ;=;");
        } else {
            String taskDescription = "";
            String deadline = "";
            int len = tokens.size();
            for (int i = 1; i < len; i++) {
                if (tokens.get(i).equals("/by")) {
                    i += 1;
                    while (i < len) {
                        deadline += tokens.get(i) + " ";
                        i += 1;
                    }
                } else {
                    taskDescription += tokens.get(i) + " ";
                }
            }
            Deadline deadlineTask = new Deadline(taskDescription, deadline);
            list.addTask(deadlineTask);
        }
    }
    @Override
    public String toString() {
        String str = "[D]" + super.toString() + String.format("( by: %s)", this.deadline);
        return str;
    }
}
