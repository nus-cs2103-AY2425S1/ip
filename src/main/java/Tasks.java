public class Tasks {
    // FIELDS-----------------------------
    String name;
    Boolean done;
    public Tasks (String name) {
        this.name = name;
        this.done = false;
    }

    public void setter (boolean x) {
        this.done = x;
    }
    public String toString() {
        if (done) {
            return String.format("[X] %s", name);
        } else {
            return String.format("[] %s", name);
        }
    }


}
