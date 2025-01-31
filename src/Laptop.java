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

    public Laptop(String laptopFromString) {
        super(laptopFromString);
        
        try {
            String[] subStrings = laptopFromString.split("#");

            String[] elementAttributes = subStrings[3].split(";");

            this.inchesScreen = Integer.parseInt(elementAttributes[0]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Format error! "+laptopFromString);
        }
    }

    // Getter
    public int getInchesScreen() {
        return inchesScreen;
    }

    // Setter
    public void setInchesScreen(int inchesScreen) {
        this.inchesScreen = inchesScreen;
    }

    @Override
    public String getType() {
        return "Laptop";
    }

    @Override
    public String toString() {
        return super.toString()+"#"+inchesScreen;
    }
    
    
}
