public class Spike {

    public static void helloMessage() {
        System.out.print("_________________________________________________________\n");
        System.out.print("Hello! I'm Spike\nWhat can I do for you?\n");
        System.out.print("_________________________________________________________\n");
        return;
    }

    public static void byeMessage() {
        System.out.print("Bye. Hope to see you again soon!\n");
        System.out.print("_________________________________________________________");
        return;
    }

    public static void main(String[] args) {
        helloMessage();
        byeMessage();
    }
}
