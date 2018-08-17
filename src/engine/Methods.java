package engine;

public class Methods {
	public static int clamp(int value, int minValue, int maxValue) {
		if(value <= minValue) {
			return minValue;
		}else if(value >= maxValue) {
			return maxValue;
		}else {
			return value;
		}
	}
}
