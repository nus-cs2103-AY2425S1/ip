public class Count {
    public final String spacer = "\n____________________________________________________________\n";
    public void greet() {
        System.out.println(spacer + "Hello! I'm Count!\nWhat can I do for you?" + spacer);
    }

    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!" + spacer);
    }

    public static void main(String[] args) {
        Count c = new Count();
        c.greet();
        c.farewell();
    }
}
