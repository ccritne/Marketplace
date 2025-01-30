public class Element {
    private long id;
    private String name;
    private double price;
    private long ean;
    private String description;
    private int stars;
    private double weight;

    // Constructor
    public Element(
        long id, 
        String name, 
        double price, 
        long ean, 
        String description, 
        int stars, 
        double weight    
    ) {
        
        this.id = id;
        this.name = name;
        this.price = price;
        this.ean = ean;
        this.description = description;
        this.stars = stars;
        this.weight = weight;
    }
    
    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getEan() {
        return ean;
    }

    public String getDescription() {
        return description;
    }

    public int getStars() {
        return stars;
    }

    public double getWeight() {
        return weight;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEan(long ean) {
        this.ean = ean;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}