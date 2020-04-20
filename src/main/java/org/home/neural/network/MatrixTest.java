package org.home.neural.network;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.math3.util.Precision;

public class MatrixTest {

	public static void main(String[] args) {
		double[] input = { 0.2, 0.5 };
		double[][] weights234 = { new double[] {-1d, 2d}, new double[] {1d, 1d}, new double[] {3d, -2d} };	
		double[] weights5 = {1.0, 2.0, 4.0};
		double expectedOutput = 0.4;
		
		double[] nets234 = multiplyMatrices(input, weights234);

		double[] fnets234 = applyActiveFunction(nets234);
		

		
		double net5 = multiplyMatricesV2(fnets234, weights5);
		
		double fnet5 = activeFunction(net5);
		
		double error5 = evalError(fnet5, expectedOutput);
		
		double[] deltaError234 = applyOutputError(weights5, error5);
		
		double[] error234 = evalErrorV2(deltaError234, fnets234);

		System.out.println(error5);
		System.out.println(error234[0]);
		System.out.println(error234[1]);
		System.out.println(error234[2]);
		
		System.out.println("------------------------------");
		
		double[][] deltaWight234 = evalDeltaWeights(input, error234);
		double[] deltaWeight5 = evalDeltaWeights(fnets234, error5);
		
		System.out.println(Arrays.toString(deltaWight234[0]));
		System.out.println(Arrays.toString(deltaWight234[1]));
		System.out.println(Arrays.toString(deltaWight234[2]));
		System.out.println(Arrays.toString(deltaWeight5));

	}
	
	static double[][] evalDeltaWeights(double[] input, double[] error){
		double[][] result = new double[error.length][input.length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.println(error[i] + "   " + input[j]);
				result[i][j] = error[i] * input[j] * 0.85;
			}
		}
		return result;
	}
	
	static double[] evalDeltaWeights(double[] input, double error) {
		double[] result = new double[input.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = input[i] * error * 0.85;
		}
		return result;
	}
	
	static double[] multiplyMatrices(double[] firstMatrix, double[][] secondMatrix) {
	    double[] result = new double[secondMatrix.length];
	    for (int row = 0; row < result.length; row++) {
    		result[row] = multiplyMatricesCell(firstMatrix, secondMatrix, row);
	    }
	    return result;
	}
	
	static double multiplyMatricesCell(double[] firstMatrix, double[][] secondMatrix, int col) {
	    double cell = 0;
	    for (int i = 0; i < secondMatrix[col].length; i++) {
	        cell += firstMatrix[i] * secondMatrix[col][i];
	    }
	    return Precision.round(cell, 4);
	}
	
	static double multiplyMatricesV2(double[] firstMatrix, double[] secondMatrix) {
		double result = 0;
		for (int i = 0; i < firstMatrix.length; i++) {
			result += firstMatrix[i] * secondMatrix[i];
		}
		return Precision.round(result, 4);
	}
	
	static double[] applyActiveFunction(double[] nets) {
		return DoubleStream.of(nets).map(MatrixTest::activeFunction).toArray();		
	}
	
	static double activeFunction(double x) {
		double f = 1 / (1 + Math.exp(-x));
		return Precision.round(f, 4);
	}
	
	static double evalError(double x, double value) {
		double f = (value - x) * x * (1 - x);
		return Precision.round(f, 4);
	}
	
	static double[] evalErrorV2(double[] deltaError234, double[] fnets) {
		double[] result = new double[deltaError234.length];
		for (int i = 0; i < deltaError234.length; i++) {
			double tr = deltaError234[i] * fnets[i] * (1 - fnets[i]);
			result[i] = Precision.round(tr, 4);
		}
		return result;
	}
	
	static double[] applyOutputError(double[] weights, double value) {
		return DoubleStream.of(weights).map(x -> {
			return x * value;
		}).toArray();
	}

}
