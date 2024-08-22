public class EmptyTextException extends Exception{
    public EmptyTextException() {
        super("Please specify a task");
    }

    @Override
    public String toString() {
        return "Please specify a task? :) \n";
    }
}
