package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film findFilmbyID(int id) throws SQLException;

//	public List<Film> getFilmsBasedOnTitleOrDescription(String desc);
	public List<Film> getFilmsBasedOnTitleOrDescription(String desc) throws SQLException;
	public Film addFilm(Film film) throws SQLException;
	public boolean deleteFilm(int id);
//	public void editFilm();
}
