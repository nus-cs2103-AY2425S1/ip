public class Simon {
    final String HOR_LINE = "\t____________________________________________________________\n";
    final String WLC_MSG = " Hello! I'm Simon \n" +
            " What can I do for you?\n";
    final String EXT_MSG = " Bye. Hope to see you again soon!";
    private String printMessage(String msg) {
        return HOR_LINE + "\t" + msg + "\n" + HOR_LINE;
    }
    public void run() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm [YOUR CHATBOT NAME]\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
    public static void main(String[] args) {
        new Simon().run();
    }
}
