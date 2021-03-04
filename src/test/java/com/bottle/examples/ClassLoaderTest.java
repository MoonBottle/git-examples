package com.bottle.examples;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            //            @Override
//            protected Class<?> findClass(String name) throws ClassNotFoundException {
//                try {
//                    final String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//
//                    final InputStream is = getClass().getResourceAsStream(fileName);
//
//                    if (is == null) {
//                        return super.loadClass(name);
//                    }
//
//
//                    final byte[] b = new byte[is.available()];
//                    is.read(b);
//
//                    return defineClass(name, b, 0, b.length);
//                } catch (IOException e) {
//                    throw new ClassNotFoundException(name);
//                }
//
//            }
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    final String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    final InputStream is = getClass().getResourceAsStream(fileName);

                    if (is == null) {
                        return super.loadClass(name);
                    }


                    final byte[] b = new byte[is.available()];
                    is.read(b);

                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }

            }
        };

        final Object obj = myLoader.loadClass("ClassLoaderTest").getDeclaredConstructor().newInstance();

        System.out.println(obj.getClass());

        System.out.println(obj instanceof ClassLoaderTest);

    }
}
