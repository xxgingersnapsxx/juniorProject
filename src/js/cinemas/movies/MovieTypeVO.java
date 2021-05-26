package js.cinemas.movies;

public class MovieTypeVO {
	private String typeId;
	private String typeName;

	public MovieTypeVO(String typeId, String typeName) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "MovieTypeVO [typeId=" + typeId + ", typeName=" + typeName + "]";
	}

}
