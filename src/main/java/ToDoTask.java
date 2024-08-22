public class ToDoTask extends Task {
    public ToDoTask(String input) {
        this.name = input.substring(5);
        this.taskTypeSymbol = "T";
    }

}
