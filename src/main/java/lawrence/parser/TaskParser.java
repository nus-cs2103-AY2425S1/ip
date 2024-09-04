package lawrence.parser;

import lawrence.task.Task;

public class TaskParser {
    public static Task createTask(String input, InputSource source) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Task input cannot be empty!");
        }
        
        TaskCreator creator;
        switch(source) {
        case FILE:
            creator = new FileTaskCreator();
            break;
        case USER:
            creator = new UserTaskCreator();
            break;
        default:
            // This case should never be reached
            throw new IllegalStateException("Unexpected source: " + source);
        }
        return creator.createTask(input);
    }
}
