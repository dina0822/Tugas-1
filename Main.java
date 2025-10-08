// Class untuk menguji sistem perbankan
public class Main {
    public static void main(String[] args) {
        
        // Membuat dua akun
        System.out.println("=== Membuat Akun ===");
        BankAccount account1 = new BankAccount("123", "Fadwa", 1300000);
        BankAccount account2 = new BankAccount("456", "Dina", 1800000);
        System.out.println("Atas nama " + account1.getOwnerName() + "(akun " + account1.getAccountNumber() + ") dengan saldo " + account1.formatRupiah((int) account1.getBalance()));
        System.out.println("Atas nama " + account2.getOwnerName() + "(akun " + account2.getAccountNumber() + ") dengan saldo " + account2.formatRupiah((int) account2.getBalance()));
        System.out.println();

        // Uji deposit
        System.out.println("=== Uji Deposit ===");
        account1.deposit(700000);     // Valid
        account1.deposit(-100000);    // Tidak valid
        System.out.println();

        // Uji penarikan
        System.out.println("=== Uji Penarikan ===");
        account2.withdraw(300000);    // Valid
        account2.withdraw(2000000);   // Tidak valid: saldo kurang
        account2.withdraw(-50000);    // Tidak valid: jumlah negatif
        System.out.println();

        // Uji transfer
        System.out.println("=== Uji Transfer ===");
        account1.transfer(account2, 500000);      // Valid
        account1.transfer(account2, 3000000);     // Tidak valid: saldo kurang
        account1.transfer(account2, -100000);     // Tidak valid: jumlah negatif
        System.out.println();

        // Uji ubah nama
        System.out.println("=== Uji Ubah Nama ===");
        account1.setOwnerName("Fadwa Febriyanti Sucipto");  // Valid
        account1.setOwnerName("");      // Tidak valid: nama kosong
        System.out.println();
        

        // Uji riwayat transaksi
        System.out.println("=== Uji Riwayat Transaksi ===");
        account1.showRiwayat();
        account2.showRiwayat();
    }
}