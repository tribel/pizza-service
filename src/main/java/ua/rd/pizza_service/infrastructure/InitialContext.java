package ua.rd.pizza_service.infrastructure;


public class InitialContext {

	private static Config config = new JavaConfig();
	
	@SuppressWarnings("unchecked")
	public <T> T getInstance(String context) {
		Class<?> type = config.getImpl(context);
		
		try {
			return (T)type.newInstance();
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
