package project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class systemReturnTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int inputKey = 0;

		while (true) {
			System.out.print("메뉴 선택 : ");
			try {
				inputKey = scanner.nextInt();
				switch (inputKey) {
				case 1:
					System.out.println("메인 1");
					// 메인1의 첫번째 하위 메뉴 시작
					while (true) {
						System.out.print("메인 1의 하위 메뉴 선택 : ");
						try {
							inputKey = scanner.nextInt();
							switch (inputKey) {
							case 1:
								System.out.println("메인 1 - 1");
								return;
							case 2:
								System.out.println("메인 1 - 2");
								return;
							default:
								System.out.println("입력 번호에 해당하는 메뉴 없음, 메인1 로 복귀");
							}
						} catch (InputMismatchException e) {
							System.out.println("메인1 - 1의 입력 타입 오류, 메인1로 복귀");
							scanner.next();
						}
					}
				case 2:
					System.out.println("메인 2");
					return;
				default:
					System.out.println("입력 번호에 해당하는 메뉴 없음, 메인 메뉴로 복귀");
				}
			} catch (InputMismatchException e) {
				System.out.println("잘못된 문자 입력, 메인 메뉴로 복귀");
				scanner.next();
			}
			if (inputKey == 0) {
				break;
			}
		}
		scanner.close();
	}
}
