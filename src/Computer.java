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

        String[] subStrings = computerFromString.split(";");

        this.processor = subStrings[7];
        this.gpu = subStrings[8];
        this.motherboard = subStrings[9];
        this.ramBytes = Long.parseLong(subStrings[10]);
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
    public String toString() {
        return super.toString()+";"+processor+";"+gpu+";"+motherboard+";"+ramBytes;
    }

}
