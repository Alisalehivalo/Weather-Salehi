package ir.airport.testweather;

public class DetailWeather {
    public String dayofweek,txtdes,txttemp;
    public String imgShow;

    public DetailWeather(String dayofweek, String imgShow,String txtdes, String txttemp) {

        this.dayofweek = dayofweek;
        this.imgShow = imgShow;
        this.txtdes = txtdes;
        this.txttemp = txttemp;
    }
}
