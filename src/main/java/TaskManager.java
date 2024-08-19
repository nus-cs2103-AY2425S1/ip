public class TaskManager {
    private final TaskStorage taskStorage;

    public TaskManager() {
        this.taskStorage = new TaskStorage();
    }

    public void manage(String currAction) {
        if (currAction.equals("bye")) {
            new Leave().speak();
        } else if (currAction.equals("list")) {
            this.taskStorage.speak();
        } else {
            String[] segments = currAction.split(" ");
            if(segments[0].equals("mark")) {
                if (segments.length != 2) {
                    throw new MendelException("OOPS! No serial given.\nSpecify serial.");
                }
                taskStorage.marker(Integer.parseInt(segments[1]) - 1);
            } else if(segments[0].equals("unmark")) {
                if (segments.length != 2) {
                    throw new MendelException("OOPS! No serial given.\nSpecify serial.");
                }
                taskStorage.unMarker(Integer.parseInt(segments[1]) - 1);
            } else {
                taskStorage.add(currAction);
            }
        }
    }
}
