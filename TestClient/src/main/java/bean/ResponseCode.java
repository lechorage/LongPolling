package bean;

public enum ResponseCode {
    // success
    OK(200),
    // failure
    FAIL(400),
    // unauthorized
    UNAUTHORIZED(401),
    // deny
    DENY(403),
    // error from server
    SERVER_UNKNOWN_ERROR(500);

    public final int value;

    ResponseCode(int value) {
        this.value = value;
    }
}
