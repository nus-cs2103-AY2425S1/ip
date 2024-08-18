import java.util.ArrayList;

public class ToDoList {
    private ArrayList<String> toDo;

    ToDoList () {
        this.toDo = new ArrayList<String>();
    }

    public void addItem(String item) {
        toDo.add(item);
        System.out.println("Added: " + item);
    }

    @Override
    public String toString() {
        if (this.toDo.size() == 0) {
            return "The list is empty, why not add something!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < toDo.size(); i++) {
                if (i == 0) {
                    sb.append((i + 1) + ". " + toDo.get(i));
                } else {
                    sb.append("\n" + (i + 1) + ". " + toDo.get(i));
                }
            }
            return sb.toString();
        }
    }

}
