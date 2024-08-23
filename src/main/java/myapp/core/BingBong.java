package myapp.core;

public class BingBong {
    public static void main(String[] args) {
        BingBongUI ui = new BingBongUI();
        BingBongBot bot = new BingBongBot(ui, new Storage("./data/tasks.txt"));
        bot.run();
    }
}
