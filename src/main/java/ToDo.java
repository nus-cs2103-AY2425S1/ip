public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.type = "T";
        this.description = description;
    }

    // the following constructor was created using ChatGPT autocomplete
    public ToDo(String description, boolean isDone) {
        super(description);
        this.type = "T";
        this.description = description.trim();
        this.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String convertToFileFormat() {
        return this.type + "|" + this.isDone + "|" + this.description;
    }

}
