package common.requests.Charts;

import common.requests.Request;

public class BarChart extends Request {
    private String username;

    public BarChart(String username){
        this.username=username;
    }

    public String getUsername() {
        return username;
    }
}
