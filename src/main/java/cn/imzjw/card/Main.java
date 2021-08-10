package cn.imzjw.card;

import cn.imzjw.card.service.CardService;

/**
 * @author https://blog.imzjw.cn
 * @date 2021/8/10 20:20
 */
public class Main {

    /**
     * 听说你是入口函数？
     *
     * @param args 这是个什么参数
     */
    public static void main(String[] args) {
        CardService.readFile(CardService.downRemoteFile(args[0], "baidu.txt", "."));
    }
}
