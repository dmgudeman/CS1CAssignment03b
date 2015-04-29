import java.util.ListIterator;

public class SparseMatWMult extends SparseMat<Double>
{

   public SparseMatWMult(int rowSize, int colSize, Double defaultVal)
   {
      super(rowSize, colSize, defaultVal);

   }

   public SparseMat<Double> transposeMat(SparseMatWMult primeMat)
         throws CloneNotSupportedException
   {
      SparseMatWMult matTemp = new SparseMatWMult(primeMat.rowSize,
            primeMat.colSize, 0.);
      for (int r = 0; r < primeMat.rowSize; r++)
      {
         for (int c = 0; c < primeMat.colSize; c++)
         {
            Double temp = primeMat.get(r, c).doubleValue();

            matTemp.set(c, r, temp);
         }
      }
      primeMat = (SparseMatWMult) matTemp.clone();

      return primeMat;
   }

   static SparseMat<Double> matMult(SparseMat<Double> matA,
         SparseMat<Double> matB, SparseMat<Double> matC)
   {
      if (matA.colSize != matB.colSize || matB.colSize != matC.colSize
            || matA.colSize != matC.colSize)
      {
         throw new IndexOutOfBoundsException();
      }

      for (int x = 0; x < matA.rowSize; x++)
      {
        
         for (int i = 0; i < matA.rowSize; i++)
         {
            Double sum = 0.0;
            Double product = 0.0;
 
//           ListIterator<MatNode> iterA = (ListIterator<MatNode>)matA.rows.get(x).listIterator();
//            while (iterA.hasNext()) 
            for (MatNode tempA : matA.rows.get(x)) 
            {
//              int indexA = iterA.nextIndex();
              
//              ListIterator<MatNode> iterB = (ListIterator<MatNode>)matB.rows.get(i).listIterator();
//              while (iterB.hasNext())
               for (MatNode tempB : matB.rows.get(i)) 
              {
//                int indexB = iterB.nextIndex();
                if (tempA.col == tempB.col) 
                {
//                                System.out.println("x:" + x +"i: " + i);        
                                product = tempA.data * tempB.data;
                              sum = sum + product;
                
                }
               }
                        
            }  
           matC.set(x, i, sum);
           
//            System.out.print(sum + "\t");   
         }
//         System.out.println("\n");
      }
      return matC;
   }

   public void showSubSquare(int start, int size, String title)
   {
      super.showSubSquare(start, size, title);
      
   }
}
