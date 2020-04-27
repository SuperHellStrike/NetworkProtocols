package hellstrike21291.scene;

import java.io.FileOutputStream;

/**
 * Класс реализующий структуру данных (односвязный список) для хранения планет
 * 
 * @author Valeriya Chekalova
 * @version 1
 */
public class PlanetList implements Comparable<PlanetList>{

	/**
	 * Вложенный приватный класс узла для односвязного списка
	 * 
	 * @author Valeriya Chekalova
	 * @version 1
	 */
	private class Node {
		Planet planet;
		Node next;
	}
	
	/** Размер списка */
	private int size = 0;
	
	/** Голова списка */
	private Node head = null;
	
	/** Возвращает размер списка */
	public int getSize() {
		return size;
	}
	
	/**
	 * Добавляет планету в голову списка
	 * @param p - планета
	 */
	public void push(Planet p) {
		Node newHead = new Node();
		newHead.planet = p;
		newHead.next = head;
		head = newHead;
		size++;
	}
	
	/**
	 * Извлекает и возвращает планету из головы списка
	 * @return планета
	 */
	public Planet pop() {
		if(size == 0)
			return null;
		
		Planet p = head.planet;
		
		head = head.next;
		size--;
		
		return p;
	}
	
	/**
	 * Вызывает метод отображения у всех планет в списке
	 */
	public void draw() {
		Node current = head;
		
		for(int i = 0; i < size; i++) {
			current.planet.draw();
			current = current.next;
		}
	}
	
	/**
	 * Поворачивает все планеты в списке
	 * @param da - дельта угла
	 */
	public void rotate(float da) {
		Node current = head;
		
		for(int i = 0; i < size; i++) {
			current.planet.rotate(da);
			current = current.next;
		}
	}

	/**
	 * Сохраняет информацию о планетах в списке в файл
	 * @param file - файл для сохранения
	 */
	public void save(FileOutputStream file) {
		Node current = head;
		
		for(int i = 0; i < size; i++) {
			current.planet.save(file);
			current  = current.next;
		}
	}
	
	
	/**
	 * Сравнивает два списка по количеству планет
	 */
	@Override
	public int compareTo(PlanetList pl) {
		return size - pl.size;
	}
}
