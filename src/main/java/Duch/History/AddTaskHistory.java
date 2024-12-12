package duch.history;

import duch.TaskList;

public class AddTaskHistory extends History {
    private TaskList tasklist;

    
    public AddTaskHistory(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Contains the code to undo the command.
     */
    public void undo() {
        tasklist.remove(tasklist.size() - 1);
    }
}
