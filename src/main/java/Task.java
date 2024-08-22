public class Task {
    private String description;
    private Boolean completed;

    public Task(String desc) {
        this.description = desc;
        this.completed = false;
    }

    @Override
    public String toString() {
        String str = "";
        if (this.completed) {
            str += "[ ]";
        }
        str += this.description;
        return str;
    }
}
