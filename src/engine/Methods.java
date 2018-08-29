package engine;

/*
 * Hold's some usefull methods
 * @author MCForsas 2018
 */

public class Methods {
	
	/*
	 * Clamp value between min and max
	 * @param float variable to clamp
	 * @param float minValue min value to allow variable to be
	 * @param float maxValue max value to allow variable to be
	 * @return float clampedValue 
	 */
	public static float clamp(float value, float minValue, float maxValue) {
		if(value <= minValue) {
			return minValue;
		}else if(value >= maxValue) {
			return maxValue;
		}else {
			return value;
		}
	}

}
