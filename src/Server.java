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

    public Server(String computerFromString) {
        super(computerFromString);

        String[] subStrings = computerFromString.split(";");

        this.url = subStrings[11];
    }

    // Getter
    public String getUrl() {
        return url;
    }

    // Setter
    public void setUrl(String url) {
        this.url = url;
    }
}
