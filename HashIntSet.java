import java.util.HashSet;

public class HashIntSet {

	private class Node{
		public int data;
		public Node next;

		public Node(int value) {
			data = value;
			next = null;
		}
		public Node(int value, Node next) {
			data = value;
			this.next = next;
		}
	}

	private Node[] elements;
	private int size;
	public HashIntSet() {
		elements = new Node[10];
		size = 0;
	}
	public int getSize(){
		return this.size;
	}
	public int hash(int i) {
		return (Math.abs(i) % elements.length);
	}

	public void add(int value) {
		if(!contains(value)) {
			int h = hash(value);
			Node newNode = new Node(value);
			newNode.next = elements[h];
			elements[h] = newNode;
			size++;

		}
	}
	public boolean contains(int value) {
		Node current = elements[hash(value)];
		while(current != null) {
			if(current.data == value) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public String toString() {
		String s = "";
		for(Node n:elements) {
			Node current = n;
			while(current != null) {
				s += current.data + " ";
				current = current.next;
			}
		}
		return s;
	}

	public void remove(int value) {
		int h = hash(value);
		if(elements[h] != null && elements[h].data == value) {
			elements[h] = elements[h].next;
			size--;
		}else {
			Node current = elements[h];
			while(current != null && current.next != null) {
				if(current.next.data == value) {
					current.next = current.next.next;
					size--;
					return;
				}
				current = current.next;
			}
		}	
	}

	/* BJP4 Exercise 18.1: addAllHashIntSet
	 * Write a method named addAll that could be placed inside 
	 * the HashIntSet class. This method accepts another HashIntSet 
	 * as a parameter and adds all elements from that set into the 
	 * current set, if they are not already present. 
	 *
	 * For example, if a set s1 contains [1, 2, 3] and another set 
	 * s2 contains [1, 7, 3, 9], the call of s1.addAll(s2); would change 
	 * s1 to store [1, 2, 3, 7, 9] in some order.
	 *
	 * You are allowed to call methods on your set and/or the other set. 
	 * Do not modify the set passed in. This method should run in O(N) time 
	 * where N is the number of elements in the parameter set passed in. 
	 */
	public void addAll(HashIntSet add){
		for(int i = 0; i < add.elements.length; i++){
			Node node;
			if(add.elements[i]!= null){
				node = add.elements[i];
				this.add(node.data);
				while(node.next != null){
					node = node.next;
					this.add(node.data);
				}
			}
		}
	}
	/* BJP4 Exercise 18.3: equalsHashIntSet
	 * Write a method in the HashIntSet class called equals that accepts 
	 * another object as a parameter and returns true if the object is 
	 * another HashIntSet that contains exactly the same elements. 
	 * The internal hash table size and ordering of the elements does not matter, 
	 * only the sets of elements themselves.
	 */
	public boolean equalsHashIntSet(HashIntSet ob){

		for(int i = 0; i < ob.elements.length; i++){
			Node node;
			if(ob.elements[i]!= null){
				node = ob.elements[i];
				if(!this.contains(node.data)){
					return false;
				}
				while(node.next != null){
					node = node.next;
					if(!this.contains(node.data)){
						return false;
					}
				}
			}
		}
		for(int i = 0; i < this.elements.length; i++){
			Node node;
			if(this.elements[i]!= null){
				node = this.elements[i];
				if(!ob.contains(node.data)){
					return false;
				}
				while(node.next != null){
					node = node.next;
					if(!ob.contains(node.data)){
						return false;
					}
				}
			}
		}
		return true;
}
	/* BJP4 Exercise 18.4: removeAllHashIntSet
	 * Write a method in the HashIntSet class called removeAll that accepts 
	 * another hash set as a parameter and ensures that this set does not contain 
	 * any of the elements from the other set. For example, if the set stores 
	 * [-2, 3, 5, 6, 8] and the method is passed [2, 3, 6, 8, 11], your set would 
	 * store [-2, 5] after the call.
	 */
	public void removeAll(HashIntSet set2){
		for(int i = 0; i < this.elements.length; i++){
			if(this.elements[i]!= null){
				Node node = this.elements[i];
				if(set2.contains(node.data)){
					this.remove(node.data);
				}
				while(node.next != null && node != null){
					if(set2.contains(node.data)){
						this.remove(node.data);
					}
					node = node.next;
				}
			}
		}
	}
	/* BJP4 Exercise 18.5: retainAllHashIntSet
	 * Write a method in the HashIntSet class called retainAll that accepts 
	 * another hash set as a parameter and removes all elements from this set that 
	 * are not contained in the other set. For example, if the set stores [-2, 3, 5, 6, 8] 
	 * and the method is passed [2, 3, 6, 8, 11], your set would store [3, 6, 8].
	 */
	public void retainAll(HashIntSet set2){
		for(int i = 0; i < this.elements.length; i++){
			if(this.elements[i]!= null){
				Node node = this.elements[i];
				if(!set2.contains(node.data)){
					this.remove(node.data);
				}
				while(node.next != null && node != null){
					if(!set2.contains(node.data)){
						this.remove(node.data);
					}
					node = node.next;
				}
			}
		}
	}
	/* BJP4 Exercise 18.6: toArrayHashIntSet
	 * Write a method in the HashIntSet class called toArray that returns the elements of 
	 * the set as a filled array. The order of the elements in the array is not important 
	 * as long as all elements from the set are present in the array, with no extra empty 
	 * slots before or afterward.
	 */
	public Node[] toArray(){
		int index = 0;
		Node[] array = new Node[this.getSize()];
		for(int i = 0; i < this.elements.length; i++){
			if(elements[i]!= null){
				Node node = elements[i];
				array[index++] = node;
				while(node.next != null){
					node = node.next;
					array[index++] = node;
				}
			}
		}
		return array;
	}
	public static void main(String[] args) {
		HashIntSet set = new HashIntSet();
		for(int i = 0; i < 75; i++){
			set.add(i);
		}
		Node[] array = set.toArray();
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i].data);
		}

	}

}
