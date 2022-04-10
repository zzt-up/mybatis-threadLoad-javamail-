package com.zzt.demo.复习.base;

/**
 * TODO
 *
 * @author zhouzhengtao
 * @version 1.0
 * @date 2021/02/24 10:27
 * @email ztzhou@isoftstone.com
 */
public class Dog  extends Animal{

    public void showDog(){
        System.out.println("Dog showDog");
    }
    @Override
    public void show(){
        System.out.println("Dog show");

    }
    //私有方法重写不了，没继承过来
    public void showp(){
        System.out.println("Dog showp");

    }
}
