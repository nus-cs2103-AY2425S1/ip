import java.time.LocalDateTime;

public abstract class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public static Task stringToTask(String str) throws BlitzException {
        String[] params = str.split("::");
        String type = params[0];

        switch (type) {
        case "T":
            if (params.length != 3) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Todo(params[2], "T", Boolean.parseBoolean(params[1]));
        case "D":
            if (params.length != 4) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Deadline(params[2], "D", stringToLocaldatetime(params[3]), Boolean.parseBoolean(params[1]));
        case "E":
            if (params.length != 5) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Event(params[2], "E", stringToLocaldatetime(params[3]), stringToLocaldatetime(params[4]), Boolean.parseBoolean(params[1]));
        default:
            throw new BlitzIOException("Failed to read from database");
        }
    }

    public static LocalDateTime stringToLocaldatetime(String str) {
        int year = Integer.parseInt(str.substring(0, 4));
        int month = Integer.parseInt(str.substring(5, 7));
        int day = Integer.parseInt(str.substring(8, 10));
        int hour = Integer.parseInt(str.substring(11, 13));
        int min = Integer.parseInt(str.substring(13, 15));

        return LocalDateTime.of(year, month, day, hour, min);
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public abstract String getType();

    public abstract String taskToString();

    @Override
    public String toString() {
        return this.desc;
    }
}
