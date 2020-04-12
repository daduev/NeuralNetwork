package org.home.neural.network;

import org.apache.commons.lang3.math.Fraction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.Precision;

public class SimpleTest01 {
	
	public static void main(String[] args) {
		double[] inputs = {0.2, 0.5};
		double[][] weights = {{-1.0, 2.0}, {1.0, 1.0}, {3.0, -2.0}};
		
		double net1 = inputs[0] * weights[0][0] + inputs[1] * weights[0][1];  
		double net2 = inputs[0] * weights[1][0] + inputs[1] * weights[1][1];  
		double net3 = inputs[0] * weights[2][0] + inputs[1] * weights[2][1];  
		
		RealVector i = new ArrayRealVector(inputs);
		RealMatrix w = new Array2DRowRealMatrix(weights);
		
		UnivariateFunction a = x -> {
			double f = 1 / (1 + Math.exp(-x));
			return Precision.round(f, 2);
		};
		
		System.out.println(w.operate(i).mapToSelf(a).toString());

	}
	
	static double applyActivation(double x) {
		return 1 / (1 + Math.exp(-x));
	}

}
