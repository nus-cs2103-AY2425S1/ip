import java.util.List;

public class Ui {
    private StringBuilder message;
    private String display;
    public Ui() {
        this.message = new StringBuilder();
    }

    public void showWelcome() {
        message.append("Welcome to Hyperion!");
        System.out.println(this.message.toString());
    }
    public void showList(List<Task> allTasks) throws CommandFoundButInvalidException{
        String initialValues = new ListAll("", allTasks).message();
        System.out.println(initialValues);
    }
    public void displayError(String message) {
        System.out.println(message);
    }
}
