
package cn.jmockit.demos.utils;

/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
import java.io.File;
import java.net.URI;

public class JNITools {
	// 加载AnOrdinaryClass类的native方法
	public static void loadNative() throws Throwable {
		URI uri = ClassLoader.class.getResource("/").toURI();
		String libraryName = "libAnOrdinaryClass.dll";
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("mac")) {
			libraryName = "libAnOrdinaryClass.dylib";
		}
		String realPath = new File(uri).getAbsolutePath() + "/" + libraryName;
		System.load(realPath);
	}

}
