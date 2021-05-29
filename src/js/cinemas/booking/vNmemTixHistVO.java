package js.cinemas.booking;

import java.time.LocalDateTime;

public class vNmemTixHistVO {
	private String nMemId;
	private int showId;
	private String seatNo;
	private String nMemMobile;
	private LocalDateTime showDate;
	private String cancelYn;

	public vNmemTixHistVO(String nMemId, int showId, String seatNo, String nMemMobile, LocalDateTime showDate,
			String cancelYn) {
		super();
		this.nMemId = nMemId;
		this.showId = showId;
		this.seatNo = seatNo;
		this.nMemMobile = nMemMobile;
		this.showDate = showDate;
		this.cancelYn = cancelYn;
	}

	public String getnMemId() {
		return nMemId;
	}

	public void setnMemId(String nMemId) {
		this.nMemId = nMemId;
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

	public String getnMemMobile() {
		return nMemMobile;
	}

	public void setnMemMobile(String nMemMobile) {
		this.nMemMobile = nMemMobile;
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
		return "vNmemTixHistVO [nMemId=" + nMemId + ", showId=" + showId + ", seatNo=" + seatNo + ", nMemMobile="
				+ nMemMobile + ", showDate=" + showDate + ", cancelYn=" + cancelYn + "]";
	}

}
