package dinamic;

import java.util.Comparator;

public class SimpleList <T>{

	private Node<T> head;
	private Comparator<T> comparadores;
	private Node<T> tail;

	public SimpleList(Comparator<T> comparador) {
		comparadores = comparador;
		this.head = null;
	}

	public SimpleList() {
		this.head = null;
		this.comparadores = null;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public boolean isEmpty(){
		return this.head == null;
	}

	/**
	 * Agrega un elemento al final de la lista
	 * @param info
	 */
	public void add(T info){
		if(head != null){
			Node<T> node = this.head;
			while(node.getNext() != null){
				node = node.next;
			}
			node.next = new Node<T>(info);
			tail = node.next;
		}else{
			this.head = new Node<T>(info);
			tail = head;
		}
	}

	public Node<T> getTail() {
		return tail;
	}

	public void setTail(Node<T> tail) {
		this.tail = tail;
	}

	/**
	 * Agrega un nuevo elemento por la cabeza
	 * @param info
	 */
	public void insert(T info){
		this.head = new Node<T>(info,this.head);
		tail = head;
	}

	public void eraseElementFirst(){
		if(head != null){
			head = head.getNext();
		}	
	}

	public void eraseElement(T info){
		if( head != null){
			Node<T> node = this.head;
			while(node.getNext() != null){
				if(node.getInfo().equals(info)){
					node.setInfo(node.next.getInfo());
					node.setNext(node.next.next);
				}
				node = node.next;
			}
		}
	}

	public boolean isHear(T info){
		boolean xD = false;
		if( head != null){
			Node<T> node = this.head;
			while(node.getNext() != null){
				if(node.getInfo().equals(info)){
					xD = true;
				}
				node = node.next;
			}
		}
		return xD;
	}

	public Node<T> search(T info){
		Node<T> nodeAux = null;
		if( head != null){
			Node<T> node = this.head;
			while(node.getNext() != null){
				if(node.getInfo().equals(info)){
					nodeAux = node;
				}
				node = node.next;
			}
		}
		return nodeAux;
	}

	/**
	 * reemplaza la informacion de info por newInfo
	 * @param info
	 * @param newInfo
	 */
	public void replaceValue(T info, T newInfo){
		Node<T> aux = search(info);
		aux.setInfo(newInfo);
		aux.setNext(aux.next);
	}

	public void print(SimpleList<T> list){
		Node<T> node = list.getHead();
		while(node != null){
			System.out.println("" + node.getInfo().toString());
			node = node.getNext();
		}
	}

	public void addSort(T info, SimpleList<T> list){
		Node<T> node = list.getHead();
		if(comparadores.compare(node.getInfo(),info)==1){
			insert(info);
		}else if(comparadores.compare(node.getInfo(),info)==-1){
			add(info);
		}else{
			add(info);
		}
	}

}