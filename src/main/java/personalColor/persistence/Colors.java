package personalColor.persistence;

import personalColor.protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Colors implements MySerializableClass
{
    private  int R;
    private int G;
    private int B;
    private String seasonType;

    public Colors (int R, int G, int B, String seasonType) {
        this.R = R;
        this.G = G;
        this.B = B;
        this.seasonType = seasonType;
    }

    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }

    public static Colors read(DataInputStream bodyReader) throws IOException
    {
        int R = bodyReader.readInt();
        int G = bodyReader.readInt();
        int B = bodyReader.readInt();
        String seasonType = bodyReader.readUTF();

        return new Colors(R, G, B, seasonType);
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeInt(R);
        dos.writeInt(G);
        dos.writeInt(B);
        dos.writeUTF(seasonType);

        return buf.toByteArray();
    }
}
