import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Darwin {
    static final String NAME = "Darwin";
    private ArrayList<Task> taskList = new ArrayList<>();
    static final String END = "bye";
    static final String CHECK_LIST = "list";
    static final String ADD_TASK_MSG = "Got it. I've added this task:";
    static final String TASK_COUNT_MSG = "Now you have %d tasks in the list.";
    static final String MARK = "mark";
    static final String MARK_MSG = "Nice! I've marked this task as done:";
    static final String UNMARK = "unmark";
    static final String UNMARK_MSG = "OK, I've marked this task as not done yet:";
    static final String TODO = "todo";
    static final String DEADLINE = "deadline";
    static final String EVENT = "event";
    static final String DELETE = "delete";
    static final String DELETE_TASK_MSG = "Noted. I've removed this task:";

    private static void reply(String in) {
        String line = "-".repeat(50);
        System.out.println(line);
        System.out.println(in);
        System.out.println(line);
    }
    private static String[] parseInput(String in) {
        in = in.trim();
        int spaceIdx = in.indexOf(" ");
        if (spaceIdx == -1) {
            return new String[] {in, ""};
        }
        String cmd = in.substring(0, spaceIdx);
        String args = in.substring(spaceIdx + 1).trim();
        return new String[] {cmd, args};
    }
    private int getTaskCount() {
        return this.taskList.size();
    }
    private static Task createTask(String cmd, String taskArgs) throws DarwinIllegalCommandException, DarwinIllegalArgumentException {
        String[] taskArgsArr = taskArgs.split(" /by| /from| /to", 3);
        String taskName = taskArgsArr[0].trim();
        switch (cmd.toLowerCase()) {
            case Darwin.TODO:
                if (taskArgsArr.length != 1 || taskName.isEmpty()) {
                    throw new DarwinIllegalArgumentException();
                }
                return new ToDo(taskName);
            case Darwin.DEADLINE:
                if (taskArgsArr.length != 2) {
                    throw new DarwinIllegalArgumentException();
                }
                String taskDeadline = taskArgsArr[1].trim();
                return new Deadline(taskName, taskDeadline);
            case Darwin.EVENT:
                // assume that /from will be before /to
                if (taskArgsArr.length != 3) {
                    throw new DarwinIllegalArgumentException();
                }
                String taskStart = taskArgsArr[1].trim();
                String taskEnd = taskArgsArr[2].trim();
                return new Event(taskName, taskStart, taskEnd);
            default:
                throw new DarwinIllegalCommandException();
        }
    }
    private void deleteTask(String cmd, String taskArgs) {
        int taskIdx = this.parseTaskIdx(taskArgs);
        if (taskIdx == -1) {
            return;
        }
        Task task = this.taskList.remove(taskIdx);
        String deleted = String.format("%s\n  %s\n%s", Darwin.DELETE_TASK_MSG, task.getTaskInfo(),
                String.format(Darwin.TASK_COUNT_MSG, this.getTaskCount()));
        Darwin.reply(deleted);
    }
    private void addTask(String cmd, String taskArgs) {
        try {
            Task task = Darwin.createTask(cmd, taskArgs);
            this.taskList.add(task);
            String added = String.format("%s\n  %s\n%s", Darwin.ADD_TASK_MSG, task.getTaskInfo(),
                    String.format(Darwin.TASK_COUNT_MSG, this.getTaskCount()));
            Darwin.reply(added);
        } catch (DarwinIllegalArgumentException e) {
            Darwin.reply("Wrong task description, ensure that it follows the different task types");
        } catch (DarwinIllegalCommandException e) {
            Darwin.reply("Wrong command, valid commands are todo, deadline, event");
        }
    }

    private void getTaskList() {
        StringBuilder taskList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String taskInfo = String.format("%d.%s", i + 1, task.getTaskInfo());
            taskList.append(taskInfo).append("\n");
        }
        Darwin.reply(String.valueOf(taskList));
    }

    private int parseTaskIdx(String taskIdxStr) {
        try {
            int taskIdx = Integer.parseInt(taskIdxStr) - 1;
            if (taskIdx < 0 || taskIdx >= this.taskList.size()) {
                throw new IndexOutOfBoundsException();
            }
            return taskIdx;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Darwin.reply("Invalid task number");
            return -1;
        }
    }
    private void parseMarkUnmarkCommand(String cmd, String taskIdxStr) {
        /*
        Handles the marking and unmarking of tasks.
         */
        int taskIdx = this.parseTaskIdx(taskIdxStr);
        if (taskIdx == -1) {
            return;
        }
        Task task = this.taskList.get(taskIdx);
        String reply = "";
        // should handle if the task is already done and mark cmd is executed
        if (cmd.equals(Darwin.MARK)) {
            reply += Darwin.MARK_MSG;
            task.markDone();
        } else {
            reply += Darwin.UNMARK_MSG;
            task.unmarkDone();
        }
        reply += "\n  " + task.getTaskInfo();
        Darwin.reply(reply);
    }
    public void initChat() {
        String startMsg = String.format("Hello! I'm %s\nWhat can I do for you?", NAME);
        String endMsg = "Bye. Hope to see you again soon!";
        Darwin.reply(startMsg);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String in = scanner.nextLine();
            String[] inArr = Darwin.parseInput(in);
            String cmd = inArr[0];
            String args = inArr[1];
            if (cmd.equals(Darwin.END)) {
                Darwin.reply(endMsg);
                break;
            } else if (cmd.equals(Darwin.CHECK_LIST)) {
                this.getTaskList();
            } else if (cmd.equals(Darwin.MARK) || cmd.equals(Darwin.UNMARK)) {
                this.parseMarkUnmarkCommand(cmd, args);
            } else if (cmd.equals(Darwin.DELETE)) {
                this.deleteTask(cmd, args);
            } else {
                this.addTask(cmd, args);
            }
        }
    }

    public static void main(String[] args) {
        Darwin darwin = new Darwin();
        darwin.initChat();
    }
}
