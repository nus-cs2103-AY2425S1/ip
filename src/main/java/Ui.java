public class Ui {
    private final String name;

    public Ui(String name) {
        this.name = name;
    }

    public void greet() {
        String greet =
                " Hello! I'm " + this.name + "\n" +
                        " What can I do for you?";
        System.out.println(formattedCommand(greet));
    }

    public void quit() {
        System.out.println(formattedCommand("Bye. Hope to see you again soon!"));
    }

    public void output(String output) {
        System.out.println(formattedCommand(output));
    }

    private String formattedCommand(String command) {
        return "____________________________________________________________\n" +
                command + "\n" +
                "____________________________________________________________\n";
    }
}
