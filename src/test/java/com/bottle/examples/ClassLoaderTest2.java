package com.bottle.examples;

public class ClassLoaderTest2 {

    public static void main(String[] args) throws Exception {
        System.out.println(ClassLoaderTest2.class.getClassLoader());
        System.out.println(ClassLoaderTest2.class.getClassLoader().getParent());
        System.out.println(ClassLoaderTest2.class.getClassLoader().getParent().getParent());
    }
}
