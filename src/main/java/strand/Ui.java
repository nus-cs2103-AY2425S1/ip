package strand;

import strand.Tasks.Task;

public class Ui {
    private static final String horizontalLine = "～～～～～～～～～～～～～～～～～～～～～～～～><>";

    public void showLoadingError() {
        print("Loading file…\n" + "█▒▒▒▒▒▒▒▒▒");
    }

    /**
     * Prints a string with an indentation of 4 spaces.
     *
     * @param str The string to be printed.
     */
    private static void print(String str) {
        System.out.println(str.indent(4));
    }

    public void showLine() {
        print(horizontalLine);
    }

    public void showError(String err) {
        print(err);
    }

    public void welcome() {
        print("ヾ(⌐■_■)ノ♪ Welcome! I'm Strand\nWhat can I do for you?");
    }

    public void goodbye() {
        print("Adios. Hope to see you again soon! ヾ(＾ ∇ ＾)");
    }

    public void addTask(Task task, Integer size) {
        print(String.format("""
                (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: Task added:
                  %s
                Now you have %d tasks in the list.""", task.toString(), size));
    }

    public void deleteTask(Task task, Integer size) {
        print(String.format("""
                (☞ﾟ∀ﾟ)☞ Task removed:
                %s
                Now you have %d tasks in the list.""", task.toString(), size));
    }

    public void markTask(Task task, Boolean mark) {
        if (mark) {
            print(String.format("( ﾟヮﾟ) You finished a task?! Congrats! I've marked this task as done:\n%s", task));
        } else {
            print(String.format("ಠ_ಠ ...OK, I've marked this task as not done yet:\n%s", task.toString()));
        }

    }

    public void list(TaskList tasks) {
        print(tasks.toString());
    }

}
