package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	@RequestMapping(path="searchByKeyword.do")
	public ModelAndView keyWordSearch() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/searchByKeyword.jsp");
		return mv;
	}
	
	@RequestMapping(path="searchById.do")
	public ModelAndView idSearch() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/searchByID.jsp");
		return mv;
	}

	@RequestMapping(path = "searchFilmById.do", method = RequestMethod.GET)
	public ModelAndView findFilmbyID(@RequestParam(name = "filmId") int filmId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// film class
		Film film;
		try {
			film = filmDAO.findFilmbyID(filmId);
			System.out.println(filmId);
			session.setAttribute("film", film);
			if (film == null) {
				mv.setViewName("WEB-INF/notFound.jsp");

				return mv;
			}
			System.out.println(film);

			mv.addObject("film", film);
			mv.setViewName("WEB-INF/filmInfo.jsp");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(path = "listFilms.do", method = RequestMethod.GET)
	public ModelAndView getFilmsByTitleOrDescription(String desc, HttpSession session, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();
		session.setAttribute("filmList",films);
		try {
			films = filmDAO.getFilmsBasedOnTitleOrDescription(desc);
			mv.addObject("films", films);
			if(films == null || films.size() == 0) {
				mv.setViewName("WEB-INF/notFound.jsp");
			}else {
				mv.setViewName("WEB-INF/filmList.jsp");					
			}
			System.out.println(desc);
		} catch (SQLException e) {
			e.printStackTrace();
			mv.setViewName("WEB-INF/error.jsp");					
		}

		return mv;
	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView getFilmDataFromForm(Film film, HttpSession session, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		try {
			Film newFilm = filmDAO.addFilm(film);
			session.setAttribute("newFilm", newFilm);
			mv.setViewName("redirect:addSuccess.do");

		} catch (SQLException e) {
			e.printStackTrace();
			mv.setViewName("WEB-INF/notFound.jsp");
		}

		return mv;

	}
	

	@RequestMapping(path = "addSuccess.do")
	public ModelAndView redirectToResult(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Film film = (Film) session.getAttribute("newFilm");
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/result.jsp");
		session.removeAttribute("newFilm");
		return mv;
	}

	@RequestMapping(path = "editFilm.do", method = RequestMethod.GET)
	public ModelAndView editFilm(int id, HttpSession session) throws SQLException {
		Film film = filmDAO.findFilmbyID(id);
		session.setAttribute("editFilm", film);
		System.out.println(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/editFilm.jsp");
		return mv;
	}

	@RequestMapping(path = "deleteFilm.do", params = "id", method = RequestMethod.POST)
	public ModelAndView deleteFilm(int id, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		boolean delete = filmDAO.deleteFilm(id);
		if(delete == true) {
			mv.setViewName("WEB-INF/deleteSuccess.jsp");
		} else {
			mv.setViewName("WEB-INF/error.jsp");
		}
		return mv;
	}
	
	@RequestMapping(path="goToAddFilm.do")
	public ModelAndView addTheFilm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addFilm.jsp");
		return mv;
	}

	@RequestMapping(path = "insertEdit.do", method = RequestMethod.POST)
	public ModelAndView submitEdit(Film film, RedirectAttributes redir, HttpSession session) throws SQLException {
		ModelAndView mv = new ModelAndView();
		Film updatedFilm = filmDAO.updateFilm(film);
		session.setAttribute("title", updatedFilm.getTitle());
		mv.setViewName("WEB-INF/editSuccess.jsp");
		return mv;
	}
	
	@RequestMapping(path="success.do")
	public ModelAndView actionSuccess(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(session.getAttribute("updatedFilm"));
		mv.setViewName("WEB-INF/editSuccess.jsp");
		return mv;
	}

}
