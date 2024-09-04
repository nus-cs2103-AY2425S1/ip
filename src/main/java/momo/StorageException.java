package momo;

import momo.exception.MomoException;

/**
 * Handles storage related exceptions - when there are issues
 * writing into or interpreting files
 */
public class StorageException extends MomoException {

    public StorageException(String message) {
        super("Storage error:" + message);
    }
}
