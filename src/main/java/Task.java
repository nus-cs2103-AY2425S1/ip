public abstract class Task {
    private final String des;
    private boolean isMark = false;
    public Task(String des) throws TinaException {
        if (des.isEmpty()) {
            throw new TinaException("Enter your description after the space");
        }
        this.des = des;
    }

    public void mark() {
        isMark = true;
    }

    public void unmark() {
        isMark = false;
    }

    public String getDes() {
        if(isMark) {
            return "[X] " + des;
        } else {
            return "[ ] " + des;
        }
    }
}
