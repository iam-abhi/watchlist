package com.driver;

public class MovieDirectorPair {

    private String movieName;

    private String directorName;

    private int durationInMinutes;

    private int numberOfMovies;

    private double movieRating;

    private double directorRating;

    public MovieDirectorPair() {
        
    }

    public MovieDirectorPair(String movieName, String directorName, int durationInMinutes, int numberOfMovies,
            double movieRating, double directorRating) {
        this.movieName = movieName;
        this.directorName = directorName;
        this.durationInMinutes = durationInMinutes;
        this.numberOfMovies = numberOfMovies;
        this.movieRating = movieRating;
        this.directorRating = directorRating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }

    public double getDirectorRating() {
        return directorRating;
    }

    public void setDirectorRating(double directorRating) {
        this.directorRating = directorRating;
    }
    
}
