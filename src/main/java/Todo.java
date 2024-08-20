public class Todo {

    private static final int MAX_TASKS = 100;
    private Task[] items;
    private int numItems;

    public Todo() {
        this.items = new Task[MAX_TASKS];
        this.numItems = 0;
    }

    public String add(String item) {
        this.items[this.numItems] = new Task(item);
        this.numItems++;
        return "     added: " + item + "\n";
    }

    public String list() {
        String message = "     Here are the tasks in your list:\n";
        for (int i = 1; i <= this.numItems; i++) {
            message += String.format("     %d. %s\n", i, this.items[i - 1]);
        }
        return message;
    }

    public String mark(int itemIdx) {
        String message = "     Nice! I've marked this task as done:\n";
        this.items[itemIdx - 1].markAsDone();
        message += String.format("       %s\n", this.items[itemIdx - 1]);
        return message;
    }

    public String unmark(int itemIdx) {
        String message = "     OK, I've marked this task as not done yet:\n";
        this.items[itemIdx - 1].markAsUndone();
        message += String.format("       %s\n", this.items[itemIdx - 1]);
        return message;
    }
}
