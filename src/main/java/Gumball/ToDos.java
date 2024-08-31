package Gumball;

public class ToDos extends Task{
    public ToDos(String desc) throws TaskException{
        super(desc.substring(4).trim(),desc);
        if(desc.trim().length() < 5) {
            throw new TaskException("Sorry, the description for todo cannot be empty.");
        }
        taskType = "[T]";
    }
}
