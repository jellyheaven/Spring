package com.spring.ex02;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

// 생성자 DI 실습
// 자바에서 생성하지 않고 스프링 프레임워크가 담당하게 하여 의존성을 최소화 한다.
public class PersonTest2 {
	
	public static void main(String[] args) {
		//Setter를 이용한 DI 기능
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		PersonService person1 = (PersonService) factory.getBean("personService1");		
		person1.sayHello();
		
		System.out.println("=================");
		
		PersonService person2 = (PersonService) factory.getBean("personService2");		
		person2.sayHello();
	}
}
