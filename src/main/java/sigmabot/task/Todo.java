package sigmabot.task;
import sigmabot.ui.UiComponent;

public class Todo extends Task {

    public Todo(String name, String description) {
        super(name, description);
    }

    public static Todo createTodo(UiComponent ui) {
        ui.printDialogue("Enter name: \n");
        String name = ui.readInput();
        ui.printDialogue("Enter description: \n");
        String description = ui.readInput();
        return new Todo(name, description);
    }
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
