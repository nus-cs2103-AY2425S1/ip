package ned;
import java.util.ArrayList;
import ned.exceptions.NedException;
import ned.tasks.Task;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructs a TaskList instance with an instance of an Arraylist
     *
     * @param listOfTasks ArrayList parameterized with Task class
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * @param index - Index of the task to be removed
     * @param uiInstance - Instance of the UI class to handle the printing
     * @throws NedException
     */
    public void removeTask(int index, Ui uiInstance) throws NedException {
        try {
            Task selectedTask = getTask(index);
            this.listOfTasks.remove(index);
            uiInstance.removeTasksToNedDialogue(selectedTask, this.listOfTasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, your command must specify an index within the bounds of the list " +
                    "size");
        }
    }

    private Task getTask(int index) throws NedException {
        try {
            return this.listOfTasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, your command must specify an index within the bounds of the list " +
                    "size");
        }
    }

    public void addTask(Task newTask, Ui uiInstance) {
        this.listOfTasks.add(newTask);
        uiInstance.addTasksToNedDialogue(newTask, this.listOfTasks.size());
    }

    public void listTasks(Ui uiInstance) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            String task = Ui.INDENTATIONS + String.format("%d.%s \n", i + 1, listOfTasks.get(i));
            uiInstance.addToNedDialogue(task);
        }
    }

    public void markTaskAsDone(int index, Ui uiInstance) throws NedException {
        Task selectedTask = getTask(index);
        selectedTask.markAsDone();
        uiInstance.addToNedDialogue("Good work. One down, more to go!");
        uiInstance.addToNedDialogue(selectedTask.toString());
    }

    public void markTaskAsUndone(int index, Ui uiInstance) throws NedException {
        Task selectedTask = getTask(index);
        selectedTask.markAsNotDone();
        uiInstance.addToNedDialogue("Oh no. One back up, more to go!");
        uiInstance.addToNedDialogue(selectedTask.toString());
    }

    public int getSize() {
        return this.listOfTasks.size();
    }

    public String getTaskTextForm(int index) throws NedException {
        return getTask(index).toTextForm();
    }

    public TaskList findRelatedTasks(String searchTerm) {
        String regexSearchPattern = ".*" + searchTerm + ".*";
        int sizeOfArray = this.listOfTasks.size();
        int index = 1;
        ArrayList<Task> listOfRelatedTasks = new ArrayList<>();
        for (int i = 0; i < sizeOfArray; i++){
            if (this.listOfTasks.get(i).isMatchingPattern(regexSearchPattern)) {
                listOfRelatedTasks.add(this.listOfTasks.get(i));
            }
        }
        return new TaskList(listOfRelatedTasks);
    }


}