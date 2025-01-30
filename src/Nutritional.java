public class Nutritional {
    private double kcal;
    private double carbs;
    private double proteins;
    private double fats;

    // Constructor
    public Nutritional(double kcal, double carbs, double proteins, double fats) {
        this.kcal = kcal;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
    }

    // Getters 
    public double getKcal() {
        return kcal;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    //Setters
    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }
    
    @Override
    public String toString() {
        return kcal+";"+carbs+";"+proteins+";"+fats;
    }
}
