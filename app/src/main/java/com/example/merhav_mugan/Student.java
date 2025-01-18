package com.example.merhav_mugan;

import java.util.ArrayList;

public class Student {

    private int id;
    private String name,address;
    private String grades;
    private int age;

    public Student(int id, String name,String address, String grades, int age) {
        this.id=id;
        this.name = name;
        this.grades = grades;
        this.age = age;
        this.address=address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", name='" + name + '\'' +
                        ", address='" + address + '\'' +
                        ", grades='" + grades + '\'' +
                        ", age=" + age
                ;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public double getAverageGrade()
    {
        int[] gradesInt;
        gradesInt=convertStringToIntArray(grades);
        double s=0;
        if (gradesInt==null){
            return 0;
        }

        for(Integer grade:gradesInt)
        {
            s+=grade;
        }
        return s/gradesInt.length;



    }

    public static int[] convertStringToIntArray(String input) {
        if (input == null || input.isEmpty()) {
            return new int[0];
        }

        String[] stringArray = input.split(",");
        int[] intArray = new int[stringArray.length];

        try {
            for (int i = 0; i < stringArray.length; i++) {
                intArray[i] = Integer.parseInt(stringArray[i].trim());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

            return new int[0];
        }

        return intArray;
    }


}
