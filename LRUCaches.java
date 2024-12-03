import java.util.HashMap;

class Node {

    int key;
    int value;
    Node next;
    Node prev;

    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

public class LRUCaches { 

    Node head;
    Node tail;
    HashMap<Integer, Node> Hashmap = null;
    int cap = 0;
    public LRUCaches(int capacity){
        this.cap = capacity;
        Hashmap =  new HashMap<>();
    }
    public int get(int key){
        if(Hashmap.containsKey(key)){
            Node node = Hashmap.get(key);
            removeNode(node);
            putOnTop(node);

            return node.value;
        }
        return -1;
    }
    public void put(int key, int value){
        if(Hashmap.containsKey(key)){
           Node t = Hashmap.get(key);
           t.value = value;
           removeNode(t);
           putOnTop(t);


        }else{
            if(Hashmap.size() >= cap){
                Hashmap.remove(tail.key);
                removeNode(tail);
            }
           Node node = new Node(key, value);
            Hashmap.put(key, node);
            putOnTop(node);
        }

    }
    private void removeNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
      
        if(prevNode != null){
            prevNode.next = node.next;
        }
        else{
            head = nextNode;
        }
        if(nextNode != null){
            nextNode.prev = prevNode;
        }else{
            tail = prevNode;
        }
    }
    private void putOnTop(Node node){
        node.next = head;
        node.prev = null;

        if(head!= null)
        head.prev = node;

        head = node;
        if(tail == null){
            tail = node;
        }
    }
    public static void main(String[] args) {
        LRUCaches cache = new LRUCaches(3);

        cache.put(1, 3);
        cache.put(4, 2);

        System.out.println(cache.get(1));

        cache.put(5,6);
        System.out.println(cache.get(7));
       System.out.println(cache.get(5));

       cache.put(7, 4);

       System.out.println(cache.get(4));

       System.out.println(cache.get(1));
       System.out.println(cache.get(5));
    }
}
