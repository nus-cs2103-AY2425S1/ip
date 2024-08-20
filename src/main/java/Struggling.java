public class Struggling {
    final private String name = "struggling";

    Struggling() {
        greeting();
        exit();
    }

    private void greeting() {
        line();
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
        line();
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    private void line() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Struggling s = new Struggling();
    }
}
