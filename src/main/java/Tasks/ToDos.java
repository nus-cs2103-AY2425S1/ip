package Tasks;

import Tasks.Task;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getExtraInfo() {
        return "";
    }
}
