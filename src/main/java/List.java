public class List {

    private String[] list;
    private int index;

    public List() {
        this.list = new String[100];
        this.index = 0;
    }

    public void add(String item) {
        if (index < list.length) {
            list[index] = item;
            index++;
        }
    }

    public void printList() {
        System.out.println("_____________________________________________");
        for (int i = 0; i < index; i++) {
            System.out.println(i+1 + ":" + list[i]);
        }
        System.out.println("_____________________________________________");
    }
}
