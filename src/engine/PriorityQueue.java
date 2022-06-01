package engine;

import java.util.ArrayList;

public class PriorityQueue {
 
   private Comparable[] elements;
   private int nItems;
   private int maxSize;

   public PriorityQueue(int size){
      maxSize = size;
      elements = new Comparable[maxSize];
      nItems=0;
   }
    
   public void insert(Comparable item) {
      
      int i;
      for (i = nItems - 1;i >= 0 && item.compareTo(elements[i]) > 0;i--)
         elements[i + 1] = elements[i];

      elements[i + 1] = item;
      nItems++;
   }
   
    
   public Comparable remove() {
      nItems--;
      return elements[nItems];
   }
    
   public boolean isEmpty() {
      return (nItems == 0);
   }
    
   public boolean isFull() {
      return (nItems == maxSize);
   }
  
   public Comparable peekMin() {
      return elements[nItems-1];
   }
     
   public int size() {
      return nItems;
   }
   public ArrayList<Object> turnorderall(){
		
		  ArrayList<Object> x =new ArrayList<>() ;
		  for(int i=0 ;i<nItems;i++) {
			  x.add(elements[i]);
		  }
		  return x ;
	  }
   
}
