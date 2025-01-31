public class Server extends Computer{

    private String endpoint;

    // Constructor
    Server(
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
        String endpoint
    ) {
        super(id, name, price, ean, description, stars, weight, processor, gpu, motherboard, ramBytes);
        this.endpoint = endpoint;
    }

    public Server(String serverFromString) {
        super(serverFromString);
        try {
            String[] subStrings = serverFromString.split("#");

            String[] elementAttributes = subStrings[3].split(";");

            this.endpoint = elementAttributes[0];
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Format error! "+serverFromString);
        }
    }

    // Getter
    public String getEndpoint() {
        return endpoint;
    }

    // Setter
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getType() {
        return "Server";
    }

    @Override
    public String toString() {
        return super.toString()+"#"+endpoint;
    }
}
