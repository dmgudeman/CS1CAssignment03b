// CIS 1C Assignment #2 
// Instructor Solution Featuring clone()

// client -----------------------------------------------------
import cs_1c.*;
import java.util.*;

//------------------------------------------------------
public class Foothill
{
   final static int MAT_SIZE = 99000;//

   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      // 100000 x 100000 filled with 0
      int k; //
      SparseMat<Double> mat //
         = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.); //

      // test mutators

      for (k = 0; k < 10; k++)
      {
         mat.set(k, k, k * 1.);
         mat.set(4, k, k * 10.);
         mat.set(k, 4, k * -10.);
      }

      mat.showSubSquare(0, 12);

      SparseMat<Double> mat2 //
         = new SparseMat<Double>(MAT_SIZE, MAT_SIZE, 0.); //

      mat2.set(99995, 99995, 55.);

      mat2.showSubSquare(99989, 10);

      System.out.println("rowSize = " + mat.rowSize);

   }
}