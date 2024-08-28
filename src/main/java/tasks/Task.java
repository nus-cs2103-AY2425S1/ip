package tasks;
import chatterboxexceptions.ChatterboxExceptions;
public abstract  class Task {
    private Boolean status;
    private String desc;


    public Task(String desc) throws ChatterboxExceptions.ChatterBoxNoInput{
        if (desc.trim().isEmpty()) {
            throw new ChatterboxExceptions.ChatterBoxNoInput("Error: No input for task");
        }
        this.status = false;
        this.desc = desc;
    }

    public String getDescription() {
        return this.desc;
    }
    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean stat) {
        this.status = stat;
    }
    public String getTaskSymbol() { return "";}

    @Override
    public String toString() {
        return this.getDescription();
    }
}