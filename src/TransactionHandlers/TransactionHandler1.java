package TransactionHandlers;

import Databases.Database;
import Exceptions.UserNotFoundException;
import java.util.HashMap;

public class TransactionHandler1 extends Database<String, Integer> {
    HashMap<String, Integer> userData;

    public TransactionHandler1(String collectionName) {
        userData = (HashMap<String, Integer>) getDatabase(collectionName);
    }

    @Override
    public boolean put(String key, Integer value) {
        if(userData.containsKey(key)){
            return false;
        }
        userData.put(key, value);
        return true;
    }

    @Override
    public boolean put(String key) {
        if(userData.containsKey(key)){
            return false;
        }
        userData.put(key, 0);
        return true;
    }

    @Override
    public Integer get(String key) throws UserNotFoundException {
        if(!userData.containsKey(key))
            throw new UserNotFoundException();
        return userData.get(key);
    }

    public boolean delete(String key){
        if(!userData.containsKey(key)){
            return false;
        }
        userData.remove(key);
        return true;
    }

    public synchronized int incrementBy(String key, int value) {
        System.out.println(Thread.currentThread().getName() + userData);
        int sum = value;
        if (userData.containsKey(key)) {
            sum += userData.get(key);
            userData.put(key, sum);
        }
        else {
            userData.put(key, sum);
        }
        return userData.get(key);
    }
}