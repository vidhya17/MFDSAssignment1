package mfds;
// Java program to round-off a number to given no. of
// significant digits

import java.io.*;
import java.util.Random;

import static java.lang.Math.*;
public class MathUtil {

	// Function to round - off the number
	static double Roundoff(double N, double n)
	{
		int h;
		double l, a, b, c, d, e, i, j, m, f, g;
		b = N;
		c = floor(N);

		// Counting the no. of digits to the left of decimal point
		// in the given no.
		for (i = 0; b >= 1; ++i)
			b = b / 10;

		d = n - i;
		b = N;
		b = b * pow(10, d);
		e = b + 0.5;
		if ((float)e == (float)ceil(b)) {
			f = (ceil(b));
			h = (int)(f - 2);
			if (h % 2 != 0) {
				e = e - 1;
			}
		}
		j = floor(e);
		m = pow(10, d);
		j = j / m;
		return j;
	}
	

	public static double[][] RoundOffMatrix(double[][]A,int m,int n, int d) { 		
		int i,j; 	
		for( i=1; i <= m ; i++ ) {
			for( j=1; j <= n ; j++ ) {
				A[i][j] = Roundoff(A[i][j],d);
				System.out.print(A[i][j]);
				System.out.print("  ");
			}	
			System.out.print("\n");
		}	
		System.out.print("\n");
		System.out.print("\n");
		return A; 	
		
	}
	public static double[][] genRandomMatrix(int m,int n) { 		
		double [][]A=new double[m+1][n+1]; 	
		Random R=new Random(); 	
		
		
		int i,j; 	
		for( i=1; i <= m ; i++ ) {
			for( j=1; j <= n ; j++ ) {
				A[i][j]=R.nextInt(9)+R.nextDouble(); 	
				System.out.print(A[i][j]);
				System.out.print("  ");
			}	
			System.out.print("\n");
		}	
		System.out.print("\n");
		System.out.print("\n");
		return A; 	
		
	}


	public static void copyArray(double[][] matOrig, double[][] mat, int n) {
		// TODO Auto-generated method stub
		int i,j; 	
		for( i=1; i <= n ; i++ ) 
		{
			for( j=1; j <= n+1 ; j++ ) 
			{
				mat[i][j]= matOrig[i][j];
			}
		
		}
		
	}

}
