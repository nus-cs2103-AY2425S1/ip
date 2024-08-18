public class ToDo extends Task{

    public ToDo (String description) {
        super(description);
        this.type = "T";
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
