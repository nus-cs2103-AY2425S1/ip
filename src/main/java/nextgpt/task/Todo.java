package nextgpt.task;

/**
 * Class for task type todo.
 */
public class Todo extends Task{
    public Todo (String name) {
        super(name);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
