package IEM_Store;
import java.util.*;

public class Stuff {
    
}

//enums
enum Driver{
    DYNAMIC,
    DUAL_DYNAMIC,
    PLANAR,
    BA,
    BA_DD_HYBRID,
    TRIBID,
    QUADBRID,
}
enum SoundSignature{
    NEUTRAL,
    BASSY,
    BRIGHT,
    BRIGHT_NEUTRAL,
    V_SHAPE,
    WARM_NEUTRAL,
}
enum ShippingStatus{
    PENDING, 
    SHIPPED, 
    DELIVERED, 
    CANCELLED,
}

enum CustomerRank{
    NONE(0),
    BASIC(10),
    PREMIUM(15),
    AUDIOPHILE(20);

    double percentage;

    CustomerRank(double discount){
        this.percentage=discount;
    }

    public double getPercentage(){
        return this.percentage;
    }
}

//interfaces
interface Sellable{
    double applyDiscount(double percentage, CustomerRank rank);
    double getPrice();
}

//classes
abstract class Product implements Sellable{
    protected double price;
    protected String name;
    protected String brand;

    Product(String name, double price, String brand){
        this.price=price;
        this.name=name;
        this.brand=brand;
    }
    @Override
    public double applyDiscount(double percentage, CustomerRank rank){
        double discount = percentage+rank.getPercentage();

        if (percentage<0 || percentage>100){
            System.out.println("Discount percentage must be between 0 and 100");
            return this.price;
        }

        if (discount > 100){
            discount=100;
        }

        return this.price - (this.price * (discount) / 100);
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public String toString(){
        return "Name: " + this.name + "\nPrice: " + this.price + "\nBrand: " + this.brand ;
    }
}

class InEarMonitor extends Product{
    private Driver[] drivers;
    private SoundSignature soundSignature;

    InEarMonitor(String name, double price, Driver[] drivers, String brand, SoundSignature soundSignature){
        super(name, price, brand);
        this.drivers = drivers;
        this.soundSignature=soundSignature;
    }

    @Override
    public double applyDiscount(double percentage, CustomerRank rank) {
        return super.applyDiscount(percentage, rank);
    }
    String getDrivers(){
        String listOfDrivers ="";
        for (Driver driver : drivers){
            listOfDrivers+=driver;
            listOfDrivers+=", ";
        }
        return listOfDrivers;
    }
    @Override
    public String toString(){
        return super.toString() + "Sound Signatures: " + this.soundSignature + "Drivers: "+ getDrivers();
    }
}
//think of this later, idk what second product can be
// class DigitalAudioConverter extends Product{
//     int 
// }

class Customer{
    String name;
    CustomerRank rank;
    ArrayList<Order> orders;
}

class Order{
    Product[] cart;
    
}

class Store{

}
