public class InputException extends Exception{
    public InputException() {
        super("invalid input");
    }
    public InputException(String msg) {
        super(msg.equals("name")?"pls include name of task"
                :msg.equals("todo")? "todo format: todo xxxxx"
                :msg.equals("event")? "event format: event xxxxx /from xxxxx /to xxxxx"
                :msg.equals("deadline")? "deadline format: deadline xxxxx /by xxxx"
                :"invalid input");
    }
}
