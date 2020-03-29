package bmi_service_publisher;
import java.lang.Math;

public class ServiceBmiImpl implements ServiceBmi{

	@Override
	public double bmi(double weight, double height) {
		double result = (weight/Math.pow((height*0.01), 2));
		return result;
	}
	
	

}
