package tecna;

public class TaskDuplicateException extends Exception{
    public TaskDuplicateException(){
        super("This task already exists.");
    }
}
