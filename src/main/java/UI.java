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
        output.append("Here are the tasks in your list:\n\t\t\t");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output.append(i + 1);
            output.append(".");
            output.append(task);
            output.append("\n\t\t\t");
        }

        if (!output.isEmpty()) {
            output.delete(output.length() - 4, output.length());
        }
        System.out.println(this.addMsgWithOutline(output.toString()));
    }

    public void printAddTaskFeedback(Task task, int noOfTasksInList) {
        System.out.println(this.addMsgWithOutline("Got it. I've added this task:\n\t\t\t\t" + task + "\n\t\t\tNow you have " + noOfTasksInList + " task(s) in the list."));
    }

    public void printTaskMarkedDone(Task task) {
        System.out.println(this.addMsgWithOutline("Nice! I've marked this task as done:\n\t\t\t\t" + task));
    }

    public void printTaskMarkedUndone(Task task) {
        System.out.println(this.addMsgWithOutline("OK, I've marked this task as not done yet:\n\t\t\t\t" + task));
    }

    public void exit() {
        System.out.println(this.addMsgWithOutline("Bye. Hope to see you again soon!"));
    }
}
