public class Ui {
    void reply(String str) {
        System.out.println("Loafy: " + str + "\n");
    }

    void errorMsg(LoafyException ex) {
        System.out.println("Hmm...\n" +
                ex.getMessage() +
                "\n Try again!\n");
    }

    void greeting() {
        reply("Hellooo, I'm Loafy!");
        reply("What can I do for you? :D");
    }

    void exit() {
        reply("Byeee see you soon! ;)");
    }
}
