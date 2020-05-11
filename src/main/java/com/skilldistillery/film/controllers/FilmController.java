package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;

	public void filmDAO(FilmDAO filmDAO) {

	}
// WHEN HOME .JSP IS CREATED CHANGE INDEX to HOME
	@RequestMapping(path = "home.do", method = RequestMethod.GET)
	public ModelAndView goHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.html");
		return mv;
	}

	@RequestMapping(path = "searchFilmById.do", method = RequestMethod.GET)
	public ModelAndView findFilmbyID(@RequestParam(name = "filmId") int filmId) {
		ModelAndView mv = new ModelAndView();
		// film class
		Film film;
		try {
			film = filmDAO.findFilmbyID(filmId);
			System.out.println(filmId);

			if (film == null) {
				mv.setViewName("WEB-INF/notFound.jsp");

				return mv;
			}
			System.out.println(film);

			mv.addObject("film", film);
			mv.setViewName("WEB-INF/filmInfo.jsp");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mv;
	}

	@RequestMapping(path = "listFilms.do", method = RequestMethod.GET)
	public ModelAndView getFilmsByTitleOrDescription(@RequestParam(name = "desc") String desc) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();
		try {
			films = filmDAO.getFilmsBasedOnTitleOrDescription(desc);
			System.out.println(desc);
			System.out.println(films);
			mv.addObject("films", films);
			if (films == null || films.size() == 0) {
				mv.setViewName("WEB-INF/notFound.jsp");
			} else {
				mv.setViewName("WEB-INF/filmList.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mv;
	}

	@RequestMapping(path="createFilm.do", method=RequestMethod.GET)
	public ModelAndView addFilm(Film film) throws SQLException{
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("film", new Film());
		
		mv.setViewName("WEB-INF/film-diplay.jsp");
		
		return mv;
	}
	
	
	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView postFilm(Film film) throws SQLException {
		Film newFilm = filmDAO.addFilm(film);
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("film", newFilm);
		mv.setViewName("WEB-INF/result.jsp");

		System.out.println(newFilm);
		return mv;
	}
	
	@RequestMapping(path = "editFilm.do", method = RequestMethod.POST)
	public ModelAndView editFilm(Film film, RedirectAttributes redir) throws SQLException {		
		ModelAndView mv = new ModelAndView();
		mv.addObject(film);
		mv.setViewName("WEB-INF/editFilm.jsp");
		return mv;
	}

	
	
	@RequestMapping(path="deleteFilm.do",params="id", method=RequestMethod.POST)
	public ModelAndView deleteFilm(int id) {
		boolean delete = filmDAO.deleteFilm(id);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("delete", delete);
		mv.addObject("filmId", id);
		mv.setViewName("WEB-INF/filmDelete.jsp");
		
		return mv;
		
	}

}
