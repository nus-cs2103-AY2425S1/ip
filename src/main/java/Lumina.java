public class Lumina {

    // Cosntructor
    public Lumina() {

    }

    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    private void printMessage(String message) {
        this.printSeparator();
        System.out.println(message);
    }

    private void greet() {
        this.printMessage(" Hello! I'm Lumina\n" +
                " What can I do for you?");
    }

    private void exit() {
        this.printMessage(" Bye. Hope to see you again soon!");
        this.printSeparator();
        System.exit(0);
    }

    public static void main(String[] args) {
       Lumina lumina = new Lumina();
       lumina.greet();
       lumina.exit();
    }
}
