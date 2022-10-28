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
import com.driver.MovieDirectorPair;

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
        MovieDirectorPair mdp1 = new MovieDirectorPair("Goodfellas", "Martin Scorcesse", 123, 32, 8.9, 9.1);
        movieController.addMovieDirectorPair(mdp1);

        MovieDirectorPair mdp2 = new MovieDirectorPair("Raging Bull", "Martin Scorcesse", 115, 32, 8.2, 9.1);
        movieController.addMovieDirectorPair(mdp2);

        ResponseEntity<Director> response = movieController.getDirectorByMovieName("Raging Bull");
        if(response.getBody() == null){
            assertEquals(false, true);
        }else{
            assertEquals(response.getBody().getImdbRating(), 9.1);
        }
    }

    @Test
    @Order(4)
    public void movieTest4(){
        ResponseEntity<List<String>> response = movieController.getMoviesByDirectorName("Martin Scorcesse");
        List<String> movies = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        int match = 0;

        for(String movie: movies){
            if(movie.equals("Goodfellas")){
                match++;
            }else if(movie.equals("Raging Bull")){
                match += 2;
            }
        }

        assertEquals(match, 3);
    }

    @Test
    @Order(5)
    public void movieTest5(){
        movieController.deleteMovieByName("Raging Bull");

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
            }else if(movie.equals("Raging Bull")){
                match += 9;
            }
        }

        assertEquals(match, 5);
    }

    @Test
    @Order(6)
    public void movieTest6(){
        movieController.deleteAllMovies();
        ResponseEntity<List<String>> response = movieController.getAllMovies();
        List<String> movies = response.getBody();

        if(response.getBody() == null){
            assertEquals(false, true);
        }

        assertEquals(movies.size(), 0);
    }

    @Test
    @Order(7)
    public void movieTest7(){
        Movie movie1 = new Movie("Fight Club", 145, 8.9);
        movieController.addMovie(movie1);

        movieController.deleteMovieByName("Fight Club");

        ResponseEntity<List<String>> response = movieController.getAllMovies();
        
        if(response.getBody() == null){
            assertEquals(false, true);
        }else{
            assertEquals(response.getBody().size(), 0);
        }
    }
}
