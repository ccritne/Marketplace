public class Food extends Element {

    private Date expirationDate; 
    private Nutritional values;

    // Constructor
    public Food(
        long id, 
        String name, 
        double price, 
        long ean, 
        String description, 
        int stars, 
        double weight, 
        Date expirationDate, 
        Nutritional values
    ) {
        super(id, name, price, ean, description, stars, weight);
        this.expirationDate = expirationDate;
        this.values = values;
    }

    public Food(String foodFromString) {
        super(foodFromString);
        try{
            String[] subStrings = foodFromString.split("#");

            String[] elementAttributes = subStrings[2].split(";");

            this.expirationDate = new Date(elementAttributes[0]);
            this.values = new Nutritional(elementAttributes[1]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Format error! "+foodFromString);
        }
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public Nutritional getNutrionalValues(){
        return values;
    }

    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }

    public void setNutrionalValues(Nutritional values){
        this.values = values;
    }

    @Override
    public String getType() {
        return "Food";
    }

    @Override
    public String toString() {
        return super.toString() + "#" + expirationDate.toString()+";"+values.toString();
    }

    
}
