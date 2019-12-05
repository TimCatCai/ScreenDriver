package interpolation;

public class CoordinateInterpolation{
    public static double accuracyAxi(int [] capacitanceArray){
        double totalCapacitance = 0;
        double productOfCoordinate = 0;
        for(int i = 0; i < capacitanceArray.length; i++){
            totalCapacitance +=capacitanceArray[i];
            productOfCoordinate += i * capacitanceArray[i];
        }
        return productOfCoordinate / totalCapacitance;
    }
}
