package makima.command;

import makima.task.ToDo;

/**
 * State to handle creation of todo tasks
 */
public class ToDoState extends State {
    @Override
    public String getResponse(String input, Makima makima) {
        makima.addTask(new ToDo(input));
        makima.setState(new WaitingState());
        return "Task added successfully!\n";
    }
}
