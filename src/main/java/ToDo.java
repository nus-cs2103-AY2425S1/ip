public class ToDo extends Task{

    public ToDo (String Description) {
        super();
        this.type = "T";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
