package quack;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import quack.tasks.Task;
import quack.tasks.ToDoTask;
import quack.tasks.DeadlineTask;
import quack.tasks.EventTask;

/** 
 * This class is to act as a Stub for the Task List class.
 */
public class TaskListStub extends TaskList{
    
    /** Date time format */
    protected static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Creates an stub TaskList object.
     */
    public TaskListStub() {
        super();
    }
    
    /**
     * Populates the task list with predefined tasks.
     */
    public void populateTaskList() {
        LocalDateTime startTime = LocalDateTime.parse("01/01/2024 10:59:30", DATE_FORMAT);
        LocalDateTime endTime = LocalDateTime.parse("10/01/2024 14:00:59", DATE_FORMAT);

        Task dummyTask1 = new ToDoTask("Dummy 1");
        Task dummyTask2 = new EventTask("Dummy 2", startTime, endTime);
        dummyTask2.mark();
        Task dummyTask3 = new DeadlineTask("Dummy 3", endTime);

        toDoList.add(dummyTask1);
        toDoList.add(dummyTask2);
        toDoList.add(dummyTask3);

        this.length = 3;
    }

    /**
     * Creates an empty task list.
     */
    public void emptyTaskList() {
        toDoList = new ArrayList<Task>();
        this.length = 0;
    }

}
