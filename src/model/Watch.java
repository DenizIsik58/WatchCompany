package model;


public class Watch {

    private int id;
    private final String name;
    private final int buyPrice;
    private final int cargo;
    private final int tariff;
    private int sellsPrice;

    public Watch(String name, int buyPrice, int cargo, int tariff) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.cargo = cargo;
        this.tariff = tariff;
        this.sellsPrice = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getCargo() {
        return cargo;
    }

    public int getTariff() {
        return tariff;
    }

    public int getSellsPrice() {
        return sellsPrice;
    }

    public void setSellsPrice(int sellsPrice) {
        this.sellsPrice = sellsPrice;
    }

    public int getBuyPriceTotal(){
        return buyPrice + cargo + tariff;
    }

    public String toString(){
        return "ID: " + id + " Name: " + name + ", BuyPrice: " +  (buyPrice + cargo + tariff);
    }
}
