public class TaskList {
    private Task[] listItems;
    private int size;

    public TaskList() {
        listItems = new Task[100];
        size = 0;
    }

    public void displayList() {
        for (int i = 0; i < size; i++) {
            System.out.println(String.valueOf(i+1) + ". " + listItems[i]);
        }
    }

    private void addTaskProcessing() {
        System.out.println("Got it! I've added this task: ");
        System.out.println(listItems[size]);
        size++;
    }

    public void addToDo(String item) {
        listItems[size] = new ToDo(item);
        addTaskProcessing();
    }

    public void addEvent(String item) {
        listItems[size] = new Event(item);
        addTaskProcessing();
    }

    public void addDeadline(String item) {
        listItems[size] = new Deadline(item);
        addTaskProcessing();
    }

    public void markItem(int index) {
        if (index >= size) {
            System.out.println("OOPS!! please enter a valid number");
            return;
        }
        listItems[index].markAsCompleted();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(listItems[index]);
    }

    public void unMarkItem(int index) {
        if (index >= size) {
            System.out.println("OOPS!! please enter a valid number");
            return;
        }
        listItems[index].markAsUncompleted();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(listItems[index]);
    }

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
}
