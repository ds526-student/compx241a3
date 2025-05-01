
public class program {

    /**
     * main method to test the StrHashTable class
     * @param args 
     */
    public static void main(String[] args) {
        StrHashTable ht = new StrHashTable(5); // creates a new hash table
        // inserts data into the hash table
        ht.insert("key1", "value1");
        ht.insert("key2", "value2");
        ht.insert("key3", "value3");
        ht.insert("key4", "value4");
        ht.insert("key5", "value5");
        ht.insert("key6", "value6");
        ht.insert("key7", "value7");
        ht.insert("key8", "value8");
        ht.insert("key9", "value9");
        ht.insert("key10", "value10");
        ht.insert("key11", "value11");

        // deletes data then displays the hash table
        ht.delete("key5");
        ht.dump();

        // checks if certain keys exist in the hash table
        System.out.println("'key1' exists: " + ht.contains("key1"));
        System.out.println("'key5' exists: " + ht.contains("key5"));

        // retrieves values from the hash table
        ht.get("key1");
        ht.get("key5");

        // displays the number of entries in the hash table
        System.out.println(ht.count() + " entries in the hash table.");
    }
}
