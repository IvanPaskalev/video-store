package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Добър ден!\nАко изкате да запишете нов филм, натиснете 1\nАко искате да намерите вече записан филм, натиснете 2\nАко искате да излезете от програмата, натиснете 3");
            boolean isQuiting = false;
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    System.out.println("enter movie title");
                    System.out.println("enter movie title");
                    Movie movie = new Movie();
                    while (true) {
                        String title = new Scanner(System.in).nextLine().replaceAll(" ", "_!_");
                        if (!title.isEmpty()) {
                            movie.setTitle(title);
                            break;
                        } else {
                            System.out.println("No input entered");
                        }
                    }
                    System.out.println("Enter year");
                    while (true) {
                        int year = new Scanner(System.in).nextInt();
                        if (year != 0) {
                            movie.setYear(year);
                            break;
                        } else {
                            System.out.println("No input entered");
                        }
                    }
                    System.out.println("enter genre");
                    while (true) {
                        String genre = new Scanner(System.in).nextLine();
                        if (!genre.isEmpty()) {
                            movie.setGenre(genre);
                            break;
                        } else {
                            System.out.println("No input entered");
                        }
                    }
                    String movieAsString = String.valueOf(movie)+"\n";
                    try {
                        Files.write(Paths.get("dataBase.txt"), movieAsString.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        System.out.println("Movie saved");

                    } catch (FileNotFoundException e) {
                        try {
                            Files.write(Paths.get("dataBase.txt"), movieAsString.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            System.out.println("Movie saved");
                        } catch (IOException ex) {
                            System.out.println("Error! Could not save the movie.");
                        }
                    } catch (IOException exception) {
                        System.out.println("Error! Could not save the movie.");
                    }
                    break;
                case 2:
                    System.out.println("If you want to search title press 1\nIf you want to search year press 2\nIf you want to search genre press 3");
                    switch (new Scanner(System.in).nextInt()) {
                        case 1:
                            System.out.println("Enter name");
                            String name = new Scanner(System.in).nextLine();
                            try {
                                List<String> lines = Files.readAllLines(Paths.get("dataBase.txt"));
                                List<Movie> movies = new ArrayList<>();
                                for (String line : lines) {
                                    String[] info = line.split(" ");
                                    int index = Integer.parseInt(info[0]);
                                    String name1 = info[1].replaceAll("_!_", " ");
                                    int year1 = Integer.parseInt(info[2].trim());
                                    String genre1 = info[3];
                                    movies.add(new Movie(index, name1, year1, genre1));
                                }
                                for (Movie singleMovie : movies) {
                                    if (singleMovie.getTitle().contains(name)) {
                                        System.out.println(singleMovie);
                                    }
                                }
                            } catch (IOException e) {
                                System.out.println("Nothing found");
                                break;
                            }
                            break;
                        case 2:
                            System.out.println("Enter year");
                            int year = new Scanner(System.in).nextInt();
                            try {
                                List<String> lines = Files.readAllLines(Paths.get("dataBase.txt"));
                                List<Movie> movies = new ArrayList<>();
                                for (String line : lines) {
                                    String[] info = line.split(" ");
                                    int index = Integer.parseInt(info[0]);
                                    String name1 = info[1].replaceAll("_!_", " ");
                                    int year1 = Integer.parseInt(info[2].trim());
                                    String genre1 = info[3];
                                    movies.add(new Movie(index, name1, year1, genre1));
                                }
                                for (Movie singleMovie : movies) {
                                    if (singleMovie.getYear() == year){
                                        System.out.println(singleMovie);
                                    }
                                }
                            } catch (IOException e) {
                                System.out.println("Nothing found");
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            System.out.println("Enter genre");
                            String genre = new Scanner(System.in).nextLine();
                            try {
                                List<String> lines = Files.readAllLines(Paths.get("dataBase.txt"));
                                List<Movie> movies = new ArrayList<>();
                                for (String line : lines) {
                                    String[] info = line.split(" ");
                                    int index = Integer.parseInt(info[0]);
                                    String name1 = info[1].replaceAll("_!_", " ");
                                    int year1 = Integer.parseInt(info[2].trim());
                                    String genre1 = info[3];
                                    movies.add(new Movie(index, name1, year1, genre1));
                                }
                                for (Movie singleMovie : movies) {
                                    if (singleMovie.getGenre().contains(genre)){
                                        System.out.println(singleMovie);
                                    }
                                }
                            } catch (IOException e) {
                                System.out.println("Nothing found");
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                case 3:
                    isQuiting = true;
                    break;
            }
            if (isQuiting) {
                break;
            }
        }
    }
}
