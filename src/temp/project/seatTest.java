package temp.project;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class seatTest {

	public static void main(String[] args) {

		// System.out.println("①②③④⑤⑥⑦⑧⑨⑩● ");
		// System.out.println("⓵⓶⓷⓸⓹⓺⓻⓼⓽⓾");
		// System.out.println("❶❷❸❹❺❻❼❽❾❿");
		// System.out.println("➊➋➌➍➎➏➐➑➒➓");

		int numberOfSeat = 50;
		int col = 10;
		int row = numberOfSeat / col;

		String[][] seat = new String[row][col];

		String seatOracleData = "⓪①②③④⑤⑥⑦⑧⑨⓪①②③④⑤⑥⑦⑧⑨⓪①②③④⑤⑥⑦⑧⑨⓪①②③④⑤⑥⑦⑧⑨⓪①②③④⑤⑥⑦⑧⑨";
		String rowAlphabet = "ⓐⓑⓒⓓⓔⓕⓖⓗⓘⓙⓚⓛⓜⓝⓞⓟⓠⓡⓢⓣⓤⓥⓦⓧⓨⓩ";

		System.out.println("      screen       ");
		System.out.println("-------------------");
		for (int i = 0; i < seat.length; i++) {
			System.out.print(rowAlphabet.substring(i, i + 1) + " ");
			for (int j = 0; j < seat[i].length; j++) {
				String iToString = String.valueOf(i);
				String jToString = String.valueOf(j);
				int startIndex = Integer.parseInt(iToString + jToString);
				seat[i][j] = seatOracleData.substring(startIndex, startIndex + 1);
				System.out.print(seat[i][j]);
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();

		seat[2][3] = "●"; // c3

		// for (int i = 0; i < seat.length; i++) {
		// System.out.print(rowAlphabet.substring(i, i + 1) + " ");
		// for (int j = 0; j < seat[i].length; j++) {
		// System.out.print(seat[i][j]);
		// }
		// System.out.println();
		// }
		// String finalSeatData = seatTest.deepToString(seat);
		// System.out.println(finalSeatData);

		// 알파벳별 숫자
		char rowAlphbet = 'A';
		int getColNum = 1;

		// System.out.println((int)rowAlphbet - 64);
		int seatNumToChange = Integer.parseInt(String.valueOf((int) rowAlphbet - 64) + String.valueOf(getColNum));
		// System.out.println(seatNumToChange);
		// 좌석 string 교체
		StringBuilder builder = new StringBuilder(seatOracleData);
		// builder.setCharAt(seatNumToChange, '❶');
		// seatOracleData = builder.toString();
		// System.out.println(seatOracleData);

		// scanner로 입력받아 자리 바꾸기 Test
		Scanner scanner = new Scanner(System.in);
		System.out.print("예약 좌석 입력 : ");
		String scannedSeat = scanner.next();
		rowAlphbet = scannedSeat.charAt(0);
		getColNum = Integer.parseInt(scannedSeat.substring(1));

		seatNumToChange = Integer.parseInt(String.valueOf((int) rowAlphbet - 65) + String.valueOf(getColNum));
		builder.setCharAt(seatNumToChange, '●');
		seatOracleData = builder.toString();
		// System.out.println(seatOracleData);

		// 자리 바꾼 뒤 출력
		System.out.println();
		System.out.println("      screen       ");
		System.out.println("-------------------");
		for (int i = 0; i < seat.length; i++) {
			System.out.print(rowAlphabet.substring(i, i + 1) + " ");
			for (int j = 0; j < seat[i].length; j++) {
				String iToString = String.valueOf(i);
				String jToString = String.valueOf(j);
				int startIndex = Integer.parseInt(iToString + jToString);
				seat[i][j] = seatOracleData.substring(startIndex, startIndex + 1);
				System.out.print(seat[i][j]);
			}
			System.out.println();
		}

	}

	public static String deepToString(Object[] a) {
		if (a == null)
			return "null";

		int bufLen = 20 * a.length;
		if (a.length != 0 && bufLen <= 0)
			bufLen = Integer.MAX_VALUE;
		StringBuilder buf = new StringBuilder(bufLen);
		deepToString(a, buf, new HashSet<Object[]>());
		return buf.toString();
	}

	private static void deepToString(Object[] a, StringBuilder buf, Set<Object[]> dejaVu) {
		if (a == null) {
			buf.append("null");
			return;
		}
		int iMax = a.length - 1;
		if (iMax == -1) {
			return;
		}

		dejaVu.add(a);
		for (int i = 0;; i++) {

			Object element = a[i];
			if (element == null) {
				buf.append("null");
			} else {
				Class<?> eClass = element.getClass();

				if (eClass.isArray()) {
					if (eClass == byte[].class)
						buf.append(toString((byte[]) element));
					else { // element is an array of object references
						if (dejaVu.contains(element))
							buf.append("...");
						else
							deepToString((Object[]) element, buf, dejaVu);
					}
				} else { // element is non-null and not an array
					buf.append(element.toString());
				}
			}
			if (i == iMax)
				break;
		}
		dejaVu.remove(a);
	}

	public static String toString(byte[] a) {
		if (a == null)
			return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
			return "";

		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(a[i]);
			if (i == iMax)
				return b.toString();
		}
	}
}
