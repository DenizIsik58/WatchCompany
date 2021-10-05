package model;


import java.util.ArrayList;
import java.util.List;

public class WatchManager {

    private List<Watch> watches = new ArrayList<>();
    private BankAccount bankAccount;

    public WatchManager(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void addWatch(Watch watch) {
        watches.add(watch);
    }

    public boolean buyWatch(Watch watch)  {
        if (bankAccount.getBalance() < watch.getBuyPriceTotal()){
            System.out.println("*** NO SUFFICIENT BALANCE ***");
            return false;
        }
        bankAccount.setBalance(-watch.getBuyPriceTotal());
        watch.setId(watches.size() + 1);
        return true;

    }

    public void sellWatch(Watch watch, int amount) {
        watch.setSellsPrice(amount);
        bankAccount.setBalance(watch.getSellsPrice());
    }

    public Watch findWayById(int id){
        for (int i = 0; i < watches.size(); i++) {
            if (watches.get(i).getId() == id){
                return watches.get(i);
            }
        }
        return null;
    }

    public List<Watch> watches(){
        return watches;
    }

    public void printWatches(){
        for (int i = 0; i < watches.size(); i++) {
            System.out.println(watches.get(i).toString());
        }
        System.out.println("----------------------------");
    }

    public BankAccount getBankAccount(){
        return bankAccount;
    }



}
