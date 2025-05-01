public class program {

    // errors to be fixed:
    // 1. Rehash function is not working properly - t is null
    public static void main(String[] args) {
        StrHashTable ht = new StrHashTable(5);
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
        
        ht.delete("key5");
        ht.dump();

        System.out.println("'key1' exists: " + ht.contains("key1")); 
        System.out.println("'key5' exists: " + ht.contains("key5"));

        ht.get("key1");
        ht.get("key5");

        System.out.println(ht.count() + " entries in the hash table.");
    }
}
