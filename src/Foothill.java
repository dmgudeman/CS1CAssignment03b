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
       
      SparseMatWMult.populateMat((SparseMatWMult) matA, smallPercent);
      SparseMatWMult.populateMat((SparseMatWMult) matB, smallPercent);

      matB = (SparseMatWMult) matB.transposeMat(matB);
           
      startTime = System.nanoTime();
      SparseMatWMult .matMult( matA, matB, 
            matC);
     
      stopTime = System.nanoTime();

      System.out.println("\nSparseness Value: " + 100 * smallPercent + "%\t  "
            + " Size: " + MAT_SIZE + " X " + MAT_SIZE 
            + "\t   Mat. Mult. Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds");
     
      //--------------testing-----------------------------
      
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

      SparseMatWMult matTest1
         = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.); 
      SparseMatWMult matTest2
         = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.); 
      SparseMatWMult matTest3
         = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.); 
      
      // test mutators
      for (int k = 0; k < 5; k++)
      {
         matTest1.set(0, k, k * 1.0 +1.0);
         matTest1.set(1, k, -(k * 1.0 +1.0));
         matTest1.set(2, k, (k%2 +k%2 +1.0));
         matTest1.set(3, k, k%2 + 0.);
         matTest1.set(4, k, -1.);
      }
      // test accessors and exceptions
      System.out.println("\nTest get()");
      try
      {
         System.out.println( matTest1.get(0,0));
         System.out.println( matTest1.get(0,1));
         System.out.println( matTest1.get(0,2));
         
         // should throw an exception
         System.out.println( matTest1.get(-4,7));
      }
      catch( IndexOutOfBoundsException e)
      {
         System.out.println("oops - bounds in get()");
      } 
      
      matTest2.set(0, 0, 2.);
      matTest2.set(0, 1, 1.);
      matTest2.set(0, 2, 5.);
      matTest2.set(0, 3, 0.);
      matTest2.set(0, 4, 2.);

      matTest2.set(1, 0, 1.0);
      matTest2.set(1, 1, 4.);
      matTest2.set(1, 2, 3.);
      matTest2.set(1, 3, 2.);
      matTest2.set(1, 4, 7.);

      matTest2.set(2, 0, 4.0);
      matTest2.set(2, 1, 4.);
      matTest2.set(2, 2, 4.);
      matTest2.set(2, 3, 4.);
      matTest2.set(2, 4, 4.);

      matTest2.set(3, 0, 7.0);
      matTest2.set(3, 1,  1.);
      matTest2.set(3, 2, -1.);
      matTest2.set(3, 3, -1.);
      matTest2.set(3, 4, -1.);
      
      matTest2.set(4, 0, 0.0);
      matTest2.set(4, 1,  0.);
      matTest2.set(4, 2,  8.);
      matTest2.set(4, 3, -1.);
      matTest2.set(4, 4, -6.);
           
      matTest1.showSubSquare(0, 5, "matTest1");
      matTest2.showSubSquare(0, 5, "matTest2");
      matTest2 = (SparseMatWMult) matTest2.transposeMat(matTest2);
      matTest2.showSubSquare(0, 5, "matTest2 after transformation");
      SparseMatWMult .matMult( matTest1, matTest2, 
            matTest3);
      matTest3.showSubSquare(0, 5, "ANSWER: matTest3 after multiplication");
   }  
}