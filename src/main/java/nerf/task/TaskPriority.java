package nerf.task;

public enum TaskPriority {
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int priorityLevel;

    TaskPriority(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public static TaskPriority getPriorityLevel(int priorityLevel) {
        switch (priorityLevel) {
        case 1:
            return HIGH;
        case 2:
            return MEDIUM;
        case 3:
            return LOW;
        default:
            throw new IllegalArgumentException("Invalid priority level: " + priorityLevel);
        }
    }
    
    @Override
    public String toString() {
        switch (this) {
        case HIGH:
            return "High";
        case MEDIUM:
            return "Medium";
        case LOW:
            return "Low";
        default:
            return "Unknown";
        }
    }
}
