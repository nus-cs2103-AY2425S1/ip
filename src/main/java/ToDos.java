public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }
    public ToDos(String description, String done) {
        super(description);
        if (done.equals("1")) {super.setDone();}
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}