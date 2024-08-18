public class BingBong {
    public static void main(String[] args) {
        BingBongUI ui = new BingBongUI();
        BingBongBot bot = new BingBongBot(ui);
        bot.run();
    }
}
