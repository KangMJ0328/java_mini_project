package project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 커피매장 회원가입 후  구매 1회시 스탬프 1회 적립 프로그램
        // 1. 회원가입
        // 2. 커피 선택 후 구매 스탬프 사용 여부 물어보기
        // 3. 회원정보 확인란 
        // 3-1. 회원의 스탬프 적립현황
        // 3-2. 회원의 멤버십 비밀번호 찾기
        Scanner sc = new Scanner(System.in);
        String answer;
        boolean run = true;
        
        // 회원가입  
        Membership m = Membership.getInstance();
        m.membershipGuide();
        
        // 커피 구매할것인지 아닌지 여부 
        Purchase p = Purchase.getInstance();
		p.startPurchase();

        System.out.println("회원 정보를 확인하시겠습니까? [1].예 [2].아니오");
        answer = sc.nextLine();
        if(answer.equals("1")|| answer.equals("예")){
            while(run){
                int choice1;
                System.out.println("회원 정보 확인");
                System.out.println("1.스탬프 적립현황 2.멤버십번호 찾기 3. 종료");
                choice1 = sc.nextInt();
                switch (choice1) {
                    case 1:
                        MembershipStamp s = MembershipStamp.getInstance();
                        s.searchmember();
                        break;
                    case 2:
                        MembershipPw w = MembershipPw.getInstance();
                        w.searchPw();
                        break;
                    case 3:
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                        break;
                }

            }
            }else if(answer.equals("2") || answer.equals("아니오")){
                sc.close();
                System.exit(0);
            }
        }
}
