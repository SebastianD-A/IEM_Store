package IEM_Store;
import java.util.*;
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
    double getPrice();
}

interface Discountable{
    double applyDiscount(double percentage);
}

//classes
abstract class Product implements Sellable{
    protected double price;
    protected String name;
    protected String brand;

    //compare
    static final Comparator<Product> priceComparator = Comparator.comparing(Product::getPrice);
    static final Comparator<Product> brandComparator = Comparator.comparing(Product::getBrand);

    Product(String name, double price, String brand){
        this.price=price;
        this.name=name;
        this.brand=brand;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public String getBrand(){
        return this.brand;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return "Name: " + this.name + 
        "\nPrice: " + this.price + 
        "\nBrand: " + this.brand ;
    }
}

class InEarMonitor extends Product{
    private Driver[] drivers;
    private SoundSignature soundSignature;

    //comparators
    static final Comparator<InEarMonitor> soundSignatureComparator = Comparator.comparing(InEarMonitor::getSound);

    InEarMonitor(String name, double price, Driver[] drivers, String brand, SoundSignature soundSignature){
        super(name, price, brand);
        this.drivers = drivers;
        this.soundSignature=soundSignature;
    }

    String getDrivers(){
        String listOfDrivers ="";
        for (Driver driver : drivers){
            listOfDrivers+=driver;
            listOfDrivers+=", ";
        }
        return listOfDrivers;
        }

    SoundSignature getSound(){
        return this.soundSignature;
    }

    @Override
    public String toString(){
        return super.toString() + 
        "\nSound Signatures: " + 
        this.soundSignature + 
        "Drivers: "+ getDrivers();
    }
}

class CarryBag extends Product{
    private double length, width, height;

    //compare
    static final Comparator<CarryBag> volumeComparator = Comparator.comparing(CarryBag::getVolume);

    CarryBag(String name, double price, String brand, double length, double width, double height){
        super(name, price, brand);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getVolume(){
        return this.length * this.width * this.height;
    }

    @Override
    public String toString(){
    return super.toString() +
           "\nLength: " + this.length + '\n' +
           "Width: " + this.width + '\n' +
           "Height: " + this.height + '\n' +
           "Volume: " + getVolume();
    }
}

class Customer{
    private String name;

    private CustomerRank rank;

    ArrayList<Order> orders = new ArrayList<>();

    Customer(String name, CustomerRank rank){
        this.name=name;
        this.rank=rank;
    }

    public void addOrder(Order newOrder){
        orders.add(newOrder);
    }

    public void removeOrder(int index) {
        if (orders.isEmpty()) {
            System.out.println("No orders to remove.");
            return;
        }

        if (index < 0 || index >= orders.size()) {
            System.out.println("Invalid order index.");
            return;
        }

        Order removed = orders.remove(index);

        System.out.println("Removed Order #" + removed.getOrderID() + " for " + name);
    }

    public CustomerRank getRank(){
        return this.rank;
    }

    public String getName(){
        return this.name;
    }

    public void printOrders() {
        if (orders.isEmpty()) {
            System.out.println(name + " got no orders");
            return;
        }
        for (Order o : orders) {
            System.out.println("Order #" + o.getOrderID() + " | Status: " + o.getStatus());
        }
    }
    
    @Override
    public String toString(){
        return "Customer: " + name +
                "\nRank: " + rank +
                "\nOrders: " + orders.size();
    }

}

class Order implements Discountable{
    private static int lastID = 0;
    private final int orderID;

    private Customer cust;

    private ArrayList<Product> cart = new ArrayList<>();

    private ShippingStatus status = ShippingStatus.PENDING;

    Order(Customer cust){
        this.cust = cust;
        this.orderID = lastID++;
    }

    public void addProduct(Product newProduct){
        cart.add(newProduct);
    }

    public void removeProduct(int productIndex) {
        if (cart.isEmpty()) {
            System.out.println("Theres nothing to remove, the cart is empty");
            return;
        }

        if (productIndex < 0 || productIndex >= cart.size()) {
            System.out.println("Invalid index, select a valid item index");
            return;
        }
        Product removed = cart.get(productIndex);
        System.out.println("Removed: " + removed.getName() + " from the cart");
        cart.remove(removed);
    }

    public void clearCart(){
        cart.clear();
    }

    public String getCart(){
        String listOfItems="";

        for (Product a:cart){
            listOfItems += a.getName() + ", ";
        }
        return listOfItems;
    }

    public void printCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Items in cart:");
        for (int i = 0; i < cart.size(); i++) {
            System.out.println((i + 1) + ". " + cart.get(i));
        }
    }

    public void setStatus(ShippingStatus newStatus){
        status = newStatus;
    }

    public int getOrderID(){
        return this.orderID;
    }

    public ShippingStatus getStatus(){
        return this.status;
    }

    public double getTotal(){
        double total = 0;
        for (Product p : cart){
            total += p.getPrice();
        }
        return total;
    }

    @Override
    public double applyDiscount(double percentage) {
        double total = 0;

        for (Product p : cart) {
            total += p.getPrice();
        }

        double totalDiscount = percentage + cust.getRank().getPercentage();
        if (totalDiscount > 100) {
            totalDiscount = 100;
        }
        if (totalDiscount < 0) {
            totalDiscount = 0;
        }

        return total - (total * totalDiscount / 100);
    }

    public double applyDiscount(int setAmount, CustomerRank rank){
        double total = 0;

        for (Product p : cart) {
            total += p.getPrice();
        }

        double discount = setAmount + (total * rank.getPercentage() / 100);
        total -= discount;

        if (total<0){
            total = 0;
        }
        
        return total;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderID +
               "\nCustomer: " + cust.getName() +
               "\nStatus: " + status +
               "\nItems: " + cart.size() +
               "\nTotal: $" + getTotal();
    }
}

class Store{
    HashMap<Product, Integer> stock = new HashMap<>();

    public void addproduct(Product newProduct, int quantity){
        if (quantity <=0){
            System.out.println("Stock must be atleast 1");
            return ;
        }

        stock.put(newProduct, quantity);
        System.out.println("Product added: " + newProduct.getName() + " | Quantity: " + quantity);
    }

    public Product getProductFromName(String name){
        for (Product a: stock.keySet()){
            if (a.getName().equalsIgnoreCase(name)){
                return a;
            }
        }

        return null;
    }

    public void removeProduct(String name){
        Product remove = getProductFromName(name);
        if (remove == null){
            System.out.println("Product not found");
            return;
        }

        System.out.println(remove.getName() + " has been removed");
        stock.remove(remove);    
    }

    public void sellProduct(String name, int quantity){
        Product sold = getProductFromName(name);
        int newQuantity = stock.get(sold) - quantity;

        stock.put(sold, newQuantity);
    }


}
