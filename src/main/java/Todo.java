public class Todo {

    private static final int MAX_TASKS = 100;
    private String[] items;
    private int numItems;

    public Todo() {
        this.items = new String[MAX_TASKS];
        this.numItems = 0;
    }

    public String add(String item) {
        this.items[this.numItems] = item;
        this.numItems++;
        return "     added: " + item + "\n";
    }

    public String list() {
        String message = "";
        for (int i = 1; i <= this.numItems; i++) {
            message += String.format("     %d. %s\n", i, this.items[i - 1]);
        }
        return message;
    }
}
