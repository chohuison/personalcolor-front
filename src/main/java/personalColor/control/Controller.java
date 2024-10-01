package personalColor.control;

import personalColor.persistence.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Controller
{
    public boolean loginCommand(String id, String pw, DataInputStream bodyReader,  DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();

        ac.login(id, pw, outputStream);

        return ac.loginResult(bodyReader);
    }

    public int signUpCommand(User user, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();

        ac.signUp(user, outputStream);
        return ac.signUpResult(bodyReader);
    }

    public String personalColorCommand(String id, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();

        ac.userInfo(id, outputStream);
        return ac.userInfoResult(bodyReader);
    }
    public List<Tip> tipCommand(String seasonType, String tipType,DataInputStream bodyReader, DataOutputStream outputStream) throws IOException{
        ActionController ac = new ActionController();

        ac.tip(seasonType,tipType, outputStream);

        return ac.tipResult(bodyReader);
    }

    public List<Accessory> accessoryCommand(String seasonType, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException{
        ActionController ac= new ActionController();
        ac.accessory(seasonType,outputStream);
        return ac.accessoryList(bodyReader);
    }

    public List<Colors> colorCommand(String seasonType, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();
        ac.reqColor(seasonType, outputStream);
        return ac.resultColor(bodyReader);
    }

    public List<Cosmetic> cosmeticCommand(String seasonType, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();
        ac.reqCosmetic(seasonType, outputStream);
        return ac.resultCosmetic(bodyReader);
    }

    public List<User> myPageInfoCommand(String id, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();

        ac.reqMyPageInfo(id, outputStream);
        return ac.resultMyPageInfo(bodyReader);
    }

    public int modifyInfoCommand(User user, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();

        ac.reqModifyInfo(user, outputStream);
        return ac.resultModifyUser(bodyReader);
    }

    public int deleteUserCommand(String userID, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();

        ac.reqDeleteUser(userID, outputStream);
        return ac.resultDeleteUser(bodyReader);
    }

    public List<LookBook> lookBookCommand(DataInputStream bodyReader, DataOutputStream outputStream) throws IOException
    {
        ActionController ac = new ActionController();
        ac.reqLookBookList(outputStream);
        return ac.lookBookList(bodyReader);
    }
}

