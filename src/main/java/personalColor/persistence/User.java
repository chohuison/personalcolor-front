package personalColor.persistence;

 import personalColor.protocol.MySerializableClass;
import java.io.ByteArrayOutputStream;
 import java.io.DataInputStream;
 import java.io.DataOutputStream;
import java.io.IOException;

public class User implements MySerializableClass
{
    private String ID;
    private String PW;
    private String name;
    private String age;
    private String gender;
    private String seasonType;

    public User(){}
    public User(String ID, String PW, String name, String age, String gender, String seasonType)
    {
        this.ID = ID;
        this.PW = PW;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.seasonType = seasonType;
    }

    public String getID() {
        return ID;
    }

    public String getPW() {
        return PW;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getSeasonType() {
        return seasonType;
    }

    public static User read(DataInputStream bodyReader) throws IOException
    {
        String ID = bodyReader.readUTF();
        String PW = bodyReader.readUTF();
        String name = bodyReader.readUTF();
        String age = bodyReader.readUTF();
        String gender = bodyReader.readUTF();
        String seasonType = bodyReader.readUTF();

        return new User(ID, PW, name, age, gender, seasonType);
    }

    public byte[] getBytes() throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(ID);
        dos.writeUTF(PW);
        dos.writeUTF(name);
        dos.writeUTF(age);
        dos.writeUTF(gender);
        dos.writeUTF(seasonType);

        return buf.toByteArray();
    }
}
