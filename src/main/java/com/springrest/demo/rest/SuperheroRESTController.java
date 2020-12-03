package com.springrest.demo.rest;

import com.springrest.demo.entity.Superhero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SuperheroRESTController {

    private List<Superhero> superheroList;

    @PostConstruct
    public void loadData(){
        superheroList = new ArrayList<>();
        superheroList.add(new Superhero("Steve Rogers", "Captain America"));
        superheroList.add(new Superhero("Tony Stark", "Ironman"));
        superheroList.add(new Superhero("Wanda Maximoff", "Scarlet Witch"));
        superheroList.add(new Superhero("Peter Parker", "Spiderman"));
        superheroList.add(new Superhero("Natasha Romanoff", "Black Widow"));
        superheroList.add(new Superhero("Scott Lang", "Ant Man"));
        superheroList.add(new Superhero("Bruce Banner", "Hulk"));
        superheroList.add(new Superhero("Thor Odinson", "-"));
        superheroList.add(new Superhero("Clint Barton", "Hawkeye"));
        superheroList.add(new Superhero("Sam Wilson", "The Falcon"));
    }

    @GetMapping("/superheroes")
    public List<Superhero> getHeroes(){
        return superheroList;
    }

    @GetMapping("/superhero/{superheroId}")
    private Superhero getHero(@PathVariable int superheroId){ //path variables name must match
        if(superheroId > superheroList.size() || superheroId < 0)
            throw new NotFoundException("Superhero Id not found: " + superheroId);
        return superheroList.get(superheroId);
    }

    /*
    Local Exception Handler
    Limited only for this controller

    //Add exception handler
    @ExceptionHandler
    // ResponseEntity<Type of Response body> methodName(Exception type to handle)
    public ResponseEntity<SuperheroErrorResponse> handleException(NotFoundException e){

        //create a SuperheroErrorResponse
        SuperheroErrorResponse errorResponse = new SuperheroErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler //to catch all exceptions
    public ResponseEntity<SuperheroErrorResponse> handleGenericException(Exception e){
        SuperheroErrorResponse errorResponse = new SuperheroErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    */
}
