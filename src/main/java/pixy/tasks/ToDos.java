// class for Tasks which are ToDos
package pixy.tasks;

import pixy.tasks.Task;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
