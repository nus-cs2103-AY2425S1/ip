import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<String> ToDoList = new ArrayList<String>();

    public String readItem(int index) {
        return ToDoList.get(index);
    }

    public String addItem(String item) {
        ToDoList.add(item);
        return "added: " + item;
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        System.out.println(toDoList.addItem("book"));
        System.out.println(toDoList.readItem(0));
    }
}
