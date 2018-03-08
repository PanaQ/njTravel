package travel.nanjing.com.travel.business.api.util;

import com.zhexinit.ov.common.model.IResultEnum;

/**
 * Created by konie on 16-8-24.
 */
public enum ResultEnum implements IResultEnum {

    Success(0, "ok"),

    // third platform login
    loginFailed(101, "Login failed"),
    //用户相关
    registed(102, "Number Registered"),
    errorPassword(103, "Wrong User Name or Password"),
    illegalUserId(106, "User login has expired. Please login again"),
    userDisabled(108, "User Disabled"),
    phoneNumError(108,"手机号码格式错误"),

    passwordNotEqual(301, "Password not equal"),
    illegalName(302, "Name Used"),
    userAlreadyExist(303, "User already exist"),
    userNotExist(304, "User not exist"),
    notFollowYourself(305, "Not follow yourself"),
    mateNoteNotExist(306, "MateNote not exist"),
    noteNotExist(307, "Note not exist"),
    alreadyFollow(308, "Already follow"),

    systemError(999, "System Error");

    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "code: " + code + " , message: " + message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
