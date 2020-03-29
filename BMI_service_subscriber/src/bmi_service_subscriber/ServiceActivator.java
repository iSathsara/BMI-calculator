package bmi_service_subscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import bmi_service_publisher.ServiceBmi;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ServiceActivator implements BundleActivator {

	Scanner inputs = new Scanner(System.in);
	
	// variable for continue checking
	String checkAgain = "";
	
	// reference
	ServiceReference serviceReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("subscriber activated!\n");
		
		do {
			serviceReference = context.getServiceReference(ServiceBmi.class.getName());
		
			double weight = 0;
			double height = 0;
			double min_weight = 0;
			double max_weight = 0;
			double bmi = 0;
		
			System.out.print("Enter your weight (kg) : ");
			weight = inputs.nextDouble();
			System.out.print("Enter your height (cm) : ");
			height = inputs.nextDouble();
		
			ServiceBmi servicebmi = (ServiceBmi)context.getService(serviceReference);
			bmi = servicebmi.bmi(weight, height);
			
			// set decimal places
			DecimalFormat format = new DecimalFormat("#.###");
			format.setRoundingMode(RoundingMode.CEILING);
			
			min_weight = 18 * Math.pow((height*0.01), 2);
			max_weight = 24 * Math.pow((height*0.01), 2);
			
			System.out.println("Your BMI value is      : "+format.format(bmi));
			
		
			// give health tips
			if(bmi <= 18) {
				System.out.println("\nUnhealthy state ==");
				System.out.println("You are in an UNDERWEIGHT condition!");
				System.out.println("You should keep your weight more than "+format.format(max_weight)+" kg.");
			}
			if(bmi > 18 && bmi < 25) {
				System.out.println("\nHealthy state ==");
				System.out.println("Good! You are a HEALTHY person");
				System.out.println("You can keep your weight between "+format.format(min_weight)+" kg and "+format.format(max_weight)+" kg.");
			}
			if(bmi>=25) {
				System.out.println("\nUnhealthy state ==");
				if(bmi<=29) {
					System.out.println("You are in an OVERWEIGHT condition");
					System.out.println("You should keep your weight less than "+format.format(min_weight)+" kg.");
				}
				if(bmi > 29 && bmi <= 39) {
					System.out.println("You are in OBESE condition ! ");
					System.out.println("You should keep your weight less than "+format.format(min_weight)+" kg.");
				}
				if(bmi > 39) {
					System.out.println("You are in EXTREMELY OBESE condition !! ");
					System.out.println("You should keep your weight less than "+format.format(min_weight)+" kg.");
				}
			}
		
			
			// ask subscriber to continue
			System.out.print("\nDo you want to check again (y/n) ? ");
			checkAgain = inputs.next();
			System.out.println();
			
		}while(checkAgain.equals("y"));
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("subscriber deactivated!");
		context.ungetService(serviceReference);
	}
	

}
