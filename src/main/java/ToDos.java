public class ToDos extends Task {
    public ToDos (String name) {
        super(name);
    }

    @Override
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        }
        else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("todo ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
