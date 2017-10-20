package dinamic;

public class DoubleList<T> {

	public NodeDouble<T> apuntador;
	protected NodeDouble<T> cursor;

	public DoubleList(){
		apuntador = null;
		this.cursor = apuntador;
	}

	public boolean estavacio(){
		if(apuntador==null){
			return true;
		}else{
			return false;
		}
	}

	public void addStart(T data){
		NodeDouble<T> nuevo = new NodeDouble<T>(data);
		if(apuntador==null){
			apuntador=nuevo;
		}else{
			nuevo.setNext(apuntador);
			apuntador.setAnt(nuevo);
			apuntador=nuevo;
		}
	}

	public void addEnd(T data){
		NodeDouble<T> nuevo = new NodeDouble<T>(data);
		if(apuntador == null){
			apuntador = nuevo;
		}else{
			NodeDouble<T> aux = apuntador;
			while(aux.getNext() != null){
				aux = aux.getNext();

			}
			aux.setNext(nuevo);
			nuevo.setAnt(aux);
		}

	}

	//    public boolean delete(T num){
		//    	NodeDouble<T> anterior=null;
		//    	NodeDouble<T> actual=primero;
		//        while(actual!=ultimo){
			//            if(actual.dato.equals(num)){
	//                if(anterior==null){
	//                    primero=actual.next;
	//                    primero.ant=null;
	//                }else{
	//                    anterior.next=actual.next;
	//                    NodeDouble<T> temporal;
	//                    temporal=actual.next;
	//                    temporal.ant=anterior;
	//                }
	//                return true;
	//            }
	//            anterior=actual;
	//            actual=actual.next;
	//        }
	//        if(num==ultimo.dato)
	//        {
	//            ultimo=actual.ant;
	//            return true;
	//        }
	//        return false;
	//    }

	public void imprimir2(){
		NodeDouble<T> actual;
		actual= apuntador;
		while(actual.getAnt() != apuntador){
			System.out.println(actual.getDato());
			actual = actual.ant;
		}

	}

	public void imprimir(){
		NodeDouble<T> actual;
		actual= apuntador;
		while(actual.getNext() != apuntador){
			System.out.println(actual.getDato());
			actual = actual.next;
		}
	}

	public void reset(){
		this.cursor = apuntador;
	}

	public boolean isLast(){
		return (cursor == null);
	}

	public T getInfoCursor(){
		return cursor.getDato();
	}

	public void next(){
		this.cursor = cursor.getNext();
	}

	public T getNextInfo(){
		T info = this.getInfoCursor();
		this.next();
		return info;
	}
}
