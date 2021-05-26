package js.cinemas.movies;

public class NowShowingInsertVO {
	private int showId;
	private int movieId;
	private String startsAt;
	private String endsAt;
	private String ratingId;
	private int priceId;
	private int zoneId;
	private String locationId;
	private String seats;
	private String showYn;

	public NowShowingInsertVO(int showId, int movieId, String startsAt, String endsAt, String ratingId, int priceId,
			int zoneId, String locationId, String seats, String showYn) {
		super();
		this.showId = showId;
		this.movieId = movieId;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.ratingId = ratingId;
		this.priceId = priceId;
		this.zoneId = zoneId;
		this.locationId = locationId;
		this.seats = seats;
		this.showYn = showYn;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}

	public String getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(String endsAt) {
		this.endsAt = endsAt;
	}

	public String getRatingId() {
		return ratingId;
	}

	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getShowYn() {
		return showYn;
	}

	public void setShowYn(String showYn) {
		this.showYn = showYn;
	}

	@Override
	public String toString() {
		return "NowShowingInsertVO [showId=" + showId + ", movieId=" + movieId + ", startsAt=" + startsAt + ", endsAt="
				+ endsAt + ", ratingId=" + ratingId + ", priceId=" + priceId + ", zoneId=" + zoneId + ", locationId="
				+ locationId + ", seats=" + seats + ", showYn=" + showYn + "]";
	}

}
