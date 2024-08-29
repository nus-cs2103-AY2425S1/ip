package mgtow.task;

import java.time.LocalDate;

public abstract class Task {
    private String desc;
    private String type;
    private boolean done;

    public Task(String desc, String type){
        this.desc = desc;
        this.type = type;
        this.done = false;
    }

    public void markDone(){
        this.done = true;
    }

    public void markNotDone(){
        this.done = false;
    }

    public String getStatus(){
        return (done ?  "X" : " ");
    }

    public String getType() {
        return this.type;
    }

    public String getDesc(){
        return this.desc;
    }

    public abstract boolean isOnDate(LocalDate date);

    @Override
    public String toString(){
        return String.format("[%s][%s] %s", this.type, this.getStatus(), this.desc);
    }
}
