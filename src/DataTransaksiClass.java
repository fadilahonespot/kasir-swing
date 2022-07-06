/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author NB01
 */
public class DataTransaksiClass {
    ArrayList<TransaksiClass> dataTransaksi = new ArrayList<TransaksiClass>();
    
    void tambahTransaksi(TransaksiClass data) {
        this.dataTransaksi.add(data);
    }
    
    ArrayList<TransaksiClass> tampilBarang() {
        return this.dataTransaksi;
    }
    
    void clearTransaksi() {
        this.dataTransaksi.clear();
    }
}
