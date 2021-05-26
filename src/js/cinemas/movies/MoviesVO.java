package js.cinemas.movies;

public class MoviesVO {

	private int movieId;
	private String mvTitle;
	private String mvOTitle;
	private String mvSTitle;
	private int duration;
	private String typeId;
	private String movieYn;

	public MoviesVO(int movieId, String movieYn) {
		super();
		this.movieId = movieId;
		this.movieYn = movieYn;
	}

	public MoviesVO(String mvTitle, int duration) {
		super();
		this.mvTitle = mvTitle;
		this.duration = duration;
	}

	public MoviesVO(int movieId, String mvTitle, int duration, String typeId, String movieYn) {
		super();
		this.movieId = movieId;
		this.mvTitle = mvTitle;
		this.duration = duration;
		this.typeId = typeId;
		this.movieYn = movieYn;
	}

	public MoviesVO(int movieId, String mvTitle, String mvOTitle, String mvSTitle, int duration, String typeId,
			String movieYn) {
		super();
		this.movieId = movieId;
		this.mvTitle = mvTitle;
		this.mvOTitle = mvOTitle;
		this.mvSTitle = mvSTitle;
		this.duration = duration;
		this.typeId = typeId;
		this.movieYn = movieYn;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}

	public String getMvOTitle() {
		return mvOTitle;
	}

	public void setMvOTitle(String mvOTitle) {
		this.mvOTitle = mvOTitle;
	}

	public String getMvSTitle() {
		return mvSTitle;
	}

	public void setMvSTitle(String mvSTitle) {
		this.mvSTitle = mvSTitle;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getMovieYn() {
		return movieYn;
	}

	public void setMovieYn(String movieYn) {
		this.movieYn = movieYn;
	}

	@Override
	public String toString() {
		return "MoviesVO [movieId=" + movieId + ", mvTitle=" + mvTitle + ", mvOTitle=" + mvOTitle + ", mvSTitle="
				+ mvSTitle + ", duration=" + duration + ", typeId=" + typeId + ", movieYn=" + movieYn + "]";
	}

}
