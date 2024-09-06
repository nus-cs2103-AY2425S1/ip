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
     * Prints the tasks in the list.
     */
    public void displayList() {
        for (int i = 0; i < size; i++) {
            System.out.println(String.valueOf(i+1) + ". " + listItems[i]);
        }
    }

    public void displayFoundList(String keyword) {
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (listItems[i].find(keyword)) {
                System.out.println(String.valueOf(j + 1) + ". " + listItems[i]);
                j++;
            }
        }
    }

    /**
     * Informs the user that the item has been added and increases the size of the list.
     */
    private void addTaskProcessing() {
        System.out.println("Got it! I've added this task: ");
        System.out.println(listItems[size]);
        size++;
    }

    /**
     * Adds a ToDo task to the list.
     * @param item the format of the task
     */
    public void addToDo(String item) {
        listItems[size] = new ToDo(item);
        addTaskProcessing();
    }

    /**
     * Adds an Event task to the list.
     * @param item the format of the task
     */
    public void addEvent(String item) {
        listItems[size] = new Event(item);
        addTaskProcessing();
    }

    /**
     * Adds a Deadline task to the list.
     * @param item the format of the task
     */
    public void addDeadline(String item) {
        listItems[size] = new Deadline(item);
        addTaskProcessing();
    }

    /**
     * Marks a task in the list as completed.
     * @param index the index to mark
     */
    public void markItem(int index) {
        if (index >= size) {
            System.out.println("OOPS!! please enter a valid number");
            return;
        }
        listItems[index].markCompleted();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(listItems[index]);
    }

    /**
     * Marks a task in the list as uncompleted.
     * @param index the index to unmark
     */
    public void unmarkItem(int index) {
        if (index >= size) {
            System.out.println("OOPS!! please enter a valid number");
            return;
        }
        listItems[index].markUncompleted();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(listItems[index]);
    }

    /**
     * Deletes a task from the list.
     * @param index the index to delete
     */
    public void deleteTask(int index) {
        if (index >= size) {
            System.out.println("OOPS!! please enter a valid number");
            return;
        }
        System.out.println("Ok, I will remove the task:");
        System.out.println(listItems[index]);
        for (int i = index; i < size - 1; i++) {
            listItems[i] = listItems[i + 1];
        }
        size--;
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
