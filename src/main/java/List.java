public class List {

    private Task[] list;
    private int itemCount;
    public List() {
        this.list = new Task[100];
        this.itemCount = 0;
    }

    public void add(String item) {
        this.list[itemCount] = new Task(item);
        this.itemCount++;
    }

    public void listOut() {
        for (int i = 1; i <= itemCount; i++) {
            System.out.println(i + "." + this.list[i - 1].toString());
        }
    }

    public void printTask(int index) {
        System.out.println(this.list[index].toString());
    }

    public void markTask(int index) {
        this.list[index].mark();
    }

    public void unmarkTask(int index) {
        this.list[index].unmark();
    }
}
