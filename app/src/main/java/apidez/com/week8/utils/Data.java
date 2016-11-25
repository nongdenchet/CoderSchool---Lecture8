package apidez.com.week8.utils;

/**
 * Created by nongdenchet on 11/24/16.
 */

public class Data {
    private int a = 1;
    private Api api;

    public Data(Api api) {
        this.api = api;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
