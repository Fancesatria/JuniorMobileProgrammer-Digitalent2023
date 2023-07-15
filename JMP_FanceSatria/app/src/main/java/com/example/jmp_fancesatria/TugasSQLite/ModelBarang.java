package com.example.jmp_fancesatria.TugasSQLite;

public class ModelBarang {
    public int id, stok;
    public String nama;

    public ModelBarang(int id, int stok, String nama) {
        this.id = id;
        this.stok = stok;
        this.nama = nama;
    }

    public ModelBarang(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
