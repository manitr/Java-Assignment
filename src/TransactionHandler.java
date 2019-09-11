import java.util.HashMap;

public class TransactionHandler {

    HashMap<String , Integer> database = new HashMap<String , Integer>();

    synchronized int incrementBy(String key, int value) {
        System.out.println(Thread.currentThread().getName() + database);
        int temp = value;
        if (database.containsKey(key)) {
            temp += database.get(key);
            database.put(key, temp);
        } else {
            database.put(key, temp);
        }
        return database.get(key);
    }

    public static void main(String[] args) {
        TransactionHandler trans = new TransactionHandler();
        RequestHandler1 rh1 = new RequestHandler1(trans);
        RequestHandler2 rh2 = new RequestHandler2(trans);
        RequestHandler3 rh3 = new RequestHandler3(trans);
        //delay
        int a = 1, MOD = 999999999;
        for(int i = 0; i < 999999999; i++){
            a = a%MOD;
        }
        System.out.println("database " + trans.database);
    }
}

class RequestHandler1 implements Runnable{
    TransactionHandler Store;
    Thread t;
    RequestHandler1(TransactionHandler store) {
        Store = store;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        Store.incrementBy("shubham", 5);
        Store.incrementBy("manit", 13);
        Store.incrementBy("karan", 21);
        Store.incrementBy("shubham", 2);
    }
}

class RequestHandler2 implements Runnable{
    TransactionHandler Store;
    Thread t;
    RequestHandler2(TransactionHandler store) {
        Store = store;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        Store.incrementBy("nitish", 13);
        Store.incrementBy("manit", 24);
        Store.incrementBy("harsh", 2);
        Store.incrementBy("noor", 6);
        Store.incrementBy("karan", 15);
    }
}

class RequestHandler3 implements Runnable{
    TransactionHandler Store;
    Thread t;
    RequestHandler3(TransactionHandler store) {
        Store = store;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        Store.incrementBy("shubham", 9);
        Store.incrementBy("noor", 18);
        Store.incrementBy("nitish", 5);
        Store.incrementBy("harsh", 16);
    }
}
