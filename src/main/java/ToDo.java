import java.util.Objects;

public class ToDo extends Task {

    public ToDo(String t) {
        super(t);
        this.type = "[T]";
    }

    public static void load(String[] arr) {
        ToDo newTodo = new ToDo(arr[2]);
        if (Objects.equals(arr[1], "1")) {
            newTodo.done();
        }
    }
    @Override
    public String saveFileFormat() {
        String status;
        if (this.getCompleted()) {
            status = "1 | ";
        } else {
            status = "0 | ";
        }
        return "T | " + status + this.getTask();
    }
}