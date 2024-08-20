import java.util.List;

public class UI {
    private String addMsgWithOutline(String msg) {
        return "\t\t\t---------------------------------------------------------------\n" + "\t\t\t" + msg + "\n" + "\t\t\t---------------------------------------------------------------";
    }

    public void greeting() {
        System.out.println(this.addMsgWithOutline("Hello! I'm EchoChat\n\t\t\tWhat can I do for you?"));
    }

    public void printTaskList(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1);
            output.append(". ");
            output.append(tasks.get(i).getTaskName());
            output.append("\n\t\t\t");
        }

        if (!output.isEmpty()) {
            output.delete(output.length() - 4, output.length());
        }
        System.out.println(this.addMsgWithOutline(output.toString()));
    }

    public void printAddTaskFeedback(String taskName) {
        System.out.println(this.addMsgWithOutline("added: "+ taskName));
    }

    public void exit() {
        System.out.println(this.addMsgWithOutline("Bye. Hope to see you again soon!"));
    }
}
