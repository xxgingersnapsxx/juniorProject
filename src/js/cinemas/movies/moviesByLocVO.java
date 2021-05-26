package js.cinemas.movies;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class moviesByLocVO {
	private int showId;
	private String mvTitle;
	private LocalDateTime startsAt;
	private LocalDateTime endsAt;
	private int priceAmt;
	private int zoneId;
	private String locationId;
	private String showYn;

	public moviesByLocVO(int showId, String mvTitle, LocalDateTime startsAt, LocalDateTime endsAt, int priceAmt,
			int zoneId, String locationId, String showYn) {
		super();
		this.showId = showId;
		this.mvTitle = mvTitle;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.priceAmt = priceAmt;
		this.zoneId = zoneId;
		this.locationId = locationId;
		this.showYn = showYn;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}

	public LocalDateTime getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(LocalDateTime startsAt) {
		this.startsAt = startsAt;
	}

	public LocalDateTime getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(LocalDateTime endsAt) {
		this.endsAt = endsAt;
	}

	public int getPriceAmt() {
		return priceAmt;
	}

	public void setPriceAmt(int priceAmt) {
		this.priceAmt = priceAmt;
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

	public String getShowYn() {
		return showYn;
	}

	public void setShowYn(String showYn) {
		this.showYn = showYn;
	}

	@Override
	public String toString() {
		// TODO tostring 수정
		StringBuilder builder = new StringBuilder();
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("시작 시간 ");		
		builder.append(startsAt.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 hh:mm")));
		String string = builder.toString();
		return "moviesByLocVO [showId=" + showId + ", mvTitle=" + mvTitle + ", startsAt=" + ", endsAt="
				+ endsAt + ", priceAmt=" + priceAmt + ", zoneId=" + zoneId + ", locationId=" + locationId + ", showYn="
				+ showYn + "]" + string;
	}

}
