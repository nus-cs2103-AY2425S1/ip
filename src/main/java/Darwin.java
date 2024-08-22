import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Darwin {
    static final String NAME = "Darwin";
    static final String END = "bye";
    static final String CHECK_LIST = "list";
    static final String MARK = "mark";
    static final String MARK_MSG = "Nice! I've marked this task as done:";
    static final String UNMARK_MSG = "OK, I've marked this task as not done yet:";
    static final String UNMARK = "unmark";
    private ArrayList<Task> taskList = new ArrayList<>();

    private static void reply(String in) {
        String line = "-".repeat(50);
        System.out.println(line);
        System.out.println(in);
        System.out.println(line);
    }

    private void addTask(String taskName) {
        Task task = new Task(taskName);
        this.taskList.add(task);
        String added = String.format("added: %s", task.getName());
        Darwin.reply(added);
    }

    private void getTaskList() {
        StringBuilder taskList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String taskInfo = String.format("%d%s", i + 1, task.getTaskInfo());
            taskList.append(taskInfo).append("\n");
        }
        Darwin.reply(String.valueOf(taskList));
    }

    private boolean parseMarkUnmarkCommand(String in) {
        /*
        Returns a boolean of whether the input is a mark or unmark command.
        Handles the marking and unmarking of tasks.
         */
        if (!in.startsWith(Darwin.MARK) && !in.startsWith(Darwin.UNMARK)) {
            return false;
        }
        String[] parts = in.split(" ");
        if (parts.length != 2) {
            return false;
        }
        try {
            String cmdName = parts[0];
            int taskIdx = Integer.parseInt(parts[1].trim()) - 1;
            if (taskIdx >= 0 && taskIdx < this.taskList.size()) {
                Task task = this.taskList.get(taskIdx);
                String reply = "";
                // should handle if the task is already done and mark cmd is executed
                if (cmdName.equals(Darwin.MARK)) {
                    reply += Darwin.MARK_MSG;
                    task.markDone();
                } else {
                    reply += Darwin.UNMARK_MSG;
                    task.unmarkDone();
                }
                reply += "\n  " + task.getTaskInfo();
                Darwin.reply(reply);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void initChat() {
        String startMsg = String.format("Hello! I'm %s\nWhat can I do for you?", NAME);
        String endMsg = "Bye. Hope to see you again soon!";
        Darwin.reply(startMsg);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String in = scanner.nextLine();
            if (in.equals(Darwin.END)) {
                Darwin.reply(endMsg);
                break;
            } else if (in.equals(Darwin.CHECK_LIST)) {
                this.getTaskList();
            } else if (!this.parseMarkUnmarkCommand(in)) {
                this.addTask(in);
            }
        }
    }

    public static void main(String[] args) {
        Darwin darwin = new Darwin();
        darwin.initChat();
    }
}
