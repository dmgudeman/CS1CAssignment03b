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

            for (int k = 0; k < matB.rowSize; k++)
            {
               double temp = (matA.get(x, k).doubleValue() * matB.get(i, k)
                     .doubleValue());
               sum = sum + temp;
            }
            System.out.print(sum + "\t");
         }
         System.out.println("\n");
      }
      return matC;
   }
}
