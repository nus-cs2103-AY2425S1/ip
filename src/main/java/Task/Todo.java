package task;

public class Todo extends Task {
    private String type;

    public Todo(String desc, String type, boolean isDone) {
        super(desc, isDone);
        this.type = type;
    }

    @Override
    public String convertTaskToString() {
        return this.type + "::" + super.isDone() + "::" + super.getDesc() + "\n";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Todo t = (Todo) o;
        return this.type.equals(t.type) && super.getDesc().equals(t.getDesc()) && (super.isDone() == t.isDone());
    }
}
