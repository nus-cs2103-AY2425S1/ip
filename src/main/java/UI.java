public class UI {
    public UI() {
        String logo = "Mendel";
        System.out.println("Hello from\n" + logo);
    }

    public void showWelcome() {
        System.out.println(this.wrapLines("Hello! I'm Mendel \n" + "What can I do for you?"));
    }

    public void preetyPrint(String message) {
        System.out.println(this.wrapLines(message));
    }

    public String wrapLines(String message) {
        return "____________________________________________________________\n" + message.indent(3) +
                "____________________________________________________________";
    }
}
