package com.frain.myapplication.Intent.model;

import java.io.Serializable;

/**
 * Created by admin on 2016/10/25.
 */
public class Person implements Serializable
{
    private static final long serialVersionUID = -1L;
    private String name;
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

}