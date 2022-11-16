package trong.fpt.duan1_nhom4_cp17310.models;

public class SoGhe {

    private int soGhe;
    private int trangThai;

    public SoGhe(int soGhe, int trangThai) {
        this.soGhe = soGhe;
        this.trangThai = trangThai;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
