package luke.task;

import luke.env.Constants;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        Task task = taskList.remove(index);
        System.out.println(Constants.DIVIDER
                + "alright i've purged this task for you:\n"
                + Constants.INDENT + task.taskDescription() + "\n"
                + listSizeUpdateMessage()
                + Constants.DIVIDER);
    }

    public static void taskNotFound(int taskNumber) {
        System.out.println(Constants.DIVIDER
                + "task " + taskNumber + " doesn't exist...try another number!\n"
                + Constants.DIVIDER);
    }

    public String listSizeUpdateMessage() {
        int listSize = taskList.size();
        if (listSize == 1) {
            return "your list has " + listSize + " item now.\n";
        } else {
            return "your list has " + listSize + " items now.\n";
        }
    }

    public void addToList(String command, String args, boolean isMarked, boolean isLoadingFromDisk)
            throws NoDescriptionException, UnknownCommandException {
        if (!(Constants.TASK_TYPES.contains(command))) {
            throw new UnknownCommandException();
        }
        if (args.isEmpty()) {
            throw new NoDescriptionException();
        }
        switch (command) {
        case "todo" -> {
            Todo todo = new Todo(args, isMarked);
            taskList.add(todo);
            if (!(isLoadingFromDisk)) {
                System.out.println(Constants.DIVIDER
                        + "i've thrown this to-do into your task list:\n"
                        + Constants.INDENT + todo.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + Constants.DIVIDER);
            }
        }
        case "deadline" -> {
            String[] taskAndDeadline;
            if (isLoadingFromDisk) {
                taskAndDeadline = args.split(" by ");
            } else {
                taskAndDeadline = args.split(" /by ");
            }
            String taskName = taskAndDeadline[0];
            String deadline = taskAndDeadline[1];
            Deadline dl = new Deadline(taskName, deadline, isMarked);
            addTask(dl);
            if (!(isLoadingFromDisk)) {
                System.out.println(Constants.DIVIDER
                        + "the new deadline's been added to your task list:\n"
                        + Constants.INDENT + dl.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + Constants.DIVIDER);
            }
        }
        case "event" -> {
            String[] taskAndTimings;
            if (isLoadingFromDisk) {
                taskAndTimings = args.split(" from | to ");
            } else {
                taskAndTimings = args.split(" /from | /to ");
            }
            String taskName = taskAndTimings[0];
            String from = taskAndTimings[1];
            String to = taskAndTimings[2];
            Event event = new Event(taskName, from, to, isMarked);
            addTask(event);
            if (!(isLoadingFromDisk)) {
                System.out.println(Constants.DIVIDER
                        + "aaaaand this event is now in your task list:\n"
                        + Constants.INDENT + event.taskDescription() + "\n"
                        + listSizeUpdateMessage()
                        + Constants.DIVIDER);
            }
        }
        }
    }

    public void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i+1) + ". " + task.taskDescription());
        }
    }
}
