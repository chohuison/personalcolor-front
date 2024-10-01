package personalColor.protocol;

import personalColor.persistence.User;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RequestSender
{
    public void login(String id, String pw, DataOutputStream outputStream) throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(id);
        dos.writeUTF(pw);

        byte[] body = buf.toByteArray();

        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_LOGIN,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void signUp(User user, DataOutputStream outputStream) throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(user);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_SIGN_UP,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void userInfo(String id, DataOutputStream outputStream) throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(id);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_USER_INFORMATION,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void tip(String seasonType, String tipType, DataOutputStream outputStream) throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(seasonType);
        dos.writeUTF(tipType);

        byte[] body = buf.toByteArray();


        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_TIP,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
   }
   public void accessory(String seasonType, DataOutputStream outputStream) throws IOException{
       ByteArrayOutputStream buf = new ByteArrayOutputStream();
       DataOutputStream dos = new DataOutputStream(buf);

       dos.writeUTF(seasonType);
       byte[] body = buf.toByteArray();


       Header header = new Header(
               Header.TYPE_REQUEST,
               Header.CODE_REQ_ACCESSORY,
               body.length
       );

       outputStream.write(header.getBytes());
       outputStream.write(body);
   }

    public void colorReq(String seasonType, DataOutputStream outputStream) throws IOException {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(seasonType);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_COLOR,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void cosmeticReq(String seasonType, DataOutputStream outputStream) throws IOException {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(seasonType);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_COSMETIC,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void myPageInfoReq(String userID, DataOutputStream outputStream) throws IOException {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(userID);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_MYPAGE_INFO,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void modifyInfo(User user, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(user);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_MODIFY_INFO,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void deleteUser(String userID, DataOutputStream outputStream) throws IOException
    {
        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.addStringBytes(userID);

        byte[] body = bodyMaker.getBody();

        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_DELETE_USER,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);
    }

    public void reqLookBookList(DataOutputStream outputStream) throws IOException{
        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_LB_INFO,
                0
        );
        outputStream.write(header.getBytes());
    }
    public void enroll(String img, String text , String seasonType, String userID, int lbShareCheck, DataOutputStream outputStream) throws IOException{
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(img);
        dos.writeUTF(text);
        dos.writeUTF(seasonType);
        dos.writeUTF(userID);
        dos.writeInt(lbShareCheck);
        byte[] body = buf.toByteArray();


        Header header = new Header(
                Header.TYPE_REQUEST,
                Header.CODE_REQ_LB_ENROLL,
                body.length
        );

        outputStream.write(header.getBytes());
        outputStream.write(body);

    }


}
