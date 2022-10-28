package com.driver;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    
    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){
        movieRepository.saveMovie(movie);
    }

    public void addDirector(Director director){
        movieRepository.saveDirector(director);
    }

    public void createMovieDirectorPair(MovieDirectorPair movieDirectorPair){
        Movie movie = new Movie(movieDirectorPair.getMovieName(), movieDirectorPair.getDurationInMinutes(), movieDirectorPair.getMovieRating());
        Director director = new Director(movieDirectorPair.getDirectorName(), movieDirectorPair.getNumberOfMovies(), movieDirectorPair.getDirectorRating());
        movieRepository.saveMovieDirectorPair(movie, director);
    }

    public Movie findMovie(String movieName){
        return movieRepository.findMovie(movieName);
    }

    public Director findDirector(String directorName){
        return movieRepository.findDirector(directorName);
    }

    public Director findDirectorByMovie(String movie){
        return movieRepository.findDirectorFromMovie(movie);
    }

    public List<String> findMoviesByDirector(String director){
        return movieRepository.findMoviesFromDirector(director);
    }

    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    public void deleteMovie(String movie){
        movieRepository.deleteMovie(movie);
    }

    public void deleteAllMovies(){
        movieRepository.deleteAllMovies();
    }
}
