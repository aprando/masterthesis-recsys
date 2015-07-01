package br.com.aprando.recommendersystem.service;

import org.junit.Test;

import br.com.aprando.recommendersystem.base.ServiceException;

public class SocialMidiaServiceTest {

	@Test
	public void test() {
		try {
			SocialMidiaService service = new SocialMidiaServiceImpl();
			service.consultarFacebookUserJson("690010777758497");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
