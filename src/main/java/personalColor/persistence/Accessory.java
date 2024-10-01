package personalColor.persistence;

import personalColor.protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Accessory implements MySerializableClass
{
    private String accExplanation;
    private String accRoot;
    private String seasonType;

    public Accessory(){}
    public Accessory(String accExplanation, String accRoot, String seasonType){
        this.accExplanation=accExplanation;
        this.accRoot=accRoot;
        this.seasonType=seasonType;
    }

    public static Accessory read(DataInputStream bodyReader) throws IOException {
        String accExplanation = bodyReader.readUTF();

        String accRoot = bodyReader.readUTF();

        String seasonType = bodyReader.readUTF();

        return new Accessory(accExplanation, accRoot, seasonType);
    }
    public byte[] getBytes() throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(accExplanation);
        dos.writeUTF(accRoot);
        dos.writeUTF(seasonType);

        return buf.toByteArray();
    }
    public String getAccExplanation(){
        return accExplanation;
    }
    public String getAccRoot(){
        return accRoot;
    }
}
