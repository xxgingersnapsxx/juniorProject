package js.cinemas.booking;

public class NMemTixHistVO {
	private int showId;
	private String nMemId;
	private String nMemMobile;
	private String seatNo;
	private String cancelYn;

	public NMemTixHistVO(int showId, String nMemId, String nMemMobile, String seatNo, String cancelYn) {
		super();
		this.showId = showId;
		this.nMemId = nMemId;
		this.nMemMobile = nMemMobile;
		this.seatNo = seatNo;
		this.cancelYn = cancelYn;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getnMemId() {
		return nMemId;
	}

	public void setnMemId(String nMemId) {
		this.nMemId = nMemId;
	}

	public String getnMemMobile() {
		return nMemMobile;
	}

	public void setnMemMobile(String nMemMobile) {
		this.nMemMobile = nMemMobile;
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

	@Override
	public String toString() {
		return "NMemTixHistVO [showId=" + showId + ", nMemId=" + nMemId + ", nMemMobile=" + nMemMobile + ", seatNo="
				+ seatNo + ", cancelYn=" + cancelYn + "]";
	}

}
