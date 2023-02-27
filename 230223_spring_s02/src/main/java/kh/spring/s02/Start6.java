package kh.spring.s02;

public class Start6 {
//멀티스레드의 속성을 모르고 웹프로그램 작성시 발생하는 낭패의 예
	static int share;
	
	public static void main(String[] args) {
		Start6 t1 = new Start6();
		Start6 t2 = new Start6();
		
//		t1.start();
//		t2.start();
	}
	
	public void run() {
		for(int count = 0; count < 10; count++) {
			System.out.println(share++);
//			
//			try {
////				{sleep(1000);}
//			} catch (InterruptedException e) {
//			} 
		}
	}

}
