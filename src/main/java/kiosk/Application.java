package kiosk;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        //TODO: 구현

        Application app = new Application();
        app.showHome();
    }

    public void showHome() {
        ArrayList<Menu> burger = new ArrayList<Menu>();
        setBurgerMenu(burger);
        ArrayList<Menu> side = new ArrayList<Menu>();
        setSideMenu(side);
        ArrayList<Menu> beverage = new ArrayList<Menu>();
        setBeverageMenu(beverage);
        ArrayList<Menu> myMenu = new ArrayList<Menu>();

        Scanner scan = new Scanner(System.in);
        int quit = 0;
        while(quit == 0) {
            System.out.println("\n=====홈=====\n");
            System.out.println("1. 햄버거");
            System.out.println("2. 사이드");
            System.out.println("3. 음료");
            System.out.println("4. 장바구니");
            System.out.println("5. 종료");
            System.out.printf("\n메뉴선택: ");

            int menu = scan.nextInt();
            if (menu < 1 || menu > 5) {
                throw new IllegalArgumentException("범위 밖 번호");
            }
            switch (menu) {
                case 1: showBurgerMenu(burger, myMenu, scan); break;
                case 2: showSideMenu(side, myMenu, scan); break;
                case 3: showBeverageMenu(beverage, myMenu, scan); break;
                case 4: quit = adjustMyMenu(myMenu, scan); break;
                case 5: exitMenu(); quit = 1; break;
                default: quit = 1; break;
            }
        }
        scan.close();
    }

    public void showBurgerMenu(ArrayList<Menu> burger, ArrayList<Menu> myMenu, Scanner scan) {
        System.out.println("\n=====햄버거 메뉴=====\n");

        for (int i = 0; i < burger.size(); i++) {
            System.out.println(i+1 + ". " + burger.get(i).getName() + " (" + burger.get(i).getPrice() + "원 )");
        }

        System.out.printf("\n메뉴선택 (0을 선택 시 홈으로): ");
        int menu = scan.nextInt();
        if (menu < 0 || menu > burger.size()) {
            throw new IllegalArgumentException("범위 밖 번호");
        }
        if (menu == 0) {
            System.out.println("홈으로 돌아갑니다.");
            return;
        }
        if (myMenu.size() < 50) {
            myMenu.add(burger.get(menu-1));
            myMenu.get(myMenu.size()-1).setQuantity(1);
            System.out.println(burger.get(menu-1).getName() + "을 장바구니에 담았습니다.");
        }
        else {
            System.out.println("장바구니가 가득 찼습니다.");
        }
    }

    public void showSideMenu(ArrayList<Menu> side, ArrayList<Menu> myMenu, Scanner scan) {
        System.out.println("\n=====사이드 메뉴=====\n");

        for (int i = 0; i < side.size(); i++) {
            System.out.println(i+1 + ". " + side.get(i).getName() + " (" + side.get(i).getPrice() + "원 )");
        }

        System.out.printf("\n메뉴선택 (0을 선택 시 홈으로): ");
        int menu = scan.nextInt();
        if (menu < 0 || menu > side.size()) {
            throw new IllegalArgumentException("범위 밖 번호");
        }
        if (menu == 0) {
            System.out.println("홈으로 돌아갑니다.");
            return;
        }

        if (myMenu.size() < 50) {
            myMenu.add(side.get(menu-1));
            myMenu.get(myMenu.size()-1).setQuantity(1);
            System.out.println(side.get(menu-1).getName() + "을 장바구니에 담았습니다.");
        }
        else {
            System.out.println("장바구니가 가득 찼습니다.");
        }
    }

    public void showBeverageMenu(ArrayList<Menu> beverage, ArrayList<Menu> myMenu, Scanner scan) {
        System.out.println("\n=====음료 메뉴=====\n");

        for (int i = 0; i < beverage.size(); i++) {
            System.out.println(i+1 + ". " + beverage.get(i).getName() + " (" + beverage.get(i).getPrice() + "원 )");
        }

        System.out.printf("\n메뉴선택 (0을 선택 시 홈으로): ");
        int menu = scan.nextInt();
        if (menu < 0 || menu > beverage.size()) {
            throw new IllegalArgumentException("범위 밖 번호");
        }
        if (menu == 0) {
            System.out.println("홈으로 돌아갑니다.");
            return;
        }

        if (myMenu.size() < 50) {
            myMenu.add(beverage.get(menu-1));
            myMenu.get(myMenu.size()-1).setQuantity(1);
            System.out.println(beverage.get(menu-1).getName() + "을 장바구니에 담았습니다.");
        }
        else {
            System.out.println("장바구니가 가득 찼습니다.");
        }
    }

    public int adjustMyMenu(ArrayList<Menu> myMenu, Scanner scan) {
        int quit = 0;
        while (quit == 0) {
            System.out.println("\n===== 장바구니 =====\n");

            int totalPrice = 0;
            for (int i = 0; i < myMenu.size(); i++) {
                System.out.println("- " + myMenu.get(i).getName() + " " + myMenu.get(i).getQuantity() + "개");
                totalPrice += myMenu.get(i).getPrice()*myMenu.get(i).getQuantity();
            }

            System.out.println("\n-=====================");
            System.out.println("1. 주문하기");
            System.out.println("2. 수량 조절하기");
            System.out.println("3. 삭제하기\n");

            System.out.println("총 가격: " + totalPrice + "원");

            System.out.printf("메뉴선택 (0을 선택 시 홈으로): ");
            int menu = scan.nextInt();
            if (menu < 0 || menu > 3) {
                throw new IllegalArgumentException("범위 밖 번호");
            }
            switch (menu) {
                case 1: quit = orderMenu(myMenu); break;
                case 2: quit = adjustQuantity(myMenu, scan); break;
                case 3: quit = deleteMenu(myMenu, scan);
                        if (quit == 1) return 0;
                        break;
                case 0: System.out.println("홈으로 돌아갑니다."); return 0;
                default: quit = 1; break;
            }
        }
        return 1;
    }

    public int orderMenu(ArrayList<Menu> menu) {
        System.out.println("주문완료! 프로그램을 종료합니다.");
        return 1;
    }

    public int adjustQuantity(ArrayList<Menu> menu, Scanner scan) {
        System.out.println("\n===== 수량 조절하기 =====\n");

        System.out.println("현재 장바구니\n");

        for (int i = 0; i < menu.size(); i++) {
            System.out.println(i+1 + ". " + menu.get(i).getName() + " " + menu.get(i).getQuantity() + "개");
        }

        System.out.printf("\n수량을 조절할 메뉴 번호를 선택하세요 (0을 선택 시 홈으로): ");
        int menuId = scan.nextInt();
        if (menuId < 0 || menuId > menu.size()) {
            throw new IllegalArgumentException("범위 밖 번호");
        }
        if (menuId == 0) {
            System.out.println("홈으로 돌아갑니다.");
            return 1;
        }
        System.out.printf("변경할 수량을 입력하세요: ");
        int quantity = scan.nextInt();
        if (quantity < 1 || quantity > 50) {
            throw new IllegalArgumentException("범위 밖 번호");
        }
        menu.get(menuId-1).adjustQuantity(quantity);
        System.out.println("수량이 변경되었습니다.");
        return 0;
    }

    public int deleteMenu(ArrayList<Menu> menu, Scanner scan) {
        System.out.println("\n===== 삭제하기 =====\n");

        System.out.println("현재 장바구니\n");

        for (int i = 0; i < menu.size(); i++) {
            System.out.println(i+1 + ". " + menu.get(i).getName() + " " + menu.get(i).getQuantity());
        }

        System.out.printf("\n삭제할 메뉴 번호를 선택하세요 (0을 선택 시 홈으로): ");
        int menuId = scan.nextInt();
        if (menuId < 0 || menuId > menu.size()) {
            throw new IllegalArgumentException("범위 밖 번호");
        }
        if (menuId == 0) {
            System.out.println("홈으로 돌아갑니다.");
            return 1;
        }
        System.out.printf("정말 삭제 하시겠습니까? (0: 취소 및 홈으로 1: 삭제): ");
        int answer = scan.nextInt();
        if (answer < 0 || answer > 1) {
            throw new IllegalArgumentException("범위 밖 번호");
        }
        if (answer == 1) {
            menu.remove(menuId-1);
            System.out.println("삭제가 완료되었습니다.");
        }
        else if (answer == 0) {
            System.out.println("홈으로 돌아갑니다.");
            return 1;
        }
        return 0;
    }

    public void exitMenu() {
        System.out.println("프로그램을 종료합니다.");
    }

    public void setBurgerMenu(ArrayList<Menu> burger) {
        Menu menu = new Menu("와퍼", 6900);
        burger.add(menu);
        menu = new Menu("큐브 스테이크 와퍼", 8900);
        burger.add(menu);
        menu = new Menu("콰트로 치즈 와퍼", 7900);
        burger.add(menu);
        menu = new Menu("몬스터 와퍼", 9300);
        burger.add(menu);
        menu = new Menu("통새우 와퍼", 7900);
        burger.add(menu);
        menu = new Menu("블랙바베큐 와퍼", 9300);
        burger.add(menu);
    }

    public void setSideMenu(ArrayList<Menu> side) {
        Menu menu = new Menu("너겟킹", 2500);
        side.add(menu);
        menu = new Menu("해쉬 브라운", 1800);
        side.add(menu);
        menu = new Menu("치즈스틱", 1200);
        side.add(menu);
        menu = new Menu("어니언링", 2400);
        side.add(menu);
        menu = new Menu("바삭킹", 3000);
        side.add(menu);
        menu = new Menu("감자튀김", 2000);
        side.add(menu);
    }

    public void setBeverageMenu(ArrayList<Menu> beverage) {
        Menu menu = new Menu("코카콜라", 2000);
        beverage.add(menu);
        menu = new Menu("코카콜라 제로", 2000);
        beverage.add(menu);
        menu = new Menu("펩시", 2000);
        beverage.add(menu);
        menu = new Menu("펩시 제로", 2000);
        beverage.add(menu);
        menu = new Menu("스프라이트", 2000);
        beverage.add(menu);
        menu = new Menu("스프라이트 제로", 2000);
        beverage.add(menu);
    }
}