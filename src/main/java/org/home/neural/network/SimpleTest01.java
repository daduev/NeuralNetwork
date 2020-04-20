package org.home.neural.network;

import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.Precision;

public class SimpleTest01 {
	
	public static void main(String[] args) {
		double[] inputs01 = {0.2, 0.5};
		double[][] weights234 = {{-1.0, 2.0}, {1.0, 1.0}, {3.0, -2.0}};
		double[] weights5 = {1.0, 2.0, 4.0};
		
//		double net1 = inputs[0] * weights[0][0] + inputs[1] * weights[0][1];  
//		double net2 = inputs[0] * weights[1][0] + inputs[1] * weights[1][1];  
//		double net3 = inputs[0] * weights[2][0] + inputs[1] * weights[2][1];  
		
		RealVector i = new ArrayRealVector(inputs01);
		RealMatrix w = new Array2DRowRealMatrix(weights234);
		
		UnivariateFunction a = x -> {
			double f = 1 / (1 + Math.exp(-x));
			return Precision.round(f, 4);
		};
		
		double[] nets234 = w.operate(i).toArray();
		double[] fnets234 = new ArrayRealVector(nets234).mapToSelf(a).toArray();
		
		double net5 = new ArrayRealVector(fnets234).ebeMultiply(new ArrayRealVector(weights5)).getL1Norm();
		net5 = Precision.round(net5, 4);
		
		double fnet5 = a.value(net5);
		
		UnivariateFunction e5 = x -> {
			double f = (0.4 - x) * x * (1 - x);
			return Precision.round(f, 4);
		};		
		double error5 = e5.value(fnet5);
		
		double[] fixWeight234 = new ArrayRealVector(weights5).mapMultiply(error5).toArray();
		
		UnivariateFunction ee = x -> {
			double f = 1.0 - x;
			return Precision.round(f, 4);
		};
		
		double[] errors234 = new ArrayRealVector(fixWeight234).ebeMultiply(new ArrayRealVector(fnets234))
				.ebeMultiply(new ArrayRealVector(fnets234).mapToSelf(ee)).toArray();
		
		//System.out.println(new ArrayRealVector(inputs01).ebeMultiply(new ArrayRealVector(errors234)).toString());
		
		System.out.println(error5);
	}
	
}
