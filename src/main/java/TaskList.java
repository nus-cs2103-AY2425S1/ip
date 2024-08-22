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

    public void addItem(String item) {
        listItems[size] = new Task(item);
        size++;
        System.out.println("added: " + item);
    }
}
