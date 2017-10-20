package test;

import java.util.Comparator;

import dinamic.Node;
import dinamic.SimpleList;
import redBlackTree.RedBlackTree;

public class TestRedBlackTree {
	
	public static void main(String[] args) {
		Comparator<Integer> comparable = new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 < o2){
					return -1;
				}else if(o1 > o2){
					return 1;
				}else{
					return 0;
				}
			}
		};
		RedBlackTree<Integer> arbol = new RedBlackTree<Integer>(comparable);
		arbol.add(2);
		arbol.add(5);
		arbol.add(12);
		arbol.add(22);
		arbol.add(1);
		arbol.add(9);
		
		SimpleList<Integer> list = arbol.printTreeInOrdenRigth();
		Node<Integer> nodeCurrent = list.getHead();
		while(nodeCurrent != null){
			System.out.println(nodeCurrent.getInfo());
			nodeCurrent = nodeCurrent.getNext();
		}
		
		
	}
}	
