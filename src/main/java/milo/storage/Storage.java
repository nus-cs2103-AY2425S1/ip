package milo.storage;

import java.util.ArrayList;

import milo.lists.List;


/**
 * Represents the Milo's storage system
 * including fields such as filePath and task list
 */
public abstract class Storage<T> {
    public abstract ArrayList<T> readData();

}
