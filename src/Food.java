public class Food extends Element {

    private Date expirationDate; 

    public Food(long id, String name, double price, long ean, String description, int stars, double weight, Date expirationDate) {
        super(id, name, price, ean, description, stars, weight);
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }

    
}
