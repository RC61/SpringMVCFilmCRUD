package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film findFilmbyID(int id) throws SQLException;

//	public List<Film> getFilmsBasedOnTitleOrDescription(String desc);
<<<<<<< HEAD
	
	public List<Film> getFilmsBasedOnTitleOrDescription(String desc) throws SQLException;
	public void addFilm(Film film) throws SQLException;

=======

	public List<Film> getFilmsBasedOnTitleOrDescription(String desc) throws SQLException;
	public Film addFilm(Film film) throws SQLException;
>>>>>>> 02e8dda9ff7634a4b4d3ca8b0e7ac0909a68143b
//	public void deleteFilm();
//	public void editFilm();
}
