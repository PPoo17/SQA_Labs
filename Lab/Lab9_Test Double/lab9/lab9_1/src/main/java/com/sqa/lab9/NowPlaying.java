package com.sqa.lab9;
/*
นายปฏิภาณ มะนิลทิพย์ 673380589-2 Sec 2
*/
import java.util.ArrayList;
import java.util.List;

public class NowPlaying {
    private MovieService movieService;

    public NowPlaying(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<String> getNowPlayingByCinemaType(String location, String date, String cinemaType) {
        List<String> allMovies = movieService.getMoviesByLocationAndDate(location, date);
        List<String> filteredMovies = new ArrayList<>();

        for (String movie : allMovies) {
            if (movie.contains(cinemaType)) {
                filteredMovies.add(movie.replace(" [" + cinemaType + "]", ""));
            }
        }
        return filteredMovies;
    }
}