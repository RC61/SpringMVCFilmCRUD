package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film findFilmbyID(int id) throws SQLException;
<<<<<<< HEAD
//	public List<Film> getFilmsBasedOnTitleOrDescription(String desc);
	public Film addFilm(Film film);
=======
	public List<Film> getFilmsBasedOnTitleOrDescription(String desc) throws SQLException;
	public void addFilm(Film film) throws SQLException;
>>>>>>> d9326c1afabd2ae819eecd8113d7cf3b01f94173
//	public void deleteFilm();
//	public void editFilm();
}
