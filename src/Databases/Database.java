package Databases;

import Exceptions.EmptyNameFieldException;
import Exceptions.InvalidHeightException;
import Exceptions.NegativeInitialBalanceException;
import Exceptions.UserNotFoundException;
import javax.naming.OperationNotSupportedException;
import java.util.*;

public abstract class Database<K,V> {
    protected abstract boolean put(K key) throws InvalidHeightException, EmptyNameFieldException, NegativeInitialBalanceException;

    protected abstract boolean put(K key, V value) throws InvalidHeightException, EmptyNameFieldException, NegativeInitialBalanceException;

    protected abstract Object get(K key) throws UserNotFoundException, OperationNotSupportedException;

    protected Object getDatabase(String collectionName) {
        switch (collectionName) {
            case "HashMap": return new HashMap<K,V>();

            case "HashSet": return new HashSet<K>();

            case "ArrayList": return new ArrayList<K>();

            case "LinkedList": return new LinkedList<K>();

            case "PriorityQueue": return new PriorityQueue<K>();

            default:
                System.out.println("Database not found.");
        }
        return null;
    }
}


