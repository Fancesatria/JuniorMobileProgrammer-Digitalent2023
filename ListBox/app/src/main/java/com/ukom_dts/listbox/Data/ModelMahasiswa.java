package com.ukom_dts.listbox.Data;

public class ModelMahasiswa {
    public int id;
    public String nama, kelamin, asal, alamat, pend_terakhir;

    public ModelMahasiswa(int id, String nama, String kelamin, String asal, String alamat, String pend_terakhir) {
        this.id = id;
        this.nama = nama;
        this.kelamin = kelamin;
        this.asal = asal;
        this.alamat = alamat;
        this.pend_terakhir = pend_terakhir;

    }

    public ModelMahasiswa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPend_terakhir() {
        return pend_terakhir;
    }

    public void setPend_terakhir(String pend_terakhir) {
        this.pend_terakhir = pend_terakhir;
    }
}
