
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static String[][] stock;
    private static String[] history = new String[0];


    private static void press(){
        System.out.print(">> Press Enter to continue... ");
        scanner.nextLine();
    }
    private static boolean check(){
        for (String[] s : stock) {
            for (String item : s) {
                if (!item.contains("EMPTY")) {
                    return true;
                }
            }
        }
        return false;
    }
    private static void resizeHistory(){
        String[] newHistory = new String[history.length+1];
        System.arraycopy(history, 0, newHistory, 0, history.length);
        history = newHistory;
    }
    private static void availableStocks(boolean check) {
        System.out.print("[*] Stock number available: ");
        for (int i = 0; i < stock.length; i++) {
            if(check){
                if (Arrays.deepToString(stock[i]).contains("EMPTY")){
                    System.out.print((i + 1) + " | ");
                }
            }else{
                boolean hasStock = false;
                for (String item : stock[i]) {
                    if (!item.contains("EMPTY")) {
                        hasStock = true;
                        break;
                    }
                }
                if (hasStock) {
                    System.out.print((i + 1) + " | ");
                }
            }
        }
        System.out.print("\b\b\n");
    }
    private static void catalogue(int stockIndex) {
        System.out.print("Stock [" + (stockIndex + 1) + "] => ");
        boolean checkStock = true;
        for (int j = 0; j < stock[stockIndex].length; j++) {
            if (stock[stockIndex][j].contains("EMPTY")) {
                System.out.print("[ " + (j + 1) + " - EMPTY ] ");
                checkStock = false;
            } else {
                System.out.print("[ " + stock[stockIndex][j] + " ] ");
            }
        }
        if (checkStock) {
            System.out.print(" - FULL STOCK");
        }
        System.out.println();
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
            catalogue(i);
        }
        System.out.println("=".repeat(44));
    }
    private static void insertProductToStock(){
        int r,c;
        System.out.println("=".repeat(10)+"| Insert Product to Stock |"+"=".repeat(10));
        try {
            do{
                availableStocks(true);
                System.out.print("[+] Insert stock number: ");
                r = scanner.nextInt();
                if (r < 1 || r > stock.length || !Arrays.deepToString(stock[r-1]).contains("EMPTY")) {
                    System.out.println("[!] Invalid Input");
                }
            }while(r < 1 || r > stock.length || !Arrays.deepToString(stock[r-1]).contains("EMPTY") );
            catalogue(r-1);
            do {
                System.out.print("[*] Catalogue numbers available: ");
                for (int j = 0; j < stock[r-1].length; j++) {
                    if (stock[r-1][j].contains("EMPTY")) {
                        System.out.print((j+1)+" | ");
                    }
                }
                System.out.print("\b\b\n[+] Insert number of catalogue to put product: ");
                c = scanner.nextInt();
                if (c < 1 || c > stock[r-1].length || !stock[r-1][c-1].contains("EMPTY")) {
                    System.out.println("[!] Invalid Input");
                }
            }while(c < 1 || c > stock[r-1].length || !stock[r-1][c-1].contains("EMPTY"));
            scanner.nextLine();
            System.out.print("[+] Insert product name: ");
            String name = scanner.nextLine();
            stock[r-1][c-1] = name;
            Date now = new Date();
            String dateString = now.toString();
            resizeHistory();
            history[history.length-1] = "[*] INSERTED AT: " + dateString + " | Product Name: [" + name + "]";
            System.out.println("[+] Product has been inserted");
        }catch(Exception e){
            System.out.println("[!] Product fail to inserted");
        }finally {
            press();
        }
        System.out.println("\b\b\n"+"=".repeat(37));
    }
    private static void updateProductToStock(){
        boolean checkStock = check();
        if (checkStock) {
            System.out.println("=".repeat(10)+"| Update Product In Stock |"+"=".repeat(10));
            availableStocks(false);
            System.out.print("[+] Insert stock number to update product: ");
            int r = scanner.nextInt();
            catalogue(r-1);
            scanner.nextLine();
            System.out.print("\b\b\n[+] Insert product name to update new one: ");
            String oldName = scanner.nextLine();
            boolean check = false;
            for (int i = 0; i < stock[r-1].length; i++) {
                if (stock[r-1][i].equals(oldName) && !stock[r-1][i].contains("EMPTY")) {
                    System.out.print("[+] Insert new product name to update: ");
                    String newName = scanner.nextLine();
                    stock[r - 1][i] = newName;
                    Date now = new Date();
                    String dateString = now.toString();
                    resizeHistory();
                    history[history.length - 1] = "[*] UPDATED AT: " + dateString + " | Product Name: [" + oldName + " -> " + newName + "]";
                    System.out.println("[+] Product has been updated");
                    check = true;
                    break;
                }
            }
            if(!check){
                System.out.println("[!] Product name not found");
                press();
            }
        }else{
            System.out.println("[!] No products available");
        }
    }
    private static void deleteProductFromStock(){
        boolean checkStock = check();
        if (checkStock) {
            System.out.println("=".repeat(10)+"| Delete Product From Stock |"+"=".repeat(10));
            availableStocks(false);
            System.out.print("[+] Insert stock number to delete product: ");
            int r = scanner.nextInt();
            catalogue(r-1);
            scanner.nextLine();
            System.out.print("\b\b\n[+] Insert product name to delete: ");
            String name = scanner.nextLine();
            boolean check = false;
            for (int i = 0; i < stock[r-1].length; i++) {
                if (stock[r-1][i].equals(name) && !stock[r-1][i].contains("EMPTY")) {
                    stock[r-1][i] = (i+1) + " - EMPTY";
                    Date now = new Date();
                    String dateString = now.toString();
                    resizeHistory();
                    history[history.length-1] = "[*] DELETED AT: " + dateString + " | Product Name: [" +name+ "]";
                    System.out.println("[+] Product has been deleted");
                    check = true;
                    break;
                }
            }
            if(!check){
                System.out.println("[!] Product name not found");
                press();
            }
        }else {
            System.out.println("[!] No products available");
        }
    }
    private static void viewHistory() {
        System.out.println("=".repeat(15) + "| View History |" + "=".repeat(15));
        for (String s : history) {
            if(s == null){
                continue;
            }
            System.out.println(s);
        }
        System.out.println("=".repeat(44));
    }

    public static void main(String[] args) {
        while(true) {
            try {
                view();
                System.out.print("[+] Insert option: ");
                int op = new Scanner(System.in).nextInt();
                if (stock == null) {
                    System.out.println("[!] Setup stock first");
                    press();
                    continue;
                }
                switch (op) {
                    case 1 -> setUpStock();
                    case 2 -> viewStock();
                    case 3 -> insertProductToStock();
                    case 4 -> updateProductToStock();
                    case 5 -> deleteProductFromStock();
                    case 6 -> viewHistory();
                    case 7 -> System.exit(0);
                }
            }catch(Exception e){
                System.out.println("[!] Invalid Input");
                press();
            }
        }
    }
}