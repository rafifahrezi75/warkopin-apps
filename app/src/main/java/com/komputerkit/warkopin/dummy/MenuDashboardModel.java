package com.komputerkit.warkopin.dummy;

public class MenuDashboardModel {
    public String namaMenuDashboard;
    public String kategoriMenuDashboard;
    public String hargaMenuDashboard;

    public MenuDashboardModel(String namaMenuDashboard, String kategoriMenuDashboard, String hargaMenuDashboard) {
        this.namaMenuDashboard = namaMenuDashboard;
        this.kategoriMenuDashboard = kategoriMenuDashboard;
        this.hargaMenuDashboard = hargaMenuDashboard;
    }

    public String getNamaMenuDashboard() {
        return namaMenuDashboard;
    }

    public String getKategoriMenuDashboard() {
        return kategoriMenuDashboard;
    }

    public String getHargaMenuDashboard() {
        return hargaMenuDashboard;
    }
}
