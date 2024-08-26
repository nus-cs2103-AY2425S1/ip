public class Todo extends Task {
    public Todo(String desc) {
        super(desc, false);
    }

    @Override
    public String stringValue() {
        return ("[T]" + super.stringValue());
    }

    public String storeValue() {
        return this.stringValue().substring(1,2) + " | " + this.isTaskDone() + " | " + super.getDesc() + "\n";
    }

}
