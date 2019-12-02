
public class Seat {
private Movie movie;
private String[] zuowei;
public Movie getMovie() {
	return movie;
}
public void setMovie(Movie movie) {
	this.movie = movie;
}

public Seat(Movie movie, String[] zuowei) {
	super();
	this.movie = movie;
	this.zuowei = zuowei;
}
public String[] getZuowei() {
	return zuowei;
}
public void setZuowei(String[] zuowei) {
	this.zuowei = zuowei;
}
public Seat(){
	
}
}
