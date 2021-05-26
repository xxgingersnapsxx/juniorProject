package js.cinemas.locs;

public class CitiesVO {
	private int cityId;
	private String cityName;

	public CitiesVO(int cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "CitiesVO [cityId=" + cityId + ", cityName=" + cityName + "]";
	}

}
