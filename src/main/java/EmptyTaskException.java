public class EmptyTaskException extends Exception{
    public EmptyTaskException(String task) {
        super (String.format("OOPS!! The description of a %s cannot be empty %nHave you forgotten to enter the details?", task));
    }
}
