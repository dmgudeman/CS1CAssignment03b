import java.util.ListIterator;

public class SparseMatWMult extends SparseMat<Double>
{
   public SparseMatWMult(int rowSize, int colSize, Double defaultVal)
   {
      super(rowSize, colSize, defaultVal);

   }
   // This method transposes the the colums and rows in the second
   // matrix. This puts the columns on the array list and sets it
   // up for the multiplication algorithm
   public SparseMat<Double> transposeMat(SparseMatWMult secondMat)
         throws CloneNotSupportedException
   {
      SparseMatWMult matTemp = new SparseMatWMult(secondMat.rowSize,
            secondMat.colSize, 0.);
      for (int r = 0; r < secondMat.rowSize; r++)
      {
         for (int c = 0; c < secondMat.colSize; c++)
         {
            Double temp = secondMat.get(r, c).doubleValue();
            matTemp.set(c, r, temp);
         }
      }
      secondMat = (SparseMatWMult) matTemp.clone();

      return secondMat;
   }
   // This algorithm multiplies by looping exhaustively through
   // the arraylists of both matrices. This allows the respective
   // linkedLists to be compared and multiplied. Only works after
   // the second matrix has been prepared with transposition
   static SparseMat<Double> matMult(SparseMat<Double> matA,
         SparseMat<Double> matB, SparseMat<Double> matC)
   {
      if (matA.colSize != matB.colSize || matB.colSize != matC.colSize
            || matA.colSize != matC.colSize)
      {
         throw new IndexOutOfBoundsException();
      }
      // first arrayList loop
      for (int x = 0; x < matA.rowSize; x++)
      {
         // second arrayList loop
         for (int i = 0; i < matA.rowSize; i++)
         {
            Double sum = 0.0;
            Double product = 0.0;
            // first loop with linkedList iterator
            for (MatNode tempA : matA.rows.get(x))
            {
               // second loop for second matrix linkedList
               for (MatNode tempB : matB.rows.get(i))
               {
                  if (tempA.col == tempB.col)
                  {
                     // if a match is found, multiply
                     product = tempA.data * tempB.data;
                     // add to the sum
                     sum = sum + product;
                  }
               }
            }
            // set the summation for that cell in matrix C
            matC.set(x, i, sum);
         }
      }
      return matC;
   }

   public void showSubSquare(int start, int size, String title)
   {
      super.showSubSquare(start, size, title);

   }
}
