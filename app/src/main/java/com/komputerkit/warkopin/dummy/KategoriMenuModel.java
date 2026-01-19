package com.komputerkit.warkopin.dummy;

public class KategoriMenuModel {
    public String namakategorimenu;
    public String kategorikategorimenu;
    public String hargakategorimenu;

    public KategoriMenuModel(String namakategorimenu, String kategorikategorimenu, String hargakategorimenu) {
        this.namakategorimenu = namakategorimenu;
        this.kategorikategorimenu = kategorikategorimenu;
        this.hargakategorimenu = hargakategorimenu;
    }
    public String getNamakategorimenu() {
        return namakategorimenu;
    }

    public String getKategorikategorimenu() {
        return kategorikategorimenu;
    }

    public String getHargakategorimenu() {
        return hargakategorimenu;
    }
}
