public class Task {
    private boolean completed;
    private final String name;
    private static final String done = "[X]";
    private static final String undone = "[ ]";

    public Task(String name) {
        this.name = name;
    }

    public static Task createFromData(String inputFromFile) {
        String[] components = inputFromFile.split(" \\| ");
        Task returned = switch (components[0]) {
            case "T" -> new Todo(components[2]);
            case "E" -> new Event(components[2], components[3], components[4]);
            case "D" -> new Deadline(components[2], components[3]);
            default -> throw new IllegalArgumentException();
        };

        if (components[1].equals("1")) {
            returned.mark();
        }

        return returned;
    }

    public static Task of(Command command, String item) throws WrongSyntaxForCommandException, UnknownCommandException {
        switch(command) {
            case TODO:
                return new Todo(item);
            case DEADLINE:
                {
                    int spaceLocation = item.indexOf(" /by ");
                    if (spaceLocation  == -1) {
                        throw new WrongSyntaxForCommandException(command);
                    }
                    String name = item.substring(0,spaceLocation);
                    String due = item.substring(spaceLocation + 5);
                    return new Deadline(name, due);
                }
            case EVENT:
                {
                    int fromLocation = item.indexOf(" /from ");
                    int toLocation = item.indexOf(" /to ");
                    String name, start, end;
                    if (fromLocation == -1 || toLocation == -1) {
                        throw new WrongSyntaxForCommandException(command);
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
                throw new UnknownCommandException(command);  // shouldn't happen
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
        System.out.println(this);
    }

    public void unmark() {
        if (!completed) {
            System.out.println("Sumo confused. This task is not completed in the first place!\n"
                            + "But SUMO will mark it as NOT done again!"
                    );
        } else {
            System.out.println("Sumo has marked this task as NOT done.");
            this.completed = false;
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return (completed ? done : undone) + this.name;
    }
}
