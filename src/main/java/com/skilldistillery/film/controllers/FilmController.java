package com.skilldistillery.film.controllers;

import java.sql.SQLException;

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
    
    @RequestMapping(path="home.do", method=RequestMethod.GET )
    public ModelAndView goHome() {
    	ModelAndView mv = new ModelAndView();
    	//film class 
//    	Film film = filmDAO.findFilmbyID(filmId);
//    	
//    	mv.addObject("filmById", film);
    	mv.setViewName("WEB-INF/views/home.jsp");
    	
    	
    	return mv;
    }
    @RequestMapping(path="searchFilmById.do", method=RequestMethod.GET )
    public ModelAndView findFilmbyID(@RequestParam(name = "filmId") int filmId ) {
    	ModelAndView mv = new ModelAndView();
    	//film class 
    	Film film;
		try {
			film = filmDAO.findFilmbyID(filmId);
			
			mv.addObject("film", film);
			mv.setViewName("WEB-INF/filmInfo.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return mv;
    }
    
    //addFilm.do use showFilm.jsp
    
    
    
}
