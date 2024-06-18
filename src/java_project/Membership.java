package project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


public class Membership {
    private static Membership m;
    private static List<Integer> MembershipNumbers = new ArrayList<>(); // 동적배열로 사용자의 가입수만큼 멤버십 비밀번호를 리스트에 저장 후 중복되는 멤버십비밀번호가 없게 만듬
    public static List<Integer> getMembershipNumbers() {
        return MembershipNumbers;
    }
    public static void setMembershipNumbers(List<Integer> membershipNumbers) {
        MembershipNumbers = membershipNumbers;
    }
    public static Membership getInstance() {
        if(m == null){
            m = new Membership();
        }
        return m;
    }
    public static void freeInstance() {
		m = null;
	}
    private Membership() {}

    String name;		// 이름
	String personalYY;	// 태어난 년도
	String personalMM;	// 태어난 월
	String personalDD;	// 태어난 일	
	String email;		// 이메일
	String address;		// 주소
	String pw;			// 카드 비밀번호
    int stamp;          // 멤버십 스탬프
    private String  membershipNumber;
    public int getStamp() {
        return stamp;
    }
    public void setStamp(int stamp) {
        this.stamp = stamp;
    }
    public void membershipGuide() {
       Scanner sc = new Scanner(System.in);
       System.out.println(
            "  \n\n  #####    ####      #####    #####  ######   ######     \r\n"
				+ " ##       ##  ##     ##       ##     ##       ##         \r\n"
				+ " ##       ##  ##     #####    #####  ######   ######    \r\n"
				+ " ##       ##  ##     ##       ##     ##       ##        \r\n"
				+ "  #####    ####      ##       ##     ######   ######   ");
		
		System.out.println("\n[INFO] 안녕하세요. 동행커피입니다.\n"
				+ "커피 구입을 위해선 회원가입이 필요합니다.\n"
				+ "회원가입을 진행하시겠습니까?\n"
				+ "[1] 예\t[2] 아니요");
		
		String answer = ""; 

        for(;;){
            System.out.print("\n입력 :");
            answer = sc.next();
            if(answer.equals("1") || answer.equals("예")) {
				personalData();
				break;				
			}else if(answer.equals("2") || answer.equals("아니요")){
				System.out.println("\n[ERROR] 회원이 아니면 커피를 구매할 수 없습니다.");
				System.exit(0);
			}else {
				System.out.println("\n[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
			}

        }

    }
    private void personalData() {
        Scanner sc = new Scanner(System.in);
		System.out.println("\n=================================================================\n");
		System.out.println("[INFO] 회원가입을 위해 아래의 양식을 채워주세요.\n");
		
		System.out.print("* 이름 : ");
		this.name = sc.next();	

        //미성년자는 가입할 수 없다.
        // 생년월일은 조건에 맞지 않으면 다시 입력을 해야한다.

        int personalYYnum = 0;
		int personalMMnum = 0;
		int personalDDnum = 0;

        //입력하는 연도가 올해를 넘기지 않게 만든다.
        Calendar cal = Calendar.getInstance();
		int yy = cal.get(Calendar.YEAR); //현재년도

        for(;;) {
			
			System.out.print("* 태어난 연도 : ");		
			this.personalYY = sc.next();
			
			personalYYnum = Integer.parseInt(personalYY);
			
                if( personalYYnum < 1900 || personalYYnum > yy) {
					System.out.println("[ERROR] 잘못된 입력입니다."
							+ "\n태어난 연도를 다시 입력해주세요.\n");
				}else {
					break;
				}
			
			
		}
        for(;;) {
			System.out.print("* 태어난 월 : ");
			this.personalMM = sc.next();
			personalMMnum = Integer.parseInt(personalMM);
			if( personalMMnum == 0 || personalMMnum > 12) {
				System.out.println("[ERROR] 잘못된 입력입니다."
						+ "\n태어난 월을 다시 입력해주세요.\n");
			}else {
				break;
			}
		}
        //입력한 월에 따른 일의 변화
        DatePrinter.printDateTime(cal);
        cal.set(Calendar.DAY_OF_MONTH,personalMMnum );
        int day_count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //personalMMnum 에 해당하는 달의 마지막 날짜를 구한다.
        for(;;) {
			
			System.out.print("* 태어난 일 : ");
			this.personalDD = sc.next();
			
			personalDDnum = Integer.parseInt(personalDD);
				if(personalDDnum == 0 || personalDDnum > day_count + 1) {
					System.out.println("[ERROR] 잘못된 입력입니다."
							+ "\n태어난 일을 다시 입력해주세요.\n");
				}else {
					break;
				}	
		}
        System.out.print("* 주소 : ");
		this.address = sc.next();
		
		
		serachMembershipNumber();
		
		System.out.println("\n[INFO] 회원가입이 완료되었습니다.");
		
		System.out.println("\n=================================================================");
		


    }
    private void serachMembershipNumber() {
        int randomNumber;
		boolean run = true;
        while (run) {
			randomNumber = (int) (Math.random() * 9000) + 1000;
			run = MembershipNumbers.contains(randomNumber); 
			MembershipNumbers.add(randomNumber);
			this.m.pw  = String.valueOf(randomNumber);
			System.out.println("* 멤버십 번호 : " + this.m.pw );
		}

       
    }

}
