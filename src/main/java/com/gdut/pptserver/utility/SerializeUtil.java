package com.gdut.pptserver.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 描 叙：对象序列化工具类
 * <p>
 * 新建时间:Aug 2, 2014 10:54:14 AM
 * 
 * @author 徐新明
 */

public class SerializeUtil {
	public static byte[] serialize(Object object) {
		if (object != null) {
			ObjectOutputStream oos = null;
			ByteArrayOutputStream baos = null;
			try {
				// 序列化
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				byte[] bytes = baos.toByteArray();
				return bytes;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		if (bytes != null && bytes.length > 0) {
			ByteArrayInputStream bais = null;
			try {
				// 反序列化
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
