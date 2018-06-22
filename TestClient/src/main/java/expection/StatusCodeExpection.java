package expection;

public class StatusCodeExpection extends RuntimeException{
    private final int m_statusCode;

    public StatusCodeExpection(int statusCode,String message){
        super(String.format("[status code: %d] %s", statusCode, message));
        this.m_statusCode = statusCode;
    }

    public int getStatusCode() {
        return m_statusCode;
    }
}
