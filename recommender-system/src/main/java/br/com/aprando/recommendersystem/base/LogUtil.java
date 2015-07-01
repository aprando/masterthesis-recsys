package br.com.aprando.recommendersystem.base;

public class LogUtil {
	
	public static void print(final BaseException bex) {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(bex.getUuid());
		System.out.println(bex.getErro());
		bex.printStackTrace();
		System.out.println("--------------------------------------------------------------------------------");
	}
	
	public static void print(final String id, final Exception e) {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(id);
		System.out.println(e.getMessage());
		e.printStackTrace();
		System.out.println("--------------------------------------------------------------------------------");
	}

}
