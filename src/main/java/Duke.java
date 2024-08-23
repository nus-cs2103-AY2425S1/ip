public class Duke {
    public static String addHorizontalLines(String dialog) {
        String res = "____________________________________________________________\n" +
                dialog + "\n" +
                "____________________________________________________________";
        return res;
    }

    public static void main(String[] args) {
        String hi = " Hello! I'm Foo\n" +
                " What can I do for you?";

        String bye = "Bye. Hope to see you again soon!";
        System.out.println(addHorizontalLines(hi));
        System.out.println(addHorizontalLines(bye));
    }
}
