package com.aftab.ratecalculator.ui.home;

public class Demo1 {

    Demo instanceOfDemo;
    float length;

    Demo1(Demo instanceOfDemo,float length){
        this.length=length;
        this.instanceOfDemo=instanceOfDemo;
    }

    public void printDemo1(){

        instanceOfDemo.txtLength.setText(Float.toString(length));
    }

}
