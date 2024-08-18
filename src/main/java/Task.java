public class Task {
    private String task;

    public Task(String task) {
        this.task = task;

        EchoBot.dashline();
        System.out.println("added: " + this.task);
        EchoBot.dashline();
    }

    @Override
    public String toString() {
        return this.task;
    }
}
