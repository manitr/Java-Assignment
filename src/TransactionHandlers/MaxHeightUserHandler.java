package TransactionHandlers;

import Databases.Database;
import Exceptions.EmptyNameFieldException;
import Exceptions.IllegalParametersListException;
import Exceptions.InvalidHeightException;
import javax.naming.OperationNotSupportedException;

public class MaxHeightUserHandler extends Database<String, Integer> {
    int userHeight;
    String userName;
    @Override
    public synchronized boolean put(String key, Integer ... value) throws InvalidHeightException, EmptyNameFieldException, IllegalParametersListException {
        if(key.isEmpty()) {
            throw new EmptyNameFieldException();
        }
        if(value[0] < 0) {
            throw new InvalidHeightException();
        }
        if(value.length != 1) {
            throw new IllegalParametersListException();
        }
        if(userHeight < value[0]) {
            userName = key;
            userHeight = value[0];
        }
        return true;
    }


    @Override
    public String get(String key) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public synchronized String getTallestUser() {
        return userName + " : " + userHeight;
    }

}
