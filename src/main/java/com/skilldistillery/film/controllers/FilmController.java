package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;


@Controller 
public class FilmController {
	
	
    @Autowired 
    private FilmDAO filmDAO;
    
    public void filmDAO(FilmDAO filmDAO) {
    	
    }
    
    
    @RequestMapping(path="searchFilmById.do", method=RequestMethod.GET )
    public ModelAndView findFilmbyID(@RequestParam(name = "filmId") String filmId ) {
    	ModelAndView mv = new ModelAndView();
    	//film class 
//    	Film film = filmDAO.findFilmbyID(filmId);
//    	
//    	mv.addObject("filmById", film);
    	mv.setViewName("WEB-INF/filmInfo.jsp");
    	
    	
    	return mv;
    }
    
    //addFilm.do use showFilm.jsp
    
    
    
}
