package com.zzt.demo.复习.base;

/**
 * TODO
 *
 * @author zhouzhengtao
 * @version 1.0
 * @date 2021/02/24 10:25
 * @email ztzhou@isoftstone.com
 */
public class Animal implements Cloneable {
    private int age;

    public void show(){
        System.out.println("Animal show");
    }
    private void showp(){
        System.out.println("Animal showp");
    }

    // 重写Object.clone()方法,并把protected改为public
    @Override
    public Object clone(){
        Animal sc = null;
        try
        {
            sc = (Animal) super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return sc;
    }
}
