package trong.fpt.duan1_nhom4_cp17310.models;

public class Banners {

    private String idBanners;
    private String linkAnh;
    private String moTa;

    public Banners(String idBanners, String linkAnh, String moTa) {
        this.idBanners = idBanners;
        this.linkAnh = linkAnh;
        this.moTa = moTa;
    }

    public String getIdBanners() {
        return idBanners;
    }

    public void setIdBanners(String idBanners) {
        this.idBanners = idBanners;
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
