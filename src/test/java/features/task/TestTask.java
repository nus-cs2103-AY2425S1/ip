package features.task;

public class TestTask extends Task {
    private boolean isMarked = false;

    public TestTask(String name) {
        super(name);
    }

    @Override
    public void mark() {
        isMarked = true;
    }

    @Override
    public void unmark() {
        isMarked = false;
    }

    @Override
    public boolean getIsMarked() {
        return isMarked;
    }

    @Override
    public String toString() {
        return super.toString() + (isMarked ? " (marked)" : " (not marked)");
    }
}
