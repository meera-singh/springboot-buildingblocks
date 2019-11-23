package com.stack.simplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
@RequestMapping (method = RequestMethod.GET, path= "/hello")
public class HelloWorldController {
	public String helloWorld()
	{
		return "Hello World";
	}
	@GetMapping("/helloworld-bean")
public UserDetails helloWorldBean() {
	return new UserDetails("Meera","Singh","Delhi");
}
}
