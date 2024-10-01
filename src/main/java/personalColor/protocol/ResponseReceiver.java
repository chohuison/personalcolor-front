package personalColor.protocol;

import personalColor.persistence.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResponseReceiver {
    public boolean receiveLoginResult(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);

        byte[] body = new byte[header.bodySize];


        inputStream.read(body);


        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));


        boolean result = bodyReader.readBoolean();


        return result;
    }

    public int receiveSignUpResult(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.bodySize];

        inputStream.read(body);

        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
        int result = bodyReader.readInt();
        System.out.println(result);

        return result;
    }

    public String receiveUserInfo(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.bodySize];

        inputStream.read(body);

        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
        String result = bodyReader.readUTF();
        System.out.println(result);

        return result;
    }

    public List<Tip> receiveTip(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        List<Tip> tips = new ArrayList<>();
        int size = inputStream.readInt();
        for (int i = 0; i < size; i++) {
            tips.add(Tip.read(inputStream));
        }
        return tips;
    }

    public List<Accessory> receiveAccessory(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        List<Accessory> accessories = new ArrayList<>();
        int size = inputStream.readInt();
        for (int i = 0; i < size; i++) {
            accessories.add(Accessory.read(inputStream));
        }

        return accessories;
    }

    public List<Colors> receiveColor(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        int size = inputStream.readInt();

        List<Colors> result = new ArrayList<>();
        for(int i = 0; i < size; i++)
            result.add(Colors.read(inputStream));

        return result;
    }

    public List<Cosmetic> receiveCosmetic(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        int size = inputStream.readInt();

        List<Cosmetic> result = new ArrayList<>();
        for(int i = 0; i < size; i++)
            result.add(Cosmetic.read(inputStream));

        return result;
    }

    public List<User> receiveMyPageInfo(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        int size = inputStream.readInt();

        List<User> result = new ArrayList<>();
        for(int i = 0; i < size; i++)
            result.add(User.read(inputStream));

        return result;
    }

    public int receiveModifyInfoResult(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.bodySize];

        inputStream.read(body);

        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
        int result = bodyReader.readInt();
        System.out.println(result);

        return result;
    }

    public int receiveDeleteUserResult(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.bodySize];

        inputStream.read(body);

        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
        int result = bodyReader.readInt();
        System.out.println(result);

        return result;
    }

    public List<LookBook> receiveLookBook(DataInputStream inputStream) throws IOException
    {
        Header header = Header.readHeader(inputStream);
        List<LookBook> lookBooks = new ArrayList<>();

        int size = inputStream.readInt();

        for (int i = 0; i < size; i++) {
            lookBooks.add(LookBook.read(inputStream));
        }

        return lookBooks;
    }





}
