package kitty.kittyexceptions;

public class TaskException extends KittyException {

    /**
     * Returns the Exception description in String.
     */
    @Override
    public String toString() {
        return "Invalid input. Add tasks by inputting:\n" + "    <task_type> <name_of_todo_item>";
    }
}
