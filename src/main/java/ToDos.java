public class ToDos extends Task{
    public ToDos(String description) throws TaskException{
        super(description.substring(4).trim());
        if(description.trim().length() < 5) {
            throw new TaskException("Sorry, the description for todo cannot be empty.");
        }
        taskType = "[T]";
    }
}
