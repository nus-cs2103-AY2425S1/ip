import java.util.ArrayList;

public class Todo extends Task {

    public Todo() {
        super();
    }

    public Todo(String s) {
        super(s);
    }
    
    public void addTodo(String[] arr, ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[1]);
        if (arr.length >= 3) {
            for (int i = 2; i < arr.length; i++) {
                String word = " " + arr[i];
                sb.append(word);
            } 
        }
        Todo task = new Todo(sb.toString());
        list.add(task);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }

}
