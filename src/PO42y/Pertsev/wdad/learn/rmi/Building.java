package PO42y.Pertsev.wdad.learn.rmi;

/**
 * Created by Faust on 18.01.2017.
 */
public class Building {
    private String street;
    private int number;

    public Building(String street, int number){
        this.street = street;
        this.number = number;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getStreet(){
        return street;
    }

    public void getNumber(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }
}
