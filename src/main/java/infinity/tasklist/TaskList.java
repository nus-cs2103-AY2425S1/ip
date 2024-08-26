
package infinity.tasklist;

import infinity.infinityexception.InfinityException;
import infinity.task.Task;
import infinity.ui.Ui;
import java.util.ArrayList;

public class TaskList {

    private final Ui botUI;

    public static final int MAXSIZE = 100;
    private ArrayList<Task> tasks = new ArrayList<>(MAXSIZE);
    private int nextTaskIndex = 0;

    public <T extends Task> void addTask(T task) throws InfinityException {
        if (nextTaskIndex >= MAXSIZE) {
            throw new InfinityException(
                    "I'm sorry, but I can't remember more tasks.");
        }

        tasks.add(task);
        nextTaskIndex++;

        botUI.botSays(String.format("I've added '%s'", task));
    }

    public void deleteTask(String currentInput) throws InfinityException {
        int taskIndex = 0;

        try {
            taskIndex = Integer.parseInt(currentInput);
        } catch (NumberFormatException e) {
            throw new InfinityException("Hey! That's not a number");
        }

        taskIndex--;

        if (taskIndex >= nextTaskIndex || taskIndex < 0) {
            throw new InfinityException(
                    "Hmmm, you seem to have chose a task that doesn't exist. Nice try :)");
        } else {
            try {
                Task removedTask = tasks.remove(taskIndex);

                botUI.botSays(String.format(
                        "I've removed task %d:", taskIndex + 1));
                System.out.println(removedTask.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new InfinityException(
                        "Hmmm, you seem to have chose a task that doesn't exist. Nice try :)");
            }
        }
    }

    public void markTask(String currentInput) throws InfinityException {
        String[] words = currentInput.split(" ");

        int taskIndex = Integer.parseInt(words[1]) - 1;
        if (taskIndex >= nextTaskIndex || taskIndex < 0) {
            throw new InfinityException(
                    "Hmmm, I can't find that task. Please try again.");
        }

        tasks.get(taskIndex).markAsDone();

        botUI.botSays(String.format(
                "I've marked task %d as done:\n%s", 
                taskIndex + 1, 
                tasks.get(taskIndex).toString()));
    }

    public void listTasks() {
        botUI.listTasks(this);
    }

    public boolean isEmpty() {
        return nextTaskIndex == 0;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    };

    @SuppressWarnings("unchecked")
    public TaskList(ArrayList initialTask, Ui botUI) {
        this.tasks = (ArrayList<Task>) initialTask;
        this.botUI = botUI;
        this.nextTaskIndex = initialTask.size();
    }
}