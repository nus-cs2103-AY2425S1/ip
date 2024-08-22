public class TaskException extends KittyException{
    @Override
    public String toString() {
        return "Invalid input. Add tasks by inputting:\n" + "    <task_type> <name_of_todo_item>";
    }
}
