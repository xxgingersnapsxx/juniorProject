package temp.main;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import js.cinemas.locs.LocationsDAO;
import js.cinemas.locs.LocationsVO;
import js.cinemas.locs.LocationsWithCityVO;
import js.cinemas.locs.ZoneVO;
import js.cinemas.locs.zoneDao;
import js.cinemas.movies.MoviesDAO;
import js.cinemas.movies.MoviesVO;
import js.cinemas.movies.moviesByLocVO;
import js.cinemas.signin.ManagerDAO;
import js.cinemas.signin.MemberDao;
import js.cinemas.signin.MemberVO;

public class CinemaMain {

	public static void main(String[] args) throws Exception {
		MemberDao memberDao = new js.cinemas.signin.MemberDao();
		ManagerDAO managerDAO = new js.cinemas.signin.ManagerDAO();
		MoviesDAO moviesDAO = new js.cinemas.movies.MoviesDAO();
		zoneDao zoneDao = new js.cinemas.locs.zoneDao();
		LocationsDAO locDAO = new js.cinemas.locs.LocationsDAO();

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
				// 관리자 로그인 성공 후 메뉴
				System.out.println("(관리자 메뉴)");
				System.out.print("메뉴 선택 (1. 영화 관리  2. 상영관 관리	 3. 영화관 관리  4. 회원 관리) : ");
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
					System.out.print(
							"메뉴 선택 (1. 전체 상영관 조회  2. 지역별 상영관 조회  3. 지점별 상영관 조회  4. 상영관 추가  5.  상영관 정보 수정  6. 상영관 삭제) : ");
					selectCode = scanner.nextInt();
					switch (selectCode) {
					case 1:
						System.out.println("(관리자 메뉴 - 2. 상영관 관리 - 1. 전체 상영관 조회)");
						List<ZoneVO> selectAllZoneList = zoneDao.selectAllZoneList();
						for (ZoneVO zoneVO : selectAllZoneList) {
							System.out.print(zoneVO.getLocationId() + "\t");
							System.out.print(zoneVO.getLocationName() + " " + zoneVO.getZoneId() + "관 ");
							System.out.print(zoneVO.getSeatCnt() + "석\t");
							System.out.print(zoneVO.getOpenedYn() + "\n");
						}
						break;
					case 2:
						System.out.println("(관리자 메뉴 - 2. 상영관 관리 - 2. 지역별 상영관 조회)");
						// TODO 지역별 상영관 조회 METHOD 만들기
						break;
					case 3:
						System.out.println("(관리자 메뉴 - 2. 상영관 관리 - 3. 지점별 상영관 조회)");
						System.out.print("조회할 지점명 입력 : ");
						String locName = scanner.next();
						List<ZoneVO> selectAllZoneListByLoc = zoneDao.selectAllZoneListByLoc(locName);
						for (ZoneVO zoneVO : selectAllZoneListByLoc) {
							System.out.print(zoneVO.getLocationName() + " " + zoneVO.getZoneId() + "관 ");
							System.out.print(zoneVO.getSeatCnt() + "석\t");
							System.out.print(zoneVO.getOpenedYn() + "\n");
						}
						break;
					case 4:
						System.out.println("(관리자 메뉴 - 2. 상영관 관리 - 4. 상영관 추가)");
						System.out.print("상영관 추가 할 지점명 입력 : ");
						locName = scanner.next();
						System.out.print("좌석 수 입력 : ");
						int seatCnt = scanner.nextInt();
						zoneDao.insertZone(locName, seatCnt);
						break;
					case 5:
						System.out.println("(관리자 메뉴 - 2. 상영관 관리 - 5. 상영관 좌석 수 수정)");
						System.out.print("상영관 정보 수정 할 지점명 입력 : ");
						locName = scanner.next();
						System.out.print("상영관 번호 입력 : ");
						int zoneId = scanner.nextInt();
						System.out.print("새 좌석 수 입력 : ");
						seatCnt = scanner.nextInt();
						zoneDao.updateZoneSeatCnt(locName, zoneId, seatCnt);
						break;
					case 6:
						System.out.println("(관리자 메뉴 - 2. 상영관 관리 - 6. 상영관 삭제)");
						System.out.print("삭제할 상영관 지점명 입력 : ");
						locName = scanner.next();
						System.out.print("상영관 번호 입력 : ");
						zoneId = scanner.nextInt();
						zoneDao.deleteZone(locName, zoneId);
						break;
					default:
						break;
					}
					break;
				case 3:
					System.out.println("(관리자 메뉴 - 3. 영화관 관리)");
					System.out.print("메뉴 선택(1. 전체 영화관 조회  2. 지역별 영화관 조회  3. 영화관 추가  4. 영화관 정보 수정  5. 영화관 삭제) : ");
					selectCode = scanner.nextInt();
					switch (selectCode) {
					case 1:
						System.out.println("(관리자 메뉴 - 3. 영화관 관리 - 1. 전체 영화관 조회)");
						List<LocationsWithCityVO> allLocationsList = locDAO.selectAllLocationsList();
						for (LocationsWithCityVO locationsVO : allLocationsList) {
							System.out.print(locationsVO.getCityName() + '\t');
							System.out.print(locationsVO.getLocationId() + '\t');
							System.out.print(locationsVO.getLocationName() + '\n');
						}
						break;
					case 2:
						System.out.println("(관리자 메뉴 - 3. 영화관 관리 - 2. 지역별 영화관 조회)");
						System.out.print("지역 입력 : ");
						String ctName = scanner.next();
						List<LocationsWithCityVO> allLocListByCity = locDAO.selectAllLocListByCity(ctName);
						for (LocationsWithCityVO locationsVO : allLocListByCity) {
							System.out.print(locationsVO.getCityName() + '\t');
							System.out.print(locationsVO.getLocationId() + '\t');
							System.out.print(locationsVO.getLocationName() + '\n');
						}
						break;
					case 3:
						System.out.println("(관리자 메뉴 - 3. 영화관 관리 - 3. 영화관 추가)");
						scanner.nextLine();
						System.out.print("지점명 입력 : ");
						String locationName = scanner.nextLine();
						System.out.print("주소 1 입력 : ");
						String locAddr1 = scanner.nextLine();
						System.out.print("주소 2 입력 : ");
						String locAddr2 = scanner.nextLine();
						locDAO.insertLocation(new LocationsVO(locationName, locAddr1, locAddr2));
						break;
					case 4:
						System.out.println("(관리자 메뉴 - 3. 영화관 관리 - 4. 영화관 정보 수정)");
						// TODO 영화관 정보 수정 메소드 만들기
						break;
					case 5:
						System.out.println("(관리자 메뉴 - 3. 영화관 관리 - 5. 영화관 삭제)");
						System.out.print("삭제할 영화관 지점명 입력 : ");
						String locName = scanner.next();
						locDAO.updateLocationYN(locName);
						break;
					default:
						break;
					}
					break;
				case 4:
					System.out.println("(관리자 메뉴 - 4. 회원 관리)");
					System.out.print("메뉴 선택  (1. 전체 회원 조회  2. 특정 회원 조회 후 마일리지 수정) : ");
					selectCode = scanner.nextInt();
					switch (selectCode) {
					case 1:
						System.out.println("(관리자 메뉴 - 4. 회원 관리 - 1. 전체 회원 조회)");
						List<MemberVO> allMemberList = memberDao.selectAllMemberList();
						for (MemberVO memberVO : allMemberList) {
							System.out.print(memberVO.getMemId() + "\t");
							System.out.print(memberVO.getMemName() + "\t");
							System.out.print(
									memberVO.getMemBir().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) + "\t");
							System.out.print(memberVO.getMemAdd1() + " " + memberVO.getMemAdd2() + "\t");
							System.out.print(memberVO.getMemMobile() + "\t");
							System.out.print(memberVO.getMemMileage() + "\t");
							System.out.print(memberVO.getMemDelete() + "\t");
							System.out.print(memberVO.getLocationId() + "\n");
						}
						break;
					case 2:
						System.out.println("(관리자 메뉴 - 4. 회원 관리 - 2. 특정 회원 조회 후 마일리지 수정");
						System.out.print("조회할 회원 ID 입력 : ");
						String memId = scanner.next();
						MemberVO memberMileage = memberDao.selectMemberMileage(memId);
						System.out.println("회원 " + memId + " 잔여 마일리지 : " + memberMileage.getMemMileage());
						System.out.print("마일리지 정보 변경을 위해 1 입력 : ");
						selectCode = scanner.nextInt();
						switch (selectCode) {
						case 1:
							System.out.println("(관리자 메뉴 - 4. 회원 관리 - 2. 특정 회원 조회 1. 회원 정보 수정(마일리지 정보 변경))");
							System.out.print("추가할 마일리지 입력 : ");
							int mileage = scanner.nextInt();
							memberDao.updateMileage(memId, mileage);							
							memberMileage = memberDao.selectMemberMileage(memId);
							System.out.println("변경 후 회원 " + memId + " 잔여 마일리지 : " + memberMileage.getMemMileage());
							break;
						default:
							break;
						}
						break;
					default:
						break;
					}
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
