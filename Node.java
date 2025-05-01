public class Node {
    String key; // key in the hash table
    String value; // value in the hash table
    Node next; // points to the next node in the linked list

    /**
     * constructor for the Node class
     * @param Key in the hash table
     * @param Value in the hash table
     */
    public Node(String Key, String Value) {
        key = Key;
        value = Value;
        next = null; 
    }  
}
