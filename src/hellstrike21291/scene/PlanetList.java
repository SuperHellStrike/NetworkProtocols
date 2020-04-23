package hellstrike21291.scene;

public class PlanetList {

	private class Node {
		Planet planet;
		Node next;
	}
	
	private int size = 0;
	private Node head = null;
	
	public int getSize() {
		return size;
	}
	
	public void push(Planet p) {
		Node newHead = new Node();
		newHead.planet = p;
		newHead.next = head;
		head = newHead;
		size++;
	}
	
	public Planet pop() {
		if(size == 0)
			return null;
		
		Planet p = head.planet;
		
		head = head.next;
		size--;
		
		return p;
	}
	
	public void draw() {
		Node current = head;
		
		for(int i = 0; i < size; i++) {
			current.planet.draw();
			current = current.next;
		}
	}
	
	public void rotate(float da) {
		Node current = head;
		
		for(int i = 0; i < size; i++) {
			current.planet.rotate(da);
			current = current.next;
		}
	}
}
