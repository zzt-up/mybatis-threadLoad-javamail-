package com.zzt.demo.复习.base;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author zhouzhengtao
 * @version 1.0
 * @date 2021/02/24 10:28
 * @email ztzhou@isoftstone.com
 */
public class TestDog {
    public static void main(String[] args) {
        Animal animal = new Dog();
        //多态，只能调用父类暴露出来的方法,自己私有方法不能调用
        animal.show();
        //animal.showDog();
        //animal.showp();
        //Animal 实现Cloneable  接口并且 重写Object.clone()方法,并把protected改为public
        Animal clone = (Animal)animal.clone();
        clone.show();
    }
}
