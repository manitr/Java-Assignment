package TransactionHandlers;

import Databases.Database;
import Exceptions.InvalidHeightException;
import javax.naming.OperationNotSupportedException;

public class TransactionHandler2 extends Database<String, Integer> {
    static final int DEFAULT_HEIGHT = 5;
    int userHeight;
    String userName;
    @Override
    public boolean put(String key, Integer value) throws InvalidHeightException {
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
    public boolean put(String key) throws InvalidHeightException {
        return put(key, DEFAULT_HEIGHT);
    }

    @Override
    public String get(String key) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public String getTallestUser() {
        return userName;
    }

}
