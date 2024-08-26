public class TaskIndexOutOfBound extends Exception{
    public TaskIndexOutOfBound() {
        super(String.format("OOPS!! I'm sorry, but the task you are referring to doesn't exist. %nCheck the index again"));
    }
}

