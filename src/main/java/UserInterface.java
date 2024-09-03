public class UserInterface {
    private String name;
    public UserInterface(String name) {
        this.name = name;
    }

    public void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to establish another GST hike.%n"
                + "What can I do for you?", name);
        showMessage(greeting);
    }

    public void exitSession() {
        showMessage("That's all folks! Hope to see you again soon!");
        System.exit(0);
    }

    public void showMessage(String message) {
        showHorizontalLine();
        System.out.println(message);
        showHorizontalLine();
    }

    public void showHorizontalLine() {
        System.out.println("====================");
    }
}
