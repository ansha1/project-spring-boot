package com.web.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import com.google.gson.Gson;

public class JsonUtil {

	public static int counter = 0;

	public static String getJson(Map<String, Object> values) {
		try {
			Map<String, Class<?>> props = new HashMap<String, Class<?>>();
			Iterator<Entry<String, Object>> iterator = values.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> next = iterator.next();
				props.put(next.getKey(), next.getValue().getClass());
			}
			CtClass generatedClass = PojoGenerator.generate("DataObject" + counter++, props);
			Class<?> clazz = generatedClass.toClass();
			Object obj = clazz.newInstance();
			iterator = values.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> next = iterator.next();
				Object value = next.getValue();
				clazz.getMethod(PojoGenerator.getSetterName(next.getKey()), value.getClass()).invoke(obj, value);
			}
			String json = new Gson().toJson(obj);
			generatedClass.defrost();
			generatedClass.detach();
			return json;
		} catch (Exception e) {
			System.out.println(e);
			return e.getMessage();
		}

	}
}

@SuppressWarnings("rawtypes")
class PojoGenerator {

	public static CtClass generate(String className, Map<String, Class<?>> properties) throws NotFoundException, CannotCompileException {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass(className);
		// cc.addInterface(resolveCtClass(Serializable.class));
		for (Entry<String, Class<?>> entry : properties.entrySet()) {
			cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));
			cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue()));
			cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
		}
		return cc;
	}

	private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass) throws CannotCompileException {
		String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		StringBuffer sb = new StringBuffer();
		sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){").append("return this.").append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class fieldClass) throws CannotCompileException {
		StringBuffer sb = new StringBuffer();
		sb.append("public void ").append(getSetterName(fieldName)).append("(").append(fieldClass.getName()).append(" ").append(fieldName).append(")").append("{").append("this.").append(fieldName)
				.append("=").append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	public static String getSetterName(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	private static CtClass resolveCtClass(Class clazz) throws NotFoundException {
		ClassPool pool = ClassPool.getDefault();
		return pool.get(clazz.getName());
	}

}
