package com.skilldistillery.film.entities;

import java.util.ArrayList;
import java.util.List;

public class Film {
	private int id;
	//private List<Act>
	private String title;
	private String description;
	private String rating;
	private int releaseYear;
	private int language;
	private List<Actor> cast;
	private String category; 
	
	public Film(String _title, String _description, String _rating, int _releaseYear, int _language, String _category) {
		title = _title;
		description = _description;
		rating = _rating;
		releaseYear = _releaseYear;
		language = _language;
		category = _category;
	}
	
	public void setID(int _id) {
		id = _id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setCategory(String _category) {
		category = _category;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setTitle(String _title) {
		title = _title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setLanguage (int _language) {
		language = _language;
	}
	
	public int getLanguage() {
		return language;
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

	public void setCast(List<Actor> _cast) {
		cast = _cast;
	}
	
	public List<Actor> getCast() {
		return cast;
	}
	
	public void addActorToCast(Actor _actor) {
		if(cast == null) {
			cast = new ArrayList<>();
		}
		if(_actor != null) {
			cast.add(_actor);
		} else {
			return;
		}
	}
	
	public String toString() {
		return "TITLE: " + title + "\nDESCRIPTION: " + description + "\nRATING " + rating + "\nRELEASE YEAR: " + releaseYear + "\nLANGUAGE: " + language;
	}
}