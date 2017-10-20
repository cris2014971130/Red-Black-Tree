package redBlackTree;

import dinamic.SimpleList;
import java.util.Comparator;

/**
 *	Clase de un arbol red-black
 */

public class RedBlackTree<T> {

	protected NodeRedBlackTree<T> root;// todo arbol posee root 
	protected Comparator<T> comparable;
	public static byte black = 0;
	public static byte red  = 1;


	/**
	 * Constructor  por defecto. Inicializa el arbol Red-Black con root nula.
	 *
	 */

	public RedBlackTree(Comparator<T> comparable){  
		root = null;
		this.comparable = comparable;
	} 

	/**
	 * Metodo publico que retorna true si el arbol se encuentra vacio y false en caso contrario.
	 * @return boolean - si arbol esta vacio (true). Si arbol no esta vacio (false).
	 */

	public boolean isEmpty(){
		return  root == null;
	}

	/** 
	 * Metodo protegido que inserta el nodo z primeramente como en un arbol ABB.
	 * @param NonodeRigthedBlackTree  nodo que se insertará en el arbol.
	 */

	protected void addABB(NodeRedBlackTree<T> z){
		NodeRedBlackTree<T> aux = null;
		NodeRedBlackTree<T> nodeCurrent = root; 
		while (nodeCurrent != null){
			aux = nodeCurrent;
			if (comparable.compare(z.info, nodeCurrent.info) < 0){
				nodeCurrent = nodeCurrent.nodeLeft;
			}else{
				nodeCurrent = nodeCurrent.nodeRigth;
			}
		}

		z.nodeFather = aux;
		if (aux == null){
			root = z;
		}else{ 
			if(comparable.compare(z.info, aux.info) < 0){
				aux.nodeLeft = z;	
			}else{  
				aux.nodeRigth = z;
			}
		}

	}

	/**
	 * Metodo privado que retorna true si info. ya encuentra en el arbol y false en caso contrario.
	 * @param NonodeRigthedBlackTree  nodo donde se busca el infoento.
	 * @param Comparable  infoento a buscar en el arbol.	
	 * @return boolean -  si info. está en el arbol (true). Si info. no esta en el arbol (false). 	 	 
	 */

	private boolean isInTree(NodeRedBlackTree<T> aux,T info){
		if (aux == null){
			return false;
		}else{
			if(comparable.compare(info, aux.info) == 0){
				return true;
			}else{	 
				return isInTree(aux.nodeLeft,info) || isInTree(aux.nodeRigth,info);
			}
		}
	}

	/** 
	 * Metodo publico que inserta un info en el arbol Red-Black
	 * @param Comparable info - infoento a insertar
	 */

	public void add(T info){
		if (isInTree(root,info)){ 
			return;
		}						
		eliminarCentinelas(root);
		NodeRedBlackTree<T> aux = new NodeRedBlackTree<T>(info);		
		addABB(aux); 
		while (aux != root && aux.nodeFather.color == red){
			boolean colorYred = false;
			if (aux.nodeFather == aux.nodeFather.nodeFather.nodeLeft){
				NodeRedBlackTree<T> nodeAux = aux.nodeFather.nodeFather.nodeRigth; 																
				if (nodeAux != null){ 
					if (nodeAux.color == red){             
						aux.nodeFather.color = black;
						nodeAux.color = black;
						aux.nodeFather.nodeFather.color = red;
						aux = aux.nodeFather.nodeFather;   
						colorYred = true;          
					}
				} 
				if(!colorYred){ 
					if (aux == aux.nodeFather.nodeRigth){            
						aux = aux.nodeFather;       
						rotarLeft(aux);           
					}
					aux.nodeFather.color = black;  
					aux.nodeFather.nodeFather.color = red;
					rotarRight(aux.nodeFather.nodeFather);								
				}
			}else{ 
				if(aux.nodeFather == aux.nodeFather.nodeFather.nodeRigth){        
					NodeRedBlackTree<T> nodeAux = aux.nodeFather.nodeFather.nodeLeft; 																								
					if (nodeAux != null){ 
						if (nodeAux.color == red){      
							aux.nodeFather.color = black;
							nodeAux.color = black;           
							aux.nodeFather.nodeFather.color = red;	
							aux = aux.nodeFather.nodeFather;         	
							colorYred = true;         	
						}		
					} 
					if (!colorYred){   
						if(aux == aux.nodeFather.nodeLeft){
							aux = aux.nodeFather;         
							rotarRight(aux);         
						}
						aux.nodeFather.color = black; 
						aux.nodeFather.nodeFather.color = red;
						rotarLeft(aux.nodeFather.nodeFather);	 											
					}
				}
			}
		}

		root.color = black;								
		crearCentinela(root);
	}

	/** 
	 * Metodo privado que realiza una rotacion simple a la nodeLeftuierda sobre el nodo x
	 * @param NodeRedBlackTree aux - Nodo sobre el cual se realiza la rotacion simple a la nodeLeftuierda.
	 */

	private void rotarLeft(NodeRedBlackTree<T> aux){
		NodeRedBlackTree<T> nodeCurrent = aux.nodeRigth;
		aux.nodeRigth = nodeCurrent.nodeLeft;		
		if(nodeCurrent.nodeLeft != null){
			nodeCurrent.nodeLeft.nodeFather = aux;
			nodeCurrent.nodeFather = aux.nodeFather;
		}
		if(aux.nodeFather == null){
			root = nodeCurrent;
		}else{ 
			if(aux == aux.nodeFather.nodeLeft){
				aux.nodeFather.nodeLeft = nodeCurrent;
			}else{
				aux.nodeFather.nodeRigth = nodeCurrent;
			}
		}
		nodeCurrent.nodeLeft = aux;
		aux.nodeFather = nodeCurrent;
	}		

	/** 
	 * Metodo privado que realiza una rotacion simple a la nodeRigth hecha sobre el nodo x
	 * @param NonodeRigthedBlackTree x - Nodo sobre el cual se realiza la rotacion simple a la nodeRigthecha.
	 */

	private void rotarRight(NodeRedBlackTree<T> aux){
		NodeRedBlackTree<T> nodeCurrent = aux.nodeLeft;
		aux.nodeLeft = nodeCurrent.nodeRigth;
		if(nodeCurrent.nodeRigth != null){
			nodeCurrent.nodeRigth.nodeFather = aux;
		}
		nodeCurrent.nodeFather = aux.nodeFather;
		if(aux.nodeFather == null){
			root = nodeCurrent;
		}else{ 
			if(aux == aux.nodeFather.nodeRigth){
				aux.nodeFather.nodeRigth = nodeCurrent;
			}else{
				aux.nodeFather.nodeLeft = nodeCurrent;
			}
		}
		nodeCurrent.nodeRigth = aux;
		aux.nodeFather = nodeCurrent;
	}		

	/** 
	 * Metodo privado que retorna el nodo cuyo dato es info y si no se encuentra retorna null
	 * @param NodeRigthBlackTree - nodo donde se compara
	 * @param info - Dato a comparar
	 * @return boolean - si encuentra el info retorna el nodo. Si no es encontrado se retorna null 
	 */

	private NodeRedBlackTree<T> buscarNodo(NodeRedBlackTree<T> aux, T info){
		while(aux != null && comparable.compare(info, aux.info)!= 0 )
			if(comparable.compare(info, aux.info) < 0){
				aux = aux.nodeLeft;
			}else{  
				aux = aux.nodeRigth;
			}
		return aux;				
	} 

	/** 
	 * Metodo publico que nos permite eliminar del arbol red-black el dato info
	 * @param info = info es el infoento a eliminar
	 */

	public void eliminarRB(T info){
		if(isEmpty()){ 
			return;
		}		 
		NodeRedBlackTree<T> z = buscarNodo(root,info);
		if(z == null){
			return; 
		}				
		if(root.esCentinela){
			root.nodeFather = null;
			root = null;
		}	
		eliminarCentinelas(root);
		crearCentinela(root);
	}		

	/** 
	 * Metodo privado que elimina todo nodo cuyo atributo esCentinela es true
	 * @param NonodeRigthedBlackTree - nodo donde se comienza al eliminar centinelas
	 */

	private void eliminarCentinelas(NodeRedBlackTree<T> aux){
		if (aux == null){
			return;
		}
		if(aux.esCentinela){ 
			if(aux.nodeFather.nodeLeft.esCentinela){
				aux.nodeFather.nodeLeft = null;
			}
			if(aux.nodeFather.nodeRigth.esCentinela){
				aux.nodeFather.nodeRigth = null;
			}
		}else{
			eliminarCentinelas(aux.nodeLeft);						 
			eliminarCentinelas(aux.nodeRigth);
		}   
	}

	/**
	 *	Metodo privado que permite que todas las referencias a null del arbol sean asinadas a nodos centinelas que nos ayudan en la eliminacion	
	 * @param NonodeRigthedBlackTree	
	 */

	private void crearCentinela(NodeRedBlackTree<T> aux){   	
		if(aux.nodeLeft == null){
			NodeRedBlackTree<T> centinela = new NodeRedBlackTree<T>(aux);
			aux.nodeLeft = centinela;
		}else{
			crearCentinela(aux.nodeLeft);				
		}				
		if(aux.nodeRigth == null){
			NodeRedBlackTree<T> centinela = new NodeRedBlackTree<T>(aux);
			aux.nodeRigth = centinela;							
		}else{				 
			crearCentinela(aux.nodeRigth);
		}
	}		 	

	public SimpleList<T> printTreeInOrdenRigth(){
		SimpleList<T> listInfo = new SimpleList<>();
		inOrnodeRigth(listInfo, root);
		return listInfo;

	}

	public void inOrnodeRigth(SimpleList<T> list, NodeRedBlackTree<T> current){
		ayudanteInorden(list, current);
	}

	//meoto recursivo para recorrido inorden
	private void ayudanteInorden(SimpleList<T> list,NodeRedBlackTree<T> nodo){
		if(nodo != null){
			ayudanteInorden(list,nodo.nodeLeft);
			list.add(nodo.info);
			ayudanteInorden(list,nodo.nodeRigth);
		}
	}



}// fin arbolRB





