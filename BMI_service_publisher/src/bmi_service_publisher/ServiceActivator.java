package bmi_service_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	// registering 
	ServiceRegistration publisherServiceRegistration;
	

	public void start(BundleContext context) throws Exception {
		System.out.println("BMI calculator service is activated!");
		ServiceBmi serviceBmi = new ServiceBmiImpl();
		
		publisherServiceRegistration = context.registerService(ServiceBmi.class.getName(), serviceBmi, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("BMI calculator is deactivated!");
		publisherServiceRegistration.unregister();
	}

}
