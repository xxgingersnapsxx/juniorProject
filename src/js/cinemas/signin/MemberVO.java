package js.cinemas.signin;

import java.time.LocalDate;

public class MemberVO {
	private String memId;
	private String memPass;
	private String memName;
	private LocalDate memBir;
	private String memBirString;
	private String memAdd1;
	private String memAdd2;
	private String memMobile;
	private int memMileage;
	private String memDelete;
	private String locationId;

	public MemberVO(String memId, String memPass, String memName, String memBirString, String memAdd1, String memAdd2,
			String memMobile) {
		super();
		this.memId = memId;
		this.memPass = memPass;
		this.memName = memName;
		this.memBirString = memBirString;
		this.memAdd1 = memAdd1;
		this.memAdd2 = memAdd2;
		this.memMobile = memMobile;

	}

	public MemberVO(String memId, String memPass, String memName, String memBirString, String memAdd1, String memAdd2,
			String memMobile, int memMileage, String memDelete, String locationId) {
		super();
		this.memId = memId;
		this.memPass = memPass;
		this.memName = memName;
		this.memBirString = memBirString;
		this.memAdd1 = memAdd1;
		this.memAdd2 = memAdd2;
		this.memMobile = memMobile;
		this.memMileage = memMileage;
		this.memDelete = memDelete;
		this.locationId = locationId;
	}

	public MemberVO(String memId, String memPass, String memName, LocalDate memBir, String memAdd1, String memAdd2,
			String memMobile, int memMileage, String memDelete, String locationId) {
		super();
		this.memId = memId;
		this.memPass = memPass;
		this.memName = memName;
		this.memBir = memBir;
		this.memAdd1 = memAdd1;
		this.memAdd2 = memAdd2;
		this.memMobile = memMobile;
		this.memMileage = memMileage;
		this.memDelete = memDelete;
		this.locationId = locationId;
	}

	public MemberVO(String memPass) {
		this.memPass = memPass;
	}

	public MemberVO(String memId, String memName, int memMileage) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memMileage = memMileage;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public LocalDate getMemBir() {
		return memBir;
	}

	public void setMemBir(LocalDate memBir) {
		this.memBir = memBir;
	}

	public String getMemAdd1() {
		return memAdd1;
	}

	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}

	public String getMemAdd2() {
		return memAdd2;
	}

	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}

	public String getMemMobile() {
		return memMobile;
	}

	public void setMemMobile(String memMobile) {
		this.memMobile = memMobile;
	}

	public int getMemMileage() {
		return memMileage;
	}

	public void setMemMileage(int memMileage) {
		this.memMileage = memMileage;
	}

	public String getMemDelete() {
		return memDelete;
	}

	public void setMemDelete(String memDelete) {
		this.memDelete = memDelete;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getMemBirString() {
		return memBirString;
	}

	public void setMemBirString(String memBirString) {
		this.memBirString = memBirString;
	}

	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memPass=" + memPass + ", memName=" + memName + ", memBir=" + memBir
				+ ", memBirString=" + memBirString + ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memMobile="
				+ memMobile + ", memMileage=" + memMileage + ", memDelete=" + memDelete + ", locationId=" + locationId
				+ "]";
	}

}
