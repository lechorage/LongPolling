package bean;

public class AjaxResult<T> {
    private int code;
    private String desc;
    private T data;

    public AjaxResult(int code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }
    public static AjaxResult OK() {
        return new AjaxResult(bean.ResponseCode.OK.value, null, null);
    }

    public static <T> AjaxResult<T> OK(T data) { return  new AjaxResult<T>(bean.ResponseCode.OK.value,null,null); }

    public static <T> AjaxResult ERROR(T data,String desc) {
        return new AjaxResult(bean.ResponseCode.FAIL.value, desc , data);
    }

    public AjaxResult(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
