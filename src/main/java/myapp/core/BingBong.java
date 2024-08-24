package myapp.core;

public class BingBong {
    public static void main(String[] args) {
        BingBongUi ui = new BingBongUi();
        BingBongBot bot = new BingBongBot(ui, new Storage("./data/tasks.txt"));
        bot.run();
    }
}
