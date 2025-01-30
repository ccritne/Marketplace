public class Laptop extends Computer{

    private int inchesScreen;

    // Constructor
    Laptop(
        long id, 
        String name, 
        double price, 
        long ean, 
        String description, 
        int stars, 
        double weight, 
        String processor,
        String gpu, 
        String motherboard, 
        long ramBytes, 
        int inchesScreen
    ) {
        super(id, name, price, ean, description, stars, weight, processor, gpu, motherboard, ramBytes);
        this.inchesScreen = inchesScreen;
    }

    // Getter
    public int getInchesScreen() {
        return inchesScreen;
    }

    // Setter
    public void setInchesScreen(int inchesScreen) {
        this.inchesScreen = inchesScreen;
    }
    
    
}
