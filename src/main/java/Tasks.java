/**
 * Represents information of tasks specified by user
 */
public class Tasks {
    // FIELDS-----------------------------
    String name;
    Boolean isDone;

    /**
     * Initialises name of task and set as not done
     * @param name
     */
    public Tasks (String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setter (boolean x) {
        this.isDone = x;
    }

    public String getName () {
        return name;
    }

    public String getTimeline() {
        return null;
    }
    public String toString() {
        if (isDone) {
            return String.format("[X] %s", name);
        } else {
            return String.format("[] %s", name);
        }
    }


}
