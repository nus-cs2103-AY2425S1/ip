public class TaskList {

    private Task[] list;
    private int index;

    public TaskList() {
        this.list = new Task[100];
        this.index = 0;
    }

    public void add(Task item) {
        if (index < list.length) {
            list[index] = item;
            index++;
        }

    }

    public void printList() {
        System.out.println("_____________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println(i+1 + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
        }
        System.out.println("_____________________________________________");
    }

    public String markAsDone(int index) {
        list[index].markAsDone();
        return list[index].toString();
    }

    public String markAsNotDone(int index) {
        list[index].markAsNotDone();
        return list[index].toString();
    }
}
