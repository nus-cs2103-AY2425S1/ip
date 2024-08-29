public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean status) {
        super(description,status);
    }

    public ToDo markAsDone() {
        return new ToDo(this.description, true);
    }

    public ToDo markAsUndone() {
        return new ToDo(this.description, false);
    }

    public String getType() {
        return "T";
    }

    public String getTime() {
        return "";
    }

}
