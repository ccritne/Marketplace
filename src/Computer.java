public class Computer extends Element{

    private String processor;
    private String gpu;
    private String motherboard;
    private long ramBytes;

    // Constructor
    Computer(
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
        long ramBytes
    ) {
        super(id, name, price, ean, description, stars, weight);
        this.processor = processor;
        this.gpu = gpu;
        this.motherboard = motherboard;
        this.ramBytes = ramBytes;
    }

    public Computer(String computerFromString) {
        super(computerFromString);
        try{
            String[] subStrings = computerFromString.split("#");

            String[] elementAttributes = subStrings[2].split(";");

            this.processor = elementAttributes[0];
            this.gpu = elementAttributes[1];
            this.motherboard = elementAttributes[2];
            this.ramBytes = Long.parseLong(elementAttributes[3]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Format error! " + computerFromString);
        }
    }

    // Getters
    public String getProcessor() {
        return processor;
    }

    public String getGpu() {
        return gpu;
    }
    
    public String getMotherboard() {
        return motherboard;
    }
    
    public long getRamBytes() {
        return ramBytes;
    }

    // Setters
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }
    
    public void setRamBytes(long ramBytes) {
        this.ramBytes = ramBytes;
    }

    @Override
    public String getType() {
        return "Computer";
    }

    @Override
    public String toString() {
        return super.toString()+"#"+processor+";"+gpu+";"+motherboard+";"+ramBytes;
    }

}
