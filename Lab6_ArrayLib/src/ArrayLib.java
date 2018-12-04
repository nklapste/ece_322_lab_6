import java.lang.reflect.Array;

public class ArrayLib {
	
	@SuppressWarnings("unchecked")
	public static <E> E[] reverse(E[] array, Class<E> c){
		
		E[] reversed = (E[]) Array.newInstance(c, array.length);
		for(int i = 0; i < array.length; i++){
			reversed[array.length-1-i] = array[i];
		}
		return reversed;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] unique(E[] array, Class<E> c){
		
		int index=0;
		E[] unique = (E[]) Array.newInstance(c, array.length);
		for(E o : array){
			if(!contains(unique , o)){
				unique[index++] = o;
			}
		}
		
		return compact(unique, c);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] intersection(E[] a, E[] b, Class<E> c){
		
		int index=0;
		E[] intersection = (E[]) Array.newInstance(c, a.length);
		for(E e : a){
			for(E d : b){
				if(d.equals(e)){
					intersection[index++] = e;
				}
			}
		}

		return unique(compact(intersection, c), c);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] union(E[] a, E[] b, Class<E> c){
		
		int index=0;
		E[] union = (E[]) Array.newInstance(c, a.length + b.length);
		for(E e : a){
			union[index++] = e;
		}
		for(E d : b){
			union[index++] = d;
		}
		
		return unique(compact(union, c), c);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] compact(E[] a, Class<E> c){
		
		int count = 0;
		for(E e : a){
			if(e == null){
				count++;
			}
		}
		E[] compacted = (E[]) Array.newInstance(c, a.length - count);
		count = 0;
		for(E e : a){
			if(e != null){
				compacted[count++] = e;
			}
		}
		
		return compacted;
	}
	
	public static <E> int indexOf(E[] a, E b){
		for(int i = 0; i<a.length; i++){
			if(b != null && a[i] != null && a[i].equals(b)){
				return i;
			}
		}
		return -1;
	}
	
	public static <E> boolean contains(E[] a, E b){
		for(E e : a){
			if(e != null && b != null && e.equals(b)){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@SafeVarargs
	public static <E> E[] without(E[] array, Class<E> c, E... remove){
		E[] removed = array.clone();
		for(E e : remove){
			int index = indexOf(array, e);
			if(index >= 0){  // TODO: NOTE: Fixed removal of first indexed items
				removed[index] = null;
			}
		}
		return compact(removed, c);
	}

}
