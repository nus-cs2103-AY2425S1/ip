package blob;

import java.util.ArrayList;

/**
 * Represents a 'ToDo' task.
 * Constructor requires name and boolean value of whether task is done or not.
 */
public class Todo extends Task {
    public Todo(String name, boolean isDone, ArrayList<String> tags) {
        super(name, isDone, tags);
        super.type = "T";
    }

    /**
     * @return string representation of the task in the form "[T] ['completion status'] 'task name'".
     */
    @Override
    public String toString() {
        if (this.tags.isEmpty()) {
            return "[T]" + "[" + this.check() + "] " + this.name;
        }
        StringBuilder tags = new StringBuilder("");
        for (int i = 0; i < this.tags.size(); i++) {
            String tag = this.tags.get(i);
            tags.append("#" + tag + " ");
        }
        return "[T] [" + this.check() + "] " + this.name + " - " + tags.toString();
    }
}

