package trong.fpt.duan1_nhom4_cp17310.models;

public class Tickets {
    private String tenPhim;
    private String suatXem;
    private String soLuong;
    private String tenNguoiDat;
    private String ngayXem;
    private String giaVe;
    private String idTickets;
    private SoGhe soGhe;

    public Tickets(String tenPhim, String suatXem, String soLuong, String tenNguoiDat, String ngayXem, String giaVe) {
        this.tenPhim = tenPhim;
        this.suatXem = suatXem;
        this.soLuong = soLuong;
        this.tenNguoiDat = tenNguoiDat;
        this.ngayXem = ngayXem;
        this.giaVe = giaVe;
    }

    public Tickets(String tenPhim, String suatXem, String soLuong, String tenNguoiDat, String ngayXem, String giaVe, SoGhe soGhe) {
        this.tenPhim = tenPhim;
        this.suatXem = suatXem;
        this.soLuong = soLuong;
        this.tenNguoiDat = tenNguoiDat;
        this.ngayXem = ngayXem;
        this.giaVe = giaVe;
        this.soGhe = soGhe;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getSuatXem() {
        return suatXem;
    }

    public void setSuatXem(String suatXem) {
        this.suatXem = suatXem;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenNguoiDat() {
        return tenNguoiDat;
    }

    public void setTenNguoiDat(String tenNguoiDat) {
        this.tenNguoiDat = tenNguoiDat;
    }

    public String getNgayXem() {
        return ngayXem;
    }

    public void setNgayXem(String ngayXem) {
        this.ngayXem = ngayXem;
    }

    public String getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(String giaVe) {
        this.giaVe = giaVe;
    }

    public String getIdTickets() {
        return idTickets;
    }

    public void setIdTickets(String idTickets) {
        this.idTickets = idTickets;
    }

    public SoGhe getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(SoGhe soGhe) {
        this.soGhe = soGhe;
    }
}