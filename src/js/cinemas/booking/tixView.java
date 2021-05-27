package js.cinemas.booking;

public class tixView {
	public void tixPrint(String title, String dateTime, int zone, String seat, int rate, String location, int tixNum) { // 티켓
																														// 출력
																														// 화면
		System.out.println("------------------------- TICKET -------------------------");
		System.out.println("티켓번호 : " + tixNum);
		System.out.println("제목 : " + title);
		System.out.println("관람일시 : " + dateTime);
		System.out.println("상영관 :  " + String.valueOf(zone) + "관");
		System.out.println("좌석 : " + seat);
		System.out.println("가격 : " + rate);
		System.out.println("관람장소 : " + location);
		System.out.println("----------------------------------------------------------");

	}
}
