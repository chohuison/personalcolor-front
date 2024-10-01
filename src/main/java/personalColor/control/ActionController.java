package personalColor.control;

import personalColor.persistence.*;
import personalColor.protocol.RequestSender;
import personalColor.protocol.ResponseReceiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class ActionController {
    private RequestSender requestSender = new RequestSender();
    private ResponseReceiver responseReceiver = new ResponseReceiver();

    public void login(String id, String pw, DataOutputStream dos) throws IOException {

        requestSender.login(id, pw, dos);
    }

    public boolean loginResult(DataInputStream bodyReader) throws IOException {

        return responseReceiver.receiveLoginResult(bodyReader);
    }

    public void signUp(User user, DataOutputStream dos) throws IOException {
        requestSender.signUp(user, dos);
    }

    public int signUpResult(DataInputStream bodyReader) throws IOException
    {
        return responseReceiver.receiveSignUpResult(bodyReader);
    }

    public void userInfo(String id, DataOutputStream dos) throws IOException
    {
        requestSender.userInfo(id, dos);
    }

    public String userInfoResult(DataInputStream bodyReader) throws IOException
    {
        return responseReceiver.receiveUserInfo(bodyReader);
    }
    public void tip(String seasonType, String tipType,DataOutputStream dos) throws IOException{
        requestSender.tip(seasonType,tipType,dos);
    }
    public List<Tip> tipResult(DataInputStream bodyReader) throws IOException{
        return responseReceiver.receiveTip(bodyReader);
    }
    public void accessory(String seasonType,DataOutputStream dos) throws IOException{
        requestSender.accessory(seasonType,dos);
    }
    public List<Accessory> accessoryList(DataInputStream bodyReader) throws IOException{
       return responseReceiver.receiveAccessory(bodyReader);
    }

    public void reqColor(String seasonType, DataOutputStream dos) throws IOException
    {
        requestSender.colorReq(seasonType, dos);
    }

    public List<Colors> resultColor(DataInputStream bodyReader) throws IOException
    {
        return responseReceiver.receiveColor(bodyReader);
    }

    public void reqCosmetic(String seasonType, DataOutputStream dos) throws IOException
    {
        requestSender.cosmeticReq(seasonType, dos);
    }

    public List<Cosmetic> resultCosmetic(DataInputStream bodyReader) throws IOException
    {
        return responseReceiver.receiveCosmetic(bodyReader);
    }

    public void reqMyPageInfo(String userID, DataOutputStream dos) throws IOException
    {
        requestSender.myPageInfoReq(userID, dos);
    }

    public List<User> resultMyPageInfo(DataInputStream bodyReader) throws IOException
    {
        return responseReceiver.receiveMyPageInfo(bodyReader);
    }

    public void reqModifyInfo(User user, DataOutputStream dos) throws IOException
    {
        requestSender.modifyInfo(user, dos);
    }

    public int resultModifyUser(DataInputStream bodyReader) throws IOException
    {
        return responseReceiver.receiveModifyInfoResult(bodyReader);
    }

    public void reqDeleteUser(String userID, DataOutputStream dos) throws IOException
    {
        requestSender.deleteUser(userID, dos);
    }

    public int resultDeleteUser(DataInputStream bodyReader) throws IOException
    {
        return responseReceiver.receiveDeleteUserResult(bodyReader);
    }

    public void enroll(String img, String text, String personalColor, String userID, int lbShareCheck, DataOutputStream dos) throws IOException
    {
        requestSender.enroll(img, text, personalColor, userID,lbShareCheck,dos);
    }

    public void reqLookBookList(DataOutputStream outputStream) throws IOException
    {
        requestSender.reqLookBookList(outputStream);
    }
    public List<LookBook> lookBookList(DataInputStream bodyReader) throws IOException
    {
        return responseReceiver.receiveLookBook(bodyReader);
    }

}
