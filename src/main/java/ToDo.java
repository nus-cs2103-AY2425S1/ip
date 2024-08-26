public class ToDo extends Task {

    public ToDo(String t) {
        super(t);
        this.type = "[T]";
    }

    public static void load(String[] arr) {
        new ToDo(arr[1]);
    }
    @Override
    public String saveFileFormat() {
        return "T | " + this.getTask();
    }
}