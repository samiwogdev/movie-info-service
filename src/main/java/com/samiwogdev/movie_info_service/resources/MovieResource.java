package com.samiwogdev.movie_info_service.resources;

import com.samiwogdev.movie_info_service.models.Movie;
import com.samiwogdev.movie_info_service.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
//        https://api.themoviedb.org/3/movie/2?api_key=b7a975d26b2e9b2191190fc9773fd7a6
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }

}
