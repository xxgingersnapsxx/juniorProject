package main;

import nonMember.NonMemberDAO;
import nonMember.NonMemberVO;

public class MainTest {

	public static void main(String[] args) throws Exception {
		// MemberDao dao = new member.MemberDao();
		// ManagerDAO managerDAO = new manager.ManagerDAO();
		NonMemberDAO nonMemberDAO = new nonMember.NonMemberDAO();
		// Scanner scanner = new Scanner(System.in);

		/*
		 * 마일리지 조회 System.out.print("회원 ID 입력 : "); String memID = scanner.next();
		 * MemberVO vo = dao.selectMemberMileage(memID);
		 * 
		 * System.out.print(vo.getMemId()); System.out.print(vo.getMemMileage());
		 */
		/*
		 * 회원 로그인 test System.out.print("ID 입력 : "); String loginID = scanner.next();
		 * System.out.print("PW 입력 : "); String loginPW = scanner.next();
		 * dao.memberLogin(loginID, loginPW);
		 */

		/* 관리자 로그인 test */
		/*
		 * System.out.print("ID 입력 : "); String loginID = scanner.next();
		 * System.out.print("PW 입력 : "); String loginPW = scanner.next();
		 * managerDAO.managerLogin(loginID, loginPW);
		 */

		/* 비회원 id 생성 test */
		// 이거 다 맞게 들어가는데 왜...

		String nMemId = "a";
		NonMemberVO vo = new NonMemberVO(nMemId);
//		System.out.println(vo.getnMemId());

	}

}
