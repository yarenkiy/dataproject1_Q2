/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author yarenk
 */
public class project1_2 {

         public static void main(String[] args) {
             List<String> hubStudents = new ArrayList<>();

        List<String> StudentNamesList = new ArrayList<>();
        try{
            File file = new File("class2.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) { 
                String StudentName = sc.nextLine();
                StudentNamesList.add(StudentName);
            }
        } catch (IOException e) {
            System.err.println("Error reading StudentNamesList from file: " + e.getMessage());
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of hub students (M): ");
        int M = scanner.nextInt();

        DoubleLinkedList studentList = new DoubleLinkedList();
        for (String name : StudentNamesList) {
            Student student = new Student(name);
            studentList.addLast(student);
        }

        boolean forward = new Random().nextBoolean();
        int studentsVisited = 0;
        while (studentsVisited < studentList.size()) {
            System.out.print("Enter the message: ");
            scanner.nextLine();
            String message = scanner.nextLine();
            studentList.display(hubStudents);
            Node current = studentList.getRandomNode();

            while (current != null && !current.student.visited) {
                
                current.student.updateCommonLetters(message);
                current.student.visited = true;
                studentsVisited++;
                System.out.println(current.student.name + " received the message: " + message);
   
              if (current == studentList.first || current == studentList.last || studentsVisited % M == 0) {
    forward = !forward;
    if (studentsVisited % M == 0) {
        System.out.print("Enter the new message: ");
        message = scanner.nextLine();
    }

    hubStudents.add(current.student.name); 


                }

                int k = new Random().nextInt(5) + 1;
                current = studentList.move(current, forward, k);
                System.out.println("Randomly generated number of k: " + k);
            }
        }

        studentList.display(hubStudents);

        
        scanner.close();
    }
}
class DoubleLinkedList {
    Node first;
    Node last;

    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
    }

    public void addLast(Student student) {
      Node newNode = new Node(student);
        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
    
    }

    public int size() {
        int count = 0;
        Node current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public Node getRandomNode() {
        if (first == null)
            return null;

        int randomN = new Random().nextInt(size());
        Node current = first;
        while (randomN > 0 && current != null) {
            current = current.next;
            randomN--;
        }
        return current;
    }

    public Node move(Node current, boolean forward, int k) {
        if (forward) {
            while (k > 0 && current != null) {
                current = current.next;
                k--;
            }
        } else {
            while (k > 0 && current != null) {
                current = current.prev;
                k--;
            }
        }
        return current;
    }

    public void display(List<String> hubStudents) {
        Node current = first;
    boolean firstStudent = true;

    while (current != null) {
        if (!firstStudent) {
            System.out.print("→");
        }
       String studentName = current.student.name;
        

        if (hubStudents.contains(studentName)) {
            System.out.print("*");
        }
        
        System.out.print(current.student.name + " " + current.student.getTotalCommonLetters());

        firstStudent = false;
        current = current.next;
    }
    System.out.println();
}
       
        



}