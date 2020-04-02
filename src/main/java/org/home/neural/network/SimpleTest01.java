package org.home.neural.network;

public class SimpleTest01 {
	
	public static void main(String[] args) {
		int[] x = {1, 0, 1}; 
		int[] w = {5, 5, 3};
		int net = 0;
		
		for (int i=0; i<x.length; i++) {
			net += x[i] * w[i];
		}
		System.out.println("sum=" + net);
		
		double out = 1/ (1 + Math.pow(Math.E, -net));
		
		System.out.println("out=" + out);
		
	}

}
