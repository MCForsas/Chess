package engine;

/*
 * Clamps a value between min and max
 * @author MCForsas 2018
 */

public class Methods {
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
