
public class Youtube {
	
	public static void main(String[] args)
	{
		Channel telusko = new Channel();
		
		Subscriber s1 = new Subscriber("aa");
		Subscriber s2 = new Subscriber("bb");
		Subscriber s3 = new Subscriber("cc");
		Subscriber s4 = new Subscriber("dd");
		Subscriber s5 = new Subscriber("ee");
		
		telusko.subscribe(s1);
		telusko.subscribe(s2);
		telusko.subscribe(s3);
		telusko.subscribe(s4);
		telusko.subscribe(s5);
		
		telusko.unSubscribe(s3);
		
		s1.subscribeChannel(telusko);
		s2.subscribeChannel(telusko);	
		s3.subscribeChannel(telusko);	
		s4.subscribeChannel(telusko);	
		s5.subscribeChannel(telusko);	
		
		telusko.upload("how to learn programming??");
	}

	
	
	
}
