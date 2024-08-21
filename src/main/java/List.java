public class List {

    private String[] list;
    private int itemCount;
    public List() {
        this.list = new String[100];
        this.itemCount = 0;
    }

    public void add(String item) {
        this.list[itemCount] = item;
        this.itemCount++;
    }

    public void listOut() {
        for (int i = 1; i <= itemCount; i++) {
            System.out.println(i + ". " + this.list[i - 1]);
        }
    }
}
