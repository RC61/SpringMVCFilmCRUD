package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public Film addFilm(Film film) throws SQLException {
		// TODO Auto-generated method stub
		String insertStatement = "INSERT INTO film (film.title, film.description, film.rating, film.release_year, film.language_id) +"
				+ " VALUES(?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DriverManager.getConnection(URL, user, password);
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, film.getTitle());
			pst.setString(1, film.getDescription());
			pst.setString(1, film.getRating());
			pst.setInt(1, film.getReleaseYear());
			pst.setString(1, film.getLanguage());

			ResultSet keys = pst.getGeneratedKeys();
			while (keys.next()) {
				int uc = pst.executeUpdate();
				if (uc == 1) {
					conn.commit();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.err.println("Error rolling back.");
					e1.printStackTrace();
				}
			}
		}
		pst.close();
		conn.close();
		return film;
	}

	@Override
	public Film findFilmbyID(int _filmid) throws SQLException {
		Film film = null;
		String selectStatement = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";

		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setInt(1, _filmid);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			film = new Film(rs.getString("film.title"), rs.getString("film.description"), rs.getString("film.rating"),
					rs.getInt("film.release_year"), rs.getInt("film.language_id"));
		}

		rs.close();
		pst.close();
		conn.close();

		return film;
	}

	@Override
	public List<Film> getFilmsBasedOnTitleOrDescription(String search) throws SQLException {
		Film film = null;
		List<Film> films = new ArrayList<>();
		String selectStatement = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.title LIKE ? OR film.description LIKE ?";
		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(selectStatement);
			pst.setString(1, "%" + search + "%");
			pst.setString(2, "%" + search + "%");
			rs = pst.executeQuery();

			while (rs.next()) {
				if (film != null) {
					film = new Film(rs.getString("film.title"), rs.getString("film.description"),
							rs.getString("film.rating"), rs.getInt("film.release_year"), rs.getInt("film.language_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs.close();
		pst.close();
		conn.close();
		return films;
	}

}
