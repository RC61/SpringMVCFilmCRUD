package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Repository
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

	@Override
	public boolean deleteFilm(int id) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, password);
			conn.setAutoCommit(false);// START TRANSACTION IN MYSQL
			String sql = "DELETE FROM film WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			int updateCount = stmt.executeUpdate();
			conn.commit(); // SEALED TRANSACTION IN MYSQL

		} catch (SQLException e) {
			System.out.println(e.getMessage() + ": delete requires attention");
			// if something goes wrong with commit to delete film
			if (conn != null) {
				try {
					conn.rollback();// plead for forgiveness
				} catch (SQLException e2) {
					System.err.println(e2.getMessage() + ": Required attention rollback on delete");
				}
			}
			return false;
		}
		return true;
	}

//
//	@Override
//	public void editFilm() {
//		// TODO Auto-generated method stub
//		
//	}
	public String findLanguage(int filmId) {
		String language = null;
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DriverManager.getConnection(URL, user, password);

			String sql = "SELECT language.name FROM language JOIN film ON language.id = film.language_id WHERE film.id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);

			ResultSet langId = pst.executeQuery();
			if (langId.next()) {
				language = langId.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println(e.getMessage() + ": language ID requires attention");
		}
		return language;
	}

	@Override
	public Film addFilm(Film _film) throws SQLException {
		try {
			Connection conn = DriverManager.getConnection(URL, user, password);
			conn.setAutoCommit(false);
			String inputStatement = "INSERT INTO film (film.title, film.description, film.rating, film.release_year, film.language_id) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(inputStatement, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, _film.getTitle());
			pst.setString(2, _film.getDescription());
			pst.setString(3, _film.getRating());
			pst.setInt(4, _film.getReleaseYear());
			pst.setInt(5, _film.getLanguage());
			int effectedRows = pst.executeUpdate();
			if (effectedRows == 1) {
				ResultSet rs = pst.getGeneratedKeys();
				while (rs.next()) {
					int newFilmId = rs.getInt(1);
					_film.setId(newFilmId);
					System.out.println(_film.getId());
					conn.commit();
					System.out.println("THIS SHOULD HAVE BEEN COMMITTED BY NOW!");
				}
			} else {
				_film = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _film;
	}

	@Override
	public Film updateFilm(Film film) throws SQLException {
		Film updatedFilm = null;
		String updateStatement = "UPDATE film SET film.title = ?, film.description = ?, film.rating = ?, film.release_year = ?, film.language_id = ? WHERE film.id = ?";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, password);
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(updateStatement, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, film.getTitle());
			pst.setString(2, film.getDescription());
			pst.setString(3, film.getRating());
			pst.setInt(4, film.getReleaseYear());
			pst.setInt(5, film.getLanguage());
			pst.setInt(6, film.getId());
			int rows = pst.executeUpdate();
			ResultSet keys = pst.getGeneratedKeys();
//			while(keys.next()) {
//				if(rows == 1) {
//					
//				}
//			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Film findFilmbyID(int _filmid) throws SQLException {
		Film film = null;
		String selectStatement = "SELECT film.*, category.name, language.name FROM film LEFT JOIN language ON film.language_id = language.id LEFT JOIN film_category ON film_category.film_id = film.id LEFT JOIN category ON category.id = film_category.category_id WHERE film.id = ?";

		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setInt(1, _filmid);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			film = new Film(rs.getString("film.title"), rs.getString("film.description"), rs.getString("film.rating"),
					rs.getInt("film.release_year"), rs.getInt("film.language_id"), rs.getString("category.name"));
			film.setId(rs.getInt("film.id"));
		}

		rs.close();
		pst.close();
		conn.close();

		return film;
	}

	@Override
	public List<Film> getFilmsBasedOnTitleOrDescription(String _desc) throws SQLException {
		Film film = null;
		List<Film> films = new ArrayList<>();
		String selectStatement = "SELECT film.*, category.name, language.name FROM film LEFT JOIN language ON film.language_id = language.id LEFT JOIN film_category ON film_category.film_id = film.id LEFT JOIN category ON category.id = film_category.category_id WHERE film.title LIKE ? OR film.description LIKE ?";
		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setString(1, "%" + _desc + "%");
		pst.setString(2, "%" + _desc + "%");
		System.out.println(pst);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			film = new Film(rs.getString("film.title"), rs.getString("film.description"), rs.getString("film.rating"),
					rs.getInt("film.release_year"), rs.getInt("film.language_id"), rs.getString("category.name"));
			film.setId(rs.getInt("film.id"));
			if (film != null) {
				addActorToFilm(film, user, password, rs.getInt("film.id"));
			}
			films.add(film);
		}
		conn.close();
		return films;
	}

	private void addActorToFilm(Film _film, String _user, String _password, int _filmId) throws SQLException {
		String selectStatement = "SELECT actor.first_name, actor.last_name, film.title FROM film_actor JOIN film ON film.id = film_actor.film_id JOIN actor ON film_actor.actor_id = actor.id WHERE film.id = ?";
		Connection conn = DriverManager.getConnection(URL, _user, _password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setInt(1, _filmId);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			Actor actor = new Actor(rs.getString("actor.first_name"), rs.getString("actor.last_name"));
			_film.addActorToCast(actor);
		}
		rs.close();
		pst.close();
		conn.close();
	}

}

//
