package personalColor.persistence;

import personalColor.protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Tip implements MySerializableClass
{
    private String detail;
    private String seasonType;
    private String tipType;


    public Tip(){}
    public Tip(String detail, String seasonType,String tipType){
        this.detail=detail;
        this.seasonType=seasonType;
        this.tipType=tipType;
    }

    public static Tip read(DataInputStream bodyReader) throws IOException {
        String detail = bodyReader.readUTF();

        String seasonType = bodyReader.readUTF();

        String tipType = bodyReader.readUTF();

        return new Tip(detail, seasonType,tipType);
    }

    public byte[] getBytes() throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(detail);
        dos.writeUTF(seasonType);
        dos.writeUTF(tipType);

        return buf.toByteArray();
    }
    public String getDetail(){
        return detail;
    }
}
