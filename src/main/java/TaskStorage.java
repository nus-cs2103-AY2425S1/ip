public class TaskStorage extends MendelAction{
    private final String message;

    public TaskStorage(String message) {
        this.message = message;
    }

    public void speak() {
        System.out.println(new FormatText(this.toString()).wrapLines());
    }

    @Override
    public String toString() {
        return message;
    }
}
