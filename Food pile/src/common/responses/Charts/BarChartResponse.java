package common.responses.Charts;

import java.util.ArrayList;

import common.responses.Response;
import common.responses.ResponseCode;

public class BarChartResponse extends Response {
    private String name;
    private int price;
    private int threshold;
    private int quantity;
    private ArrayList<BarChartResponse> Detail;

    public BarChartResponse(ResponseCode code,String message,String name, int price, int threshold, int quantity) {
        super(code, message);
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.threshold=threshold;
    }

    public BarChartResponse(ResponseCode code,String message,ArrayList<BarChartResponse>Detail){
        super(code, message);
        this.Detail=Detail;
    }

    public BarChartResponse(ResponseCode code,String message){
        super(code, message);
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getThreshold() {
            return threshold;
    }
    public ArrayList<BarChartResponse> getDetail() {
        return Detail;
    }
}
