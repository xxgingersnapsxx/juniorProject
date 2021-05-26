package js.cinemas.locs;

public class ZoneVO {
	private int zoneId;
	private int seatCnt;
	private String openedYn;
	private String locationId;
	private String locationName;

	public ZoneVO(int zoneId, int seatCnt, String openedYn, String locationId, String locationName) {
		super();
		this.zoneId = zoneId;
		this.seatCnt = seatCnt;
		this.openedYn = openedYn;
		this.locationId = locationId;
		this.locationName = locationName;
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

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public String toString() {
		return "zoneByLocNameVO [zoneId=" + zoneId + ", seatCnt=" + seatCnt + ", openedYn=" + openedYn + ", locationId="
				+ locationId + ", locationName=" + locationName + "]";
	}

}
