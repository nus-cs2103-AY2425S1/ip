package mediell;

/** Represents an array of Task. */
public class TaskList {
    private Task[] listItems;
    private int size;

    public TaskList() {
        listItems = new Task[100];
        size = 0;
    }

    /**
     * returns the tasks in the list.
     */
    public String displayList() {
        String output = "";
        for (int i = 0; i < size; i++) {
            output += String.valueOf(i+1) + ". " + listItems[i] + "\n";
        }
        return output;
    }

    public String displayFoundList(String keyword) {
        String output = "";
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (listItems[i].find(keyword)) {
                output += String.valueOf(j + 1) + ". " + listItems[i] + "\n";
                j++;
            }
        }
        return output;
    }

    /**
     * Informs the user that the item has been added and increases the size of the list.
     */
    private String addTaskProcessing() {
        String output = "Got it! I've added this task: \n" + listItems[size];
        size++;
        return output;
    }

    /**
     * Adds a ToDo task to the list.
     * @param item the format of the task
     */
    public String addToDo(String item) {
        listItems[size] = new ToDo(item);
        return addTaskProcessing();
    }

    /**
     * Adds an Event task to the list.
     * @param item the format of the task
     */
    public String addEvent(String item) {
        listItems[size] = new Event(item);
        return addTaskProcessing();
    }

    /**
     * Adds a Deadline task to the list.
     * @param item the format of the task
     */
    public String addDeadline(String item) {
        listItems[size] = new Deadline(item);
        return addTaskProcessing();
    }

    /**
     * Marks a task in the list as completed.
     * @param index the index to mark
     */
    public String markItem(int index) {
        if (index >= size) {
            return "OOPS!! please enter a valid number";
        }
        listItems[index].markCompleted();
        return "Nice! I've marked this task as done:\n" + listItems[index];
    }

    /**
     * Marks a task in the list as uncompleted.
     * @param index the index to unmark
     */
    public String unmarkItem(int index) {
        if (index >= size) {
            return "OOPS!! please enter a valid number";
        }
        listItems[index].markUncompleted();
        return "Ok, I've marked this task as not done yet:\n" + listItems[index];
    }

    /**
     * Deletes a task from the list.
     * @param index the index to delete
     */
    public String deleteTask(int index) {
        if (index >= size) {
            return "OOPS!! please enter a valid number";
        }
        String output = "Ok, I will remove the task:\n" + listItems[index];
        for (int i = index; i < size - 1; i++) {
            listItems[i] = listItems[i + 1];
        }
        size--;
        return output;
    }

    /**
     * Exports all the task from the list
     * @return String[] the tasks in an array of string
     */
    public String[] exportTasks() {
        String[] temp = new String[size];
        for (int i = 0; i < size; i++) {
            temp[i] = listItems[i].taskToStorageFormat();
        }
        return temp;
    }

    /**
     * Initialises all tasks into TaskList
     * @param tasks string of tasks
     */
    public void initTasks(String[] tasks) {
        size = tasks.length;
        for (int i = 0; i < size; i++) {
            if (ToDo.isToDoFormat(tasks[i])) {
                listItems[i] = new ToDo();
                listItems[i].initStorageFormat(tasks[i]);
            }
            else if (Event.isEventFormat(tasks[i])) {
                listItems[i] = new Event();
                listItems[i].initStorageFormat(tasks[i]);
            }
            else if (Deadline.isDeadlineFormat(tasks[i])) {
                listItems[i] = new Deadline();
                listItems[i].initStorageFormat(tasks[i]);
            }
            else {
                listItems[i] = new Event();
                listItems[i].initStorageFormat(tasks[i]);
            }
        }
    }
}
