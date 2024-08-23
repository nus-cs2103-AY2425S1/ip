public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    char getTaskType() {
        return 'T';
    }
}
