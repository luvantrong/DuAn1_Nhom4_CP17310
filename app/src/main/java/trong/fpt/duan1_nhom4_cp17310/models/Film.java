package trong.fpt.duan1_nhom4_cp17310.models;

public class Film {

    private String idFilm;
    private String tenFilm;
    private String ngayChieu;
    private int giaVe;

    public Film(String idFilm, String tenFilm, String ngayChieu, int giaVe) {
        this.idFilm = idFilm;
        this.tenFilm = tenFilm;
        this.ngayChieu = ngayChieu;
        this.giaVe = giaVe;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getTenFilm() {
        return tenFilm;
    }

    public void setTenFilm(String tenFilm) {
        this.tenFilm = tenFilm;
    }

    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public int getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }
}
