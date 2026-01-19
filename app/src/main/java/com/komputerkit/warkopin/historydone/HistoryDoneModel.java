package com.komputerkit.warkopin.historydone;

import java.util.List;

public class HistoryDoneModel {
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
        public String tglorder;
        public String kodeorder;
        public String status;
        public String totalharga;
        public String bayar;
        public String kembali;

        public User user;

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

        public String getIduser() {
            return iduser;
        }

        public void setIduser(String iduser) {
            this.iduser = iduser;
        }

        public String getTglorder() {
            return tglorder;
        }

        public void setTglorder(String tglorder) {
            this.tglorder = tglorder;
        }

        public String getKodeorder() {
            return kodeorder;
        }

        public void setKodeorder(String kodeorder) {
            this.kodeorder = kodeorder;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTotalharga() {
            return totalharga;
        }

        public void setTotalharga(String totalharga) {
            this.totalharga = totalharga;
        }

        public String getBayar() {
            return bayar;
        }

        public void setBayar(String bayar) {
            this.bayar = bayar;
        }

        public String getKembali() {
            return kembali;
        }

        public void setKembali(String kembali) {
            this.kembali = kembali;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id='" + id + '\'' +
                    ", iduser='" + iduser + '\'' +
                    ", tglorder='" + tglorder + '\'' +
                    ", kodeorder='" + kodeorder + '\'' +
                    ", status='" + status + '\'' +
                    ", totalharga='" + totalharga + '\'' +
                    ", bayar='" + bayar + '\'' +
                    ", kembali='" + kembali + '\'' +
                    ", user=" + user +
                    '}';
        }
    }
}
