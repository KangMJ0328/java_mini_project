package project;

import java.util.Scanner;

public class Purchase {
    int count = 0;
    private static Purchase instance;
    private String customerNum = "";
    private int custemoerPrice = 0;

    public static Purchase getInstance() {
        if (instance == null) {
            instance = new Purchase();
        }
        return instance;
    }

    private Purchase() {}

    public void startPurchase() {
        Membership m = Membership.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n[INFO] " + m.name + "님, 안녕하세요.");
        System.out.println("커피를 구매하시겠습니까?");
        System.out.println("[1] 예 \t [2] 아니요");

        String answer;
        while (true) {
            System.out.print("\n입력 : ");
            answer = scanner.nextLine();

            if (answer.equals("1") || answer.equals("예")) {
                purchaseCoffee();
                break;
            } else if (answer.equals("2") || answer.equals("아니요")) {
                System.out.println("\n[INFO] 커피 구매가 종료되었습니다.");
                return;
            } else {
                System.out.println("\n[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private void purchaseCoffee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n[INFO] 원하시는 커피를 선택해주세요.");
        System.out.println("1. 아메리카노, 2. 아이스아메리카노, 3. 카페라떼, 4. 아인슈페너, 5. 콜드블루, 6. 카푸치노");
        int[] coffeePrice = new int[]{2000,2500,3000,5000,4500,3000};
        String[] coffeeOptions = new String[]{"아메리카노", "아이스아메리카노", "카페라떼", "아인슈페너", "콜드블루", "카푸치노"};

        while (true) {
            count++;
            System.out.print("구매하실 커피 입력 : ");
            int input;
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자만 입력해주세요. (1 ~ 6)");
                continue;
            }
            
            if (input >= 1 && input <= coffeeOptions.length) {
                customerNum += coffeeOptions[input - 1] + " ";
                custemoerPrice += coffeePrice[input -1];
                if (!askForMoreCoffee(scanner)) {
                    break;
                }
            } else {
                System.out.println("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        confirmOrder(scanner);
    }

    private boolean askForMoreCoffee(Scanner scanner) {
        while (true) {
            System.out.println("\n추가 구매 진행하시겠습니까?");
            System.out.println("[1] 예 \t [2] 아니요");

            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("예")) {
                return true;
            } else if (input.equals("2") || input.equals("아니요")) {
                System.out.println("\n[INFO] 결제 진행 도와드리겠습니다.");
                return false;
            } else {
                System.out.println("\n[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private void confirmOrder(Scanner scanner) {
        System.out.println("\n[INFO] 구매하시려는 커피는 " + customerNum + "이며 총 결제하실 금액은 : "+custemoerPrice +"입니다.");
        System.out.println("카드 결제 혹은 스탬프 사용하시겠습니까?");
        System.out.println("[1] 카드결제 \t [2] 스탬프 \t [3] 아니요");
        Membership m = Membership.getInstance();
        while (true) {
            System.out.print("\n입력 : ");
            String input = scanner.nextLine();

            if (input.equals("1") || input.equals("카드결제")) {
                completeOrder(scanner);
                break;
            }else if(input.equals("2") || input.equals("스탬프")){
                if(m.stamp < 5){
                    System.out.println("스탬프가 부족합니다");
                }else if(m.stamp >=5){
                    m.stamp =-5;
                    System.out.println("스탬프 사용이 완료되었습니다");
                }
            }else if (input.equals("3") || input.equals("아니요")) {
                System.out.println("\n[INFO] 커피 구매가 종료되었습니다.");
                return;
            } else {
                System.out.println("\n[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private void completeOrder(Scanner scanner) {
        Membership m = Membership.getInstance();

        while (true) {
            System.out.println("\n[INFO] 멤버십 비밀번호를 입력하여 결제를 진행해주세요.");
            System.out.print("입력 : ");
            String input = scanner.nextLine();

            if (input.equals(m.pw)) {
                System.out.println("\n[INFO] 결제가 완료되었습니다.");
                System.out.println("[INFO] 스탬프+"+count);
                m.stamp+=count;
                break;
            } else {
                System.out.println("\n[ERROR] 멤버십 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
            }
        }
    }
}

