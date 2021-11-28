package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
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
                System.out.println("Enter year");
                while (true){
                    int year = new Scanner(System.in).nextInt();
                    if (year != 0){
                        movie.setYear(year);
                        break;
                    } else {
                        System.out.println("No input entered");
                    }
                }
                System.out.println("enter genre");
                while (true){
                    String genre = new Scanner(System.in).nextLine();
                    if (!genre.isEmpty()){
                        movie.setGenre(genre);
                        break;
                    } else {
                        System.out.println("No input entered");
                    }
                }
                BufferedReader bufferedReader = null;
                String movieAsString = movie.toString();
                try {
                    bufferedReader = new BufferedReader(new FileReader("dataBase.txt"));
                    try {
                        Files.write(Paths.get("dataBase.txt"), movieAsString.getBytes(), StandardOpenOption.APPEND);
                        System.out.println("Movie saved");
                    } catch (IOException e) {
                        System.out.println("Error! Could not save the movie.");
                    }

                } catch (FileNotFoundException e) {
                    try {
                        Files.write(Paths.get("dataBase.txt"), movieAsString.getBytes());
                        System.out.println("Movie saved");
                    } catch (IOException ex) {
                        System.out.println("Error! Could not save the movie.");
                    }
                }

                break;

            case 2:
                break;
        }
    }
}
