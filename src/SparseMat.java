import java.util.Iterator;
import java.util.ListIterator;

import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

public class SparseMat<E extends Comparable> implements Cloneable
{
   protected int rowSize, colSize;
   protected E defaultVal;
   protected FHarrayList<FHlinkedList<MatNode>> rows;

   public SparseMat(int rowSize, int colSize, E defaultVal)
   {
      if (rowSize < 1)
      {
         rowSize = 1;
      }
      if (colSize < 1)
      {
         colSize = 1;
      }
      this.rowSize = rowSize;
      this.colSize = colSize;
      this.defaultVal = defaultVal;
      rows = new FHarrayList<FHlinkedList<MatNode>>(rowSize);    
      allocateEmptyMatrix();
   }

   // helper method for the constructor
   private void allocateEmptyMatrix()
   {
      for (int i = 0; i < rowSize; i++)
      {
         FHlinkedList<MatNode> starter = new FHlinkedList<MatNode>();
         rows.add(starter);
      }
   }

   protected E get(int row, int col)
   {
      MatNode node;

      if (!valid(col, row))
         throw new IndexOutOfBoundsException();

      ListIterator<MatNode> listIter = rows.get(row).listIterator();
      while (listIter.hasNext())
      {
         node = listIter.next();

         if (node.col == col)
         {
            return node.data;
         }
      }
      return defaultVal;
   }
   protected boolean valid(int row, int col)
   {
      if (row>= 0 && col  >= 0 && row < rowSize && col >= colSize)
         return true;
      return false;
   }

   protected boolean set(int row, int col, E x)
   {
      if (!valid(col, row))
         return false;
      
      MatNode matNode = new MatNode(col, x);

      ListIterator<MatNode> iterS;

     for (iterS = (ListIterator<MatNode>)rows.get(row).listIterator() ;
           iterS.hasNext();)
    
         // check to see if there is an existent node
         if (iterS.next().col == col)
         {
            // removes node if defaultVal
            if (x.equals(defaultVal))
            {
               iterS.remove();
               return true;
            }
            
            iterS.set(matNode);
            return true;
         }

//         if (iterS.next().col > c)
//         {
            iterS.previous();
            iterS.add(matNode);
            return true;
//         }       
//      return true;
   }

   protected void clear()
   {
      Iterator<FHlinkedList<SparseMat<E>.MatNode>> iterR = rows.iterator();

      while (iterR.hasNext())
      {
         iterR.next().clear();
      }
   }


   public void showSubSquare(int start, int size) 
   {
      int row, col;
      
      if (start < 0 || size <0
            || start + size > rowSize
            || start + size > colSize)
         return;
      for (row = start; row < start + size; row++)
      {
         for (col = start; col < start + size; col++)
            System.out.println( String.format("%5.1f",
                  (Double)get(row, col)) + "\t");
      }
   }
  
   
   // protected enables us to safely make col/data public
   protected class MatNode implements Cloneable
   {
      public int col;
      public E data;

      // we need a default constructor for lists
      MatNode()
      {
         col = 0;
         data = null;
      }

      MatNode(int cl, E dt)
      {
         col = cl;
         data = dt;
      }

      public Object clone() throws CloneNotSupportedException
      {
         // shallow copy
         MatNode newObject = (MatNode) super.clone();
         return (Object) newObject;
      }
   };

}
