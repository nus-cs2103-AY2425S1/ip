public class EventBuilder extends TaskBuilder {

    @Override
    public Task build(String input) throws LukeException {
        int firstSlashIndex = input.indexOf(" /from ");
        if (firstSlashIndex == -1) {
            if (input.contains("/from")) {
                throw new LukeException("There needs to be spacing between /from and other words.");
            } else {
                throw new LukeException("Missing /from to indicate the start time of the event.");
            }
        }
        int secondSlashIndex = input.indexOf(" /to ");
        if (secondSlashIndex == -1) {
            if (input.contains("/to")) {
                throw new LukeException("There needs to be spacing between /to and other words.");
            } else {
                throw new LukeException("Missing /to to indicate the end time of the event.");
            }
        }
        String description = input.substring(0, firstSlashIndex).trim();
        String from = input.substring(firstSlashIndex + 6, secondSlashIndex).trim();
        String to = input.substring(secondSlashIndex + 4).trim();
        return new Event(description, from, to);
    }
}
