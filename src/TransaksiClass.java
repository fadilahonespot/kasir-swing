/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author NB01
 */
public class TransaksiClass {
    String kodeBarang;
    String namaBarang;
    Integer harga;
    Integer quantity;
    Integer jumlah;
    
    TransaksiClass(String namaBarang, String kodeBarang, Integer harga, Integer quantity, Integer jumlah) {
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.quantity = quantity;
        this.jumlah = jumlah;
        this.kodeBarang = kodeBarang;
    }
}
