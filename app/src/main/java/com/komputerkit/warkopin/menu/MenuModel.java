package com.komputerkit.warkopin.menu;

import java.util.List;

public class MenuModel {
    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    private List<Result> result;

    public class Result {
        public String id;
        public String image;
        public String nama;
        public String idkategori;
        public String harga;
        public String stok;
        public String deskripsi;

        public MenuModel.Result.Kategori kategori;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getIdkategori() {
            return idkategori;
        }

        public void setIdkategori(String idkategori) {
            this.idkategori = idkategori;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }

        public String getStok() {
            return stok;
        }

        public void setStok(String stok) {
            this.stok = stok;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        public Kategori getKategori() {
            return kategori;
        }

        public void setKategori(Result.Kategori kategori) {
            this.kategori = kategori;
        }

        public class Kategori {
            private String kategori;

            public String getKategori() {
                return kategori;
            }

            public void setKategori(String kategori) {
                this.kategori = kategori;
            }
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id='" + id + '\'' +
                    ", image='" + image + '\'' +
                    ", nama='" + nama + '\'' +
                    ", idkategori='" + idkategori + '\'' +
                    ", harga='" + harga + '\'' +
                    ", stok='" + stok + '\'' +
                    ", deskripsi='" + deskripsi + '\'' +
                    ", kategori=" + kategori +
                    '}';
        }
    }
}
