import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static String[][] stock;
    private static String[][] history;

    private static void press(){
        System.out.print(">> Press Enter to continue... ");
        scanner.nextLine();
    }
    private static void view(){
        System.out.println("=".repeat(10)+"| Stock Management System |"+"=".repeat(10));
        System.out.println("[1]. Set up Stock");
        System.out.println("[2]. View Stock");
        System.out.println("[3]. Insert Product to Stock");
        System.out.println("[4]. Update Product to Stock");
        System.out.println("[5]. Delete Product from Stock");
        System.out.println("[6]. View History");
        System.out.println("[7]. Exit");
        System.out.println("=".repeat(47));
    }
    private static void setUpStock() {
        System.out.print("[+] Insert number of stock items: ");
        int r = scanner.nextInt();
        stock = new String[r][];
        for (int i = 0; i < r; i++) {
            System.out.print("[+] Insert number of catalogue on stock ["+(i+1)+"]: ");
            int c = scanner.nextInt();
            stock[i] = new String[c];
            for (int j = 0; j < c; j++) {
                stock[i][j] = (j+1)+" - EMPTY";
            }
        }
        viewStock();
    }
    private static void viewStock(){
        System.out.println("=".repeat(15)+"| View Stock |"+"=".repeat(15));
        for (int i = 0; i < stock.length; i++) {
            System.out.print("Stock [" + (i+1) + "] => ");
            boolean checkStock = true;
            for (int j = 0; j < stock[i].length; j++) {
                if (stock[i][j].contains("EMPTY")) {
                    System.out.print("[ " + (j+1) + " - EMPTY ] ");
                    checkStock = false;
                }
                else {
                    System.out.print("[ " + stock[i][j] + " ] ");
                }
            }
            if (checkStock) {
                System.out.print(" - FULL STOCK");
            }
            System.out.println();
        }
        System.out.println("=".repeat(44));
    }
    private static void insertProductToStock(){
        int r,c;
        System.out.println("=".repeat(10)+"| Insert Product to Stock |"+"=".repeat(10));
        try {
            do{
                System.out.print("[*] Stock number available: ");
                for (int i = 0; i < stock.length; i++) {
                    System.out.print((i+1)+" | ");
                }
                System.out.print("\b\b\n[+] Insert stock number: ");
                r = scanner.nextInt();
                if (r < 1 || r > stock.length || !Arrays.deepToString(stock[r-1]).contains("EMPTY")) {
                    System.out.println("[!] Invalid Input");
                }
            }while(r < 1 || r > stock.length || !Arrays.deepToString(stock[r-1]).contains("EMPTY") );
            System.out.print("Stock [" + (r) + "] => ");
            for(int j = 0; j < stock[r-1].length; j++) {
                if (stock[r-1][j].contains("EMPTY")) {
                    System.out.print("[ " + (j+1) + " - EMPTY ] ");
                }
                else {
                    System.out.print("[ " + stock[r-1][j] + " ] ");
                }
            }
            do {
                System.out.print("\n[*] Catalogue numbers available: ");
                for (int j = 0; j < stock[r-1].length; j++) {
                    if (stock[r-1][j].contains("EMPTY")) {
                        System.out.print((j+1)+" | ");
                    }
                }
                System.out.print("\b\b\n[+] Insert number of catalogue to put product: ");
                c = scanner.nextInt();
                if (c < 1 || c > stock[r-1].length) {
                    System.out.println("[!] Invalid Input");
                }
            }while(c < 1 || c > stock[r-1].length);
            scanner.nextLine();
            System.out.print("[+] Insert product name: ");
            stock[r-1][c-1] = scanner.nextLine();
            System.out.println("[+] Product has been inserted");
        }catch(Exception e){
            System.out.println("[!] Product fail to inserted");
        }
        press();
        System.out.println("\b\b\n"+"=".repeat(37));
    }
    private static void updateProductToStock(){}
    private static void deleteProductFromStock(){}
    private static void viewHistory(){}


    public static void main(String[] args) {
        view();
        while(true){
            System.out.print("[+] Insert option: ");
            int op = new Scanner(System.in).nextInt();
            switch(op){
                case 1-> setUpStock();
                case 2-> viewStock();
                case 3-> insertProductToStock();
                case 4-> updateProductToStock();
                case 5-> deleteProductFromStock();
                case 6-> viewHistory();
                case 7-> System.exit(0 );
            }
        }
    }

}