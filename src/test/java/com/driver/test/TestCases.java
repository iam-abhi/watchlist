package com.driver.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.driver.Application;
import com.driver.Director;
import com.driver.Movie;
import com.driver.MovieController;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

    @Autowired
    MovieController movieController;

    @Test
    @Order(1)
    public void movieTest1(){
        Movie movie1 = new Movie("Irishman", 193, 7.9);
        movieController.addMovie(movie1);

        Movie movie2 = new Movie("Goodfellas", 123, 8.8);
        movieController.addMovie(movie2);

        Movie movie3 = new Movie("Fight Club", 145, 8.9);
        movieController.addMovie(movie3);

        Movie movie4 = new Movie("Heat", 170, 8.2);
        movieController.addMovie(movie4);

        Movie movie5 = new Movie("Carlito's Way", 144, 7.9);
        movieController.addMovie(movie5);

        ResponseEntity<Movie> response = movieController.getMovieByName(movie1.getName());
        
        if(response.getBody() == null){
            assertEquals(false, true);
        }else{
            assertEquals(response.getBody().getDurationInMinutes(), 193);
        }
    }

    @Test
    @Order(2)
    public void movieTest2(){
        Director director1 = new Director("David Fincher", 21, 8.2);
        movieController.addDirector(director1);

        Director director2 = new Director("Martin Scorcesse", 32, 9.1);
        movieController.addDirector(director2);

        ResponseEntity<Director> response = movieController.getDirectorByName(director1.getName());

        if(response.getBody() == null){
            assertEquals(false, true);
        }else{
            assertEquals(response.getBody().getImdbRating(), 8.2);
        }
    }

    @Test
    @Order(3)
    public void movieTest3(){
        
        movieController.addMovieDirectorPair("Goodfellas", "Martin Scorcesse");
        movieController.addMovieDirectorPair("Irishman", "Martin Scorcesse");
        movieController.addMovieDirectorPair("Fight Club", "David Fincher");

        ResponseEntity<List<String>> response = movieController.getMoviesByDirectorName("Martin Scorcesse");
        if(response.getBody() == null){
            assertEquals(false, true);
        }else{
            int match = 0;
            for(String movie: response.getBody()){
                if(movie.equals("Irishman")){
                    match++;
                }else if(movie.equals("Goodfellas")){
                    match += 4;
                }
            }

            assertEquals(match, 5);
        }
    }

    @Test
    @Order(4)
    public void movieTest4(){
        ResponseEntity<List<String>> response = movieController.getAllMovies();
        List<String> movies = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        int match = 0;

        for(String movie: movies){
            if(movie.equals("Irishman")){
                match++;
            }else if(movie.equals("Goodfellas")){
                match += 4;
            }else if(movie.equals("Fight Club")){
                match += 9;
            }else if(movie.equals("Heat")){
                match += 11;
            }else if(movie.equals("Carlito's Way")){
                match += 14;
            }
        }

        assertEquals(match, 39);
    }

    @Test
    @Order(5)
    public void movieTest5(){
        movieController.deleteDirectorByName("David Fincher");

        ResponseEntity<List<String>> response = movieController.getAllMovies();
        List<String> movies = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        int match = 0;

        for(String movie: movies){
            if(movie.equals("Irishman")){
                match++;
            }else if(movie.equals("Goodfellas")){
                match += 4;
            }else if(movie.equals("Fight Club")){
                match += 9;
            }else if(movie.equals("Heat")){
                match += 11;
            }else if(movie.equals("Carlito's Way")){
                match += 14;
            }
        }

        assertEquals(match, 30);
    }

    @Test
    @Order(6)
    public void movieTest6(){
        movieController.deleteAllDirectors();
        ResponseEntity<List<String>> response = movieController.getAllMovies();
        List<String> movies = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        assertEquals(movies.size(), 2);
    }

    @Test
    @Order(7)
    public void movieTest7(){
        movieController.deleteAllDirectors();
        ResponseEntity<List<String>> response = movieController.getAllMovies();
        List<String> movies = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        int match = 0;

        for(String movie: movies){
            if(movie.equals("Irishman")){
                match++;
            }else if(movie.equals("Goodfellas")){
                match += 4;
            }else if(movie.equals("Fight Club")){
                match += 9;
            }else if(movie.equals("Heat")){
                match += 11;
            }else if(movie.equals("Carlito's Way")){
                match += 14;
            }
        }

        assertEquals(match, 25);
    }
}
