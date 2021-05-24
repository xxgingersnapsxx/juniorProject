package manager;

public class ManagerVO {
	private String managerId;
	private String managerPw;

	public ManagerVO(String managerId, String managerPw) {
		super();
		this.managerId = managerId;
		this.managerPw = managerPw;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPw() {
		return managerPw;
	}

	public void setManagerPw(String managerPw) {
		this.managerPw = managerPw;
	}

	@Override
	public String toString() {
		return "ManagerVO [managerId=" + managerId + ", managerPw=" + managerPw + "]";
	}

}
