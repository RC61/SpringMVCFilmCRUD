package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skilldistillery.film.entities.Film;

public class FilmDAOImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=MST";
	private static final String user = "student";
	private static final String password = "student";

	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public List<Film> getFilmsBasedOnTitleOrDescription(String desc) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	@Override
	public Film addFilm(Film film) {
		// TODO Auto-generated method stub
		return film;
	}
//
//	@Override
//	public void deleteFilm() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void editFilm() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public Film findFilmbyID(int _filmid) throws SQLException {
		Film film = null;
		String selectStatement = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";

		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setInt(1, _filmid);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			film = new Film(rs.getNString("film.title"), rs.getString("film.description"), rs.getString("film.rating"),
					rs.getInt("film.release_year"), rs.getInt("film.language_id"));
			//addActorToFilm(film, user, password, filmId);
		}
		rs.close();
		pst.close();
		conn.close();

		return film;
	}

}
