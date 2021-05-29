package js.cinemas.booking;

import java.time.LocalDateTime;

public class vMemTixHistVO {
	private String memId;
	private int showId;
	private String seatNo;
	private LocalDateTime showDate;
	private String cancelYn;

	public vMemTixHistVO(String memId, int showId, String seatNo, LocalDateTime showDate, String cancelYn) {
		super();
		this.memId = memId;
		this.showId = showId;
		this.seatNo = seatNo;
		this.showDate = showDate;
		this.cancelYn = cancelYn;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public LocalDateTime getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDateTime showDate) {
		this.showDate = showDate;
	}

	public String getCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}

	@Override
	public String toString() {
		return "vMemTixHistVO [memId=" + memId + ", showId=" + showId + ", seatNo=" + seatNo + ", showDate=" + showDate
				+ ", cancelYn=" + cancelYn + "]";
	}

}
