import com.phone.OS;
import com.phone.OperatingSystemFactory;

public class FactoryMain {
	public static void main(String arg[])
	{
		OperatingSystemFactory osf = new OperatingSystemFactory();
		
		OS obj = osf.getInstance("open");
		obj.spec();
		
		OS obj1 = osf.getInstance("close");
		obj1.spec();
		
		OS obj2 = osf.getInstance("another");
		obj2.spec();
		
	}
}
