package TransactionHandlers;

import Databases.Database;
import Exceptions.*;

import javax.naming.InsufficientResourcesException;
import java.util.HashMap;

public class UserBalanceHandler extends Database<String, Integer> {
    HashMap<String, Integer> userData;
    static final int INITIAL_BALANCE = 0;

    public UserBalanceHandler(String collectionName) {
        userData = (HashMap<String, Integer>) getDatabase(collectionName);
    }

    @Override
    public synchronized Integer get(String key) throws UserNotFoundException {
        if(!userData.containsKey(key))
            throw new UserNotFoundException();
        return userData.get(key);
    }

    public synchronized boolean put(String key) throws NegativeInitialBalanceException, EmptyNameFieldException, IllegalParametersListException {
        return put(key, INITIAL_BALANCE);
    }

    @Override
    public synchronized boolean put(String key, Integer ... value) throws NegativeInitialBalanceException, EmptyNameFieldException, IllegalParametersListException {
        if(key.isEmpty()) {
            throw new EmptyNameFieldException();
        }
        if(value.length != 1) {
            throw new IllegalParametersListException();
        }
        if(userData.containsKey(key)) {
            return false;
        }

        if(value[0] < 0) {
            throw new NegativeInitialBalanceException();
        }
        userData.put(key, value[0]);
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
        }
        userData.put(key, credit);
        return credit;
    }

    public synchronized int decrementBy(String key, int value) throws IllegalDecrementValueException, UserNotFoundException, InsufficientBalanceException {
        System.out.println(Thread.currentThread().getName() + userData);
        if(value < 0) {
            throw new IllegalDecrementValueException();
        }
        if(value > userData.get(key)) {
            throw new InsufficientBalanceException();
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
