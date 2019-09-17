import Exceptions.*;
import TransactionHandlers.UserBalanceHandler;
import TransactionHandlers.MaxHeightUserHandler;
import javax.naming.OperationNotSupportedException;

public class RequestHandler implements Runnable {
    UserBalanceHandler userBalanceStore;
    MaxHeightUserHandler MaxHeightUserStore;
    Thread thread;
    private int option;

    RequestHandler(UserBalanceHandler store) {
        userBalanceStore = store;
        option = getOption();
        thread = new Thread(this);
        thread.start();
    }
    RequestHandler(MaxHeightUserHandler store) {
        MaxHeightUserStore = store;
        option = getOption();
        thread = new Thread(this);
        thread.start();
    }


    RequestHandler(UserBalanceHandler store1, MaxHeightUserHandler store2) {
        userBalanceStore = store1;
        MaxHeightUserStore = store2;
        option = getOption();
        thread = new Thread(this);
        thread.start();
    }

    private int getOption() {
        if(this.userBalanceStore != null && this.MaxHeightUserStore == null) {
            return 1;
        }
        if(this.userBalanceStore == null && this.MaxHeightUserStore != null) {
            return 2;
        }
        return 3;
    }

    @Override
    public void run() {
        switch (option) {
            case 1:
                try {
                    userBalanceStore.put("shubham", 5);
                    userBalanceStore.incrementBy("shubham", 5);
                    userBalanceStore.incrementBy("manit", 13);
                    System.out.println(userBalanceStore.decrementBy("manit", 2));
                    System.out.println(userBalanceStore.delete("shubham"));
                    System.out.println(userBalanceStore.get("shubham"));
//                    dataStore1.get("karan");
                }
                catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalIncrementValueException e) {
                    e.printStackTrace();
                }
                catch (IllegalDecrementValueException e) {
                    e.printStackTrace();
                }
                catch (NegativeInitialBalanceException e) {
                    e.printStackTrace();
                }
                catch (InsufficientBalanceException e) {
                    e.printStackTrace();
                }
                catch (IllegalParametersListException e) {
                    e.printStackTrace();
                }
                catch (EmptyNameFieldException e) {
                    e.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    MaxHeightUserStore.put("Rohit", 131);
                    System.out.println(MaxHeightUserStore.getTallestUser());
                    MaxHeightUserStore.put(new String("kunal"), 179);
                    System.out.println(MaxHeightUserStore.getTallestUser());
                    MaxHeightUserStore.put("Sahil", Integer.valueOf("178"));
                    MaxHeightUserStore.put("Yash", 181);
                    System.out.println(MaxHeightUserStore.getTallestUser());
                    MaxHeightUserStore.get("Sahil");
                }
                catch (InvalidHeightException e) {
                    e.printStackTrace();
                }
                catch (OperationNotSupportedException e) {
                    e.printStackTrace();
                }
                catch (EmptyNameFieldException e) {
                    e.printStackTrace();
                }
                catch (IllegalParametersListException e) {
                    e.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    userBalanceStore.put("Karan", 5);
                    userBalanceStore.decrementBy("Karan", 1);
                    userBalanceStore.incrementBy("Nitish", 13);
                    MaxHeightUserStore.put("Rohit", 141);
                    System.out.println(MaxHeightUserStore.getTallestUser());
                    MaxHeightUserStore.put("Yash", 181);
                    userBalanceStore.put("Noor");
                    MaxHeightUserStore.put("Sahil", 178);
                    System.out.println(MaxHeightUserStore.getTallestUser());
                }
                catch (InvalidHeightException e) {
                    e.printStackTrace();
                }
                catch (EmptyNameFieldException e) {
                    e.printStackTrace();
                }
                catch (IllegalDecrementValueException e) {
                    e.printStackTrace();
                }
                catch (IllegalIncrementValueException e) {
                    e.printStackTrace();
                }
                catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
                catch (NegativeInitialBalanceException e) {
                    e.printStackTrace();
                }
                catch (InsufficientBalanceException e) {
                    e.printStackTrace();
                }
                catch (IllegalParametersListException e) {
                    e.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default: {
                try {
                    throw new OperationNotSupportedException();
                }
                catch (OperationNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        UserBalanceHandler handler1 = new UserBalanceHandler("HashMap");
        MaxHeightUserHandler handler2 = new MaxHeightUserHandler();
        UserBalanceHandler handler3 = new UserBalanceHandler("HashMap");
        MaxHeightUserHandler handler4 = new MaxHeightUserHandler();
        RequestHandler req1 = new RequestHandler(handler1);
        RequestHandler req2 = new RequestHandler(handler2);
        RequestHandler req3 = new RequestHandler(handler3, handler4);
    }
}
