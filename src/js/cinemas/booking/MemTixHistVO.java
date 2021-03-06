package js.cinemas.booking;

public class MemTixHistVO {
	private int memTixHistId;
	private String memId;
	private int showId;
	private String seatNo;
	private String cancelYn;

	public MemTixHistVO(String memId, int showId, String seatNo, String cancelYn) {
		super();
		this.memId = memId;
		this.showId = showId;
		this.seatNo = seatNo;
		this.cancelYn = cancelYn;
	}

	public MemTixHistVO(int memTixHistId, String memId, int showId, String seatNo, String cancelYn) {
		super();
		this.memTixHistId = memTixHistId;
		this.memId = memId;
		this.showId = showId;
		this.seatNo = seatNo;
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

	public String getCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}

	public int getMemTixHistId() {
		return memTixHistId;
	}

	public void setMemTixHistId(int memTixHistId) {
		this.memTixHistId = memTixHistId;
	}

	@Override
	public String toString() {
		return "MemTixHistVO [memTixHistId=" + memTixHistId + ", memId=" + memId + ", showId=" + showId + ", seatNo="
				+ seatNo + ", cancelYn=" + cancelYn + "]";
	}

}
