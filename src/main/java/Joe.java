public class Joe {
    public static final String horizontalLine = "____________________________________________________________";
    public static final String chatbotName = "Joe";

    public static void greet() {
        System.out.printf("%s\nHello! I'm %s \nWhat can I do for you? \n%s\n", horizontalLine, chatbotName, horizontalLine);
    }

    public static void farewell() {
        System.out.printf("Bye. Hope to see you again soon!\n%s", horizontalLine);
    }
    public static void main(String[] args) {
        greet();
        farewell();
}
