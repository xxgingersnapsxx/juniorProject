package js.cinemas.signin;

public class NonMemberVO {
	private String nMemId;

	public NonMemberVO(String nMemId) {
		super();
		this.nMemId = nMemId;
	}

	public String getnMemId() {
		return nMemId;
	}

	public void setnMemId(String nMemId) {
		this.nMemId = nMemId;
	}

	@Override
	public String toString() {
		return "NonMemberVO [nMemId=" + nMemId + "]";
	}

}
