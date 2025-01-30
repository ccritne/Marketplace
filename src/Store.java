public class Store {
    private long id;
    private String name;
    private Element[] elements = new Element[100];

    // Constructor
    public Store(long id, String name) {
        this.id = id;
        this.name = name;
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
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
            throw new IndexOutOfBoundsException("Index must be between 0 and 99");
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
        if (elements.length <= 100) {

            // It creates an array with 100 null
            Element[] tempElements = new Element[100];
            
            // Fill partially (or entire) tempElements with elements
            for (int i = 0; i < elements.length; i += 1) {
                tempElements[i] = elements[i];
            }

            this.elements = tempElements;
        } else {
            throw new IllegalArgumentException("Elements array must have <= 100 items");
        }
    }

    // Method to set an Element at a specific index
    public void setElement(int index, Element element) {
        if (index >= 0 && index < elements.length) {
            elements[index] = element;
        } else {
            throw new IndexOutOfBoundsException("Index must be between 0 and 99");
        }
    }

    
}
