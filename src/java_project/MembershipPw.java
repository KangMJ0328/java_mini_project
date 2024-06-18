package project;

import java.util.Scanner;

public class MembershipPw {
    String name;
    String address;
    private static MembershipPw instance;
    Membership m = Membership.getInstance();
    public static MembershipPw getInstance() {
        if (instance == null) {
            instance = new MembershipPw();
        }
        return instance;
    }
    public void searchPw() {
        System.out.println("회원님의 스탬프 적립 현황");
        System.out.println("회원님의 정보를 입력해주세요");
        Scanner sc = new Scanner(System.in);
        System.out.print("* 이름 : ");
        name =sc.next();
        System.out.print("고객님의 주소:  ");
        address = sc.next();
        

        if(name.equals(m.name) && address.equals(m.address)){
            System.out.println("고객님의 멤버십 비밀번호는 "+m.pw+"입니다.");
        }else{
            System.out.println("잘못 입력하셨습니다.");
        }
    }

}
