public class StrHashTableCollisions {
    Node[] hashTable;
    
    int size, numFullEntries, numRehashes;

    public StrHashTableCollisions(int size) {
        this.size = size;
        hashTable = new Node[size];
        numFullEntries = 0;
        numRehashes = 0;
    }

    public int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return hash % size;
    }

    public void insert(String key, String value) {
        
        int index = hashFunction(key);
        Node node = hashTable[index];

        if (numFullEntries >= size * 0.8) {
            rehash();
            numRehashes++;
        }
        
        if (node == null) {
            node = new Node(key, value);
            hashTable[index] = node;
            numFullEntries++;
        } else {
            while (node.next != null) {
                node = node.next;
            }
            Node newNode = new Node(key, value);
            node.next = newNode;
            numFullEntries++;
        }
    }

    public void delete(String key) {
        int index = hashFunction(key);
        Node node = hashTable[index];

        while (node != null) {
            if (node.key.equals(key)) {
                node.value = null;
                node = node.next;
                hashTable[index] = null;
                numFullEntries--;
            } else {
                node = node.next;
            }
        }
    }   
    public void rehash() {
        Node[] temp = hashTable;
        size *= 2;
        hashTable = new Node[size];
        numFullEntries = 0; 

        for (Node t : temp) {
            while (t != null) {
                insert(t.key, t.value);
                t = t.next; 
            }
        }
    }

    public boolean contains(String key) {
        int index = hashFunction(key);
        if (hashTable[index] != null && hashTable[index].key.equals(key)) {
            return true;
        } else {
            return false;
        }
    }

    public void get(String key) {
        int index = hashFunction(key);
        Node node = hashTable[index];
        while (node != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(node.key).append(" : ");
            while (node != null) {
                sb.append(node.value);
                if (node.next != null) {
                    sb.append(", ");
                }
                node = node.next;
            }
            System.out.println(sb.toString());
        }
    }

    public int count() {
        return numFullEntries;
    }
    public void dump() {
        for (Node node : hashTable) {
            if (node != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(node.key).append(" : ");
                while (node != null) {
                    sb.append(node.value);
                    if (node.next != null) {
                        sb.append(", ");
                    }
                    node = node.next;
                }
                System.out.println(sb.toString());
            } else {
                System.out.println("null");
            }
        }
    }
    
}
