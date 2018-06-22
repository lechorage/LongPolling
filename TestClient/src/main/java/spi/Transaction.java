package spi;

import java.util.HashMap;

public class Transaction {
    String SUCCESS = "0";

    private String status;

    private Throwable e;

    private HashMap<String,Object> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }

    private void setData() {
        this.data = new HashMap<>();
    }

    public void addData(String key,Object value){
        data.put(key,value);
    }

    public Transaction(){
        setData();
    }
}
