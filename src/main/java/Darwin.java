import exception.IllegalTaskArgumentException;
import exception.IllegalTaskTypeException;
import task.Task;
import task.TaskManager;

import java.util.Scanner;

public class Darwin {
    static final String NAME = "Darwin";
    private final TaskManager taskManager = new TaskManager();
    static final String END = "bye";
    static final String CHECK_LIST = "list";
    static final String CHECK_LIST_STR = "Here are the tasks in your list:";
    static final String ADD_TASK_MSG = "Got it. I've added this task:";
    static final String TASK_COUNT_MSG = "Now you have %d tasks in the list.";
    static final String MARK = "mark";
    static final String MARK_MSG = "Nice! I've marked this task as done:";
    static final String UNMARK = "unmark";
    static final String UNMARK_MSG = "OK, I've marked this task as not done yet:";
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
        String[] parts = in.split("\\s+", 2);
        return parts.length == 2 ? parts : new String[] {parts[0], ""};
    }
    private void addTask(String cmd, String taskArgs) {
        try {
            Task task = this.taskManager.addTask(cmd, taskArgs);
            String added = String.format("%s\n  %s\n%s", Darwin.ADD_TASK_MSG, task.getTaskInfo(),
                    String.format(Darwin.TASK_COUNT_MSG, this.taskManager.getTaskCount()));
            Darwin.reply(added);
        } catch (IllegalTaskArgumentException e) {
            Darwin.reply("Wrong task description, ensure that it follows the different task types");
        } catch (IllegalTaskTypeException e) {
            Darwin.reply("Wrong command, valid commands are todo, deadline, event");
        }
    }
    private void deleteTask(String cmd, String taskIdxStr) {
        int taskIdx = this.parseTaskIdx(taskIdxStr);
        if (taskIdx == -1) {
            return;
        }
        Task task = this.taskManager.deleteTask(cmd, taskIdx);
        String deleted = String.format("%s\n  %s\n%s", Darwin.DELETE_TASK_MSG, task.getTaskInfo(),
                String.format(Darwin.TASK_COUNT_MSG, this.taskManager.getTaskCount()));
        Darwin.reply(deleted);
    }
    private void checkTaskList() {
        String taskListStr = Darwin.CHECK_LIST_STR + "\n";
        taskListStr += this.taskManager.getTaskListStr();
        Darwin.reply(taskListStr);
    }
    private int parseTaskIdx(String taskIdxStr) {
        try {
            int taskIdx = Integer.parseInt(taskIdxStr) - 1;
            if (taskIdx < 0 || taskIdx >= this.taskManager.getTaskCount()) {
                throw new IndexOutOfBoundsException();
            }
            return taskIdx;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Darwin.reply("Invalid task number");
            return -1;
        }
    }
    private void markUnmarkTask(String cmd, String taskIdxStr) {
        int taskIdx = this.parseTaskIdx(taskIdxStr);
        if (taskIdx == -1) {
            return;
        }
        String reply = "";
        Task task;
        if (cmd.equals(Darwin.MARK)) {
            reply += Darwin.MARK_MSG;
            task = this.taskManager.markTask(taskIdx);
        } else {
            reply += Darwin.UNMARK_MSG;
            task = this.taskManager.unmarkTask(taskIdx);
        }
        reply += "\n  " + task.getTaskInfo();
        Darwin.reply(reply);
    }
    /**
     * Initialises a chat with the user and chat bot Darwin through standard input and output
     */
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
            switch (cmd) {
                case Darwin.END -> {
                    Darwin.reply(endMsg);
                    return;
                }
                case Darwin.CHECK_LIST -> this.checkTaskList();
                case Darwin.MARK, Darwin.UNMARK -> this.markUnmarkTask(cmd, args);
                case Darwin.DELETE -> this.deleteTask(cmd, args);
                default -> this.addTask(cmd, args);
            }
        }
    }

    public static void main(String[] args) {
        Darwin darwin = new Darwin();
        darwin.initChat();
    }
}
