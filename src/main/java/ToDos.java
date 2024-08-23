public class ToDos extends Task{
    protected ToDos(String description) {
        super(description);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    protected String getExtraInfo() {
        return "";
    }
}
