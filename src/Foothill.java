// CIS 1C Assignment #2 
// Instructor Solution Featuring clone()

// client -----------------------------------------------------
import cs_1c.*;
import java.util.*;

//------------------------------------------------------
public class Foothill
{
   final static int MAT_SIZE = 5;//

   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      // 100000 x 100000 filled with 0
      int k; //
      SparseMat<Double> matA //
         = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.); //
      SparseMatWMult matB
         = new SparseMatWMult(MAT_SIZE, MAT_SIZE, 0.);
      
      // test mutators

      for (k = 0; k < 5; k++)
      {
         matB.set(0, k, k * 1.0 +1.0);
         matB.set(1, k, -(k * 1.0 +1.0));
         matB.set(2, k, (k%2 +k%2 +1.0));
         matB.set(3, k, k%2 + 0.);
         matB.set(4, k, -1.);
      }
      
//      matB.set(0,  2, 115.0);
//      matB.set(2,  0, 10.);
//      matB.set(2,  4, 30.);
      

      matB.showSubSquare(0,MAT_SIZE);
      matB = (SparseMatWMult) matB.transposeMat(matB);
      matB.showSubSquare(0, MAT_SIZE);
//      SparseMat<Double> mat2 //
//         = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.); //
//
//      mat2.set(99995, 99995, 55.);
//
//      mat2.showSubSquare(99989, 10);

      System.out.println("rowSize = " + matB.rowSize);

   }
}