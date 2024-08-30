package greetbot;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String showMarked(Task task, int total) {
        return String.format("Nice! I've marked this task as done:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.", task.toString(), total);
    }

    public String showUnmarked(Task task, int total) {
        return String.format("OK, I've marked this task as not done yet:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.", task.toString(), total);
    }

    public String showAdd(Task task, int total) {
        return String.format("Got it. I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.", task.toString(), total);
    }

    public String showDelete(Task task, int total) {
        return String.format("Noted. I've removed this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.", task.toString(), total);
    }

    public String showFind(String searchItem, TaskList list) {
        return String.format("Here are the matching tasks in your list:\n" +
                "%s", "test");
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