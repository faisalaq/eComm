package com.springlearning.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

public class SaveHelperService {

//	Java Reflection
	public static <T> T save(JpaRepository<T, Long> repo, T obj, Class<T> clazz, String fieldName, String fieldValue) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		fieldName = StringUtils.capitalize(fieldName);
		
		Method methods[] = clazz.getMethods();
		Method method = null;
		
		for(Method aMethod: methods){
			if(aMethod.getName().equals("set"+fieldName)){
				method = aMethod;
				if(method.getParameterTypes()[0].getName().indexOf("String") > -1){
					method.invoke(obj, fieldValue);
					
				}
				else if(method.getParameterTypes()[0].getName().indexOf("Double") > -1){
					fieldValue = fieldValue.replace("$", "");
					method.invoke(obj, Double.valueOf(fieldValue));
				}
				break;				
			}
		}
		
		return repo.save(obj);	
	}
}
