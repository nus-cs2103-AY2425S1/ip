public class Ui {
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void showGreetings() {
        showLine();
        System.out.println("Hi, I'm your favourite idol, Ai!!!\n"
                + "What shall we do today? Teehee o(◠u◠)o\n");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("Don't you wanna get my autograph first?\n"
                + "Aww okie :,( See ya!!\n");
    }

    public void showError(String msg) {
        System.err.println(msg);
    }
}
