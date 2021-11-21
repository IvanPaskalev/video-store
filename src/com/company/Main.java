package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Добър ден!\nАко изкате да запишете нов филм, натиснете 1\nАко искате да намерите вече записан филм, натиснете 2\nАко искате да излезете от програмата, натиснете 3");
        switch (new Scanner(System.in).nextInt()){
            case 1:
                System.out.println("enter movie title");
                Movie movie = new Movie();
                while (true){
                    String title = new Scanner(System.in).nextLine();
                    if (!title.isEmpty()){
                        movie.setTitle(title);
                        break;
                    } else {
                        System.out.println("No input entered");
                    }
                }

                break;

            case 2:
                break;
        }
    }
}
