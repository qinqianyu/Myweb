package com.jxk.myWeb.core;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author jxk
 */
@Component
public class RandomPSWd {

    public String noSymbol(int length) {
        StringBuilder rtnstr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            switch (random.nextInt(3)) {
                case 0:
                    rtnstr.append((char) (Math.random() * 26 + 'A'));
                    break;
                case 1:
                    rtnstr.append((char) (Math.random() * 26 + 'a'));
                    break;
                case 2:
                    rtnstr.append((int) (Math.random() * 10));
                    break;
                default:
                    break;
            }
        }
        return rtnstr.toString();
    }

    /**
     * 随机生成1-10位的密码
     *
     * @param pwdType 密码类型，大写，小写，数字 或三都的混合
     * @param length  生成密码的长度
     * @return 密码字符串
     */
    public String GernaratePWD(PasswordType pwdType, int length) {
        String rtnstr = "";
        try {
            for (int i = 0; i < length; i++) {
                switch (pwdType) {
                    case UpCase:
                        rtnstr += (char) (Math.random() * 26 + 'A');    //生成随机大写字母
                        break;
                    case LowerCase:
                        rtnstr += (char) (Math.random() * 26 + 'a');  //生成随机小写字母
                        break;
                    case Number:
                        rtnstr += String.valueOf((int) (Math.random() * 10));  //生成随机数字
                        break;
                    case Symbol:
                        rtnstr += "!@#.".charAt(new Random().nextInt(4)); //生成随机数字
                        break;
                    case Mixed:        //生成随机大写字母、小写字母或数字
                        Random random = new Random();
                        switch (random.nextInt(4)) {
                            case 0:
                                rtnstr += (char) (Math.random() * 26 + 'A');
                                break;
                            case 1:
                                rtnstr += (char) (Math.random() * 26 + 'a');
                                break;
                            case 2:
                                rtnstr += String.valueOf((int) (Math.random() * 10));
                                break;
                            case 3:
                                rtnstr += "!@#.".charAt(new Random().nextInt(4));
                                ;
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            rtnstr = "";
        }
        return rtnstr;
    }

    //密码的类型，枚举类型
    public enum PasswordType {
        UpCase,         //大写
        LowerCase,      //小写
        Number,         //数字
        Symbol,         //符号
        Mixed           //混合
    }
}
