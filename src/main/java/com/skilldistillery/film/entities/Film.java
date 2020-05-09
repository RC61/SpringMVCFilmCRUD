package com.skilldistillery.film.entities;

public class Film {
	private int id;
	//private List<Act>
	private String title;
	private String description;
	private String rating;
	private int releaseYear;
	private int language;
	
	public Film(String _title, String _description, String _rating, int _releaseYear, int _language) {
		title = _title;
		description = _description;
		rating = _rating;
		releaseYear = _releaseYear;
		language = _language;
	}
	
	public void setID(int _id) {
		id = _id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setTitle(String _title) {
		title = _title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDescription(String _description) {
		description = _description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setRating(String _rating) {
		rating = _rating;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setReleaseYear(int _releaseYear) {
		releaseYear = _releaseYear;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public void setLanguageID(int _language) {
		_language = language;
	}
	
	public int getLanguageID() {
		return language;
	}
	
	public String getLanguage() {
		return null;
	}
	
	public String toString() {
		return "TITLE: " + title + "\nDESCRIPTION: " + description + "\nRATING " + rating + "\nRELEASE YEAR: " + releaseYear + "\nLANGUAGE: " + language;
	}
}