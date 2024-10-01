package personalColor.protocol;

import java.io.*;

public class Header implements Serializable
{
    public final static byte TYPE_REQUEST = 1;
    public final static byte TYPE_RESPONSE = 2;

    // 회원가입 요청 = 11
    public final static byte CODE_REQ_SIGN_UP = 11;

    // 회원가입 응답 = 12
    public final static byte CODE_RES_SIGN_UP = 12;

    // 로그인 요청 = 21
    public final static byte  CODE_REQ_LOGIN= 21;

    // 로그인 응답 = 22
    public final static byte  CODE_RES_LOGIN = 22;

    // 유저정보 요청 = 31
    public final static byte CODE_REQ_USER_INFORMATION = 31;

    // 유저정보 응답 = 32
    public final static byte CODE_RES_USER_INFORMATION = 32;

    // 색체 데이터 요청 = 41
    public final static byte CODE_REQ_COLOR = 41;

    // 색체 데이터 응답 = 42
    public final static byte CODE_RES_COLOR = 42;

    // 화장품 데이터 요청 = 51
    public final static byte CODE_REQ_COSMETIC = 51;

    // 화장품 데이터 응답 = 52
    public final static byte CODE_RES_COSMETIC = 52;

    // 스타일링 데이터 요청 = 61
    public final static byte CODE_REQ_TIP = 61;
    // 스타일링 데이터 응답 = 62
    public final static byte CODE_RES_TIP= 62;
    // 악세서리 데이터 요청 = 71
    public final static byte CODE_REQ_ACCESSORY = 71;
    // 악세서리 데이터 응답 = 72
    public final static byte CODE_RES_ACCESSORY = 72;

    public final static byte  CODE_REQ_LB_INFO = 81;
    public final static byte  CODE_RES_LB_INFO = 82;

    public final static byte  CODE_REQ_LB_ENROLL = 91;
    public final static byte  CODE_RES_LB_ENROLL = 92;

    public final static byte  CODE_REQ_MYPAGE_INFO = 101;
    public final static byte  CODE_RES_MYPAGE_INFO = 102;

    public final static byte  CODE_REQ_MODIFY_INFO = 111;
    public final static byte  CODE_RES_MODIFY_INFO = 112;

    public final static byte  CODE_REQ_DELETE_USER = 121;
    public final static byte  CODE_RES_DELETE_USER = 122;


    public byte type;
    public byte code;
    public int bodySize;

    public Header(byte type, byte code, int bodySize)
    {
        this.type = type;
        this.code = code;
        this.bodySize = bodySize;
    }

    public static Header readHeader(DataInputStream dis) throws IOException
    {
        byte type = dis.readByte();
        byte code = dis.readByte();
        int bodySize = dis.readByte();

        return new Header(type,code, bodySize);
    }

    public byte[] getBytes() throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeByte(type);
        dos.writeByte(code);
        dos.writeByte(bodySize);

        return buf.toByteArray();
    }
}
