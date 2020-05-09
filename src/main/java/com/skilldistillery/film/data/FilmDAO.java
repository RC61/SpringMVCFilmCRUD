package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film findFilmbyID(int id) throws SQLException;
//	public List<Film> getFilmsBasedOnTitleOrDescription(String desc);
	public Film addFilm(Film film);
//	public void deleteFilm();
//	public void editFilm();
}
