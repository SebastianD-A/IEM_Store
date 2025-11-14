package IEM_Store;
import java.util.*;

public class Stuff{
    public static void main(String[] args){
        Store iemStore = new Store();

        InEarMonitor iem1 = new InEarMonitor("Moondrop Blessing 3", 319.99, new Driver[]{Driver.BA_DD_HYBRID, Driver.BA}, "Moondrop", SoundSignature.NEUTRAL);
        InEarMonitor iem2 = new InEarMonitor("TruthEar Hexa", 999.99, new Driver[]{Driver.TRIBID, Driver.BA_DD_HYBRID}, "TruthEar", SoundSignature.WARM_NEUTRAL);
        InEarMonitor iem3 = new InEarMonitor("7Hz Timeless", 219.99, new Driver[]{Driver.PLANAR, Driver.DYNAMIC}, "7Hz", SoundSignature.BRIGHT);
        InEarMonitor iem4 = new InEarMonitor("Moondrop Variations", 520.00, new Driver[]{Driver.TRIBID, Driver.BA_DD_HYBRID}, "Moondrop", SoundSignature.BRIGHT);
        InEarMonitor iem5 = new InEarMonitor("Letshuoer S12", 119.00, new Driver[]{Driver.PLANAR}, "Letshuoer", SoundSignature.NEUTRAL);
        InEarMonitor iem6 = new InEarMonitor("Kiwi Ears Quintet", 219.00, new Driver[]{Driver.TRIBID, Driver.BA, Driver.DYNAMIC}, "Kiwi Ears", SoundSignature.WARM_NEUTRAL);
        InEarMonitor iem7 = new InEarMonitor("Tanchjim Oxygen", 269.00, new Driver[]{Driver.DYNAMIC}, "Tanchjim", SoundSignature.BRIGHT);
        InEarMonitor iem8 = new InEarMonitor("Dunu SA6 MKII", 579.00, new Driver[]{Driver.BA}, "Dunu", SoundSignature.NEUTRAL);     
        
        CarryBag bag1 = new CarryBag("MoonDrop C2023", 59.99, "Moondrop", 10, 8, 5);
        CarryBag bag2 = new CarryBag("Tripowin Case", 19.99, "Tripowin", 7, 6, 3);
        CarryBag bag3 = new CarryBag("Dunu Pouch", 29.99, "Dunu", 8, 6, 4);
        CarryBag bag4 = new CarryBag("Moondrop Little Black Case", 24.99, "Moondrop", 8, 7, 4);
        CarryBag bag5 = new CarryBag("CCA Pro Case", 14.99, "CCA", 6, 5, 3);
        CarryBag bag6 = new CarryBag("Dunu Hard Shell XL", 39.99, "Dunu", 12, 10, 6);
        CarryBag bag7 = new CarryBag("FiiO Premium Case", 34.99, "FiiO", 9, 8, 5);
        CarryBag bag8 = new CarryBag("Tripowin Traveler", 22.99, "Tripowin", 7, 6, 4);

        iemStore.addproduct(iem1, 10);
        iemStore.addproduct(iem2, 5);
        iemStore.addproduct(iem3, 15);
        iemStore.addproduct(iem4, 8);
        iemStore.addproduct(iem5, 12);
        iemStore.addproduct(iem6, 10);
        iemStore.addproduct(iem7, 6);
        iemStore.addproduct(iem8, 4);

        iemStore.addproduct(bag1, 20);
        iemStore.addproduct(bag2, 30);
        iemStore.addproduct(bag3, 25);
        iemStore.addproduct(bag4, 40);
        iemStore.addproduct(bag5, 50);
        iemStore.addproduct(bag6, 15);
        iemStore.addproduct(bag7, 20);
        iemStore.addproduct(bag8, 35);
        int orderIDCounter = 0;

        System.out.println("Name: ");
        String custName = In.nextLine();

        System.out.println("What is your rank?");
        CustomerRank[] ranks = CustomerRank.values();

        for (int i = 0; i < ranks.length; i++){
            System.out.println((i + 1) + ". " + ranks[i]);
        }

        int user = In.nextInt();
        CustomerRank custRank = CustomerRank.values()[user - 1];

        Customer customer = new Customer(custName, custRank);
        Order currentOrder = new Order(customer, orderIDCounter);

        customer.addOrder(currentOrder);
        orderIDCounter++;

        System.out.println("Hello " + customer.getName() + ", Welcome into the IEM store");

        boolean running = true;

        while (running){
            System.out.println("Main menu (Order #" + currentOrder.getOrderID() + ")");
            System.out.println("""
1. Browse Product
2. Add item
3. Manage cart
4. Checkout
5. Profile details and Total Orders
6. Exit
7. Inventory (staff only)
Choice:""");
            user = In.nextInt();

            switch (user){
                case 1:
                    System.out.println("""
Browse Menu:
1. Show All products
2. Show All IEMs, sorted by sound signature
3. Filter IEMs by specific Sound Signature
4. Show All products (Sorted by Brand)
5. Filter Products by specific Brand
6. Show Carry Bags (Sorted by Volume)
7. back
Choice:""");
            
                    user=In.nextInt();
                    
                    switch (user){
                        case 1:
                            System.out.println("All Products:");

                            for (Product item : iemStore.getStock().keySet()){
                                System.out.println(item + "\nIn Stock: " + iemStore.getQuantity(item));
                                System.out.println();
                            }
                            break;
                        case 2:
                            ArrayList<InEarMonitor> iemsBroadSignature = iemStore.sortBySoundSignature();

                            for (InEarMonitor iem : iemsBroadSignature){
                                System.out.println(iem + "\nIn Stock: " + iemStore.getQuantity(iem));
                                System.out.println();
                            }
                            break;
                        case 3:
                            System.out.println("Which sound Signature");
                            
                            for (int i = 1; i < (SoundSignature.values()).length + 1; i++){
                                System.out.println(i + ". " + SoundSignature.values()[i-1]);
                            }
                            System.out.println("Choice:");
                            
                            user = In.nextInt();
                            ArrayList<InEarMonitor> iemsSpecificSignature = iemStore.sortBySoundSignature(SoundSignature.values()[user - 1]);

                            for (InEarMonitor iem : iemsSpecificSignature){
                                System.out.println(iem + "\nIn stock: " + iemStore.getQuantity(iem));
                                System.out.println();
                            }
                            break;
                        case 4:
                            ArrayList<Product> itemBroadBrand = iemStore.sortByBrand();

                            for (Product item : itemBroadBrand){
                                System.out.println(item + "\nIn stock: " + iemStore.getQuantity(item));
                                System.out.println();
                            }

                            break;
                        case 5:
                            ArrayList<String> brandList = new ArrayList<>();

                                for (Product item : iemStore.getStock().keySet()){
                                    String brand = item.getBrand();
                                    boolean exists = false;

                                    for (String b : brandList){
                                        if (b.equalsIgnoreCase(brand)){
                                            exists = true;
                                            break;
                                        }
                                    }
                                    if (!exists){
                                        brandList.add(brand);
                                    }
                                }

                                System.out.println("Select Brand:");
                                for (int i = 0; i < brandList.size(); i++){
                                    System.out.println((i + 1) + ". " + brandList.get(i));
                                }
                                System.out.println("Choice:");

                                user = In.nextInt();
                                String selectedBrand = brandList.get(user - 1);

                                ArrayList<Product> filteredProducts = iemStore.sortByBrand(selectedBrand);

                                for (Product item : filteredProducts){
                                    System.out.println(item + "\nStock: " + iemStore.getQuantity(item));
                                    System.out.println();
                                }

                                break;
                        case 6:
                            ArrayList<CarryBag> sortedBags = iemStore.sortByVolume();

                            for (CarryBag bag : sortedBags){
                                System.out.println(bag + "\nStock: " + iemStore.getQuantity(bag));
                                System.out.println();
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                    
                    case 2:
                        ArrayList<Product> products = new ArrayList<>(iemStore.getStock().keySet());

                        for (int i = 0; i < products.size(); i++){
                            Product item = products.get(i);
                            System.out.println((i + 1) + ". " + item.getName() + " | $" + item.getPrice() + " | Stock: " + iemStore.getQuantity(item));
                        }

                        System.out.print("Select item number: ");
                        user = In.nextInt();

                        if (user <= 0 || user > products.size()){
                            System.out.println("Invalid choice");
                            break;
                        }

                        Product selected = products.get(user - 1);

                        System.out.print("Quantity: ");
                        int quantitytoAdd = In.nextInt();

                        if (quantitytoAdd <= 0 || quantitytoAdd > iemStore.getQuantity(selected)){
                            System.out.println("Invalid quantity");
                            break;
                        }

                        currentOrder.addProduct(selected, quantitytoAdd);
                        iemStore.sellProduct(selected.getName(), quantitytoAdd);

                        System.out.println("Added " + quantitytoAdd + " of " + selected.getName());
                        break;

                    case 3:
                        System.out.println("""
Cart Menu:
1. View cart
2. Remove product
3. Clear cart
4. Go back
Choice:""");
                        user = In.nextInt();

                        switch (user){
                            case 1:
                                currentOrder.printCart();
                                break;
                            
                            case 2:
                                if (currentOrder.getCart().size() == 0){
                                    System.out.println("Cart is empty");
                                    break;
                                }

                                System.out.println("Which product to remove?");
                                currentOrder.printCart();

                                user =In.nextInt();

                                if (user <= 0 || (user - 1) >= currentOrder.getCart().size()){
                                    System.out.println("Invalid option");
                                    break;
                                }
                                int index = 1;
                                Product selectedProduct = null;

                                for (Map.Entry<Product, Integer> entry : currentOrder.getCart().entrySet()){
                                    if (index == user){
                                        selectedProduct = entry.getKey();
                                        break;
                                    }
                                    index++;
                                }

                                if (selectedProduct == null){
                                    System.out.println("Product not found");
                                    break;
                                }

                                System.out.println("Enter quantity to remove:");
                                user = In.nextInt();

                                if (user <= 0){
                                    System.out.println("Invalid quantity");
                                    break;
                                }

                                currentOrder.removeProduct(selectedProduct, user);
                                iemStore.addStock(selectedProduct.getName(), user);
                                break;

                            case 3:
                                currentOrder.clearCart();
                                break;
                            
                            default:
                                break;
                        }
                    break;

                    case 4:
                        if (currentOrder.getCart().size() == 0){
                            System.out.println("Your cart is empty");
                            break;
                        }
                        System.out.println("Cart:");
                        currentOrder.printCart();
                        System.out.println("Total before discount; " + currentOrder.getTotal());
                        
                        System.out.println("""
Discount Options:
1. Apply percentage discount
2. Apply set amount
3. Go back
Choice:""");
                        double totalAfterDiscount = 0;
                        user = In.nextInt();
                        
                        switch (user){
                            case 1:
                                System.out.println("How many % ?");
                                double percentageDisc = In.nextInt();
                                totalAfterDiscount = currentOrder.applyDiscount(percentageDisc);
                                break;
                            case 2:
                                System.out.println("How much $?");
                                int setAmountDisc = In.nextInt();
                                totalAfterDiscount = currentOrder.applyDiscount(setAmountDisc);
                                break;
                            default:
                            break;
                        }
                        
                        System.out.println("Total Price after discount: " + totalAfterDiscount);
                        System.out.println("New Order Created");

                        currentOrder.setStatus(ShippingStatus.SHIPPED);

                        currentOrder = new Order(customer, orderIDCounter);
                        customer.addOrder(currentOrder);
                        orderIDCounter++;
                    break;   
                case 5: 
                    System.out.println(customer);
                    customer.printOrders();
                    break;
                case 7:
                    System.out.println("""
Inventory Menu:
1. Add Stock
2. Remove Stock
3. Update Shipping Status
4. Back
Choice:""");
                    user = In.nextInt();

                    switch (user){

                        case 1:
                            ArrayList<Product> stockList1 = new ArrayList<>(iemStore.getStock().keySet());
                            for (int i = 0; i < stockList1.size(); i++){
                                Product item = stockList1.get(i);
                                System.out.println((i + 1) + ". " + item.getName() + "\nIn Stock: " + iemStore.getQuantity(item));
                                System.out.println();
                            }

                            System.out.print("Select product: ");
                            user = In.nextInt();
                            if (user <= 0 || user > stockList1.size()){
                                break;
                            }
                            Product addP = stockList1.get(user - 1);

                            System.out.print("Amount to add: ");
                            user = In.nextInt();
                            iemStore.addStock(addP.getName(), user);

                            System.out.println("Updated stock for " + addP.getName());
                            break;


                        case 2:
                            ArrayList<Product> stockList2 = new ArrayList<>(iemStore.getStock().keySet());
                            for (int i = 0; i < stockList2.size(); i++){
                                Product item = stockList2.get(i);
                                System.out.println((i + 1) + ". " + item.getName() + "\nIn Stock: " + iemStore.getQuantity(item));
                                System.out.println();
                            }

                            System.out.print("Select product: ");
                            user = In.nextInt();
                            if (user <= 0 || user > stockList2.size()){
                                break;
                            }
                            Product remP = stockList2.get(user - 1);

                            System.out.print("Amount to remove: ");
                            user = In.nextInt();
                            iemStore.sellProduct(remP.getName(), user);

                            System.out.println("Updated stock for " + remP.getName());
                            break;


                        case 3:
                            if (customer.getOrders().size() == 0){
                                System.out.println("No orders.");
                                break;
                            }

                            for (int i = 0; i < customer.getOrders().size(); i++){
                                Order o = customer.getOrders().get(i);
                                System.out.println(i + ": Order #" + o.getOrderID() + " | Status: " + o.getStatus());
                            }

                            System.out.print("Select order: ");
                            user = In.nextInt();
                            if (user < 0 || user >= customer.getOrders().size()) break;
                            int orderIdx = user;

                            ShippingStatus[] s = ShippingStatus.values();
                            for (int i = 0; i < s.length; i++){
                                System.out.println(i + ": " + s[i]);
                            }

                            System.out.print("Select new status: ");
                            user = In.nextInt();
                            if (user < 0 || user >= s.length) break;

                            customer.getOrders().get(orderIdx).setStatus(s[user]);
                            System.out.println("Status updated.");
                            break;

                        default:
                            break;
                    }
                    break;

                    default:
                    running = false;
            }
        }

    }
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
        this.price = price;
        this.name = name;
        this.brand = brand;
    }

    @Override
    public double getPrice(){
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

    //compare
    static final Comparator<InEarMonitor> soundSignatureComparator = Comparator.comparing(InEarMonitor::getSound);

    InEarMonitor(String name, double price, Driver[] drivers, String brand, SoundSignature soundSignature){
        super(name, price, brand);
        this.drivers = drivers;
        this.soundSignature=soundSignature;
    }
    //return a string for drivers, only used in toString
    String getDrivers(){
        String listOfDrivers = "";
        for (Driver driver : drivers){
            listOfDrivers += driver;
            listOfDrivers += ", ";
        }
        return listOfDrivers;
        }
    //get the sound signature of the IEM
    SoundSignature getSound(){
        return this.soundSignature;
    }

    @Override
    public String toString(){
        return super.toString() + 
        "\nSound Signatures: " + 
        this.soundSignature + 
        "\nDrivers: " + getDrivers();
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
    // get the volume of the bag, its a square shape so this the formula i think
    public double getVolume(){
        return this.length * this.width * this.height;
    }

    @Override
    public String toString(){
    return super.toString() + "\nLength: " + this.length + '\n' + "Width: " + this.width + '\n' + "Height: " + this.height + '\n' + "Volume: " + getVolume();
    }
}

class Customer{
    private String name;

    private CustomerRank rank;

    private ArrayList<Order> orders = new ArrayList<>();

    Customer(String name, CustomerRank rank){
        this.name=name;
        this.rank=rank;
    }
    //add an Order object to their orders, this acts as like an order history
    public void addOrder(Order newOrder){
        orders.add(newOrder);
    }
    public ArrayList<Order> getOrders(){
        return orders;
    }
    public CustomerRank getRank(){
        return this.rank;
    }

    public String getName(){
        return this.name;
    }
    //print out past orders and its status
    public void printOrders(){
        if (orders.size() == 0){
            System.out.println(name + " got no orders");
            return;
        }
        for (Order order : orders){
            System.out.println("Order #" + order.getOrderID() + " | Status: " + order.getStatus());
        }
    }
    
    @Override
    public String toString(){
        return "Customer: " + name + "\nRank: " + rank + "\nTotal number of Orders: " + orders.size();
    }

}

class Order implements Discountable{
    private final int orderID;

    private Customer cust;

    private LinkedHashMap<Product, Integer> cart = new LinkedHashMap<>();

    private ShippingStatus status = ShippingStatus.PENDING;

    Order(Customer cust, int orderID){
        this.cust = cust;
        this.orderID = orderID;
    }
    //adds the stock to the linked hashmap
    public void addProduct(Product newProduct, int quantity){
        if (quantity <= 0) return;

        if (cart.containsKey(newProduct)){
            int currentQty = cart.get(newProduct);
            cart.put(newProduct, currentQty + quantity);
        } else{
            cart.put(newProduct, quantity);
        }
    }
    //remove a product by a certain amount, like you want to buy 5 but in the cart you have 7, you remove 2
    public void removeProduct(Product product, int quantity){
        if (!cart.containsKey(product)){
            System.out.println("Product not in cart");
            return;
        }

        int currentQty = cart.get(product);

        if (quantity >= currentQty){
            cart.remove(product);
            System.out.println("Removed " + product.getName() + " completely from the cart");
        } else{
            cart.put(product, currentQty - quantity);
            System.out.println("Removed " + quantity + " of " + product.getName());
        }
    }

    public void clearCart(){
        cart.clear();
    }

    public LinkedHashMap<Product, Integer> getCart(){
        return cart;
    }
    //prints the content of the cart
    public void printCart(){
        if (cart.isEmpty()){
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Items in cart:");
        int index = 1;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            Product product = entry.getKey();
            int qty = entry.getValue();
            System.out.println(index + ". " + product.getName() + " | Quantity: " + qty + " | Price: $" + product.getPrice());
            index++;
        }
    }
    //set the status of the order, like pending, completeed, cancelled
    public void setStatus(ShippingStatus newStatus){
        status = newStatus;
    }

    public int getOrderID(){
        return this.orderID;
    }

    public ShippingStatus getStatus(){
        return this.status;
    }
    //get the total price without discount
    public double getTotal(){
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            Product product = entry.getKey();
            int qty = entry.getValue();
            total += product.getPrice() * qty;
        }
        return total;
    }
    //get the total price WITH discounts, with percentages like 25% off
    @Override
    public double applyDiscount(double percentage){
        double total = 0;

        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            total += entry.getKey().getPrice() * entry.getValue();
        }

        double totalDiscount = percentage + cust.getRank().getPercentage();
        if (totalDiscount > 100){
            totalDiscount = 100;
        }
        if (totalDiscount < 0){
            totalDiscount = 0;
        }

        return total - (total * totalDiscount / 100);
    }
    // get the total price with discounts, with a set amount tho, like $50 off strictly
    public double applyDiscount(int setAmount){
        double total = 0;

        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            total += entry.getKey().getPrice() * entry.getValue();
        }

        double discount = setAmount + (total * cust.getRank().getPercentage() / 100);
        total -= discount;

        if (total < 0){
            total = 0;
        }
        
        return total;
    }

    @Override
    public String toString(){
        return "Order ID: " + orderID + "\nCustomer: " + cust.getName() + "\nStatus: " + status + "\nItems: " + cart.size() + "\nTotal: $" + getTotal();
    }
}

class Store{
    private HashMap<Product, Integer> stock = new HashMap<>();

    public void addproduct(Product newProduct, int quantity){
        if (quantity <= 0){
            System.out.println("Stock must be atleast 1");
            return ;
        }

        stock.put(newProduct, stock.getOrDefault(newProduct, 0) + quantity);
        System.out.println("Product added: " + newProduct.getName() + " | Quantity: " + quantity);
    }

    // return the product by only its name
    public Product getProductFromName(String name){
        for (Product product : stock.keySet()){
            if (product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        return null;
    }

    //remove the product entirely
    public void removeProduct(String name){
        Product remove = getProductFromName(name);
        if (remove == null){
            System.out.println("Product not found");
            return;
        }

        System.out.println(remove.getName() + " has been removed");
        stock.remove(remove);    
    }

    public int getQuantity(Product item){
        return stock.get(item);
    }

    // adds stock to an item in the linkedhashmap
    public void addStock(String name, int quantity){
        Product item = getProductFromName(name);
        int newQuantity = stock.get(item) + quantity;
        
        stock.put(item, newQuantity);
    }

    // gets the item and reduces it by 1
    public void sellProduct(String name, int quantity){
        Product sold = getProductFromName(name);

        
        if (sold == null){
            System.out.println("Item Not found");
            return;
        }
        
        else if (quantity <= 0 || quantity > stock.get(sold)){
            System.out.println("Invalid Quantity");
            return;
        }

        int newQuantity = stock.get(sold) - quantity;

        stock.put(sold, newQuantity);
    }

    public ArrayList<Product> sortByPrice(){
        ArrayList<Product> products = new ArrayList<>(stock.keySet());
        Collections.sort(products, Product.priceComparator);

        return products;
    }

    public ArrayList<Product> sortByBrand(){
        ArrayList<Product> products = new ArrayList<>(stock.keySet());
        Collections.sort(products, Product.brandComparator);
        
        return products;
    }

    public ArrayList<Product> sortByBrand(String brandName){
        ArrayList<Product> sorted = new ArrayList<>();

        for (Product product:stock.keySet()){
            if (product.getBrand().equalsIgnoreCase(brandName)){
                sorted.add(product);
            }
        }
        
        Collections.sort(sorted, Product.brandComparator);

        return sorted;
    }

    public HashMap<Product, Integer> getStock(){
        return stock;
    }

    public ArrayList<InEarMonitor> sortBySoundSignature(){
        ArrayList<InEarMonitor> sorted = new ArrayList<>();
        
        for (Product product:stock.keySet()){
            if (product instanceof InEarMonitor){
                sorted.add((InEarMonitor) product);
            }
        }

        Collections.sort(sorted, InEarMonitor.soundSignatureComparator);
        return sorted;
    }

    public ArrayList<InEarMonitor> sortBySoundSignature(SoundSignature sound){
        ArrayList<InEarMonitor> sorted = new ArrayList<>();

        for (Product product : stock.keySet()){
            if (product instanceof InEarMonitor && ((InEarMonitor) product).getSound() == sound){
                sorted.add((InEarMonitor) product);
            }
        }

        Collections.sort(sorted, Product.priceComparator);
        return sorted;
    }

    public ArrayList<CarryBag> sortByVolume(){
        ArrayList<CarryBag> sorted = new ArrayList<>();

        for (Product product : stock.keySet()){
            if (product instanceof CarryBag){
                sorted.add((CarryBag) product);
            }
        }

        Collections.sort(sorted, CarryBag.volumeComparator);
        return sorted;
    }

    @Override
    public String toString(){
        String output = "";
        
        for (Map.Entry<Product, Integer> product: stock.entrySet()){
            Product item = product.getKey();
            int quantity = product.getValue();

            output += item.getName() + " | Stock: " + quantity + "\n";
        }
        
        return output;
    }
}
