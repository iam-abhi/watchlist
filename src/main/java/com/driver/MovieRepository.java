package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, String> movieDirectorMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.movieDirectorMapping = new HashMap<String, String>();
    }

    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(Movie movie, Director director){
        movieMap.put(movie.getName(), movie);
        directorMap.put(director.getName(), director);
        movieDirectorMapping.put(movie.getName(), director.getName());
    }

    public Movie findMovie(String movie){
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        return directorMap.get(director);
    }

    public Director findDirectorFromMovie(String movie){
        String directorName = movieDirectorMapping.get(movie);
        return directorMap.get(directorName);
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<String>();
        for(String movie: movieDirectorMapping.keySet()){
            if(director.equals(movieDirectorMapping.get(movie))){
                moviesList.add(movie);
            }
        }
        return moviesList;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteMovie(String movie){
        movieMap.remove(movie);
        movieDirectorMapping.remove(movie);
    }

    public void deleteAllMovies(){
        movieMap = new HashMap<String, Movie>();
        movieDirectorMapping = new HashMap<String, String>();
    }
}
