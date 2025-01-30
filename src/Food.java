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


    
}
