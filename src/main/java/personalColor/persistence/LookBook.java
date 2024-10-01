package personalColor.persistence;

import personalColor.protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LookBook implements MySerializableClass
{
    private String lbRoot;
    private String lbExplanation;
    private String seasonType;
    private String userID;
    private int lbShareCheck;

    public String getLbRoot() {
        return lbRoot;
    }

    public void setLbRoot(String lbRoot) {
        this.lbRoot = lbRoot;
    }

    public String getLbExplanation() {
        return lbExplanation;
    }

    public void setLbExplanation(String lbExplanation) {
        this.lbExplanation = lbExplanation;
    }

    public String getSeasonType() {
        return seasonType;
    }

    public void setSeasonType(String seasonType) {
        this.seasonType = seasonType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getLbShareCheck() {
        return lbShareCheck;
    }

    public void setLbShareCheck(int lbShareCheck) {
        this.lbShareCheck = lbShareCheck;
    }

    public LookBook(String lbRoot, String lbExplanation, String seasonType, String userID, int lbShareCheck)
    {
        this.lbRoot = lbRoot;
        this.lbExplanation = lbExplanation;
        this.seasonType = seasonType;
        this.userID = userID;
        this.lbShareCheck = lbShareCheck;
    }

    public byte[] getBytes() throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(lbRoot);
        dos.writeUTF(lbExplanation);
        dos.writeUTF(seasonType);
        dos.writeUTF(userID);
        dos.writeInt(lbShareCheck);

        return buf.toByteArray();
    }

    public static LookBook read(DataInputStream bodyReader) throws IOException {
        String lbRoot = bodyReader.readUTF();
        String lbExplanation = bodyReader.readUTF();
        String seasonType = bodyReader.readUTF();
        String userID = bodyReader.readUTF();
        int lbShareCheck = bodyReader.readInt();

        return new LookBook(lbRoot, lbExplanation, seasonType, userID, lbShareCheck);
    }
}
