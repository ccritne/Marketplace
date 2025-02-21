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

    public Nutritional(String nutrionalFromString) {

        String[] subStrings = nutrionalFromString.split(",");

        this.kcal = Double.parseDouble(subStrings[0]);
        this.carbs = Double.parseDouble(subStrings[1]);
        this.proteins = Double.parseDouble(subStrings[2]);
        this.fats = Double.parseDouble(subStrings[3]);

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
        return kcal+","+carbs+","+proteins+","+fats;
    }
}
