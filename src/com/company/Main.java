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
            System.out.println("Hi!\n1 - Save new movie\n2 - search\n3 - Edit\n4 - See all movies\n5 - Exit");
            boolean isQuiting = false;
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    int index = 0;
                    List<Movie> movies = getAllMovies();
                    index = movies.size();
                    Movie movie = createMovieObject();
                    if (movie.equals(null)){
                        System.out.println("Movie already exists");
                    }else {
                        movie.setIndex(index);
                        writeMovieToFile(String.valueOf(movie));
                    }
                    break;


                case 2:
                    System.out.println("If you want to search title press 1\nIf you want to search year press 2\nIf you want to search genre press 3");
                    switch (new Scanner(System.in).nextInt()) {
                        case 1:
                            System.out.println("Enter name");
                            String name = new Scanner(System.in).nextLine();
                            List<Movie> results = searchByName(name);
                            if (results.isEmpty()){
                                System.out.println("Nothing found");
                            } else {
                                System.out.println("I__Title______Year__Genre___Description__Rented_");
                                for (Movie singleMovie : results) {
                                    System.out.println(singleMovie);
                                }
                                System.out.println("------------------");
                            }
                            break;
                        case 2:
                            System.out.println("Enter year");
                            int year = new Scanner(System.in).nextInt();
                            List<Movie> resultsYear = searchByYear(year);
                            if (resultsYear.isEmpty()){
                                System.out.println("Nothing found");
                            } else {
                                System.out.println("I__Title______Year__Genre___Description__Rented_");
                                for (Movie singleMovie : resultsYear) {
                                    System.out.println(singleMovie);
                                }
                                System.out.println("------------------");
                            }
                            break;
                        case 3:
                            System.out.println("Enter genre");
                            String genre = new Scanner(System.in).nextLine();
                            List<Movie> resultsGenre = searchByGenre(genre);
                            if (resultsGenre.isEmpty()){
                                System.out.println("Nothing found");
                            } else {
                                System.out.println("I__Title______Year__Genre___Description__Rented_");
                                for (Movie singleMovie : resultsGenre) {
                                    System.out.println(singleMovie);
                                }
                                System.out.println("------------------");
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Enter Title");
                    String title = new Scanner(System.in).nextLine();
                    List<Movie> movieSearch = searchByName(title); //Must return an Object!?
                    if (movieSearch.isEmpty()){
                        System.out.println("Nothing found");
                    } else {
                        System.out.println("Results:");
                        for (Movie singleMovie : movieSearch) {
                            System.out.println(singleMovie);
                        }
                        System.out.println("-----------------------");
                        System.out.println("What do you want to do?\n1 - Correct\n2 - delete");
                        switch (new Scanner(System.in).nextInt()){
                            case 1:
                                for (Movie singleMovie : movieSearch) {
                                    singleMovie = createMovieObject();
//                                    singleMovie.setIndex();
                                    List<Movie> allMovies = getAllMovies(); // -> How to replace them?..
                                    System.out.println(singleMovie);
                                    for (Movie movie1 : allMovies) {
//                                        allMovies.removeIf();
                                    }

                                } // -> What to correct? Must assign IDs/Method for IDs!
                                break;
                            case 2:
                                break;
                        }
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

    private static Movie createMovieObject() {
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
            List<Movie> allMovies = getAllMovies();
            for (Movie singleMovie : allMovies) {
                if (singleMovie.getTitle().equalsIgnoreCase(title)){
                    return null;
                }
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
        return movie;
    }

    private static List<Movie> searchByGenre(String genre) {
        List<String> lines;
        List<Movie> movies = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("dataBase.txt"));
            for (String line : lines) {
                String[] info = line.split(" ");
                movies.add(new Movie(Integer.parseInt(info[0]), info[1].replaceAll("_!_", " "), Integer.parseInt(info[2].trim()), info[3]));
            }
            movies.removeIf(singleMovie -> !singleMovie.getGenre().contains(genre));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private static List<Movie> searchByYear(int year) {
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
//            if (singleMovie.getYear() != year){
//                movies.remove(singleMovie);
//            }
//        }
        movies.removeIf(singleMovie -> singleMovie.getYear() != year);

        return movies;
    }

    private static boolean writeMovieToFile(String movieAsString) {
        try {
            Files.write(Paths.get("dataBase.txt"), movieAsString.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            movies.removeIf(singleMovie -> !singleMovie.getTitle().contains(name));

        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (Movie singleMovie : movies) {
//            if (!singleMovie.getTitle().contains(name)) {
//                movies.remove(singleMovie);
//            }
//        }




        return movies;

    }
}
