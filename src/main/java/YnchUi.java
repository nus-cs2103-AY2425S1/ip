/**
 * Outputs pre-set chatbot lines.
 *
 */
class YnchUi {
    String name;

    YnchUi() {
        this.name = "YNCH";
    }

    void printAdd(Task task, int size) {
        System.out.println("Meow! I've added this task: \n" + task + 
        "\n Now you have " + size + " tasks in the list.");
    }

    void printDelete(Task task, int size) {
        System.out.println("Meow! I've removed this task: \n" + task + 
        "\n Now you have " + size + " tasks in the list.");
    }

    void printMark(Task task) {
        System.out.println("Meow! I've marked this task as done: \n" + task);
    }

    void printUnmark(Task task) {
        System.out.println("Meow! I've marked this task as not done yet: \n" + task);
    }

    void greet() {
        System.out.println("Meow! I'm YNCH. What can I do for you?");
    }
    
    void exit() {
        System.out.println("Bye. Hope to see you again soon meow!");
    }

}
