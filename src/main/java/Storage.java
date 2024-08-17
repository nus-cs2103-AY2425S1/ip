import java.util.ArrayList;

public class Storage {
    private final ArrayList<String> toDoList = new ArrayList<String>();

    public String readItem(int index) {
        return this.toDoList.get(index);
    }

    public String addItem(String item) {
        this.toDoList.add(item);
        return "added: " + item;
    }

    public String processCommand(String command) {
        if (command.equals("list")) {
            return this.printList();
        } else {
            return this.addItem(command);
        }
    }

    public String printList() {
        StringBuilder currentList = new StringBuilder();
        for (int i = 1; i < toDoList.size() + 1; i++) {
            currentList.append(Integer.toString(i)).append(". ").append(this.readItem(i - 1));
            if (i < toDoList.size()) {
                currentList.append("\n");
            }
        }
        return currentList.toString();
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        System.out.println(storage.addItem("book"));
        System.out.println(storage.readItem(0));
    }
}
