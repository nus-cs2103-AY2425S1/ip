package tasks;
import chatterboxexceptions.ChatterboxExceptions;
import java.util.Objects;

/**
 * Abstract class that defines Task
 */
public abstract  class Task {
    private Boolean status;
    private String desc;


    /**
     * initializes an undone task
     * @param desc is a string describing task
     * @throws ChatterboxExceptions.ChatterBoxNoInput if no input
     */
    public Task(String desc) throws ChatterboxExceptions.ChatterBoxNoInput{
        if (desc.trim().isEmpty()) {
            throw new ChatterboxExceptions.ChatterBoxNoInput("Error: No input for task");
        }
        this.status = false;
        this.desc = desc;
    }

    /**
     * returns a string with description of task
     * @return string of description
     */
    public String getDescription() {
        return this.desc;
    }

    /**
     * gets status of task
     * @return true if done, false if undone
     */
    public Boolean getStatus() {
        return this.status;
    }

    /**
     * Sets status of task
     * @param stat true or false
     */
    public void setStatus(Boolean stat) {
        this.status = stat;
    }
    public String getTaskSymbol() { return "";}

    @Override
    public String toString() {
        return this.getDescription();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        return Objects.hash(this.getDescription()) == Objects.hash(other.getDescription());
    }
}