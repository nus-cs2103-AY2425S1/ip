public class Ui {
    static final String line = "   ______________________________________________________\n";

    public void printStatus(Task t, int i) {
        System.out.println(line + "   Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.printf("   Now you have %d tasks in the list\n%s", i, line);
    }

    public void printWelcome() {
        System.out.println(Ui.line + "   Hello! I'm Bro\n   What can I do for you?\n" + line);
    }

    public void printList(TaskList tasks) {
        int len = tasks.size();
        System.out.print(line + "   Here are the tasks in your list:\n");
        for (int i = 0; i < len; i++) {
            System.out.printf("   %d.%s\n", i + 1, tasks.get(i));
        }
        System.out.print(line);
    }

    public void printDefault() {
        System.out.println(line + "   Well, what are u trying to do here? " +
                "I don't quite understand :(\n" + line);
    }

    public void printErrorFormat() {
        System.out.println(line + "   Please input a number after mark, " +
                "unmark or delete!!!\n" + line);
    }

    public void printErrorSize(int i) {
        System.out.println(line + "   Input a valid number");
        System.out.printf("   You only have %d tasks in the list\n%s", i, line);
    }

    public void printExit() {
        System.out.println(line + "   Bye. Hope to see you again soon!\n" + line);
    }

    public void printMark(int i, TaskList tasks) {
        System.out.print(line + "   Nice! I've marked this task as done:\n"
                + "   " + tasks.get(i - 1) + "\n" + line);
    }

    public void printUnmark(int i, TaskList tasks) {
        System.out.print(line + "   OK, I've marked this task as not done yet:\n"
                + "   " + tasks.get(i - 1) + "\n" + line);
    }

    public void printDelete(int i, TaskList tasks) {
        System.out.println(line + "   Noted. I've removed this task:");
        System.out.println("   " + tasks.get(i - 1));
        System.out.printf("   Now you have %d tasks in the list\n%s", tasks.size() - 1, line);
    }
}
