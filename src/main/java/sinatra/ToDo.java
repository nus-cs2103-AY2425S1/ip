package sinatra;

public class ToDo extends Task {


    public static ToDo newObjectFromData(String data) {
        String[] parts = data.split(",");
        return new ToDo(parts[0], Boolean.parseBoolean(parts[1]));


    }

    public ToDo(String content, Boolean status) {
        super(content, status);


    }

    public String getDataForStorage() {
        return "Sinatra.ToDo:" + super.getContent() + "," + super.getStatusString();
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
