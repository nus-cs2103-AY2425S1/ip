public class StoredList {
    private String[] data;
    private int last = 0;

    public StoredList(int len) {
        data = new String[len];
    }

    public void addItem(String item) {
        data[last] = item;
        last++;
    }

    public String getItem(int index) {
        return data[index];
    }

    public int getSize() {
        return last;
    }
}
