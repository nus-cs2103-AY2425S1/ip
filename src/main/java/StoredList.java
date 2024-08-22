public class StoredList {
    private Task[] data;
    private int last = 0;

    public StoredList(int len) {
        this.data = new Task[len];
    }

    public void addItem(Task item) {
        this.data[last] = item;
        this.last++;
    }

    public Task getItem(int index) {
        return this.data[index];
    }

    public int getSize() {
        return this.last;
    }
}
