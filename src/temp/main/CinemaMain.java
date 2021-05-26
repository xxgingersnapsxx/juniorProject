package temp.main;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import js.cinema.signin.ManagerDAO;
import js.cinema.signin.MemberDao;
import js.cinema.signin.MemberVO;
import js.cinemas.movies.MoviesDAO;
import js.cinemas.movies.MoviesVO;
import js.cinemas.movies.moviesByLocVO;

public class CinemaMain {

	public static void main(String[] args) throws Exception {
		MemberDao memberDao = new js.cinema.signin.MemberDao();
		ManagerDAO managerDAO = new js.cinema.signin.ManagerDAO();
		MoviesDAO moviesDAO = new js.cinemas.movies.MoviesDAO();

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

		System.out.print("LOGIN 선택 (1. 관리자 	2.회원 로그인 	3. 비회원 예매) : ");
		Scanner scanner = new Scanner(System.in);
		int selectCode = scanner.nextInt(); // LOGIN 선택
		String loginID = null;
		String loginPW = null;
		boolean roginResult = false;

		switch (selectCode) {
		case 1:
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
				System.out.println("(관리자 메뉴)");
				System.out.println("메뉴 선택 (1. 영화 관리  2. 상영관 관리	 3. 영화관 관리  4. 회원 관리) : ");
				selectCode = scanner.nextInt();
				switch (selectCode) {
				case 1:
					System.out.println("(관리자 메뉴 - 1. 영화 관리)");
					System.out.println(
							"메뉴 선택 (1. 전체 영화 조회  2. 상영중인 영화 조회  3. 지점별 영화 조회  4. 영화 추가  5. 영화 정보 수정  6. 영화 삭제) : ");
					selectCode = scanner.nextInt();
					switch (selectCode) {
					case 1:
						System.out.println("(관리자 메뉴 - 1. 영화 관리 - 1. 전체 영화 조회)");
						List<MoviesVO> allMoviesList = moviesDAO.selectAllMoviesList();
						for (MoviesVO moviesVO : allMoviesList) {
							System.out.print(moviesVO.getMovieId() + "\t");
							// TODO lpad같은거 찾아서 오라클에서 수정하기
							System.out.print(moviesVO.getMvTitle() + "\t\t\t");
							System.out.print(moviesVO.getDuration() + "\t");
							System.out.print(moviesVO.getMovieYn() + "\n");
						}
						break;
					case 2:
						System.out.println("(관리자 메뉴 - 1. 영화 관리 - 2. 상영중인 영화 조회)");
						List<MoviesVO> nowShowingMVList = moviesDAO.selectNowShowingMVList();
						for (MoviesVO moviesVO : nowShowingMVList) {
							System.out.print(moviesVO.getMovieId() + "\t");
							// TODO lpad같은거 찾아서 오라클에서 수정하기
							System.out.print(moviesVO.getMvTitle() + "\t\t\t");
							System.out.print(moviesVO.getDuration() + "\t");
							System.out.print(moviesVO.getMovieYn() + "\n");
						}
						break;
					case 3:
						System.out.println("(관리자 메뉴 - 1. 영화 관리 - 3. 지점별 영화 조회)");
						System.out.println("조회할 지점명 입력");
						String locName = scanner.next();
						List<moviesByLocVO> nowShowingMVListByLoc = moviesDAO.selectNowMVListByLoc(locName);
						for (moviesByLocVO moviesByLocVO : nowShowingMVListByLoc) {
							System.out.print(moviesByLocVO.getShowId() + "\t");
							System.out.print(moviesByLocVO.getMvTitle() + "\t");
							System.out.print(
									moviesByLocVO.getStartsAt().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 hh:mm"))
											+ " - ");
							System.out.print(
									moviesByLocVO.getEndsAt().format(DateTimeFormatter.ofPattern("hh:mm")) + "\t");
							System.out.print(moviesByLocVO.getPriceAmt() + "원" + "\t\t");
							System.out.print(moviesByLocVO.getZoneId() + "\t");
						}
						break;
					case 4:
						System.out.println("(관리자 메뉴 - 1. 영화 관리 - 4. 영화 추가)");
						System.out.print("등록할 영화 제목 입력 : ");
						scanner.nextLine();
						String mvTitle = scanner.nextLine();
						System.out.print("등록할 영화 상영 길이 입력 : ");
						int duration = scanner.nextInt();
						moviesDAO.insertMovies(new MoviesVO(mvTitle, duration));
						break;
					case 5:
						System.out.println("(관리자 메뉴 - 1. 영화 관리 - 5. 영화 정보 수정)");
						// TODO : 영화 정보 수정 메소드 만들고 추가
						break;
					case 6:
						System.out.println("(관리자 메뉴 - 1. 영화 관리 - 6. 영화 삭제)");
						System.out.print("삭제할 영화 ID 입력 : ");
						int movieId = scanner.nextInt();
						moviesDAO.changeMovieStatus(movieId, "N");
						break;
					default:
						break;
					}
					break;
				case 2:
					System.out.println("(관리자 메뉴 - 2. 상영관 관리)");
					switch (selectCode) {
					case 1:
						System.out.println("(관리자 메뉴 - 2. 상영관 관리 - 1. 전체 상영관 조회)");
						System.out.println("");
						break;
					case 2:

						break;
					case 3:

						break;

					default:
						break;
					}
					break;
				case 3:
					System.out.println("(관리자 메뉴 - 3. 영화관 관리)");
					break;
				case 4:
					System.out.println("(관리자 메뉴 - 2. 회원 관리)");
					break;
				default:
					System.out.println("관리자 메뉴 1 -1 잘못된 선택");
					break;
				}
			} else {
				// TODO 관리자 로그인 재시도
				System.out.println("나가기");
			}

			break;

		case 2:

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
				System.out.println("ID : " + loginID + " 로그인 성공!");
				System.out.println("다음 메뉴 선택 (1. 영화별 예매	2. 극장별 예매 	3. 마일리지 조회) : ");
				selectCode = scanner.nextInt();
				switch (selectCode) {
				case 1:
					// TODO
					break;
				case 2:
					// TODO
					// TODO : 지점별 상영 영화 조회 Method 만들어서 삽입, 리스트 sysout으로 보여주기
					// TODO : show_id scanner로 입력받기
					// TODO : show_id로 좌석 정보 띄워주기
					// TODO : 좌석 입력받기
					// TODO : 입력확정
					// TODO : tixhist에 데이터 입력
					// TODO : 티켓 보여주기
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
		case 3:
			System.out.println("비회원 예매");
			// TODO
			break;
		default:
			System.out.println("오류");
			// TODO 입력 오류시 main으로 return
			break;
		}

	}

}
