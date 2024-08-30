package src.main.java;

public class EmptyTodoException extends BonnieException {

    public EmptyTodoException() {
        super("Hey there, the body of your todo task cannot be empty!");
    }
}
