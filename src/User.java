public class User {

    private long id;
    private String username;
    private String password;

    // Constructors
    User(long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    User(String userFromString) {
        try {
            String[] subStrings = userFromString.split("#");
            String[] elementAttributes = subStrings[1].split(";");

            id = Long.parseLong(elementAttributes[0]);
            username = elementAttributes[1];
            password = elementAttributes[2];
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Format error! "+userFromString);
        }
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return "User";
    }

    @Override
    public String toString() {
        return getType()+"#"+id+";"+username+";"+password;
    }


    
}
