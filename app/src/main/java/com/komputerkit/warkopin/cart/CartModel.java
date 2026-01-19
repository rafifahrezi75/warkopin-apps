package com.komputerkit.warkopin.cart;

import java.util.List;

public class CartModel {
    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    private List<Result> result;

    public class Result {
        public String id;
        public String iduser;
        public String idmenu;
        public String jumlah;
        public String cartharga;
        public String hargajual;
        public Menu menu;
        public User user;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIduser() {
            return iduser;
        }

        public void setIduser(String iduser) {
            this.iduser = iduser;
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

        public String getCartharga() {
            return cartharga;
        }

        public void setCartharga(String cartharga) {
            this.cartharga = cartharga;
        }

        public String getHargajual() {
            return hargajual;
        }

        public void setHargajual(String hargajual) {
            this.hargajual = hargajual;
        }

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
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

        public class User {
            public String name;
            public String email;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id='" + id + '\'' +
                    ", iduser='" + iduser + '\'' +
                    ", idmenu='" + idmenu + '\'' +
                    ", jumlah='" + jumlah + '\'' +
                    ", cartharga='" + cartharga + '\'' +
                    ", hargajual='" + hargajual + '\'' +
                    ", menu=" + menu +
                    ", user=" + user +
                    '}';
        }
    }
}
