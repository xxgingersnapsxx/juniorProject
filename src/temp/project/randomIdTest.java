package temp.project;

import java.util.Random;

public class randomIdTest {
	public static void main(String[] args) {
		Random random = new Random();
		int num = random.nextInt(9999 - 1000) + 1000; // 1000부터 9999까지
		int a = num / 1000;
		System.out.print(a);
		int b = (num % 1000) / 100;
		System.out.print(b);
		int c = ((num % 1000) % 100) / 10;
		System.out.print(c);
		int d = (((num % 1000) % 100) % 10);
		System.out.println(d);
		char e = (char) (a + 65);
		System.out.print(e);
		char f = (char) (b + 65);
		System.out.print(f);
		char g = (char) (c + 65);
		System.out.print(g);
		char h = (char) (d + 65);
		System.out.println(h);
	}
}
