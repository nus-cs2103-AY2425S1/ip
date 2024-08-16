public class Task {
    private String name;
    private boolean done;

    Task(String name) throws CheeseException {
        if (name.isBlank()) throw new CheeseException("Cheese needs to have a name");
        this.name = name;
        done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (done){
            result.append("[X] ");
        } else {
            result.append("[ ] ");
        }
        result.append(name);
        return result.toString();
    }
}
