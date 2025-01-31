public class Server extends Computer{

    private String url;

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
        String url
    ) {
        super(id, name, price, ean, description, stars, weight, processor, gpu, motherboard, ramBytes);
        this.url = url;
    }

    public Server(String serverFromString) {
        super(serverFromString);
        try {
            String[] subStrings = serverFromString.split("#");

            String[] elementAttributes = subStrings[3].split(";");

            this.url = elementAttributes[0];
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Format error! "+serverFromString);
        }
    }

    // Getter
    public String getUrl() {
        return url;
    }

    // Setter
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getType() {
        return "Server";
    }

    @Override
    public String toString() {
        return super.toString()+"#"+url;
    }
}
