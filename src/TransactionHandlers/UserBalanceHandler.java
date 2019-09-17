package TransactionHandlers;

import Databases.Database;
import Exceptions.IllegalDecrementValueException;
import Exceptions.IllegalIncrementValueException;
import Exceptions.NegativeInitialBalanceException;
import Exceptions.UserNotFoundException;
import java.util.HashMap;

public class UserBalanceHandler extends Database<String, Integer> {
    HashMap<String, Integer> userData;

    public UserBalanceHandler(String collectionName) {
        userData = (HashMap<String, Integer>) getDatabase(collectionName);
    }

    @Override
    public synchronized Integer get(String key) throws UserNotFoundException {
        if(!userData.containsKey(key))
            throw new UserNotFoundException();
        return userData.get(key);
    }

    @Override
    public synchronized boolean put(String key) throws NegativeInitialBalanceException {
        return put(key, 0);
    }

    @Override
    public synchronized boolean put(String key, Integer value) throws NegativeInitialBalanceException {
        if(userData.containsKey(key)) {
            return false;
        }

        if(value < 0) {
            throw new NegativeInitialBalanceException();
        }
        userData.put(key, value);
        return true;
    }

    public synchronized int incrementBy(String key, int value) throws IllegalIncrementValueException {
        System.out.println(Thread.currentThread().getName() + userData);
        if(value < 0) {
            throw new IllegalIncrementValueException();
        }
        int credit = value;
        if (userData.containsKey(key)) {
            credit += userData.get(key);
            userData.put(key, credit);
            return credit;
        }
        userData.put(key, credit);
        return userData.get(key);
    }

    public synchronized int decrementBy(String key, int value) throws IllegalDecrementValueException, UserNotFoundException {
        System.out.println(Thread.currentThread().getName() + userData);

        if(value < 0) {
            throw new IllegalDecrementValueException();
        }
        if (!userData.containsKey(key)) {
            throw new UserNotFoundException();
        }
        int debit = value;
        debit = userData.get(key) - debit;
        userData.put(key, debit);
        return debit;
    }

    public synchronized boolean delete(String key) {
        if(!userData.containsKey(key)) {
            return false;
        }
        userData.remove(key);
        return true;
    }
}