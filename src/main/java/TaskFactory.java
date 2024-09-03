public class TaskFactory {
    public static Task createTask(String input, InputSource source) {
        TaskCreator creator;
        switch(source) {
        case FILE:
            creator = new FileTaskCreator();
            return creator.createTask(input);
        case USER:
            creator = new UserTaskCreator();
            return creator.createTask(input);
        default:
            // This case should never be reached
            throw new IllegalStateException("Unexpected source: " + source);
        }
    }
}
