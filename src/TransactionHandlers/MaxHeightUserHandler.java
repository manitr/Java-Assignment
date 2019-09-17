package TransactionHandlers;

import Databases.Database;
import Exceptions.EmptyNameFieldException;
import Exceptions.InvalidHeightException;
import javax.naming.OperationNotSupportedException;

public class MaxHeightUserHandler extends Database<String, Integer> {
    static final int DEFAULT_HEIGHT = 5;
    int userHeight;
    String userName;
    @Override
    public synchronized boolean put(String key, Integer value) throws InvalidHeightException, EmptyNameFieldException {
        if(key.isEmpty()){
            throw new EmptyNameFieldException();
        }
        if(value < 0) {
            throw new InvalidHeightException();
        }
        if(userName != null) {
            if(userHeight < value) {
                userName = key;
                userHeight = value;
            }
        }
        else {
            userName = key;
            userHeight = value;
        }
        return true;
    }

    @Override
    public synchronized boolean put(String key) throws InvalidHeightException, EmptyNameFieldException {
        return put(key, DEFAULT_HEIGHT);
    }

    @Override
    public String get(String key) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public synchronized String getTallestUser() {
        return userName + " : " + userHeight;
    }

}
