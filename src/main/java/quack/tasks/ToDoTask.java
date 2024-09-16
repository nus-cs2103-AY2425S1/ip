package quack.tasks;

/**
 * This class defines and prodides functionality for todo tasks.
 */
public class ToDoTask extends Task {

    /**
     * Creates a ToDo task object with the given description.
     * @param taskName Description of the task.
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toCsvFormat() {
        return "TODO," + super.toCsvFormat() + "," + this.isChecked + "," + this.getTag();
    }

    @Override
    public boolean equals(Object obj) {

        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ToDoTask)) {
            return false;
        }

        ToDoTask taskObj = (ToDoTask) obj;

        boolean isSameDescription = this.description == taskObj.description;
        boolean sameStatus = this.isChecked == taskObj.isChecked;
        boolean sameTag = this.tag == taskObj.tag;

        return isSameDescription && sameStatus && sameTag;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
