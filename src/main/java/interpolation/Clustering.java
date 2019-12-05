package interpolation;

import pre.IPreProcessor;
import pre.Layer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Clustering implements IInterpolation{
    private IPreProcessor preProcessor;
    public Clustering(IPreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public List<Point> getPoints() {
        Layer layer = preProcessor.newLayer();

        List<Double> xAxiAccuracyPoint = clickPoints(layer.getxAxis());
        List<Double> yAxiAccuracyPoint = clickPoints(layer.getyAxis());

        return null;
    }

    /**
     * 只要是极小值即是分割点，获取所有分割点
     * @param capacitanceArray 电容数组
     * @return 所有极小值所在坐标
     */
    private List<Integer> breakPoints(int [] capacitanceArray){
        boolean breakPointLeft = false;
        boolean breakPointRight = false;
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 1; i < capacitanceArray.length; i++){
            // 如何解决相等问题
            if(capacitanceArray[i] - capacitanceArray[i - 1] < 0){
                breakPointLeft = true;
            }
            if(breakPointLeft && capacitanceArray[i] - capacitanceArray[i - 1] > 0){
                breakPointRight = true;
            }
            if(breakPointLeft && breakPointRight){
                result.add(i);
                breakPointLeft = false;
                breakPointRight = false;
            }
        }
        return result;
    }

    private List<Double> clickPoints( int [] capacitanceArray){
        List<Integer> breakPoints = breakPoints(capacitanceArray);
        // 第一个及最后一个都当成分割点，方便聚簇操作
        breakPoints.add(0, 0);
        breakPoints.add(breakPoints.size(), capacitanceArray.length - 1);
        List<Double> result = new ArrayList<Double>();
        double accuracyPoint;
        int start;
        int end;
        for(int i = 1; i < breakPoints.size(); i++){
            start = breakPoints.get(i - 1);
            end = breakPoints.get(i);
            accuracyPoint = CoordinateInterpolation.accuracyAxi(Arrays.copyOfRange(capacitanceArray, start, end));
            result.add(accuracyPoint);
        }
        return result;
    }
}
