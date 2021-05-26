package js.cinemas.locs;

public class ZoneVO {
	private String locationId;
	private int zoneId;
	private int seatCnt;
	private String openedYn;

	public ZoneVO(String locationId, int zoneId, int seatCnt, String openedYn) {
		super();
		this.locationId = locationId;
		this.zoneId = zoneId;
		this.seatCnt = seatCnt;
		this.openedYn = openedYn;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public int getSeatCnt() {
		return seatCnt;
	}

	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}

	public String getOpenedYn() {
		return openedYn;
	}

	public void setOpenedYn(String openedYn) {
		this.openedYn = openedYn;
	}

	@Override
	public String toString() {
		return "ZoneVO [locationId=" + locationId + ", zoneId=" + zoneId + ", seatCnt=" + seatCnt + ", openedYn="
				+ openedYn + "]";
	}

}
