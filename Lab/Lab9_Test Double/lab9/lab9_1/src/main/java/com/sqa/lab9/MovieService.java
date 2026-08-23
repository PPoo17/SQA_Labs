package com.sqa.lab9;
/*
นายปฏิภาณ มะนิลทิพย์ 673380589-2 Sec 2
*/
import java.util.List;

public interface MovieService {
    List<String> getMoviesByLocationAndDate(String location, String date);
}