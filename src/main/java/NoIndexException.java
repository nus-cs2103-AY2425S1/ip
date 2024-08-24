public class NoIndexException extends DiegoException{
    public NoIndexException(){
        super(
                "Please try mark or unmark followed by the index of the task.\n" +
                "Ex: mark 1\n" +
                "Ex: unmark 1\n" +
                "Ex: delete 1\n"+
                "____________________________________________________________"
        );
    }
}
