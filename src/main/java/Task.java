public class Task {
    private int id;
    private boolean status;
    private String desc;
    private static int count = 1;

    public Task(String desc) {
        this.id = count;
        this.status = false;
        this.desc = desc;
        count++;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatusString() {
        return status ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusString(), getDesc());
    }
}
