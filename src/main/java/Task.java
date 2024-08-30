import java.time.LocalDateTime;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    @Override
    public String toString(){ return this.description; }
    public String getDescription(){ return this.description; }
    public char getTaskType(){ return this.getClass().toString().charAt(6); }
    public String listedString(){ return this.classFirstChar() + this.getStatusIcon() + " " + this; }
    public String classFirstChar() {return "[" + this.getTaskType() + "]";}
    public boolean isDone() {return this.isDone;}
    public void setUndone(){ this.isDone = false;}
    public void setDone(){ this.isDone = true;}

    protected String localDateTimeToString(LocalDateTime localDateTime){
        return localDateTime.getDayOfMonth() + " " + localDateTime.getMonth() + " " + localDateTime.getYear() + " " +
                padTimeLeft(localDateTime.getHour())+ padTimeRight(localDateTime.getMinute()) + " Hours (" +
                localDateTime.getDayOfWeek() + " " +
                (localDateTime.getHour() > 12 ? padTimeLeft(localDateTime.getHour() - 12) : localDateTime.getHour()) + ":" +
                padTimeRight(localDateTime.getMinute()) +
                (localDateTime.getHour() > 12 ? "pm)" : "am)");
    }

    private String padTimeLeft(int hour) {
        return String.format("%2s", hour).replace(' ', '0');
    }

    private String padTimeRight(int minute){
        return String.format("%-2s", minute).replace(' ', '0');
    }
}