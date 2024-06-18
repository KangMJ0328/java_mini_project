package project;

import java.util.Scanner;

public class MembershipStamp {
    String name;		// 이름
	String personalYY;	// 태어난 년도
	String personalMM;	// 태어난 월
	String personalDD;	// 태어난 일	
	String email;		// 이메일
	String address;		// 주소
	String pw;			// 멤버십 비밀번호
    int stamp;          // 멤버십 스탬프
    Membership m = Membership.getInstance();
    private static MembershipStamp instance;

    public static MembershipStamp getInstance() {
        if (instance == null) {
            instance = new MembershipStamp();
        }
        return instance;
    }

    public void searchmember() {
        System.out.println("회원님의 스탬프 적립 현황");
        System.out.println("회원님의 정보를 입력해주세요");
        Scanner sc = new Scanner(System.in);
        System.out.print("* 이름 : ");
        name =sc.next();
        System.out.print("멤버십 비밀번호: ");
        pw = sc.next();
        

        if(name.equals(m.name) && pw.equals(m.pw)){
            System.out.println("고객님의 스탬프 적립현황은 "+m.stamp+"입니다.");
        }else {
            System.out.println("잘못 입력하셨습니다.");
        }


    }

   
}
