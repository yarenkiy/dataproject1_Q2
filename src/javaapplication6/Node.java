/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author yarenk
 */
public class Node {
    Student student;
    Node next;
    Node prev;

    public Node(Student student) {
        this.student = student;
        this.next = null;
        this.prev = null;
    }

  
}

    class Student {
    String name;
    int M;
    boolean visited;
    int[] commonLetters;

    public Student(String name) {
        this.name = name;
        this.M = 0;
        this.visited = false;
        this.commonLetters = new int[26]; 
    }
      public int getTotalCommonLetters() {
        int total = 0;
        for (int count : commonLetters) {
            total += count;
        }
        return total;
    }

    public void updateCommonLetters(String message) {
        for (char c : message.toCharArray()) {
            int index = Character.toUpperCase(c) - 'A';
            if (index >= 0 && index < 26) {
                commonLetters[index]++;
            }
        }
    }
}


