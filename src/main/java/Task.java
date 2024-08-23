public class Task {

    private boolean marked;
    private String taskRep;

    protected Task(String taskRep) {
        this.marked = false;
        this.taskRep = taskRep;
    }

    public static Task of(String task) {
        if (task.startsWith("todo")) {
            return new Todo(task.substring(5, task.length()));
        } else if (task.startsWith("deadline")) {
            for (int i = 0; i < task.length(); i++) {
                if (task.charAt(i) == '/') {
                    return new Deadline(task.substring(9, i - 1), task.substring(i + 1, task.length()));
                }
            }

            return null;
        } else if (task.startsWith("event")) {
            for (int j = task.length() - 1; j > 5; j--) {
                if (task.charAt(j) != '/') {
                    continue;
                }
                for (int i = 0; i < task.length(); i++) {
                    if (task.charAt(i) == '/') {
                        return new Event(task.substring(6, i - 1), task.substring(i + 1, j - 1),
                                task.substring(j + 1, task.length()));
                    }
                }
            }
            return null;
//            for (int i = 0; i < task.length(); i++) {
//                if (task.charAt(i) == '/') {
//                    taskString = task.substring(5, i - 1);
//                    taskStart = task.substring(i + 1, task.length() - 1);
//                    break;
//                }
//            }
//
//            for (int j = task.length() - 1; j > 5; j--) {
//                if (task.charAt(j) == '/') {
//                    return new Event(taskString, taskStart, task.substring(j + 1, task.length() - 1));
//                }
//            }
        } else {
            System.out.println("outside");
            return null;
        }
    }
    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + taskRep;
        } else {
            return "[ ] " + taskRep;
        }
    }


}
