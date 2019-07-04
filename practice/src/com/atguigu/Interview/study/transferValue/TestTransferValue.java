package com.atguigu.Interview.study.transferValue;/**
 * @Author: Dulcinea
 * @Description: ${description}
 * @Date: 2019/7/2 21:26
 * @Version: 1.0
 */

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

/**
 *@ClassName TestTransferValue
 *@Description TODO
 *@Author TestTransferValue
 *@Date 2019/7/2 21:26
 *@Version 1.0
 **/

class Person{
  private String personName;
  private int age;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String personName, int age) {
        this.personName = personName;
        this.age = age;
    }

    public Person() {
    }

    public Person(String personName) {
        this.personName = personName;
    }
}
public class TestTransferValue {

    public void changeValue1(int age){

    }

    public void changeVaule2(Person person){
        person.setPersonName("xxx");
    }

    public void changeValue3(String str){
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age----" + age);

        Person person = new Person("abc");
        test.changeVaule2(person);
        System.out.println("personName----"+ person.getPersonName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("String-----"+str);

        /**
         * age----20 存放在main的方法栈中，test.ChangeValue(age)执行的是他自己方法栈的age值
         * personName----xxx Person存放在堆内存中，指针修改指向xxx
         * String-----abc String如果没有new String ,则存放在常量池中，如果有，则指针指向该对象，如果没有，则新建一个放在常量池中
         *                故str的指针还是指向abc
         */
    }
}
