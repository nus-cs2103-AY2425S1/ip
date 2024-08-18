public class Task {
    private boolean completed;
    private String name;
    private static final String done = "[X]";
    private static final String undone = "[ ]";

    public Task(String name) {
        this.name = name;
    }

    public static Task of(String command, String item) {
        switch(command) {
            case "todo":
                return new Todo(item);
            case "deadline":
                {
                    int spaceLocation = item.indexOf(" /by ");
                    if (spaceLocation  == -1) {
                        return new Task(""); // will replace with error in future
                    }
                    String name = item.substring(0,spaceLocation);
                    String due = item.substring(spaceLocation + 5);
                    return new Deadline(name, due);
                }
            case "event":
                {
                    int fromLocation = item.indexOf(" /from ");
                    int toLocation = item.indexOf(" /to ");
                    String name, start, end;
                    if (fromLocation == -1 || toLocation == -1) {
                        return new Task(""); // will replace with error in future
                    }
                    if (fromLocation < toLocation) {
                        name = item.substring(0,fromLocation);
                        start = item.substring(fromLocation + 7, toLocation);
                        end = item.substring(toLocation + 5);
                    } else {
                        name = item.substring(0, toLocation);
                        end = item.substring(toLocation + 5, fromLocation);
                        start = item.substring(fromLocation + 7);
                    }
                    return new Event(name,start,end);
                }
            default:
                return new Task("");  // shouldn't happen
        }

    }

    public void mark() {
        if (completed) {
            System.out.println("Sumo confused. This task is marked as done in the first place!\n"
                            + "But SUMO will mark it as done again!"
                    );
        } else {
            System.out.println("Sumo has marked this task as done.");
            this.completed = true;
        }
        System.out.println(this.toString());
    }

    public void unmark() {
        if (!completed) {
            System.out.println("Sumo confused. This task is not completed in the first place!\n"
                            + "But SUMO will mark it as NOT done again!"
                    );
        } else {
            System.out.println("Sumo has marked this task as  NOT done.");
            this.completed = false;
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return (completed ? done : undone) + this.name;
    }
}
