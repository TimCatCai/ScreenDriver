package pre;

public class Layer {
    private int [] xAxis;
    private int [] yAxis;
    public Layer(){
        xAxis = new int[10];
        yAxis = new int[10];
    }

    public int[] getxAxis() {
        return xAxis;
    }

    public int[] getyAxis() {
        return yAxis;
    }

    public void setxAxis(int[] xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(int[] yAxis) {
        this.yAxis = yAxis;
    }
}
