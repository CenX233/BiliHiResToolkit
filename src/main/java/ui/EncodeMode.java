package ui;

public enum EncodeMode {
    COPY("复制"),
    AUTO("自动"),
    CUSTOM("自定义");

    private final String string;
    EncodeMode(String s) {
        this.string = s;
    }
    @Override
    public String toString() {
        return this.string;
    }
}
