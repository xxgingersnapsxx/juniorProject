package temp.project;

public class generateSeatsTest {

	public static void main(String[] args) {
		// TODO 좌석 string 이렇게 생성해서 넣어주면 됨
		int seatCnt = 20;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < seatCnt / 10; i++) {
			builder.append("⓪①②③④⑤⑥⑦⑧⑨");
		}

		System.out.println(builder.toString());
	}

}
