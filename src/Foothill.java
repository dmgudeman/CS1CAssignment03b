// CIS 1C Assignment #2 
// Instructor Solution Featuring clone()

// client -----------------------------------------------------
import java.text.NumberFormat;
import java.util.Locale;

//------------------------------------------------------
public class Foothill
{
   final static int MAT_SIZE = 100;
   final static double smallPercent = 0.01;

   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      long startTime, stopTime;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      SparseMatWMult matA 
         = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.); 
      SparseMatWMult matB
         = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.);
      SparseMatWMult matC
         = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.);
      
      // test mutators

//      for (k = 0; k < 5; k++)
//      {
//         matA.set(0, k, k * 1.0 +1.0);
//         matA.set(1, k, -(k * 1.0 +1.0));
//         matA.set(2, k, (k%2 +k%2 +1.0));
//         matA.set(3, k, k%2 + 0.);
//         matA.set(4, k, -1.);
//      }
      
      populateMat((SparseMatWMult) matA);
      populateMat((SparseMatWMult) matB);
      

      matB.set(0,  0, 2.0);
      matA.set(0, 0, 1.);
      
      matB.set(2,  2, 3.0);
      matA.set(2, 2, -1.);
      
//      matB.set(0,  1, 1.);
//      matB.set(0,  2, 5.);
//      matB.set(0,  3, 00.);
//      matB.set(0,  4, 2.);
//
//      matB.set(1,  0, 1.0);
//      matB.set(1,  1, 4.);
//      matB.set(1,  2, 3.);
//      matB.set(1,  3, 2.);
//      matB.set(1,  4, 7.);
//
//      matB.set(2,  0, 4.0);
//      matB.set(2,  1, 4.);
//      matB.set(2,  2, 4.);
//      matB.set(2,  3, 4.);
//      matB.set(2,  4, 4.);
//
//      matB.set(3,  0, 7.0);
//      matB.set(3,  1, 1.);
//      matB.set(3,  2, -1.);
//      matB.set(3,  3, -1.);
//      matB.set(3,  4, -1.);
//      
//      matB.set(4,  0, 0.0);
//      matB.set(4,  1, 0.0);
//      matB.set(4,  2, 8.);
//      matB.set(4,  3, -1.);
//      matB.set(4,  4, -6.);
      

//      matA.showSubSquare(0, 5, "A");
        matB = (SparseMatWMult) matB.transposeMat(matB);
//      matB.showSubSquare(0, 5, "B after transformation");
           
      startTime = System.nanoTime();
      SparseMatWMult .matMult( matA, matB, 
            matC);
     
      stopTime = System.nanoTime();

//      matC.showSubSquare(0, 5, "C (answer): ");    

      System.out.println("\nSparseness Value: " + 100 * smallPercent + "%\t   Size: " + MAT_SIZE + " X " + MAT_SIZE +  "\t   Mat. Mult. Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds");

      
      System.out.println("Test SparseMat constructor");
      try
      {
         SparseMat<Double> badMat
            = new SparseMat<Double>(MAT_SIZE, -MAT_SIZE, 0.);
      }
      catch(IllegalArgumentException e)
      {
         System.out.println("oops - bad arg in SparseMat constructor");
      }
   }
     
   public static SparseMatWMult populateMat(SparseMatWMult mat)
   { 
      for (int k = 0; k < mat.colSize; k++)
         for (int j = 0; j < mat.colSize; j++)
         {
            double d = Math.random();
            if (d < smallPercent)
               mat.set(k, j, d);  
            else
               mat.set(k, j, 0.0); ;
         }
      return mat;
   }
}