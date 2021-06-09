package mfds;
import java.io.*;
import java.util.Scanner;
class GE
{
 
  public static boolean pivoting = true;
 
  // function to get matrix content
  static void gaussianElimination(double mat[][],int n,int d)
  {
 
    /* reduction into r.e.f. */
    int singular_flag = forwardElim(mat,n,d);
 
    /* if matrix is singular */
    if (singular_flag == -1)
    {
      System.out.println("Singular Matrix.");
 
      /* if the RHS of equation corresponding to
               zero row  is 0, * system has infinitely
               many solutions, else inconsistent*/
      if (mat[singular_flag][n] != 0)
        System.out.print("Inconsistent System.");
      else
        System.out.print(
        "May have infinitely many solutions.");
 
      return;
    }
 
    /* get solution to system and print it using
           backward substitution */
    backSub(mat,n,d);
  }
 
  // function for elementary operation of swapping two
  // rows
  static void swap_row(double mat[][], int n, int i, int j)
  {
    // printf("Swapped rows %d and %d\n", i, j);
 
    for (int k = 1; k <= n+1; k++)
    {
      double temp = mat[i][k];
      mat[i][k] = mat[j][k];
      mat[j][k] = temp;
    }
  }
 
  // function to print matrix content at any stage
  static void print(double mat[][],int m, int n)
  {
    for (int i = 1; i <= m; i++, System.out.println())
      for (int j = 1; j <= n; j++)
        System.out.print(mat[i][j]+"   ");
    System.out.println();
  }
 
  // function to reduce matrix to r.e.f.
  static int forwardElim(double mat[][],int n,int d)
  {
    for (int k = 1; k <= n-1; k++)
    {
    	if(pivoting) {
		      // Initialize maximum value and index for pivot
		      int m = k;
		      double v_max = Math.abs(mat[m][k]);
		 
		      /* find greater amplitude for pivot if any */
		      
		      for (int j = k + 1; j <= n; j++)
		      {
		        if (v_max < Math.abs(mat[j][k]))
		        {
		          v_max = mat[j][k];
		          m = j;
		        }
		      }
		 
		      /* if a prinicipal diagonal element  is zero,
		             * it denotes that matrix is singular, and
		             * will lead to a division-by-zero later. */
		      if (mat[m][k] == 0)
		        return -1; // Matrix is singular
		 
		      /* Swap the greatest value row with current row
		             */
		      if (m != k)
		        swap_row(mat, n,k, m);
    	}
    	 print(mat,n,n+1); 
		     
		      if(mat[n][n]==0)
		    	  return -1;
	    
 
      for (int j = k + 1; j <= n; j++)
      {
 
        /* factor f to set current row kth element
                 * to 0, and subsequently remaining kth
                 * column to 0 */
        double f = mat[j][k] / mat[k][k];
        f = MathUtil.Roundoff(f, d);
 
        /* subtract fth multiple of corresponding
                   kth row element*/
        for (int p = k + 1; p <= n+1; p++)
        {
          mat[j][p] =  MathUtil.Roundoff(mat[j][p] - MathUtil.Roundoff(mat[k][p] * f,d),d);;
        }
       
        /* filling lower triangular matrix with
                 * zeros*/
        mat[j][k] = 0;
      }
 
       print(mat,n,n+1);        //for matrix state
    }
 
     print(mat,n,n+1);            //for matrix state
    return 0;
  }
 
  // function to calculate the values of the unknowns
  static void backSub(double mat[][],int n,int d)
  {
    double x[]
      = new double[n+1]; // An array to store solution
 
    x[n]=mat[n][n+1]/mat[n][n];
    x[n] =MathUtil.Roundoff(x[n], d);
    /* Start calculating from last equation up to the
           first */
    for (int i = n-1; i >= 1; i--)
    {
 
      /* start with the RHS of the equation */
      x[i] = mat[i][n+1];
 
      /* Initialize j to i+1 since matrix is upper
               triangular*/
      for (int j = i + 1; j <= n; j++)
      {
 
        /* subtract all the lhs values
                 * except the coefficient of the variable
                 * whose value is being calculated */
        x[i] -= mat[i][j] * x[j];
      }
 
      /* divide the RHS by the coefficient of the
               unknown being calculated */
      x[i] = x[i] / mat[i][i];
      x[i] = MathUtil.Roundoff(x[i], d);
    }
 
    System.out.println();
    System.out.println("Solution for the system:");
    for (int i = 1; i < n+1; i++)
    {
      System.out.format("x%d=%f",i, x[i]);
      System.out.println();
    }
	System.out.print("\n");
  }
 
  // Driver program
  public static void main(String[] args)
  {
	
    int [] d= {3,4,5}; 
    int n=3;
    /* input matrix */
    double matOrig[][] =  MathUtil.genRandomMatrix(n,n+1);/*{{0,0,0,0,0},{0,0,8,2,-7},{0,3,5,2,8},{0,6,2,8,26}};*/
    		
    		// MathUtil.genRandomMatrix(n,n+1);
   
    
    for(int sigDig:d) 
    {
    	System.out.println(sigDig);
    	double mat[][] = new double[n+1][n+2];
    	MathUtil.copyArray(matOrig,mat,n);
    	mat= MathUtil.RoundOffMatrix(mat,n, n+1, sigDig);
    	gaussianElimination(mat,n,sigDig);   
    }

  }
}