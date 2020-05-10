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
			mv.addObject("film", films);
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

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film, RedirectAttributes redir) throws SQLException {
		Film ourFilm = filmDAO.addFilm(film);
		System.out.println(film);
		ModelAndView mv = new ModelAndView();
		if (ourFilm == null) {
			redir.addFlashAttribute("failureToCreate", "Failed to add new film");
		}

		else {
			redir.addFlashAttribute("film", film);
		}

		mv.setViewName("redirect:filmCreated.do");
		return mv;
	}


	@RequestMapping(path="createFilm.do", method = RequestMethod.GET)
	public ModelAndView seeStuff(@RequestParam("title") String s) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(s);
		mv.setViewName("seeStuff.jsp");
		return mv;
	}

	@RequestMapping(path = "filmCreated.do", method = RequestMethod.GET)
	public ModelAndView created() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addFilm.jsp");
		return mv;
	}


}
