package personalColor.persistence;

import personalColor.protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Cosmetic implements MySerializableClass
{
    private String brandName;
    private String productType;
    private String productName;
    private String cosRoot;
    private String seasonType;

    public Cosmetic(String brandName, String productType,String productName, String cosRoot, String seasonType){
        this.brandName = brandName;
        this.productType = productType;
        this.productName = productName;
        this.cosRoot = cosRoot;
        this.seasonType = seasonType;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductName() {
        return productName;
    }

    public String getCosRoot() {
        return cosRoot;
    }

    public static Cosmetic read(DataInputStream bodyReader) throws IOException
    {
        String brandName = bodyReader.readUTF();
        String productType = bodyReader.readUTF();
        String productName = bodyReader.readUTF();
        String cosRoot = bodyReader.readUTF();
        String seasonType = bodyReader.readUTF();

        return new Cosmetic(brandName, productType, productName, cosRoot, seasonType);
    }

    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(brandName);
        dos.writeUTF(productType);
        dos.writeUTF(productName);
        dos.writeUTF(cosRoot);
        dos.writeUTF(seasonType);

        return buf.toByteArray();
    }
}
