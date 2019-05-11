package parameter;

import javax.ws.rs.core.MultivaluedMap;

public class FindingBestRoadParameters {

    private int mx;
    private int my;
    private int p;
    private int r;

    public FindingBestRoadParameters() {
    }

    public FindingBestRoadParameters(int mx, int my, int p, int r) {
        this.mx = mx;
        this.my = my;
        this.p = p;
        this.r = r;
    }

    public int getMx() {
        return mx;
    }

    public void setMx(int mx) {
        this.mx = mx;
    }

    public int getMy() {
        return my;
    }

    public void setMy(int my) {
        this.my = my;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
