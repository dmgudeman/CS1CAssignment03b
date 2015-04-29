// CIS 1C Assignment #2 
// Instructor Solution Featuring clone()

// client -----------------------------------------------------
import cs_1c.*;

import java.text.NumberFormat;
import java.util.*;

//------------------------------------------------------
public class Foothill
{
   final static int MAT_SIZE = 200;//

   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      long startTime, stopTime;
      double smallPercent;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // 100000 x 100000 filled with 0
      int k; //
      SparseMatWMult matA //
         = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.); //
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
      
      populateMat((SparseMatWMult) matA, 0.10);
      populateMat((SparseMatWMult) matB, 0.10);
      

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
      

      matA.showSubSquare(0, 5, "Matrix A");
      matB = (SparseMatWMult) matB.transposeMat(matB);
      matB.showSubSquare(0, 5, "Matrix B after transformaion");
     
      
      startTime = System.nanoTime();
      SparseMatWMult .matMult( matA, matB, 
            matC);
     
      stopTime = System.nanoTime();

      matC.showSubSquare(0, 5, "Matrix C (answer): ");    

      System.out.println("\nSize = " + MAT_SIZE + " Mat. Mult. Elapsed Time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds.");
      
//      SparseMat<Double> mat2 //
//         = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.); //
//
//      mat2.set(99995, 99995, 55.);
//
//      mat2.showSubSquare(99989, 10);

      System.out.println("rowSize = " + matB.rowSize);

   }
   
   
   public static SparseMatWMult populateMat(SparseMatWMult mat, Double percent)
   { 
      for (int k = 0; k < mat.colSize; k++)
         for (int j = 0; j < mat.colSize; j++)
         {
            double d = Math.random();
            if (d < percent)
               mat.set(k, j, d);  
            else
               mat.set(k, j, 0.0); ;
         }
      return mat;
   }
}