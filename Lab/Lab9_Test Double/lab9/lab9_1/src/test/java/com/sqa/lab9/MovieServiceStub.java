package com.sqa.lab9;
/*
นายปฏิภาณ มะนิลทิพย์ 673380589-2 Sec 2
*/
import java.util.ArrayList;
import java.util.List;

public class MovieServiceStub implements MovieService {

    @Override
    public List<String> getMoviesByLocationAndDate(String location, String date) {
        List<String> movies = new ArrayList<>();
        movies.add("Shadow of the Lake [VIP]");
        movies.add("The Last Signal [Standard]");
        movies.add("Neon Horizon [VIP]");
        movies.add("Whispers in the Fog [IMAX with Laser]");
        movies.add("Iron Compass [VIP]");
        return movies;
    }
}