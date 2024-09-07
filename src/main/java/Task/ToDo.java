package Task;

public class ToDo extends Task {

    private ToDo(String name, TaskType taskType) {
        super(name, taskType);
    }

    /**
     * Returns the task type
     *
     * @return  the task type as a string
     */
    public String getTaskTypeAsString(){
        return "T";
    }

    /**
     * Creates a ToDo instance with given string.
     *
     * @param name the string containing information about the task type
     * @param taskType the type of task
     * @throws TaskCreationException if error occurs while creating task
     * @return ToDo instance
     */
    public static ToDo of(String name, TaskType taskType) {
        assert !name.isEmpty() : "Task name should not be empty";
        return new ToDo(name, TaskType.T);
    }
}
