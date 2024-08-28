import java.util.Scanner;

public class Ui {
    private Scanner commandLineReader = new Scanner(System.in);

    public String greetUser() {
        return String.format("こんにちわ！　私はグリーティングボットです\n" +
                "(Hello! I'm GreetBot)\n" +
                "どんな御用でしょうか\n" +
                "(What can I do for you?)"
        );
    }

    public String farewellUser() {
        return String.format("また近いうちにお会いできるのを楽しみにしています！\n" +
                "(I'm looking forward to seeing you again soon!)"
        );
    }

    public String readInput() {
        return this.commandLineReader.nextLine();
    }

    public void closeInput() {
        this.commandLineReader.close();
    }

    public String showList(TaskList list) {
        return list.showTasks();
    }
}