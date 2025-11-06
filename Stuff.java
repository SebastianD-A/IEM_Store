package IEM_Store;

public class Stuff {
    
}

//enums
enum Drivers{
    DYNAMIC,
    DUAL_DYNAMIC,
    PLANAR,
    BA,
    BA_DD_HYBRID,
    TRIBID,
    QUADBRID,
}
//interfaces
interface Sell{
    double applyDiscount(double percentage);
    void displayInfo();
}
//classes
class Product implements Sell{
    public double applyDiscount(double percentage){
        return 0;
    }

    public void displayInfo(){
        
    }
}

class InEarMonitor extends Product{

}

class Accessory extends Product{

}

class Customer{

}

class Order{

}

class Store{

}
