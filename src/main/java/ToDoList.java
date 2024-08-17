import java.util.ArrayList;

public class ToDoList {
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
        String currentList = "";
        for (int i = 1; i < toDoList.size() + 1; i++) {
            currentList += Integer.toString(i) + ". " + this.readItem(i - 1);
            if (i < toDoList.size()) {
                currentList += "\n";
            }
        }
        return currentList;
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        System.out.println(toDoList.addItem("book"));
        System.out.println(toDoList.readItem(0));
    }
}
