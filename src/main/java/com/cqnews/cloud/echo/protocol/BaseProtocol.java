package com.cqnews.cloud.echo.protocol;

/**
 * 基础协议
 * @author uYxUuu
 */
public class BaseProtocol {

    /** 魔数 */
    public static final byte[] MAGIC_NUMBER = {0x69, 0x6D, 0x79, 0x78};

    /** 魔数长度 */
    public static final int MAGIC_NUMBER_LENGTH = 4;

    /** 协议最小长度 = 魔数长度(4字节) + 类型(1字节) + 消息长度(4字节) */
    public static final int MIN_LENGTH = MAGIC_NUMBER_LENGTH + 1 + 4;

    /** 类型 */
    private byte type;

    /** 消息长度 */
    private int contentLength;

    /** 消息内容 */
    private String content;

    public BaseProtocol(byte type, int contentLength, String content) {
        this.type = type;
        this.contentLength = contentLength;
        this.content = content;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
