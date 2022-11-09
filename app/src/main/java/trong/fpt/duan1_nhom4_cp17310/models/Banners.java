package trong.fpt.duan1_nhom4_cp17310.models;

public class Banners {

    private String linkAnh;
    private String moTa;

    public Banners(String linkAnh, String moTa) {
        this.linkAnh = linkAnh;
        this.moTa = moTa;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
