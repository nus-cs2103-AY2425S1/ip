package thebotfather.util;

import thebotfather.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskArrayList;
    protected static int NUMBER_OF_REMAINING_TASKS = 0;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public void addTask(Task task) {
        taskArrayList.add(task);
        NUMBER_OF_REMAINING_TASKS++;
    }

    public String getListDesc() throws TheBotFatherException {
        int size = taskArrayList.size();
        if (size < 1) throw new TheBotFatherException("How do I print what is not there?");
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            string.append(i + 1).append(". ").append(taskArrayList.get(i)).append("\n");
        }
        return string.toString();
    }

    public void markAsDone(int index) throws TheBotFatherException {
        try {
            Task task = taskArrayList.get(index);
            if (!task.isDone()) {
                NUMBER_OF_REMAINING_TASKS--;
            }
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son.\n" +
                    "\tTo mark a task as done enter \"mark <index>\"");
        }
    }

    public void unmark(int index) throws TheBotFatherException {
        try {
            Task task = taskArrayList.get(index);
            if (task.isDone()) {
                NUMBER_OF_REMAINING_TASKS++;
            }
            task.unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son.\n" +
                    "\tTo unmark a task enter \"unmark <index>\"");
        }
    }

    public Task delete(int index) throws TheBotFatherException {
        try {
            Task task = taskArrayList.get(index);
            taskArrayList.remove(index);
            if (!task.isDone()) {
                NUMBER_OF_REMAINING_TASKS--;
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son.\n" +
                    "\tTo delete a task enter \"delete <index>\"");
        }
    }

    public int numberOfElements() {
        return taskArrayList.size();
    }

    public String toFile() {
        StringBuilder dataInFile = new StringBuilder();
        for (Task task : taskArrayList) {
            dataInFile.append(task.toFile()).append(System.lineSeparator());
        }

        return dataInFile.toString();
    }

    public String getTaskDescAtIndex(int index) {
        return taskArrayList.get(index).toString();
    }
}
