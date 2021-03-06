package js.cinemas.locs;

public class LocationsWithCityVO {
	private String locationId;
	private String cityId;
	private String locationName;
	private String locAddr1;
	private String locAddr2;
	private String cityName;

	public LocationsWithCityVO(String locationId, String locationName) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
	}

	public LocationsWithCityVO(String locationId, String cityName, String locationName) {
		super();
		this.locationId = locationId;
		this.cityName = cityName;
		this.locationName = locationName;
	}

	public LocationsWithCityVO(String locationId, String cityId, String locationName, String locAddr1, String locAddr2,
			String cityName) {
		super();
		this.locationId = locationId;
		this.cityId = cityId;
		this.locationName = locationName;
		this.locAddr1 = locAddr1;
		this.locAddr2 = locAddr2;
		this.cityName = cityName;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocAddr1() {
		return locAddr1;
	}

	public void setLocAddr1(String locAddr1) {
		this.locAddr1 = locAddr1;
	}

	public String getLocAddr2() {
		return locAddr2;
	}

	public void setLocAddr2(String locAddr2) {
		this.locAddr2 = locAddr2;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "LocationsVO [locationId=" + locationId + ", cityId=" + cityId + ", locationName=" + locationName
				+ ", locAddr1=" + locAddr1 + ", locAddr2=" + locAddr2 + ", cityName=" + cityName + "]";
	}

}