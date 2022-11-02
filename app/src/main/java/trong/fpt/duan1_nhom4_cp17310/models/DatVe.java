package trong.fpt.duan1_nhom4_cp17310.models;

public class DatVe {
    private String tenPhim;
    private String thoiGianXem;
    private int soLuong;
    private String tenNguoiDat;

    public DatVe(String tenPhim, String thoiGianXem, int soLuong, String tenNguoiDat) {
        this.tenPhim = tenPhim;
        this.thoiGianXem = thoiGianXem;
        this.soLuong = soLuong;
        this.tenNguoiDat = tenNguoiDat;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getThoiGianXem() {
        return thoiGianXem;
    }

    public void setThoiGianXem(String thoiGianXem) {
        this.thoiGianXem = thoiGianXem;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenNguoiDat() {
        return tenNguoiDat;
    }

    public void setTenNguoiDat(String tenNguoiDat) {
        this.tenNguoiDat = tenNguoiDat;
    }
}
