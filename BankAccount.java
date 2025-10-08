// ENKAPSULASI DALAM SISTEM PERBANKAN 
import java.text.DecimalFormat;
public class BankAccount {
    private final String accountNumber;   // Tidak bisa diubah setelah dibuat
    private String ownerName;             // Bisa diubah, tapi tidak boleh kosong
    private int balance;               // Hanya bisa diubah lewat metode resmi
    private java.util.ArrayList<String> riwayat; // fitur Riwayat transaksi berbentuk ArrayList
    
    // Method buat format angka jadi rupiah (misal: Rp. 1.300.000)
    final String formatRupiah(int amount) {
        DecimalFormat df = new DecimalFormat("#,###");
        return "Rp. " + df.format(amount).replace(",", ".");
    }

    // Untuk membuat akun baru:
        // accountNumber = Nomor akun
        // ownerName = Nama pemilik akun
        // initialBalance = Saldo awal
    public BankAccount(String accountNumber, String ownerName, int initialBalance) {
        this.accountNumber = accountNumber;
        if (ownerName == null || ownerName.isEmpty()) {
            System.out.println("Nama pemilik tidak boleh kosong");
            return;
        }
        this.ownerName = ownerName;
        if (initialBalance < 0) {
            System.out.println("Saldo awal tidak boleh negatif");
            return;
        }
        this.balance = initialBalance;
        this.riwayat = new java.util.ArrayList<String>();
        riwayat.add("Akun dibuat dengan saldo " + formatRupiah(initialBalance));
    }

    // Getter untuk accountNumber
    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter dan Setter untuk ownerName
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String newName) {
        if (newName == null || newName.isEmpty()) {
            System.out.println("Gagal ubah nama: Nama baru tidak boleh kosong");
            return;
        }
        this.ownerName = newName;
        riwayat.add("Nama diubah menjadi: " + newName);
        System.out.println("Nama pemilik akun " + accountNumber + " diubah menjadi: " + newName);
    }

    // Getter untuk balance (saldo)
    public int getBalance() {
        return balance;
    }

    // Untuk setoran uang (depodit)
    public void deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Gagal deposit: Jumlah deposit harus lebih dari 0.");
            return;
        }
        balance = balance + amount;
        riwayat.add("Deposit masuk " + formatRupiah(amount));
        System.out.println("Berhasil deposit " + formatRupiah(amount) + " ke " + ownerName + "(akun " + accountNumber + "). Saldo sekarang: " + formatRupiah((int) balance));
    }

    // Untuk menarik uang
    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Gagal tarik: Jumlah penarikan harus lebih dari 0.");
            return;
        }
        if (amount > balance) {
            System.out.println("Gagal tarik: Saldo tidak cukup.");
            return;
        }
        balance = balance - amount;
        riwayat.add("Penarikan " + formatRupiah(amount));
        System.out.println("Berhasil tarik " + formatRupiah(amount) + " dari " + ownerName + "(akun " + accountNumber + "). Saldo sekarang: " + formatRupiah((int) balance));
    }

    // Untuk transfer uang antar akun
    public void transfer(BankAccount akunTujuan, int amount) {
        if (akunTujuan == null) {
            System.out.println("Gagal transfer: Akun tujuan tidak valid.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Gagal transfer: Jumlah transfer harus lebih dari 0.");
            return;
        }
        if (amount > balance) {
            System.out.println("Gagal transfer: Saldo tidak cukup.");
            return;
        }
        balance = balance - amount;
        akunTujuan.balance = akunTujuan.balance + amount;
        riwayat.add("Berhasil Transfer " + formatRupiah(amount) + " ke akun " + akunTujuan.accountNumber);
        akunTujuan.riwayat.add("Berhasil terima " + formatRupiah(amount) + " dari akun " + this.accountNumber);
        System.out.println("Berhasil transfer " + formatRupiah(amount) + " dari " + ownerName + "(akun " + accountNumber + ")" + " ke " + akunTujuan.ownerName + "(akun "  + akunTujuan.accountNumber + ").");
        System.out.println("Saldo " + ownerName + " sekarang: " + formatRupiah((int) balance));
        System.out.println("Saldo " + akunTujuan.ownerName + " sekarang: " + formatRupiah((int) akunTujuan.balance));
    }

    // Menampilkan riwayat transaksi
    public void showRiwayat() {
        System.out.println("Riwayat Transaksi Akun " + accountNumber + " (" + ownerName + ")");
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (int i = 0; i < riwayat.size(); i++) {
                System.out.println((i + 1) + ". " + riwayat.get(i));
            }
        }
        System.out.println("Saldo sekarang: " + formatRupiah((int) balance));
        System.out.println();
    }
}