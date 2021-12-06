package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
                    String movieAsString = movie +"\n";
                    try {
                        boolean writeSuccessful = writeMovieToFile(movieAsString);
                        System.out.println("Movie saved");
                    } catch (IOException e) {
                        System.out.println("Couldn't create/open the file");
                        e.printStackTrace();
                    }
                    try {
                        Files.write(Paths.get("dataBase.txt"), movieAsString.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        System.out.println("Movie saved");
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
                            List<Movie> results = searchByName(name);
                            System.out.println("I__Title______Year__Genre___Description__Rented_");
                            for (Movie singleMovie : results) {
                                System.out.println(singleMovie);
                            }
                            System.out.println("------------------");
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
                    System.out.println("Enter Title");
                    String title = new Scanner(System.in).nextLine();
                    List<Movie> movies = searchByName(title);
                    for (Movie movie1: movies) {
                        System.out.println(movie1);
                    }
                    break;

                case 4:
                    List<Movie> allMovies = getAllMovies();
                    for (Movie movie1: allMovies) {
                        System.out.println(movie1);
                    }
                    break;
                case 5:
                    isQuiting = true;
                    break;
            }
            if (isQuiting) {
                break;
            }
        }
    }

    private static boolean writeMovieToFile(String movieAsString) throws IOException {
        Files.write(Paths.get("dataBase.txt"), movieAsString.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.println("Movie saved");
        return false;
    }

    private static List<Movie> getAllMovies() {
        List<String> lines;
        List<Movie> movies = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("dataBase.txt"));
            for (String line : lines) {
                String[] info = line.split(" ");
                movies.add(new Movie(Integer.parseInt(info[0]), info[1].replaceAll("_!_", " "), Integer.parseInt(info[2].trim()), info[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private static List<Movie> searchByName(String name) {
        List<String> lines;
        List<Movie> movies = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("dataBase.txt"));
            for (String line : lines) {
                String[] info = line.split(" ");
                movies.add(new Movie(Integer.parseInt(info[0]), info[1].replaceAll("_!_", " "), Integer.parseInt(info[2].trim()), info[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (Movie singleMovie : movies) {
//            if (!singleMovie.getTitle().contains(name)) {
//                movies.remove(singleMovie);
//            }
//        }
        movies.removeIf(singleMovie -> !singleMovie.getTitle().contains(name));



        return movies;
    }
}
