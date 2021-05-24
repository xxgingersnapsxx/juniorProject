package main;

import java.util.Scanner;

import manager.ManagerDAO;
import member.MemberDao;
import member.MemberVO;

public class CinemaMain {

	public static void main(String[] args) throws Exception {
		MemberDao memberDao = new member.MemberDao();
		ManagerDAO managerDAO = new manager.ManagerDAO();

		// main logo print
		System.out.println("        CCCCCCCCCCCCC       GGGGGGGGGGGGGVVVVVVVV           VVVVVVVV");
		System.out.println("     CCC::::::::::::C    GGG::::::::::::GV::::::V           V::::::V");
		System.out.println("   CC:::::::::::::::C  GG:::::::::::::::GV::::::V           V::::::V");
		System.out.println("  C:::::CCCCCCCC::::C G:::::GGGGGGGG::::GV::::::V           V::::::V");
		System.out.println(" C:::::C       CCCCCCG:::::G       GGGGGG V:::::V           V:::::V ");
		System.out.println("C:::::C             G:::::G                V:::::V         V:::::V  ");
		System.out.println("C:::::C             G:::::G                 V:::::V       V:::::V   ");
		System.out.println("C:::::C             G:::::G    GGGGGGGGGG    V:::::V     V:::::V    ");
		System.out.println("C:::::C             G:::::G    G::::::::G     V:::::V   V:::::V     ");
		System.out.println("C:::::C             G:::::G    GGGGG::::G      V:::::V V:::::V      ");
		System.out.println("C:::::C             G:::::G        G::::G       V:::::V:::::V       ");
		System.out.println(" C:::::C       CCCCCCG:::::G       G::::G        V:::::::::V        ");
		System.out.println("  C:::::CCCCCCCC::::C G:::::GGGGGGGG::::G         V:::::::V         ");
		System.out.println("   CC:::::::::::::::C  GG:::::::::::::::G          V:::::V          ");
		System.out.println("     CCC::::::::::::C    GGG::::::GGG:::G           V:::V           ");
		System.out.println("        CCCCCCCCCCCCC       GGGGGG   GGGG            VVV            ");

		System.out.println();
		System.out.println();

		System.out.print("LOGIN 선택 (1. 회원	2. 비회원 예매	3. 관리자 화면) : ");
		Scanner scanner = new Scanner(System.in);
		int selectCode = scanner.nextInt(); // LOGIN 선택
		String loginID = null;
		String loginPW = null;
		boolean roginResult = false;

		switch (selectCode) {
		case 1:
			// TODO

			// 회원 로그인 시작
			System.out.println("회원 로그인");
			System.out.print("ID 입력 : ");
			loginID = scanner.next();
			System.out.print("PW 입력 : ");
			loginPW = scanner.next();

			roginResult = memberDao.memberLogin(loginID, loginPW);
			// 회원 로그인 성공/실패 분기
			if (roginResult) {
				// TODO 회원 로그인 성공 후 메뉴
				// TODO 로그인 id get으로 받아오기
				// TODO 뒤로가기 어떻게 구현해... 
				System.out.println(loginID + " 로그인 성공");
				System.out.println("다음 메뉴 선택 (1. 영화별 예매	2. 극장별 예매 	3. 마일리지 조회) : ");
				selectCode = scanner.nextInt();
				switch (selectCode) {
				case 1:
					// TODO
					break;
				case 2:
					// TODO
					break;
				case 3: // 보유 마일리지 조회
					MemberVO vo = memberDao.selectMemberMileage(loginID);
					System.out.print("보유 마일리지 : " + vo.getMemMileage());
					break;
				default:
					break;
				}

			} else {
				// TODO 회원 로그인 재시도
				System.out.println("나가기");
			}
			break;

		case 2:
			System.out.println("비회원 예매");
			// TODO
			break;
		case 3:
			System.out.println("관리자 화면");
			// TODO

			// 관리자 로그인 시작
			System.out.print("ID 입력 : ");
			loginID = scanner.next();
			System.out.print("PW 입력 : ");
			loginPW = scanner.next();
			roginResult = managerDAO.managerLogin(loginID, loginPW);

			// 관리자 로그인 성공/실패 분기
			if (roginResult) {
				// TODO 관리자 로그인 성공 후 메뉴
				System.out.println("다음 메뉴 선택 : ");
			} else {
				// TODO 관리자 로그인 재시도
				System.out.println("나가기");
			}

			break;

		default:
			System.out.println("오류");
			// TODO 입력 오류시 main으로 return
			break;
		}

	}

}
