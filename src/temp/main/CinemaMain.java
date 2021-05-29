package temp.main;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import js.cinemas.booking.BookingDAO;
import js.cinemas.booking.MemTixHistDAO;
import js.cinemas.booking.MemTixHistVO;
import js.cinemas.booking.PriceDAO;
import js.cinemas.booking.cancelDao;
import js.cinemas.booking.seatDAO;
import js.cinemas.booking.tixView;
import js.cinemas.booking.vMemTixHistVO;
import js.cinemas.locs.LocationsDAO;
import js.cinemas.locs.LocationsVO;
import js.cinemas.locs.LocationsWithCityVO;
import js.cinemas.locs.ZoneVO;
import js.cinemas.locs.zoneDao;
import js.cinemas.movies.MoviesDAO;
import js.cinemas.movies.MoviesVO;
import js.cinemas.movies.MvTypeDao;
import js.cinemas.movies.MvTypeVO;
import js.cinemas.movies.NowShowingDAO;
import js.cinemas.movies.NowShowingInsertVO;
import js.cinemas.movies.NowShowingVO;
import js.cinemas.movies.moviesByLocVO;
import js.cinemas.signin.ManagerDAO;
import js.cinemas.signin.MemberDao;
import js.cinemas.signin.MemberVO;
import js.cinemas.signin.NonMemberDAO;

public class CinemaMain {

	public static void main(String[] args) throws Exception {
		MemberDao memberDao = new js.cinemas.signin.MemberDao();
		ManagerDAO managerDAO = new js.cinemas.signin.ManagerDAO();
		MoviesDAO moviesDAO = new js.cinemas.movies.MoviesDAO();
		zoneDao zoneDao = new js.cinemas.locs.zoneDao();
		LocationsDAO locDAO = new js.cinemas.locs.LocationsDAO();
		NowShowingDAO nowShowingDAO = new NowShowingDAO();
		BookingDAO bookingDAO = new BookingDAO();
		LocationsDAO locationsDAO = new LocationsDAO();
		seatDAO seatDAO = new seatDAO();
		MemTixHistDAO memTixHistDAO = new MemTixHistDAO();
		tixView tixView = new tixView();
		PriceDAO priceDAO = new PriceDAO();
		MvTypeDao mvTypeDao = new MvTypeDao();
		cancelDao cancelDao = new cancelDao();
		NonMemberDAO nonMemberDAO = new NonMemberDAO();
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

		System.out.print("LOGIN 선택 (1. 관리자 	2.회원 로그인 	3. 비회원 예매  4. 회원가입) : ");
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
				System.out.print("메뉴 선택 (1. 영화 관리  2. 상영관 관리	 3. 영화관 관리  4. 상영 일정 관리  5. 회원 관리) : ");
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
						System.out.println();

						List<MvTypeVO> selectMvTypeList = mvTypeDao.selectMvTypeList();
						for (MvTypeVO mvTypeVO : selectMvTypeList) {
							System.out.println(mvTypeVO.getTypeId() + " " + mvTypeVO.getTypeName());
						}

						System.out.print("등록할 영화 상영 타입 입력 : ");
						int mvType = scanner.nextInt();
						System.out.print("등록할 영화 상영 길이 입력 : ");
						int duration = scanner.nextInt();
						moviesDAO.insertMovies(new MoviesVO(mvTitle, mvType, duration));
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
						System.out.println();
						selectAllZoneListByLoc = zoneDao.selectAllZoneListByLoc(locName);
						for (ZoneVO zoneVO : selectAllZoneListByLoc) {
							System.out.print(zoneVO.getLocationName() + " " + zoneVO.getZoneId() + "관 ");
							System.out.print(zoneVO.getSeatCnt() + "석\t");
							System.out.print(zoneVO.getOpenedYn() + "\n");
						}
						System.out.println();
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
						List<LocationsWithCityVO> allLocListByCity = locDAO.selectAllLocListByAddr(ctName);
						for (LocationsWithCityVO locationsVO : allLocListByCity) {
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

						// FIXME 2개 들어가..
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
					System.out.println("(관리자 메뉴 - 4. 상영 일정 관리)");
					System.out.print("메뉴 선택  (1. 상영 일정 조회  2. 상영 일정 추가) : ");
					selectCode = scanner.nextInt();
					switch (selectCode) {
					case 1:
						System.out.println("(관리자 메뉴 - 4. 상영 일정 관리 - 1. 상영 일정 조회 )");
						System.out.print("메뉴 선택  (1. 지점별 상영 일정 조회  2. 지점별 상영관별 상영 일정 조회  3. 지점별 영화별 상영 일정 조회) : ");
						selectCode = scanner.nextInt();
						switch (selectCode) {
						case 1:
							System.out.println("(관리자 메뉴 - 4. 상영 일정 관리 - 1. 상영 일정 조회 - 1. 지점별 상영 일정 조회  )");
							System.out.print("조회할 지점명 입력 : ");
							String locName = scanner.next();

							List<NowShowingVO> selectNowShowingListByLoc = nowShowingDAO
									.selectNowShowingListByLoc(locName);
							for (NowShowingVO nowShowingVO : selectNowShowingListByLoc) {
								System.out.print(nowShowingVO.getShowId() + "\t");
								System.out.print(moviesDAO.returnMvName(nowShowingVO.getMovieId()) + "\t");
								System.out.print(nowShowingVO.getStartsAt()
										.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm")) + " - ");
								System.out.print(
										nowShowingVO.getEndsAt().format(DateTimeFormatter.ofPattern("HH:mm")) + "\t");
								System.out.print(nowShowingVO.getZoneId() + "관\t");
								// System.out.print(nowShowingDAO.availableSeatCnt(nowShowingVO.getShowId()) +
								// "관\n"); // 잔여 좌석
								int seatCnt = nowShowingDAO.availableSeatCnt(nowShowingVO.getShowId());
								if (seatCnt == 0) {
									System.out.println(nowShowingDAO.ifSoldOut(seatCnt));
								} else {
									System.out.println(seatCnt + "/" + nowShowingDAO.SeatCnt(nowShowingVO.getShowId(),
											nowShowingVO.getLocationId()));
								}
							}

							break;
						case 2:
							System.out.println("(관리자 메뉴 - 4. 상영 일정 관리 - 1. 상영 일정 조회 - 2. 지점별 상영관별 상영 일정 조회  )");

							System.out.print("조회할 지점명 입력 : ");
							locName = scanner.next();
							System.out.print("조회할 상영관 입력 : ");
							int zoneId = scanner.nextInt();
							List<NowShowingVO> selectNowShowingListByLocZone = nowShowingDAO
									.selectNowShowingListByLocZone(locName, zoneId);
							for (NowShowingVO nowShowingVO : selectNowShowingListByLocZone) {
								System.out.print(nowShowingVO.getShowId() + "\t");
								System.out.print(moviesDAO.returnMvName(nowShowingVO.getMovieId()) + "\t");
								System.out.print(nowShowingVO.getStartsAt()
										.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm")) + " - ");
								System.out.print(
										nowShowingVO.getEndsAt().format(DateTimeFormatter.ofPattern("HH:mm")) + "\t");
								System.out.print(nowShowingVO.getZoneId() + "관\t");
								// System.out.print(nowShowingDAO.availableSeatCnt(nowShowingVO.getShowId()) +
								// "관\n"); // 잔여 좌석
								int seatCnt = nowShowingDAO.availableSeatCnt(nowShowingVO.getShowId());
								if (seatCnt == 0) {
									System.out.println(nowShowingDAO.ifSoldOut(seatCnt));
								} else {
									System.out.println(seatCnt + "/" + nowShowingDAO.SeatCnt(nowShowingVO.getShowId(),
											nowShowingVO.getLocationId()));
								}
							}
							break;
						case 3:
							System.out.println("(관리자 메뉴 - 4. 상영 일정 관리 - 1. 상영 일정 조회 - 3. 지점별 영화별 상영 일정 조회  )");
							break;

						default:
							break;
						}
						break;
					case 2:
						System.out.println("(관리자 메뉴 - 4. 상영 일정 관리 - 2. 상영 일정 추가 )");
						System.out.println();
						System.out.print("지점명 입력 : ");
						String locName = scanner.next();

						// 지점별 상영관 list 출력
						List<ZoneVO> selectAllZoneListByLoc = zoneDao.selectAllZoneListByLoc(locName);
						for (ZoneVO zoneVO : selectAllZoneListByLoc) {
							System.out.print(zoneVO.getLocationName() + " " + zoneVO.getZoneId() + "관 ");
							System.out.print(zoneVO.getSeatCnt() + "석\t");
							System.out.print(zoneVO.getOpenedYn() + "\n");
						}

						System.out.println();
						System.out.print("상영관 입력 : ");
						int zoneId = scanner.nextInt();

						// 전체 추가 가능한 영화 리스트 출력
						System.out.println();
						System.out.println("*현재 상영중 영화 목록*");
						List<MoviesVO> allMoviesList = moviesDAO.selectAllMoviesList();
						for (MoviesVO moviesVO : allMoviesList) {
							System.out.print(moviesVO.getMovieId() + "\t");
							// TODO lpad같은거 찾아서 오라클에서 수정하기
							System.out.print(moviesVO.getMvTitle() + "\t\t\t");
							System.out.print(moviesVO.getDuration() + "\t");
							System.out.print(moviesVO.getMovieYn() + "\n");
						}

						System.out.println();
						System.out.print("추가 할 영화 ID 입력 : ");
						int movieId = scanner.nextInt();

						System.out.println();
						System.out.print("시작 날짜 입력 (YYYYMMDD) : ");
						int startDate = scanner.nextInt(); // INT 길이 때문에 그럼
						System.out.print("시작 시간 입력 (HH24MI) : ");
						int startTime = scanner.nextInt(); // INT 길이 때문에 그럼
						String startTimeToString = String.valueOf(startTime);
						// FIXME : 시간 길이 안맞음
						if (startTime < 1000) {
							startTimeToString = "0" + String.valueOf(startTime);
						}
						String startsAt = String.valueOf(startDate) + startTimeToString; // INT 길이 때문에 그럼

						int tixPrice = bookingDAO.returnPriceId(bookingDAO.returnIntTypeID(movieId),
								bookingDAO.returnTimeID(startsAt),
								bookingDAO.returnDayType(bookingDAO.returnDay(startsAt)));

						nowShowingDAO.insertMovieSchedule(new NowShowingInsertVO(nowShowingDAO.createNewShowId(),
								movieId, startsAt, startsAt, tixPrice, zoneId, locationsDAO.returnLocId(locName),
								nowShowingDAO.generateSeatsString(locationsDAO.returnLocId(locName), zoneId)));
						break;

					default:
						break;
					}

					break;
				case 5:
					System.out.println("(관리자 메뉴 - 5. 회원 관리)");
					System.out.print("메뉴 선택  (1. 전체 회원 조회  2. 특정 회원 조회 후 마일리지 수정) : ");
					selectCode = scanner.nextInt();
					switch (selectCode) {
					case 1:
						System.out.println("(관리자 메뉴 - 5. 회원 관리 - 1. 전체 회원 조회)");
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
				System.out.print("다음 메뉴 선택 (1. 예매	2. 예매 내역 조회  3. 예매 취소  4. 마일리지 조회) : ");
				selectCode = scanner.nextInt();
				switch (selectCode) {
				case 1:
					System.out.println("(회원메뉴 - 1. 예매)");
					// 지점별 예매 가능 영화 목록
					System.out.print("예매할 지점명 입력 : ");
					String locName = scanner.next();

					List<NowShowingVO> selectNowShowingListByLoc = nowShowingDAO.selectAvaNowShowingListByLoc(locName);
					if (selectNowShowingListByLoc.size() != 0) {
						for (NowShowingVO nowShowingVO : selectNowShowingListByLoc) {
							// 예매 불가한 상영관 건너뛰기
							if (nowShowingVO.getShowYn().equals("N")) {
								continue;
							}
							System.out.print(nowShowingVO.getShowId() + "\t");
							System.out.print(moviesDAO.returnMvName(nowShowingVO.getMovieId()) + "\t");
							System.out.print(
									nowShowingVO.getStartsAt().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm"))
											+ " - ");
							System.out.print(
									nowShowingVO.getEndsAt().format(DateTimeFormatter.ofPattern("HH:mm")) + "\t");
							System.out.print(nowShowingVO.getZoneId() + "관\t");
							int seatCnt = nowShowingDAO.availableSeatCnt(nowShowingVO.getShowId());
							if (seatCnt == 0) {
								System.out.println(nowShowingDAO.ifSoldOut(seatCnt));
							} else {
								System.out.println(seatCnt + "/" + nowShowingDAO.SeatCnt(nowShowingVO.getShowId(),
										nowShowingVO.getLocationId()));
							}
						}

						System.out.println();
						System.out.print("예매할 영화 번호 선택 : ");
						int showId = scanner.nextInt();
						System.out.println();

						// 해당 영화 좌석 정보 출력
						seatDAO.printSeat(locName, nowShowingDAO.returnZoneId(showId), showId);
						System.out.println();
						System.out.print("예매할 좌석 번호 선택 : ");
						String seatNum = scanner.next();

						// 좌석 정보 변경
						System.out.println();
						seatDAO.updateSeatStatus(seatDAO.SeatString(
								seatDAO.getSeatData(locName, nowShowingDAO.returnZoneId(showId), showId), seatNum),
								showId);
						System.out.println();

						// 예매 후 좌석 출력

						seatDAO.printSeat(locName, nowShowingDAO.returnZoneId(showId), showId);
						System.out.println();

						String dateTime = null;
						int zone = 0;
						int price = 0;
						String location = null;
						int mvId = 0;
						// returnTixId

						for (NowShowingVO nowShowingVO : selectNowShowingListByLoc) {
							dateTime = nowShowingVO.getStartsAt()
									.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm")) + " - "
									+ nowShowingVO.getEndsAt().format(DateTimeFormatter.ofPattern("HH:mm"));
							zone = nowShowingVO.getZoneId();
							location = locationsDAO.returnLocName(nowShowingVO.getLocationId());
							price = priceDAO.returnPriceAmt(nowShowingVO.getPriceId());
							mvId = nowShowingVO.getMovieId();
						}
						// tixhist에 데이터 입력
						memTixHistDAO.insertMemTixHist(loginID, showId, seatNum);

						// 마일리지 지급 : 10%
						int mileage = (int) (price * 0.1);
						memberDao.updateMileage(loginID, mileage);

						// 티켓 출력 보여주기
						int tixNum = memTixHistDAO.returnTixId(loginID, showId, seatNum);
						tixView.tixPrint(moviesDAO.returnMvName(mvId), dateTime, zone, seatNum, price, location,
								tixNum);
					} else {
						System.out.println("현재 상영 정보 없음");
					}
					break;
				case 2:
					System.out.println("(회원메뉴 - 2. 예매 내역 조회)");
					System.out.print("다음 메뉴 선택 (1. 출력 가능한 티켓 조회  2. 전체 예매 내역 조회  3. 취소 내역 조회) : ");
					selectCode = scanner.nextInt();
					switch (selectCode) {
					case 1:
						System.out.println("(회원메뉴 - 2. 예매 내역 조회 - 1. 출력 가능한 티켓 조회)");
						// FIXME view 다시 보고 수정
						List<vMemTixHistVO> selectAvaMemTixHist = memTixHistDAO.selectAvaMemTixHist(loginID);
						for (vMemTixHistVO memTixHistVO : selectAvaMemTixHist) {
							System.out.print(memTixHistVO.getShowId() + "\t");
							System.out.print(memTixHistVO.getSeatNo() + "\t");
							System.out.print(
									memTixHistVO.getShowDate().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm"))
											+ "\t");
							System.out
									.print(moviesDAO.returnMvName(nowShowingDAO.returnMovieId(memTixHistVO.getShowId()))
											+ "\t");
							System.out.print(
									locationsDAO.returnLocName(nowShowingDAO.returnLocID(memTixHistVO.getShowId()))
											+ "\t");
							System.out.print(nowShowingDAO.returnZoneId(memTixHistVO.getShowId()) + "관\n");
						}
						break;
					case 2:
						System.out.println("(회원메뉴 - 2. 예매 내역 조회 - 2. 전체 예매 내역 조회)");
						List<MemTixHistVO> selectMemTixHist = memTixHistDAO.selectMemTixHist(loginID);
						for (MemTixHistVO memTixHistVO : selectMemTixHist) {
							if (memTixHistVO.getCancelYn().equals("Y")) {
								continue;
							}
							System.out.print(memTixHistVO.getMemTixHistId() + "\t");
							System.out.print(nowShowingDAO.returnRunDate(memTixHistVO.getShowId())
									.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm")) + "\t");
							System.out
									.print(moviesDAO.returnMvName(nowShowingDAO.returnMovieId(memTixHistVO.getShowId()))
											+ "\t");
							System.out.print(
									locationsDAO.returnLocName(nowShowingDAO.returnLocID(memTixHistVO.getShowId()))
											+ "\t");
							System.out.print(nowShowingDAO.returnZoneId(memTixHistVO.getShowId()) + "관\t");
							System.out.println(memTixHistVO.getSeatNo() + "\t");
						}

						break;
					case 3:
						System.out.println("(회원메뉴 - 2. 예매 내역 조회 - 3. 취소 내역 조회)");
						selectMemTixHist = memTixHistDAO.selectMemTixHist(loginID);
						for (MemTixHistVO memTixHistVO : selectMemTixHist) {
							if (memTixHistVO.getCancelYn().equals("N")) {
								continue;
							}
							System.out.print(memTixHistVO.getMemTixHistId() + "\t");
							System.out.print(nowShowingDAO.returnRunDate(memTixHistVO.getShowId())
									.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm")) + "\t");
							System.out
									.print(moviesDAO.returnMvName(nowShowingDAO.returnMovieId(memTixHistVO.getShowId()))
											+ "\t");
							System.out.print(
									locationsDAO.returnLocName(nowShowingDAO.returnLocID(memTixHistVO.getShowId()))
											+ "\t");
							System.out.print(nowShowingDAO.returnZoneId(memTixHistVO.getShowId()) + "관\t");
							System.out.println(memTixHistVO.getSeatNo() + "\t");
						}

						break;

					default:
						break;
					}

					break;
				case 3:
					System.out.println("(회원메뉴 -  3. 예매 취소)");
					System.out.println("취소 가능한 티켓 목록");
					System.out.println();
					// 취소 가능 티켓 목록
					List<vMemTixHistVO> selectAvaMemTixHist = memTixHistDAO.selectAvaMemTixHist(loginID);
					for (vMemTixHistVO memTixHistVO : selectAvaMemTixHist) {
						System.out.print(memTixHistVO.getShowId() + "\t");
						System.out.print(memTixHistVO.getSeatNo() + "\t");
						System.out.print(
								memTixHistVO.getShowDate().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm"))
										+ "\t");
						System.out.print(
								moviesDAO.returnMvName(nowShowingDAO.returnMovieId(memTixHistVO.getShowId())) + "\t");
						System.out.print(
								locationsDAO.returnLocName(nowShowingDAO.returnLocID(memTixHistVO.getShowId())) + "\t");
						System.out.print(nowShowingDAO.returnZoneId(memTixHistVO.getShowId()) + "관\n");
					}
					System.out.println();
					// 취소할 티켓 상영 번호 입력
					System.out.print("취소 할 티켓 상영 번호 입력 : ");
					int showID = scanner.nextInt();
					// 취소할 티켓 좌석 번호 입력
					System.out.print("취소 할 티켓 좌석 번호 입력 : ");
					String seatNo = scanner.next();
					// memTixHist 정보 변경
					cancelDao.cancelMemTix(showID, seatNo);

					// 상영 좌석 원복
					System.out.println();
					seatDAO.updateSeatStatus(
							seatDAO.SeatStringAfterRefund(
									seatDAO.getSeatDataWithLocId(nowShowingDAO.returnZoneId(showID), showID), seatNo),
							showID);

					int price = 0;
					// 마일리지 차감
					List<NowShowingVO> selectAllNowShowingList = nowShowingDAO.selectNowShowingListWithShowId(showID);
					for (NowShowingVO nowShowingVO : selectAllNowShowingList) {
						price = priceDAO.returnPriceAmt(nowShowingVO.getPriceId());
					}
					int mileage = (int) (price * 0.1);
					memberDao.updateMileage(loginID, mileage * (-1));
					break;

				case 4:
					System.out.println("(회원메뉴 - 4. 마일리지 조회)");
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
			// 사용자 입력
			// 패스워드 검증
			boolean nMpassValidation = false;
			String pass = null;

			while (nMpassValidation == false) {
				System.out.print("사용할 비밀번호 입력 : ");
				pass = scanner.next();
				System.out.print("비밀번호 확인 : ");
				String pass2 = scanner.next();
				nMpassValidation = pass.equals(pass2);
				if (nMpassValidation == false) {
					System.out.println("비밀번호 불일치 비밀번호 재 입력");
				} else {
					nMpassValidation = true;
				}
			}
			System.out.print("핸드폰 번호 입력 : ");
			String mobile = scanner.next();

			nonMemberDAO.insertNewNonMem(mobile, pass);

			// 예매 시작
			// 지점별 예매 가능 영화 목록
			System.out.print("예매할 지점명 입력 : ");
			String locName = scanner.next();

			List<NowShowingVO> selectNowShowingListByLoc = nowShowingDAO.selectAvaNowShowingListByLoc(locName);
			if (selectNowShowingListByLoc.size() != 0) {
				for (NowShowingVO nowShowingVO : selectNowShowingListByLoc) {
					// 예매 불가한 상영관 건너뛰기
					if (nowShowingVO.getShowYn().equals("N")) {
						continue;
					}
					System.out.print(nowShowingVO.getShowId() + "\t");
					System.out.print(moviesDAO.returnMvName(nowShowingVO.getMovieId()) + "\t");
					System.out.print(nowShowingVO.getStartsAt().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm"))
							+ " - ");
					System.out.print(nowShowingVO.getEndsAt().format(DateTimeFormatter.ofPattern("HH:mm")) + "\t");
					System.out.print(nowShowingVO.getZoneId() + "관\t");
					int seatCnt = nowShowingDAO.availableSeatCnt(nowShowingVO.getShowId());
					if (seatCnt == 0) {
						System.out.println(nowShowingDAO.ifSoldOut(seatCnt));
					} else {
						System.out.println(seatCnt + "/"
								+ nowShowingDAO.SeatCnt(nowShowingVO.getShowId(), nowShowingVO.getLocationId()));
					}
				}

				System.out.println();
				System.out.print("예매할 영화 번호 선택 : ");
				int showId = scanner.nextInt();
				System.out.println();

				// 해당 영화 좌석 정보 출력
				seatDAO.printSeat(locName, nowShowingDAO.returnZoneId(showId), showId);
				System.out.println();
				System.out.print("예매할 좌석 번호 선택 : ");
				String seatNum = scanner.next();

				// 좌석 정보 변경
				System.out.println();
				seatDAO.updateSeatStatus(seatDAO.SeatString(
						seatDAO.getSeatData(locName, nowShowingDAO.returnZoneId(showId), showId), seatNum), showId);
				System.out.println();

				// 예매 후 좌석 출력

				seatDAO.printSeat(locName, nowShowingDAO.returnZoneId(showId), showId);
				System.out.println();

				String dateTime = null;
				int zone = 0;
				int price = 0;
				String location = null;
				int mvId = 0;
				// returnTixId

				for (NowShowingVO nowShowingVO : selectNowShowingListByLoc) {
					dateTime = nowShowingVO.getStartsAt().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm"))
							+ " - " + nowShowingVO.getEndsAt().format(DateTimeFormatter.ofPattern("HH:mm"));
					zone = nowShowingVO.getZoneId();
					location = locationsDAO.returnLocName(nowShowingVO.getLocationId());
					price = priceDAO.returnPriceAmt(nowShowingVO.getPriceId());
					mvId = nowShowingVO.getMovieId();
				}
				// tixhist에 데이터 입력
				bookingDAO.insertNMTixHist(showId, mobile, seatNum);

				// // 티켓 출력 보여주기
				int tixNum = memTixHistDAO.returnNMTixId(bookingDAO.returnNMemId(mobile));
				tixView.tixPrint(moviesDAO.returnMvName(mvId), dateTime, zone, seatNum, price, location, tixNum);
			} else {
				System.out.println("현재 상영 정보 없음");
			}

			break;
		case 4:
			System.out.println("4. 회원 가입");
			boolean memIdValidation = false;
			while (memIdValidation == false) {
				System.out.print("사용할 아이디 입력 : ");
				String memId = scanner.next();
				memIdValidation = memberDao.memberIdValidation(memId);
				if (memIdValidation == true) { // 아이디 중복 검사 통과시 아래 진행
					boolean passValidation = false;
					String memPass = null;
					// 패스워드 검증
					while (passValidation == false) {
						System.out.print("사용할 비밀번호 입력 : ");
						memPass = scanner.next();
						System.out.print("비밀번호 확인 : ");
						String memPass2 = scanner.next();
						passValidation = memPass.equals(memPass2);
						if (passValidation == false) {
							System.out.println("비밀번호 불일치 비밀번호 재 입력");
						} else {
							passValidation = true;
						}
					}
					System.out.print("이름 입력 : ");
					String memName = scanner.next();
					System.out.print("생일 입력 (YYYYMMDD): ");
					String memBirString = String.valueOf(scanner.nextInt());
					System.out.print("핸드폰 번호 입력 : ");
					String memMobile = memberDao.replaceMobileNum(scanner.next());
					scanner.nextLine();
					System.out.print("주소 1 입력 : ");
					String memAdd1 = scanner.nextLine();
					System.out.print("상세 주소 입력 : ");
					String memAdd2 = scanner.nextLine();
					memberDao.memberSignIn(
							new MemberVO(memId, memPass, memName, memBirString, memAdd1, memAdd2, memMobile));
					return;
				} else {
					System.out.println("이미 사용중인 아이디 입니다. 다시 입력해주세요.");
				}
			}

			break;
		default:
			System.out.println("오류");
			break;
		}

	}

}
