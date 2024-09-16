package tasktypes;

public enum TaskName {
    DEADLINE("DEADLINE"),
    EVENT("EVENT"),
    TODO("TODO");
    
    private String taskName;
    
    TaskName(String taskName) {
        this.taskName = taskName;
    }
    
    public String getName() {
        return this.taskName;
    }
}

