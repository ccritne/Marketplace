public class Store {
    private long id;
    private String name;
    private Element[] elements;

    // Constructor
    public Store(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Store(String storeFromString) {

        String[] subStrings = storeFromString.split(";");

        id = Long.parseLong(subStrings[0]);
        name = subStrings[1];
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Element[] getElements() {
        return elements;
    }

    public Element getElement(int index) {
        if (index >= 0 && index < elements.length) {
            return elements[index];
        } else {
            throw new IndexOutOfBoundsException("Index must be between 0 and "+(elements.length-1));
        }
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElements(Element[] elements) {

        // It creates an array filled null
        Element[] tempElements = new Element[elements.length];
        
        // Fill partially (or entire) tempElements with elements
        for (int i = 0; i < elements.length; i += 1) {
            tempElements[i] = elements[i];
        }

        this.elements = tempElements;
    }

    // Method to set an Element at a specific index
    public void setElement(int index, Element element) {
        if (index >= 0 && index < elements.length) {
            elements[index] = element;
        } else {
            throw new IndexOutOfBoundsException("Index must be between 0 and "+(elements.length - 1));
        }
    }

    @Override
    public String toString() {
        return id+";"+name;
    }

    
}
