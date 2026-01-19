package com.komputerkit.warkopin.historydetail;

import java.util.List;

public class HistoryDetailModel {
    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    private List<Result> result;
    public class Result {
        public String idorder;
        public String idmenu;
        public String jumlah;
        public String hargapenjualan;
        public Menu menu;

        public String getIdorder() {
            return idorder;
        }

        public void setIdorder(String idorder) {
            this.idorder = idorder;
        }

        public String getIdmenu() {
            return idmenu;
        }

        public void setIdmenu(String idmenu) {
            this.idmenu = idmenu;
        }

        public String getJumlah() {
            return jumlah;
        }

        public void setJumlah(String jumlah) {
            this.jumlah = jumlah;
        }

        public String getHargapenjualan() {
            return hargapenjualan;
        }

        public void setHargapenjualan(String hargapenjualan) {
            this.hargapenjualan = hargapenjualan;
        }

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public class Menu {
            public String image;
            public String nama;
            public Kategori kategori;

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

            public Kategori getKategori() {
                return kategori;
            }

            public void setKategori(Kategori kategori) {
                this.kategori = kategori;
            }

            public class Kategori {
                public String kategori;

                public String getKategori() {
                    return kategori;
                }

                public void setKategori(String kategori) {
                    this.kategori = kategori;
                }
            }
        }

        @Override
        public String toString() {
            return "Result{" +
                    "idorder='" + idorder + '\'' +
                    ", idmenu='" + idmenu + '\'' +
                    ", jumlah='" + jumlah + '\'' +
                    ", hargapenjualan='" + hargapenjualan + '\'' +
                    ", menu=" + menu +
                    '}';
        }
    }
}
