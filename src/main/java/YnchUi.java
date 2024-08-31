/**
 * Outputs pre-set chatbot lines.
 *
 */
class YnchUi {
    String name;

    YnchUi() {
        this.name = "YNCH";
    }

    String printAdd(Task task, int size) {
        return "Meow! I've added this task: \n" + task + 
            "\n Now you have " + size + " tasks in the list.";
    }

    String printDelete(Task task, int size) {
        return "Meow! I've removed this task: \n" + task + 
            "\n Now you have " + size + " tasks in the list.";
    }

    String printMark(Task task) {
        return "Meow! I've marked this task as done: \n" + task;
    }

    String printUnmark(Task task) {
        return "Meow! I've marked this task as not done yet: \n" + task;
    }

    String greet() {
        return "Meow! I'm YNCH. What can I do for you?";
    }
    
    String exit() {
        return "Bye. Hope to see you again soon meow!";
    }

}
