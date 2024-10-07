package lib;

public class ActiveRecord {

    public DbDriverInterface dbDriver;

    public ActiveRecord(DbDriverInterface dbDriver) {
        this.dbDriver = dbDriver;
    }

}
