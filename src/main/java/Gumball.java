public class Gumball {
    public static void main(String[] args) {
        line();
        intro();
        line();
        outro();
        line();

    }

    public static void intro() {
        String str = "Hello! I'm Gumball \n"
                + "What can I do for you?";
        System.out.println(str);
    }

    public static void outro() {
        String str ="Bye. Hope to see you again soon!";
        System.out.println(str);
    }

    public static void line() {
        String str ="____________________________________________________________";
        System.out.println(str);
    }
}
