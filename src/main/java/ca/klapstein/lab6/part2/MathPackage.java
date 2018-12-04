public class MathPackage {
	
	/**
	 * Creates an array of n random values in the range [a,b]
	 * @param n Number of values to generate
	 * @param a lower bound
	 * @param b upper bound
	 * @return Array of random values
	 */
	public static double[] random(int n, double a, double b)
	{
		double[] values = new double[n];
		for(int i=0;i<values.length;i++)
			values[i] = a + (Math.random()*(b - a));
		
		return values;
	}
	
	/**
	 * Returns the maximum values contained in the passed array
	 * @param values Array to search in
	 * @return Highest value in passed array
	 */
	public static double max(double[] values) {
		double max = Double.MIN_VALUE;
		for(int i=0;i<values.length;i++){
			if(max <= values[i])
				max = values[i];
		}
		return max;
	}
	
	/**
	 * Returns the minimum values of an array
	 * @param values Array to search through
	 * @return Smallest value in the array
	 */
	public static double min(double[] values) {
		double min = Double.MAX_VALUE;
		for(int i=0;i<values.length;i++){
			if(min >= values[i])
				min = values[i];
		}
		return min;
	}
	
	/**
	 * Normalizes the values in the passed array to [0,1]
	 * @param values Array to be normalized
	 * @return Normalized array
	 */
	public static double[] normalize(double[] values) {
		double max = MathPackage.max(values);
		double min = MathPackage.min(values);
		double[] normalized = new double[values.length];
		
		for (int i=0;i<values.length;i++)
			normalized[i] = (min - values[i])/(max-min);
		
		return normalized;
	}
	
	/**
	 * Calculates the sum of the array elements
	 * @param values Array to sum
	 * @return summed value
	 */
	public static double sum(double[] values)
	{
		double sum = 0.0;
		for(int i=0;i<values.length;i++)
			sum += values[i];
		return sum;
	}
	
	/**
	 * Calculates the standard deviation of the values in the array
	 * @param values Array to calculate deviation of
	 * @return standard deviation
	 */
	public static double stddev(double[] values)
	{
		double mean = sum(values)/values.length;
		double variance = 0;
		for(int i=0;i<values.length;i++)
			variance += Math.pow(values[i]-mean, 2);
		return Math.sqrt(variance/values.length);
	}
	
	/**
	 * Adds two arrays together, element-wise
	 * @param d1 first array
	 * @param d2 second array
	 * @return result of addition
	 */
	public static double[] arrayAdd(double[] d1, double[] d2){
		double[] result = new double[d1.length];
		for(int i=0; i<result.length; i++){
			result[i] = d1[i] + d2[i];
		}
		return result;
	}
	
	/**
	 * Negates the values in the array
	 * @param d values
	 * @return result
	 */
	public static double[] arrayNegate(double[] d){
		double[] result = new double[d.length];
		for(int i=0; i<d.length; i++){
			result[i] = 0 - d[i];
		}
		return result;
	}	
}
