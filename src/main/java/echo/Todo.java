package echo;

/**
 * The Todo class represents a simple task that does not have any additional details such as a deadline or event time.
 * It extends the Task class and is used for tasks that are just descriptions.
 */
public class Todo extends Task {


    /**
     * Returns the type letter for this task, which is "T" for Todo.
     *
     * @return The string "T".
     */
    public String getTypeLetter() {
        return "T";
    }

    @Override
    public String editTask(String input) {
        String[] str = input.split(" ", 2);
        String command = str[0];


        switch (command) {
            case"name":
                this.taskDes = str[1];
                return this.toString();
        default:
            return "enter name (new name)";
            }

    }


    /**
     * Constructs a Todo task with the specified description.
     *
     * @param taskDes The description of the task.
     */
    public Todo(String taskDes) {
        super(taskDes);
    }

    /**
     * Returns a string representation of the Todo task, including its type and description.
     *
     * @return A string that shows the type "[T]" and the task description.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns the additional information for the task.
     * Since a Todo task has no additional information, this returns a space character.
     *
     * @return A space character " ".
     */
    @Override
    public String getAdd() {
        return " ";
    }
}
